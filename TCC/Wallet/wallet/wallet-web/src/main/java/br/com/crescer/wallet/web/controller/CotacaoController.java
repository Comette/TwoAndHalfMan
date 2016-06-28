/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.entity.Cotacao;
import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.service.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author victor.ribeiro
 */
@RestController
public class CotacaoController {
    
    @Autowired
    CotacaoService service;
    
    @RequestMapping(value = "/cotar", method = RequestMethod.POST)
    public double cotar(@RequestBody Moeda moeda){
        Cotacao cotacao = service.buscarUltimaCotacao();
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
    
    @RequestMapping(value = "/media", method = RequestMethod.POST)
    public double media(@RequestBody Moeda moeda){
        return service.buscarUltimaMediaMoeda(moeda);        
    }
}

