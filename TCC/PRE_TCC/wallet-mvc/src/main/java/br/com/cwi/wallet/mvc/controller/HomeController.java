/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.cwi.wallet.mvc.controller;

import br.com.cwi.wallet.mvc.webservice.AppID;
import br.com.cwi.wallet.dominio.model.Moedas;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author victo
 */
@Controller
public class HomeController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String toIndex(Model model){
        model.addAttribute("AppID", AppID.AppID);
        List<String> moedas = new ArrayList<>();
        moedas.addAll(Moedas.getPrincipais());
        model.addAttribute("moedas", moedas);
        return "index";
    }
}
