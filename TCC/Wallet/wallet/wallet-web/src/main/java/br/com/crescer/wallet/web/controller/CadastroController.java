package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.dto.UsuarioDTO;
import br.com.crescer.wallet.service.service.ServicoService;
import br.com.crescer.wallet.service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author victo
 */
@Controller
public class CadastroController {
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    ServicoService servicoService;
    
    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastro(Model model, @RequestParam(required = false) Long idUsuario, @RequestParam(required = false) Long idServico){
        if(idUsuario != null && idUsuario > 0){
            model.addAttribute("usuario", usuarioService.findByIdReturningDTO(idUsuario));
        }else{
            model.addAttribute("usuario", new UsuarioDTO());
        }
        if(idServico != null && idServico > 0){
            model.addAttribute("servico", servicoService.getServicoDTO(idServico));
        }else{
            model.addAttribute("servico", new ServicoDTO());
        }        
        model.addAttribute("guia", "nenhum");
        model.addAttribute("usuariosCadastrados", usuarioService.findAllReturningDTOs());
        return "cadastro";
    }
}
