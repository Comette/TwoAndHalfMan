/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.cwi.wallet;

import br.com.cwi.wallet.dominio.model.Cotacao;
import br.com.cwi.wallet.infraestrutura.repositorio.CotacaoRepositorio;
import br.com.cwi.wallet.infraestrutura.servico.CotacaoServico;
import java.util.Date;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author victo
 */
@Component
public class Teste {

    @Autowired
    CotacaoServico servico;
        
    @Scheduled(cron = "0 20 18 1/1 * ? *")
    public void buscaFechamento() {
        System.out.println("TA CHAMANDO");
        Cotacao cotacao = servico.buscarUltimaCotacao();
        boolean tudoOk;
        if(cotacao == null){
            tudoOk = servico.alimentaBancoCotacoes();
        }else{
            tudoOk = true;
        }
        System.out.println(tudoOk);
    }
    
    @Scheduled(cron = "0 1 0 1/1 * ? *")
    public void buscaDiaAnterior() {
        System.out.println("TA CHAMANDO");
        Cotacao cotacao = servico.buscarUltimaCotacao();
        boolean tudoOk;
        if(cotacao == null){
            tudoOk = servico.alimentaBancoCotacoes();
        }else{
            tudoOk = true;
        }
        System.out.println(tudoOk);
    }
}
