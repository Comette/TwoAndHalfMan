/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.cwi.wallet.infraestrutura.servico;

import br.com.cwi.wallet.dominio.model.Cotacao;
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
import java.util.Date;
import org.joda.time.DateTime;
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
//        if (cotacao == null) {
//            DateTime ontem = new DateTime(new Date());
//            ontem = ontem.plusDays(-1);
//            cotacao = repositorio.findFirstByDtCotacaoOrderByIdCotacaoDesc(ontem.toDate());
//        }
        return cotacao;
    }
    
    public boolean alimentaBancoCotacoes(){
        try{
            this.alimentaBanco();
            return true;
        }catch(IOException ex){
            System.out.println(ex.getMessage());
            return false;
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }   

    private Cotacao salvar(Cotacao cotacao) {
        return repositorio.save(cotacao);
    }

    private void alimentaBanco() throws MalformedURLException, IOException {
        
        URL url = new URL(WebServiceConfig.URL_GET_COTACAO);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        //add request header
        connection.setRequestProperty("User-Agent", WebServiceConfig.USER_AGENT);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        int corte = response.toString().indexOf("AED")-3;
        String json = "{" + response.toString().substring(corte, response.toString().length()-1);        
        Rates rates = new Gson().fromJson(json, Rates.class);
        
        Cotacao cotacao = rates.toCotacao();
        this.salvar(cotacao);
    }
}
