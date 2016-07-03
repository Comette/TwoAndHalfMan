package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.service.dto.DashboardDTO;
import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.service.ServicoService;
import br.com.crescer.wallet.service.dto.GraficoDTO;
import br.com.crescer.wallet.service.dto.UsuarioDTO;
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
    ServicoService service;
    
    @ResponseBody
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public DashboardDTO dashboard(Pageable pageable){        
        return service.geraDadosDashboard(pageable);
    }
    
    @ResponseBody
    @RequestMapping(value = "/gasto-total-atual", method = RequestMethod.GET)
    public BigDecimal gastoTotalAtual(){        
        return service.getGastoTotalAtual();
    }
    
    @ResponseBody
    @RequestMapping(value = "/gasto-total-proximo-mes", method = RequestMethod.GET)
    public BigDecimal gastoTotalProximoMes(){
        return service.getGastoTotalProximoMes();
    }
    
    @ResponseBody
    @RequestMapping(value = "/servicos-mes-atual", method = RequestMethod.GET)
    public List<ServicoDTO> servicosMesAtual(@RequestParam(required = false) Long idGerente, Pageable pageable){
        return idGerente == null || idGerente == 0 ? 
                service.getServicosDTOMesAtualPaginados(pageable) : service.getServicosDTOMesAtualFiltradosPorGerentePaginados(idGerente, pageable);
    }
    
    @ResponseBody
    @RequestMapping(value = "/servicos-proximo-mes", method = RequestMethod.GET)
    public List<ServicoDTO> servicosProximosMes(@RequestParam(required = false) Long idGerente, Pageable pageable){
        return idGerente == null || idGerente == 0 ? 
                service.getServicosDTOProximoMesPaginados(pageable) : service.getServicosDTOProximoMesFiltradosPorGerentePaginados(idGerente, pageable);
    }
    
    @ResponseBody
    @RequestMapping( value = "/servicos-inflar-grafico", method = RequestMethod.GET)
    public GraficoDTO inflarGrafico(){        
        return service.getDadosGraficoServicos();
    }
    
    @RequestMapping( value = "/servico", method = RequestMethod.GET)
    public String getServico(@RequestParam Long idServico, Model model){
        ServicoDTO servico = service.getServicoDTO(idServico);
        model.addAttribute("servico",servico);
        model.addAttribute("valorServicoFormatado", NumberFormat.getCurrencyInstance().format(servico.getCustoMensal()));
        return "servico"; 
    }

    @RequestMapping( value = "/salvar-servico", method = RequestMethod.POST)    
    public ModelAndView salvarServico(@ModelAttribute("servico") @Valid ServicoDTO servicoDTO, BindingResult result) {
        if(result.hasErrors()){
            ModelAndView model = new ModelAndView();
            model.addObject("usuario", new UsuarioDTO());
            model.addObject("servico", servicoDTO);
            model.addObject("guia", "servico");
            model.setViewName("cadastro");
            return model;        
        }else {
            Servico retornado = service.salvarServico(servicoDTO);
            ModelAndView model = new ModelAndView();
            model.setViewName("dashboard");
            model.addObject("sucesso", 
                     retornado != null ? 
                            "Serviço " + retornado.getNmServico() + " cadastrado com sucesso!" : 
                            "Desculpe-nos, aconteceu algum erro e o serviço não pôde ser cadastrado.");
            return model;
        }
    }
    
}
