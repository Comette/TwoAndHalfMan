/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.web.dto.ServicoDTO;
import br.com.crescer.wallet.web.utils.LoggedInUserUtils;
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
    public String toIndex(Model model) {
//        List<String> moedas = new ArrayList<>();
//        moedas.addAll(Moeda.getPrincipais());
//        model.addAttribute("moedas", moedas);
        List<ServicoDTO> dto = new ArrayList<>();
        dto.add(new ServicoDTO("GitBub",1200.00));        
        dto.add(new ServicoDTO("SourceBree",500.00));        
        
        model.addAttribute("user",LoggedInUserUtils.getLoggedInUser());    
        model.addAttribute("precoTotalMes", 1700.00);
        model.addAttribute("servicoMaisCaro", dto.get(0));
        model.addAttribute("listaServicos", dto);
        model.addAttribute("precoTotalProximoMes", 1700.00);
        return "dashboard";
    }
}
