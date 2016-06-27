/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.service.service;

/**
 *
 * @author victor.ribeiro
 */
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author victo
 */
@Component
public class AppTask {

    @Autowired
    CotacaoService servico;
    
    @PostConstruct
    public void teste() {
        System.out.println("\n--------------- VERIFICANDO INTEGRIDADE DO BANCO DE DADOS ------------------");
        System.out.println(servico.AgenteIntegridadeBanco());        
    } 
        
    @Scheduled(cron = "0 20 18 1/1 * ?")
    public void buscaCotacaoFechamento() {
        System.out.println("\n--------------- VERIFICANDO INTEGRIDADE DO BANCO DE DADOS ------------------");
        System.out.println(servico.AgenteIntegridadeBanco());
    }   
}

