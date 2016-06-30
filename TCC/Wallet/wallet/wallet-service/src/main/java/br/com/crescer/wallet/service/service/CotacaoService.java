/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Cotacao;
import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.service.repository.CotacaoRepository;
import static br.com.crescer.wallet.service.service.ServiceUtils.CALC_SCALE;
import br.com.crescer.wallet.service.webservice.Rates;
import br.com.crescer.wallet.service.webservice.WebServiceConfig;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author victor.ribeiro
 */
@Service
public class CotacaoService implements InitializingBean {

    private static final Logger LOG = Logger.getLogger(CotacaoService.class.getName());
    @Autowired
    CotacaoRepository repository;

    public Cotacao findLastExchangeRate() {
        Cotacao cotacao = repository.findFirstByDtCotacaoOrderByIdCotacaoDesc(LocalDate.now());
        if (cotacao == null) {
            LocalDate today = LocalDate.now();
            cotacao = repository.findFirstByDtCotacaoOrderByIdCotacaoDesc(today.plusDays(-1));
        }
        return cotacao;
    }

    public BigDecimal findLastCurrencyAverage(Moeda moeda) {
        LocalDate dia = LocalDate.now();
        List<Cotacao> cotacoes = repository.findByDtCotacaoBetween(dia.minusDays(29), dia);
        BigDecimal average;
        switch (moeda.toString()) {
            case "EUR":
                average = cotacoes.stream()
                        .map(Cotacao::getDsCotacaoEuro)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(cotacoes.size()));
                break;
            case "BRL":
                average = cotacoes.stream()
                        .map(Cotacao::getDsCotacaoReal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(cotacoes.size()));
                break;
            case "JPY":
                average = cotacoes.stream()
                        .map(Cotacao::getDsCotacaoYen)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(cotacoes.size()));
                break;
            case "GBP":
                average = cotacoes.stream()
                        .map(Cotacao::getDsCotacaoLibra)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(cotacoes.size()));
                break;
            case "AUD":
                average = cotacoes.stream()
                        .map(Cotacao::getDsCotacaoDollarAutraliano)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(cotacoes.size()));
                break;
            case "CAD":
                average = cotacoes.stream()
                        .map(Cotacao::getDsCotacaoDollarCanadense)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(cotacoes.size()));
                break;
            case "CHF":
                average = cotacoes.stream()
                        .map(Cotacao::getDsCotacaoFrancoSuico)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(cotacoes.size()));
                break;
            case "CNY":
                average = cotacoes.stream()
                        .map(Cotacao::getDsCotacaoYuan)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(cotacoes.size()));
                break;
            default:
                //TODO: throws exception
                average = BigDecimal.ZERO;
                LOG.error("Moeda inválida");
                break;
        }
        return average;
    }

    public Map<Moeda, BigDecimal> findLastAverage() {
        LocalDate today = LocalDate.now();
        List<Cotacao> cotacoes = repository.findByDtCotacaoBetween(today.minusDays(29), today);
        Map<Moeda, BigDecimal> averages = new HashMap<>();
        {
            BigDecimal totalCotacoes = BigDecimal.valueOf(cotacoes.size());
            averages.put(Moeda.BRL, cotacoes.stream()
                    .map(Cotacao::getDsCotacaoReal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(totalCotacoes, CALC_SCALE, RoundingMode.HALF_UP));
            averages.put(Moeda.EUR, cotacoes.stream()
                    .map(Cotacao::getDsCotacaoEuro)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(totalCotacoes, CALC_SCALE, RoundingMode.HALF_UP));
            averages.put(Moeda.JPY, cotacoes.stream()
                    .map(Cotacao::getDsCotacaoYen)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(totalCotacoes, CALC_SCALE, RoundingMode.HALF_UP));
            averages.put(Moeda.GBP, cotacoes.stream()
                    .map(Cotacao::getDsCotacaoLibra)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(totalCotacoes, CALC_SCALE, RoundingMode.HALF_UP));
            averages.put(Moeda.AUD, cotacoes.stream()
                    .map(Cotacao::getDsCotacaoDollarAutraliano)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(totalCotacoes, CALC_SCALE, RoundingMode.HALF_UP));
            averages.put(Moeda.CAD, cotacoes.stream()
                    .map(Cotacao::getDsCotacaoDollarCanadense)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(totalCotacoes, CALC_SCALE, RoundingMode.HALF_UP));
            averages.put(Moeda.CHF, cotacoes.stream()
                    .map(Cotacao::getDsCotacaoFrancoSuico)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(totalCotacoes, CALC_SCALE, RoundingMode.HALF_UP));
            averages.put(Moeda.CNY, cotacoes.stream()
                    .map(Cotacao::getDsCotacaoYuan)
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(totalCotacoes, CALC_SCALE, RoundingMode.HALF_UP));
            averages.put(Moeda.USD, BigDecimal.ONE);
        }
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
            Cotacao cotacao = repository.findFirstByDtCotacaoOrderByIdCotacaoDesc(verificationDay);
            if (cotacao == null) {
                LOG.info("");
                LOG.warn("------- >>> FALTANDO:  Cotacao dia: " + verificationDay + " <<< -------");
                this.addCurrencyByDate(verificationDay);
                i++;
            }
        }
        List<Cotacao> lista = repository.findByDtCotacaoBetween(today.minusDays(29), today);
        LOG.info("");
        LOG.info("------ Fim da verificacao: " + LocalTime.now());
        LOG.info("------ Foram encontrados " + lista.size() + " registros nos ultimos 30 dias");
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

            Cotacao cotacao = new Gson().fromJson(json, Rates.class).toCotacao();
            cotacao.setDtCotacao(date);
            LOG.info(" +++++++ Salvando Cotacao +++++++ ");
            repository.save(cotacao);
            LOG.info(" +++++++ Cotacao dia: " + cotacao.getDtCotacao() + " atualizada! +++++++ ");

        } catch (MalformedURLException ex) {
            LOG.error("ERRO: ao alimentar o banco de dados!");
            LOG.error(ex.getMessage());
        } catch (IOException ex) {
            LOG.error("ERRO: ao alimentar o banco de dados!");
            LOG.error(ex.getMessage());
        }
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
