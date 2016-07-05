package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.ContractDTO;
import br.com.crescer.wallet.service.dto.ClientDTO;
import br.com.crescer.wallet.service.service.ContractService;
import br.com.crescer.wallet.service.service.ClientService;
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
public class RegisterController {
    
    @Autowired
    ClientService userService;
    
    @Autowired
    ContractService contractService;
    
    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String register(Model model, @RequestParam(required = false) Long idClient, @RequestParam(required = false) Long idContract, @RequestParam(required = false) String tab){
        if(idClient != null && idClient > 0){
            model.addAttribute("usuario", userService.findByIdReturningDTO(idClient));
        }else{
            model.addAttribute("usuario", new ClientDTO());
        }
        if(idContract != null && idContract > 0){
            model.addAttribute("servico", contractService.getContractDTO(idContract));
        }else{
            model.addAttribute("servico", new ContractDTO());
        }        
        model.addAttribute("tab", (tab != null ? tab : "nenhuma"));
        model.addAttribute("usuariosCadastrados", userService.findAllActiveReturningDTOs());
        return "register";
    }
}
