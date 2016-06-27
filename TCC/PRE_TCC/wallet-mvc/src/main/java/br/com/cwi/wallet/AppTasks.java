/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.cwi.wallet;

import br.com.cwi.wallet.infraestrutura.servico.CotacaoServico;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author victo
 */
@Component
public class AppTasks {

    @Autowired
    CotacaoServico servico;
    
    @PostConstruct
    public void teste() {
        System.out.println("\n--------------- VERIFICANDO INTEGRIDADE DO BANCO DE DADOS ------------------");
        System.out.println(servico.AgenteIntegridadeBanco());
        System.out.println(servico.AgenteIntegridadeBanco());
    } 
        
    @Scheduled(cron = "0 20 18 1/1 * ?")
    public void buscaCotacaoFechamento() {
        System.out.println("\n--------------- VERIFICANDO INTEGRIDADE DO BANCO DE DADOS ------------------");
        System.out.println(servico.AgenteIntegridadeBanco());
        System.out.println(servico.AgenteIntegridadeBanco());
    }   
}
