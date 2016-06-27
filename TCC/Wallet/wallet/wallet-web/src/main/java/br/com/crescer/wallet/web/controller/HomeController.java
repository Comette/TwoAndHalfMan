/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.entity.Moeda;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author victor.ribeiro
 */
@Controller
public class HomeController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toIndex(Model model){        
        List<String> moedas = new ArrayList<>();
        moedas.addAll(Moeda.getPrincipais());
        model.addAttribute("moedas", moedas);
        return "index";
    }
}

