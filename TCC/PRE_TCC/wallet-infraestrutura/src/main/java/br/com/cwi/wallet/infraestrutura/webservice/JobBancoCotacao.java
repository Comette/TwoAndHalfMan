/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.cwi.wallet.infraestrutura.webservice;

import br.com.cwi.wallet.infraestrutura.servico.CotacaoServico;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author victo
 */
public class JobBancoCotacao implements Job{

    @Autowired
    CotacaoServico servico;
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
//        if(servico.buscarUltimaCotacao() == null){
//            servico.alimentaBancoCotacoes();
//        }
        System.out.println("EXECUTOUUUUUUUUU");
    }    
}
