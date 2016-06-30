package br.com.crescer.wallet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author victo
 */
@Controller
public class CadastroController {
    
    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastro(){
        return "cadastro";
    }
}
