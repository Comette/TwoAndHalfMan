// Error reading included file Templates/Classes/Templates/Licenses/license-wallet-br.txt
package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.DashboardDTO;
import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.service.ServicoService;
import br.com.crescer.wallet.service.dto.GraficoDTO;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author victo
 */

@RestController
public class ServicoController {
    
    @Autowired
    ServicoService service;
    
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public DashboardDTO dashboard(Pageable pageable){        
        return service.geraDadosDashboard(pageable);
    }
    
    @RequestMapping(value = "/gasto-total-atual", method = RequestMethod.GET)
    public BigDecimal gastoTotalAtual(){        
        return service.getGastoTotalAtual();
    }
    
    @RequestMapping(value = "/gasto-total-proximo-mes", method = RequestMethod.GET)
    public BigDecimal gastoTotalProximoMes(){
        return service.getGastoTotalProximoMes();
    }
    
    @RequestMapping(value = "/servicos-mes-atual", method = RequestMethod.GET)
    public List<ServicoDTO> servicosMesAtual(@RequestParam(required = false) Long idGerente, Pageable pageable){
        return idGerente == null ? 
                service.getServicosDTOMesAtualPaginados(pageable) : service.getServicosDTOMesAtualFiltradosPorGerentePaginados(idGerente, pageable);
    }
    
    @RequestMapping(value = "/servicos-proximo-mes", method = RequestMethod.GET)
    public List<ServicoDTO> servicosProximosMes(@RequestParam(required = false) Long idGerente, Pageable pageable){
        return idGerente == null ? 
                service.getServicosDTOProximoMesPaginados(pageable) : service.getServicosDTOProximoMesFiltradosPorGerentePaginados(idGerente, pageable);
    }
    
    @RequestMapping( value = "/servicos-inflar-grafico", method = RequestMethod.GET)
    public GraficoDTO inflarGrafico(){        
        return service.getDadosGraficoServicos();
    }
//    
//    @RequestMapping(value = "/filtrar-por-gerente", method = RequestMethod.GET)
//    public List<ServicoDTO> buscarPorGerentePaginado(@RequestParam Long idGerente,Pageable pageable) {
//        return service.getServicosDTOFiltradosPorGerentePaginados(idGerente,pageable);
//    }
}
