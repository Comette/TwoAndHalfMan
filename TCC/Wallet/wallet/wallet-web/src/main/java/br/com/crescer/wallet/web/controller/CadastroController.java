package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.UsuarioDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author victo
 */
@Controller
public class CadastroController {
    
    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastro(Model model){
        model.addAttribute("usuario", new UsuarioDTO());
        return "cadastro";
    }
}
