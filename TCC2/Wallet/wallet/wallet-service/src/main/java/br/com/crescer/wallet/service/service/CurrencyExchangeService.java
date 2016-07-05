/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.entity.CurrencyExchange;
import br.com.crescer.wallet.entity.util.Coin;
import static br.com.crescer.wallet.service.service.ServiceUtils.CALC_SCALE;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import java.time.LocalDate;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.crescer.wallet.service.repository.CurrencyExchangeRepository;
import br.com.crescer.wallet.service.webservice.Rates;
import br.com.crescer.wallet.service.webservice.WebServiceConfig;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author victo
 */
@Service
public class CurrencyExchangeService implements InitializingBean {

    private static final Logger LOG = Logger.getLogger(CurrencyExchangeService.class.getName());

    @Autowired
    CurrencyExchangeRepository currencyExchangeRepository;

    public BigDecimal findLastCurrencyAverage(Coin coin) {
        LocalDate today = LocalDate.now();
        List<CurrencyExchange> currencyExchanges = currencyExchangeRepository.findByDsCoinAndDtCurrencyExchangeBetween(coin, today.minusDays(29), today);
        BigDecimal currencyAmount = BigDecimal.valueOf(currencyExchanges.size());
        BigDecimal average;
        average = currencyExchanges.stream()
                .map(CurrencyExchange::getVlRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(currencyAmount, CALC_SCALE, HALF_UP);
        return average;
    }

    public Map<Coin, BigDecimal> findLastAverage() {
        LocalDate today = LocalDate.now();
        List<CurrencyExchange> currencyExchanges = currencyExchangeRepository.findByDtCurrencyExchangeBetween(today.minusDays(29), today);
        BigDecimal currencyAmount = BigDecimal.valueOf(currencyExchanges.size());

        Map<Coin, BigDecimal> averages = new HashMap<>();
        currencyExchanges.stream().forEach((currencyExchange) -> {
            averages.compute(currencyExchange.getDsCoin(), (k, v) -> v == null ? currencyExchange.getVlRate() : v.add(currencyExchange.getVlRate()));
        });
        averages.forEach((Coin k, BigDecimal v) -> v = v.divide(currencyAmount, CALC_SCALE, HALF_UP));

        return averages;
    }

    public Map<Coin, BigDecimal> findLastExchangeRate() {
        LocalDate today = LocalDate.now();
        List<CurrencyExchange> currencyExchanges = currencyExchangeRepository.findByDtCurrencyExchange(today);
        Map<Coin, BigDecimal> averages = new HashMap<>();
        currencyExchanges.stream().forEach((currencyExchange) -> {
            averages.put(currencyExchange.getDsCoin(), currencyExchange.getVlRate());
        });
        return averages;
    }

    public void databaseIntegrityAgent() {
        LocalDate today = LocalDate.now();
        LocalDate verificationDay;
        LOG.info("");
        LOG.info("");
        LOG.info("<<<<<<>>>>>> VERIFICANDO INTEGRIDADE DO BANCO DE COTACOES <<<<<<>>>>>>");
        LOG.info("------ Inicio da verificacao: " + LocalTime.now());
        LOG.info("");
        for (int i = 29; i >= 0; i--) {
            verificationDay = today.minusDays(i);
            CurrencyExchange currencyExchange = currencyExchangeRepository.findFirstByDtCurrencyExchangeOrderByIdCurrencyExchangeDesc(verificationDay);
            if (currencyExchange == null) {
                LOG.info("");
                LOG.warn("------- >>> FALTANDO:  Cotacoes dia: " + verificationDay + " <<< -------");
                this.addCurrencyByDate(verificationDay);
                i++;
            }
        }
        List<CurrencyExchange> list = currencyExchangeRepository.findByDtCurrencyExchangeBetween(today.minusDays(29), today);
        LOG.info("");
        LOG.info("------ Fim da verificacao: " + LocalTime.now());
        LOG.info("------ Foram encontrados " + list.size() + " registros nos ultimos 30 dias");
        LOG.info("<<<<<<>>>>>> Integridade do Banco de cotacoes VERIFICADA! <<<<<<>>>>>>");
        LOG.info("");
        LOG.info("");
    }

    private void addCurrencyByDate(LocalDate date) {
        try {
            URL url = new URL(WebServiceConfig.URL_GET_POR_DATA + date + WebServiceConfig.URL_GET_POR_DATA_2);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("User-Agent", WebServiceConfig.USER_AGENT);

            LOG.info(" +++++++ Buscando Cotacao no WebService +++++++ ");
            StringBuffer response;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            LOG.info(" +++++++ Convertendo Resultados +++++++ ");
            String json = "{" + response.toString().substring(response.toString().indexOf("AED") - 3, response.toString().length() - 1);

            Rates rates = new Gson().fromJson(json, Rates.class);
            List<CurrencyExchange> currencies = this.ratesToCurrencies(rates, date);

            LOG.info(" +++++++ Salvando Cotacoes +++++++ ");
            currencyExchangeRepository.save(currencies);
            LOG.info(" +++++++ Cotacoes dia: " + date + " atualizadas! +++++++ ");

        } catch (MalformedURLException ex) {
            LOG.error("ERRO: ao alimentar o banco de dados!");
            LOG.error(ex.getMessage());
        } catch (IOException ex) {
            LOG.error("ERRO: ao alimentar o banco de dados!");
            LOG.error(ex.getMessage());
        }
    }

    private List<CurrencyExchange> ratesToCurrencies(Rates rate, LocalDate date) {
        List<CurrencyExchange> list = new ArrayList<>();

        for (Field field : rate.getClass().getDeclaredFields()) {
            // if you want to modify private fields
            try {
                field.setAccessible(true);
                Coin coin = Coin.valueOf(field.getName());
                BigDecimal currency = (BigDecimal) field.get(rate);
                currency.setScale(CALC_SCALE, HALF_UP);
                list.add(new CurrencyExchange(0l, coin, currency, date));

            } catch (IllegalArgumentException | IllegalAccessException ex) {
                LOG.fatal(ex);
            }
        }
        return list;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.databaseIntegrityAgent();
    }

    @Scheduled(cron = "0 20 18 1/1 * ?")
    public void findClosureExchangeRate() {
        this.databaseIntegrityAgent();
    }

}
