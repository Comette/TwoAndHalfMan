package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.UsuarioDTO;
import br.com.crescer.wallet.service.service.UsuarioService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hedo
 */
@Controller
public class UsuarioController {

    @Autowired
    UsuarioService service;
    
    @ResponseBody
    @RequestMapping(value = "/buscar-gerentes", method = RequestMethod.GET)
    public List<UsuarioDTO> getGerentes() {
        return service.findAllReturningDTOs();
    }
    
    @RequestMapping(value = "/gerente", method = RequestMethod.GET)
    public String gerente(){
        return "gerente";
    }
    
    @RequestMapping(value = "/salvar-usuario", method = RequestMethod.POST)
    public ModelAndView salvarUsuario(@ModelAttribute @Valid UsuarioDTO usuarioDTO, BindingResult result, Model model) {
        if(result.hasErrors()) 
            return new ModelAndView("cadastros");
        
        else {
            UsuarioDTO retornado = service.salvarUsuario(usuarioDTO);
            model.addAttribute("sucesso", 
                     retornado != null ? 
                            "Usuário " + usuarioDTO.getNome() + " cadastrado com sucesso!" : 
                            "Desculpe-nos, aconteceu algum erro e o usuário não pôde ser cadastrado.");
            return new ModelAndView("redirect:/");
        }
        
    } 
}
