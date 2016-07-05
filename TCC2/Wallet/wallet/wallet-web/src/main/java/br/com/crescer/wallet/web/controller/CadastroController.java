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
public class CadastroController {
    
    @Autowired
    ClientService userService;
    
    @Autowired
    ContractService contractService;
    
    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastro(Model model, @RequestParam(required = false) Long idUser, @RequestParam(required = false) Long idContract){
        if(idUser != null && idUser > 0){
            model.addAttribute("usuario", userService.findByIdReturningDTO(idUser));
        }else{
            model.addAttribute("usuario", new ClientDTO());
        }
        if(idContract != null && idContract > 0){
            model.addAttribute("servico", contractService.getContractDTO(idContract));
        }else{
            model.addAttribute("servico", new ContractDTO());
        }        
        model.addAttribute("guia", "nenhum");
        model.addAttribute("usuariosCadastrados", userService.findAllReturningDTOs());
        return "cadastro";
    }
}
