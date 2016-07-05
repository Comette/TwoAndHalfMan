package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.ContractDTO;
import br.com.crescer.wallet.service.dto.ClientDTO;
import br.com.crescer.wallet.service.service.ClientService;
import br.com.crescer.wallet.service.service.ContractService;
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
public class ClientController {

    @Autowired
    ClientService clientService;
    
    @Autowired
    ContractService contractService;

    @ResponseBody
    @RequestMapping(value = "/buscar-usuarios-ativos", method = RequestMethod.GET)
    public List<ClientDTO> getActiveClients() {
        return clientService.findAllActiveReturningDTOs();
    }
    @ResponseBody
    @RequestMapping(value = "/buscar-todos-usuarios", method = RequestMethod.GET)
    public List<ClientDTO> getAllClients() {
        return clientService.findAllReturningDTOs();
    }

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public String listClients() {
        return "clients";
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public String getGerente(@RequestParam Long idClient, Model model) {
        model.addAttribute("usuario", clientService.findByIdReturningDTO(idClient));
        return "client";
    }

    @RequestMapping(value = "/salvar-usuario", method = RequestMethod.POST)
    public ModelAndView salvarUsuario(@ModelAttribute("usuario") @Valid ClientDTO clientDTO, BindingResult result) {
        if (result.hasErrors()) {
            return addAttributesToModel(clientDTO, new ContractDTO(), "usuario", "cadastro");
        } else {
            ClientDTO retornado = clientService.saveClient(clientDTO);
            ModelAndView model = new ModelAndView();
            model.setViewName("usuarios");
            model.addObject("sucesso",
                    retornado != null
                            ? "Usuário " + clientDTO.getName() + " salvo com sucesso!"
                            : "Desculpe-nos, aconteceu algum erro e o usuário não pôde ser salvo.");
            return model;
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "/inativar-usuario", method = RequestMethod.POST)
    public boolean deactivateClient(@RequestParam Long idClient) {
        try {
            if (LoggedInUserUtils.checkIfUserIsAdmin() && contractService.countServicosByUsuarioId(idClient) > 0) {
                contractService.cancelContractByIdClient(idClient);
                return clientService.deactivateClient(idClient);
            } else {
                return clientService.deactivateClient(idClient);
            }
        } catch (NullPointerException e) {
            e.getMessage();
            return false;
        }
    }
    
    @RequestMapping(value = "/editar-usuario", method = RequestMethod.GET)
    public ModelAndView editClient(@RequestParam Long idClient) {
        ClientDTO dto = clientService.findByIdReturningDTO(idClient);
        if (LoggedInUserUtils.checkIfUserIsAdmin()) {
            return addAttributesToModel(dto, new ContractDTO(), "usuario", "cadastro");
        } else {
            ModelAndView model = new ModelAndView();
            model.addObject("sucesso", "Você não tem as permissões necessárias para editar este usuário.");
            model.setViewName("clients");
            return model;
        }
    }
    
    
    @RequestMapping(value = "/check-username", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkUsername(@RequestParam String username, @RequestParam long id){
        return clientService.checkUsernameAvailability(username, id);
    }
    
    private ModelAndView addAttributesToModel(ClientDTO clientDTO, ContractDTO contractDTO, String targetNavTab, String viewName) {
        ModelAndView model = new ModelAndView();
        model.addObject("usuario", clientDTO);
        model.addObject("servico", contractDTO);
        model.addObject("guia", targetNavTab);
        model.setViewName(viewName);
        return model;
    }
}
