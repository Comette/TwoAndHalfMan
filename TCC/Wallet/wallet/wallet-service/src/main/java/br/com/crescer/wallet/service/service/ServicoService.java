// Error reading included file Templates/Classes/Templates/Licenses/license-wallet-br.txt
package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.service.dto.DashboardDTO;
import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.repository.ServicoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author victo
 */
@Service
public class ServicoService {
    
    @Autowired
    ServicoRepository repository;
    
    @Autowired
    CotacaoService cotacaoService;
    
    public DashboardDTO geraDadosDashboard(Pageable pageable){
        List<ServicoDTO> servicosDTOMesAtualPaginados = this.getServicosDTOMesAtualPaginados(pageable);
        List<ServicoDTO> servicosDTOProximoMesPaginados = this.getServicosDTOProximoMesPaginados(pageable);
        List<ServicoDTO> servicosDTOProximoMes = this.getServicosDTOProximoMes();
        List<ServicoDTO> servicosDTOMesAtual = this.getServicosDTOMesAtual();
        
        BigDecimal gastoTotalAtual = this.getGastoTotalAtual();
        servicosDTOMesAtual.stream().forEach((servico) -> {
            servico.setPorcentagemCustoTotal(gastoTotalAtual);
        });
                
        BigDecimal gastoTotalProximoMes = this.getGastoTotalProximoMes();
        servicosDTOProximoMes.stream().forEach((servico) -> {
            servico.setPorcentagemCustoTotal(gastoTotalProximoMes);
        });
        
        return new DashboardDTO(servicosDTOMesAtualPaginados, servicosDTOProximoMesPaginados, gastoTotalAtual, gastoTotalProximoMes);
    }
    
    public BigDecimal getGastoTotalAtual() {
        List<ServicoDTO> servicosDTOMesAtual = this.getServicosDTO(this.servicosMesAtual());
        BigDecimal gastoTotalAtual = this.calculaGastoMensal(servicosDTOMesAtual);
        return gastoTotalAtual;
    }

    public BigDecimal getGastoTotalProximoMes() {
        List<ServicoDTO> servicosDTOProximoMes = this.getServicosDTO(this.servicosProximoMes());
        BigDecimal gastoTotalProximoMes = this.calculaGastoMensal(servicosDTOProximoMes);
        return gastoTotalProximoMes;
    }
    
    public List<ServicoDTO> getServicosDTOMesAtual(){
        return this.getServicosDTO(this.servicosMesAtual());
    }
    
    public List<ServicoDTO> getServicosDTOMesAtualPaginados(Pageable pageable){
        return this.getServicosDTO(servicosMesAtualPaginados(pageable));
    }
    
    public List<ServicoDTO> getServicosDTOProximoMes(){
        return this.getServicosDTO(this.servicosProximoMes());
    }
    
    public List<ServicoDTO> getServicosDTOProximoMesPaginados(Pageable pageable){
        return this.getServicosDTO(this.servicosProximoMesPaginados(pageable));
    }
    
    private List<Servico> servicosMesAtual(){       
        return repository.findByDsSituacaoNot(Situacao.INATIVO);
    }
    
    private List<Servico> servicosMesAtualPaginados(Pageable pageable){
        pageable = new PageRequest(pageable.getPageNumber(),4,Sort.Direction.DESC, "vlTotalServico");
        return repository.findByDsSituacaoNot(Situacao.INATIVO, pageable);
    }
    
    private List<Servico> servicosProximoMes(){         
        return repository.findByDsSituacao(Situacao.ATIVO);
    }
    
    private List<Servico> servicosProximoMesPaginados(Pageable pageable){
        pageable = new PageRequest(pageable.getPageNumber(),4,Sort.Direction.DESC, "vlTotalServico");
        return repository.findByDsSituacao(Situacao.ATIVO, pageable);
    }
    
    private BigDecimal calculaGastoMensal(List<ServicoDTO> servicosDTO){         
        BigDecimal gastoTotal = BigDecimal.ZERO;        
        servicosDTO.stream().forEach((servico) -> {
            gastoTotal.add(servico.getCustoMensal());
        });        
        return gastoTotal;
    }
    
    private List<ServicoDTO> getServicosDTO(List<Servico> servicos){          
        List<ServicoDTO> servicosDTO = new ArrayList<>();                
        servicos.stream().forEach((servico) -> {            
            servicosDTO.add(this.buildDTO(servico));
        });              
        return servicosDTO;
    }
    
    private ServicoDTO buildDTO(Servico servico){
        Map<Moeda, BigDecimal> medias = cotacaoService.findLastAverage();
        long id = servico.getIdServico();
        String nome = servico.getNmServico();
        BigDecimal custoMensal = servico.getVlTotalServico().divide(BigDecimal.valueOf(servico.getDsPeriodicidade().getNumeral())).divide(medias.get(servico.getDsSimboloMoeda())).multiply(medias.get(Moeda.BRL));
        return new ServicoDTO(id, nome, custoMensal);
    }

}
