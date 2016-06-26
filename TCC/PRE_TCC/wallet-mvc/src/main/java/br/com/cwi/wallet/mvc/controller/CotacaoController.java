/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.cwi.wallet.mvc.controller;

import br.com.cwi.wallet.dominio.model.Cotacao;
import br.com.cwi.wallet.dominio.model.Moedas;
import br.com.cwi.wallet.infraestrutura.servico.CotacaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author victo
 */
@RestController
public class CotacaoController {
    
    @Autowired
    CotacaoServico servico;
    
    @RequestMapping(value = "/cotar", method = RequestMethod.POST)
    public double cotar(@RequestBody Moedas moeda){
        Cotacao cotacao = servico.buscarUltimaCotacao();
        switch (moeda.toString()){
            case "EUR":
                return cotacao.getDsCotacaoEuro();
            case "BRL":
                return cotacao.getDsCotacaoReal();
            case "JPY":
                return cotacao.getDsCotacaoYen();
            case "GBP":
                return cotacao.getDsCotacaoLibra();
            case "AUD":
                return cotacao.getDsCotacaoDollarAutraliano();
            case "CAD":
                return cotacao.getDsCotacaoDollarCanadense();
            case "CHF":
                return cotacao.getDsCotacaoFrancoSuico();
            case "CNY":
                return cotacao.getDsCotacaoYuan();
            default:
                return 0;
        }        
    }    
    
    @RequestMapping(value = "/alimentar", method = RequestMethod.GET)
    public String alimentarBanco(){       
        if(servico.alimentaBancoCotacoes()){
            return "Banco de Cotações atualizado com sucesso!";
        }else{
            return "ERRO";
        }        
    }
}
