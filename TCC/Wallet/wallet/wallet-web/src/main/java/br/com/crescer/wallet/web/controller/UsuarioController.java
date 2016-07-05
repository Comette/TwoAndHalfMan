package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.dto.UsuarioDTO;
import br.com.crescer.wallet.service.service.ServicoService;
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
    UsuarioService usuarioService;

    @Autowired
    ServicoService servicoService;

    @ResponseBody
    @RequestMapping(value = "/buscar-usuarios-ativos", method = RequestMethod.GET)
    public List<UsuarioDTO> getUsuariosAtivos() {
        return usuarioService.findAllActiveReturningDTOs();
    }

    @ResponseBody
    @RequestMapping(value = "/buscar-todos-usuarios", method = RequestMethod.GET)
    public List<UsuarioDTO> getUsuariosQualquerStatus() {
        return usuarioService.findAllReturningDTOs();
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public String listUsuarios() {
        return "usuarios";
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public String getUsuario(@RequestParam Long idUsuario, Model model) {
        model.addAttribute("usuario", usuarioService.findByIdReturningDTO(idUsuario));
        return "usuario";
    }

    @RequestMapping(value = "/salvar-usuario", method = RequestMethod.POST)
    public ModelAndView salvarUsuario(@ModelAttribute("usuario") @Valid UsuarioDTO usuarioDTO, BindingResult result) {
        if (result.hasErrors()) {
            return addAttributesToModel(usuarioDTO, new ServicoDTO(), "usuario", "cadastro");
        } else {

            UsuarioDTO retornado = usuarioService.salvarUsuario(usuarioDTO);
            ModelAndView model = new ModelAndView();
            model.setViewName("usuarios");
            model.addObject("sucesso",
                    retornado != null
                            ? "Usuário " + usuarioDTO.getNome() + " salvo com sucesso!"
                            : "Desculpe-nos, aconteceu algum erro e o usuário não pôde ser cadastrado.");
            return model;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/inativar-usuario", method = RequestMethod.POST)
    public boolean inativarUsuario(@RequestParam Long idUsuario) {
        try {
            if (LoggedInUserUtils.checkIfUserIsAdmin() && servicoService.countServicosByUsuarioId(idUsuario) > 0) {
                servicoService.cancelarServicosByIdUsuario(idUsuario);
                return usuarioService.inativarUsuario(idUsuario);
            } else {
                return usuarioService.inativarUsuario(idUsuario);
            }
        } catch (NullPointerException e) {
            e.getMessage();
            return false;
        }
    }

    @RequestMapping(value = "/editar-usuario", method = RequestMethod.GET)
    public ModelAndView editarUsuario(@RequestParam Long idUsuario) {
        UsuarioDTO dto = usuarioService.findByIdReturningDTO(idUsuario);
        if (LoggedInUserUtils.checkIfUserIsAdmin()) {
            return addAttributesToModel(dto, new ServicoDTO(), "usuario", "cadastro");
        } else {

            ModelAndView model = new ModelAndView();
            model.addObject("sucesso", "Você não tem as permissões necessárias para editar este usuário.");
            model.setViewName("usuarios");

            return model;
        }
    }

    @RequestMapping(value = "/check-username", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkUsername(@RequestParam String username, @RequestParam long id) {
        return usuarioService.checkUsernameAvailability(username, id);
    }

    private ModelAndView addAttributesToModel(UsuarioDTO userDTO, ServicoDTO serviceDTO, String targetNavTab, String viewName) {
        ModelAndView model = new ModelAndView();
        model.addObject("usuario", userDTO);
        model.addObject("servico", serviceDTO);
        model.addObject("guia", targetNavTab);
        model.setViewName(viewName);
        return model;
    }
}
