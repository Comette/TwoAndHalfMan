package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.entity.entity.Contract;
import br.com.crescer.wallet.service.dto.ContractDTO;
import br.com.crescer.wallet.service.dto.DashboardDTO;
import br.com.crescer.wallet.service.dto.GraphDTO;
import br.com.crescer.wallet.service.dto.UsuarioDTO;
import br.com.crescer.wallet.service.service.ContractService;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
 * @author victo
 */

@Controller
public class ServicoController {
    
    @Autowired
    ContractService service;
    
    @ResponseBody
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public DashboardDTO dashboard(Pageable pageable){        
        return service.generateDashboardData(pageable);
    }
    
    @ResponseBody
    @RequestMapping(value = "/gasto-total-atual", method = RequestMethod.GET)
    public BigDecimal gastoTotalAtual(){        
        return service.getThisMonthAmountExpense();
    }
    
    @ResponseBody
    @RequestMapping(value = "/gasto-total-proximo-mes", method = RequestMethod.GET)
    public BigDecimal gastoTotalProximoMes(){
        return service.getNextMonthAmountExpense();
    }
    
    @ResponseBody
    @RequestMapping(value = "/servicos-mes-atual", method = RequestMethod.GET)
    public List<ContractDTO> servicosMesAtual(@RequestParam(required = false) Long idUser, Pageable pageable){
        return idUser == null || idUser == 0 ? 
                service.getThisMonthPagedContractDTOs(pageable) : service.getThisMonthPagedAndFilteredByUserContractDTOs(idUser, pageable);
    }
    
    @ResponseBody
    @RequestMapping(value = "/servicos-proximo-mes", method = RequestMethod.GET)
    public List<ContractDTO> servicosProximosMes(@RequestParam(required = false) Long idUser, Pageable pageable){
        return idUser == null || idUser == 0 ? 
                service.getNextMonthPagedContractDTOs(pageable) : service.getNextMonthPagedAndFilteredByUserContractDTOs(idUser, pageable);
    }
    
    @ResponseBody
    @RequestMapping( value = "/servicos-inflar-grafico", method = RequestMethod.GET)
    public GraphDTO inflarGrafico(){        
        return service.generateGranphData();
    }
    
    @RequestMapping( value = "/servico", method = RequestMethod.GET)
    public String getServico(@RequestParam Long idContract, Model model){
        ContractDTO contract = service.getContractDTO(idContract);
        model.addAttribute("servico",contract);
        model.addAttribute("valorServicoFormatado", NumberFormat.getCurrencyInstance().format(contract.getMonthlyExpense()));
        return "servico"; 
    }

    @RequestMapping( value = "/salvar-servico", method = RequestMethod.POST)    
    public ModelAndView salvarServico(@ModelAttribute("servico") @Valid ContractDTO contractDTO, BindingResult result) {
        if(result.hasErrors()){
            ModelAndView model = new ModelAndView();
            model.addObject("usuario", new UsuarioDTO());
            model.addObject("servico", contractDTO);
            model.addObject("guia", "servico");
            model.setViewName("cadastro");
            return model;        
        }else {
            Contract returned = service.saveContract(contractDTO);
            ModelAndView model = new ModelAndView();
            model.setViewName("dashboard");
            model.addObject("sucesso", 
                     returned != null ? 
                            "Serviço " + returned.getNmContract()+ " cadastrado com sucesso!" : 
                            "Desculpe-nos, aconteceu algum erro e o serviço não pôde ser cadastrado.");
            return model;
        }
    }
    
}
