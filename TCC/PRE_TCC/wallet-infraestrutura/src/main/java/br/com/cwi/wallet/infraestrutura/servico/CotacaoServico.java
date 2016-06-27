/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.cwi.wallet.infraestrutura.servico;

import br.com.cwi.wallet.dominio.model.Cotacao;
import br.com.cwi.wallet.dominio.model.Moedas;
import br.com.cwi.wallet.infraestrutura.repositorio.CotacaoRepositorio;
import br.com.cwi.wallet.infraestrutura.webservice.Rates;
import br.com.cwi.wallet.infraestrutura.webservice.WebServiceConfig;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author victo
 */
@Service
public class CotacaoServico {

    @Autowired
    CotacaoRepositorio repositorio;

    public Cotacao buscarUltimaCotacao() {
        Cotacao cotacao = repositorio.findFirstByDtCotacaoOrderByIdCotacaoDesc(new Date());
        if (cotacao == null) {
            DateTime hoje = new DateTime(new Date());
            cotacao = repositorio.findFirstByDtCotacaoOrderByIdCotacaoDesc(hoje.plusDays(-1).toDate());
        }
        return cotacao;
    }

    public double[] buscarUltimaMediaMoeda(Moedas moeda) {
        DateTime dia = new DateTime();
        List<Cotacao> cotacoes = repositorio.findByDtCotacaoBetween(dia.minusDays(29).toDate(), dia.toDate());
        double[] media = new double[30];
        switch (moeda.toString()) {
            case "EUR":
                for (int i = 0; i < cotacoes.size(); i++) {
                    media[i] = cotacoes.get(i).getDsCotacaoEuro();
                }
                break;
            case "BRL":
                for (int i = 0; i < cotacoes.size(); i++) {
                    media[i] = cotacoes.get(i).getDsCotacaoReal();
                }
                break;
            case "JPY":
                for (int i = 0; i < cotacoes.size(); i++) {
                    media[i] = cotacoes.get(i).getDsCotacaoYen();
                }
                break;
            case "GBP":
                for (int i = 0; i < cotacoes.size(); i++) {
                    media[i] = cotacoes.get(i).getDsCotacaoLibra();
                }
                break;
            case "AUD":
                for (int i = 0; i < cotacoes.size(); i++) {
                    media[i] = cotacoes.get(i).getDsCotacaoDollarAutraliano();
                }
                break;
            case "CAD":
                for (int i = 0; i < cotacoes.size(); i++) {
                    media[i] = cotacoes.get(i).getDsCotacaoDollarCanadense();
                }
                break;
            case "CHF":
                for (int i = 0; i < cotacoes.size(); i++) {
                    media[i] = cotacoes.get(i).getDsCotacaoFrancoSuico();
                }
                break;
            case "CNY":
                for (int i = 0; i < cotacoes.size(); i++) {
                    media[i] = cotacoes.get(i).getDsCotacaoYuan();
                }
                break;
            default:
                //TODO: throws exception
                System.out.println("Moeda inválida");
                break;
        }
        return media;
    }

    public String AgenteIntegridadeBanco() {
        DateTime dia = new DateTime();
        DateTime diaVerificacao;
        Date date;
        System.out.println("\nInicio da verificação: " + DateTime.now());
        for (int i = 29; i >= 0; i--) {
            diaVerificacao = dia.minusDays(i);
            date = diaVerificacao.toDate();
            Cotacao cotacao = repositorio.findFirstByDtCotacaoOrderByIdCotacaoDesc(date);
            if (cotacao == null) {
                System.out.println("\n-----------FALTANDO:  Cotação dia: " + diaVerificacao + " --------------");
                this.alimentaCotacaoPorData(diaVerificacao);
            }
        }
        Date teste = dia.minusDays(29).toDate();
        List<Cotacao> lista = repositorio.findByDtCotacaoBetween(dia.minusDays(29).toDate(), dia.toDate());
        System.out.println("Fim da verificação: " + DateTime.now());
        System.out.println("Foram encontrados " + lista.size() + " registros nos ultimos 30 dias");
        return "\nIntegridade do Banco verificada!\n\n";
    }

    private void alimentaCotacaoPorData(DateTime data) {
        try {
            DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

            URL url = new URL(WebServiceConfig.URL_GET_POR_DATA + fmt.print(data) + WebServiceConfig.URL_GET_POR_DATA_2);

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
            cotacao.setDtCotacao(data.toDate());
            System.out.println("-----------Salvando Cotação--------------");
            repositorio.save(cotacao);
            System.out.println("Cotação dia: " + cotacao.getDtCotacao() + " atualizada!");

        } catch (MalformedURLException ex) {
            System.out.println("ERRO: ao alimentar o banco de dados!");
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERRO: ao alimentar o banco de dados!");
            System.out.println(ex.getMessage());
        }
    }
}
