package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.DashboardDTO;
import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.service.ServicoService;
import br.com.crescer.wallet.service.dto.GraficoDTO;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        model.addAttribute("servico",service.getServico(idServico));
        return "servico"; 
    }
}
