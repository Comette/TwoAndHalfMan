/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Cotacao;
import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.service.repository.CotacaoRepository;
import br.com.crescer.wallet.service.webservice.Rates;
import br.com.crescer.wallet.service.webservice.WebServiceConfig;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    CotacaoRepository repository;

    public Cotacao buscarUltimaCotacao() {
        Cotacao cotacao = repository.findFirstByDtCotacaoOrderByIdCotacaoDesc(LocalDate.now());
        if (cotacao == null) {
            LocalDate hoje = LocalDate.now();
            cotacao = repository.findFirstByDtCotacaoOrderByIdCotacaoDesc(hoje.plusDays(-1));
        }
        return cotacao;
    }

    public double buscarUltimaMediaMoeda(Moeda moeda) {
        LocalDate dia = LocalDate.now();
        List<Cotacao> cotacoes = repository.findByDtCotacaoBetween(dia.minusDays(29), dia);
        double media;
        switch (moeda.toString()) {
            case "EUR":
                media = cotacoes.stream().mapToDouble(c -> c.getDsCotacaoEuro()).average().getAsDouble();
                break;
            case "BRL":
                media = cotacoes.stream().mapToDouble(c -> c.getDsCotacaoReal()).average().getAsDouble();
                break;
            case "JPY":
                media = cotacoes.stream().mapToDouble(c -> c.getDsCotacaoYen()).average().getAsDouble();
                break;
            case "GBP":
                media = cotacoes.stream().mapToDouble(c -> c.getDsCotacaoLibra()).average().getAsDouble();
                break;
            case "AUD":
                media = cotacoes.stream().mapToDouble(c -> c.getDsCotacaoDollarAutraliano()).average().getAsDouble();
                break;
            case "CAD":
                media = cotacoes.stream().mapToDouble(c -> c.getDsCotacaoDollarCanadense()).average().getAsDouble();
                break;
            case "CHF":
                media = cotacoes.stream().mapToDouble(c -> c.getDsCotacaoFrancoSuico()).average().getAsDouble();
                break;
            case "CNY":
                media = cotacoes.stream().mapToDouble(c -> c.getDsCotacaoYuan()).average().getAsDouble();
                break;
            default:
                //TODO: throws exception
                media=0;
                System.out.println("Moeda inválida");
                break;
        }
        return media;
    }

    public Map<Moeda, Double> buscarUltimaMedia() {
        LocalDate dia = LocalDate.now();
        List<Cotacao> cotacoes = repository.findByDtCotacaoBetween(dia.minusDays(29), dia);
        Map<Moeda, Double> medias = new HashMap<>();
        medias.put(Moeda.USD, 1.0);
        medias.put(Moeda.BRL, cotacoes.stream().mapToDouble(c -> c.getDsCotacaoReal()).average().getAsDouble());
        medias.put(Moeda.EUR, cotacoes.stream().mapToDouble(c -> c.getDsCotacaoEuro()).average().getAsDouble());
        medias.put(Moeda.JPY, cotacoes.stream().mapToDouble(c -> c.getDsCotacaoYen()).average().getAsDouble());
        medias.put(Moeda.GBP, cotacoes.stream().mapToDouble(c -> c.getDsCotacaoLibra()).average().getAsDouble());
        medias.put(Moeda.AUD, cotacoes.stream().mapToDouble(c -> c.getDsCotacaoDollarAutraliano()).average().getAsDouble());
        medias.put(Moeda.CAD, cotacoes.stream().mapToDouble(c -> c.getDsCotacaoDollarCanadense()).average().getAsDouble());
        medias.put(Moeda.CHF, cotacoes.stream().mapToDouble(c -> c.getDsCotacaoFrancoSuico()).average().getAsDouble());
        medias.put(Moeda.CNY, cotacoes.stream().mapToDouble(c -> c.getDsCotacaoYuan()).average().getAsDouble());        
        return medias;
    }

    public String AgenteIntegridadeBanco() {
        LocalDate dia = LocalDate.now();
        LocalDate diaVerificacao;
        System.out.println("\nInicio da verificação: " + LocalTime.now());
        for (int i = 29; i >= 0; i--) {
            diaVerificacao = dia.minusDays(i);
            Cotacao cotacao = repository.findFirstByDtCotacaoOrderByIdCotacaoDesc(diaVerificacao);
            if (cotacao == null) {
                System.out.println("\n-----------FALTANDO:  Cotação dia: " + diaVerificacao + " --------------");
                this.alimentaCotacaoPorData(diaVerificacao);
                i++;
            }
        }
        List<Cotacao> lista = repository.findByDtCotacaoBetween(dia.minusDays(29), dia);
        System.out.println("Fim da verificação: " + LocalTime.now());
        System.out.println("Foram encontrados " + lista.size() + " registros nos ultimos 30 dias");
        return "\nIntegridade do Banco verificada!\n\n";
    }

    private void alimentaCotacaoPorData(LocalDate data) {
        try {
            URL url = new URL(WebServiceConfig.URL_GET_POR_DATA + data + WebServiceConfig.URL_GET_POR_DATA_2);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("User-Agent", WebServiceConfig.USER_AGENT);

            System.out.println("-----------Buscando Cotação no WebService--------------");
            StringBuffer response;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            System.out.println("-----------Convertendo Resultados--------------");
            String json = "{" + response.toString().substring(response.toString().indexOf("AED") - 3, response.toString().length() - 1);

            Cotacao cotacao = new Gson().fromJson(json, Rates.class).toCotacao();
            cotacao.setDtCotacao(data);
            System.out.println("-----------Salvando Cotação--------------");
            repository.save(cotacao);
            System.out.println("Cotação dia: " + cotacao.getDtCotacao() + " atualizada!");

        } catch (MalformedURLException ex) {
            System.out.println("ERRO: ao alimentar o banco de dados!");
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERRO: ao alimentar o banco de dados!");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("\n--------------- VERIFICANDO INTEGRIDADE DO BANCO DE DADOS ------------------");
        System.out.println(this.AgenteIntegridadeBanco());
    }

    @Scheduled(cron = "0 16 19 1/1 * ?")
    public void buscaCotacaoFechamento() {
        System.out.println("\n--------------- VERIFICANDO INTEGRIDADE DO BANCO DE DADOS ------------------");
        System.out.println(this.AgenteIntegridadeBanco());
    }
}
