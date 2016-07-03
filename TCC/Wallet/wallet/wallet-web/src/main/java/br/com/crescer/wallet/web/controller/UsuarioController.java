package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.dto.UsuarioDTO;
import br.com.crescer.wallet.service.service.UsuarioService;
import br.com.crescer.wallet.web.utils.LoggedInUserUtils;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping(value = "/buscar-usuarios-ativos", method = RequestMethod.GET)
    public List<UsuarioDTO> getUsuariosAtivos() {
        return service.findAllActiveReturningDTOs();
    }

    @ResponseBody
    @RequestMapping(value = "/buscar-todos-usuarios", method = RequestMethod.GET)
    public List<UsuarioDTO> getUsuariosQualquerStatus() {
        return service.findAllReturningDTOs();
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public String listUsuarios() {
        return "usuarios";
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public String getUsuario(@RequestParam Long idUsuario, Model model) {
        model.addAttribute("usuario", service.findByIdReturningDTO(idUsuario));
        return "usuario";
    }

    @RequestMapping(value = "/salvar-usuario", method = RequestMethod.POST)
    public ModelAndView salvarUsuario(@ModelAttribute("usuario") @Valid UsuarioDTO usuarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView model = new ModelAndView();
            model.addObject("usuario", usuarioDTO);
            model.addObject("servico", new ServicoDTO());
            model.addObject("guia", "usuario");
            model.setViewName("cadastro");
            return model;
        } else {
            UsuarioDTO retornado = service.salvarUsuario(usuarioDTO);
            ModelAndView model = new ModelAndView();
            model.setViewName("usuarios");
            model.addObject("sucesso",
                    retornado != null
                            ? "Usuário " + usuarioDTO.getNome() + " cadastrado com sucesso!"
                            : "Desculpe-nos, aconteceu algum erro e o usuário não pôde ser cadastrado.");
            return model;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/inativar-usuario", method = RequestMethod.POST)
    public boolean inativarUsuario(@RequestParam Long idUsuario) {
        
        return LoggedInUserUtils.checkIfUserIsAdmin() ? service.inativarUsuario(idUsuario) : false;
    }

}
