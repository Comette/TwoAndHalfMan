package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.dto.UsuarioDTO;
import br.com.crescer.wallet.service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastro(Model model){
        model.addAttribute("usuario", new UsuarioDTO());
        model.addAttribute("servico", new ServicoDTO());
        model.addAttribute("usuariosCadastrados", usuarioService.findAllReturningDTOs());
        return "cadastro";
    }
}
