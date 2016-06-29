// Error reading included file Templates/Classes/Templates/Licenses/license-wallet-br.txt
package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.service.dto.DashboardDTO;
import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.repository.ServicoRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    public DashboardDTO geraDadosDashboard(){
        List<ServicoDTO> servicosDTOMesAtual = this.getServicosDTOMesAtual();
        List<ServicoDTO> servicosDTOProximoMes = this.getServicosDTOProximoMes();
        
        BigDecimal gastoTotalAtual = this.getGastoTotalAtual();
        servicosDTOMesAtual.stream().forEach((servico) -> {
            servico.setPorcentagemCustoTotal(gastoTotalAtual);
        });
                
        BigDecimal gastoTotalProximoMes = this.getGastoTotalProximoMes();
        servicosDTOProximoMes.stream().forEach((servico) -> {
            servico.setPorcentagemCustoTotal(gastoTotalProximoMes);
        });
        
        return new DashboardDTO(servicosDTOMesAtual, servicosDTOProximoMes, gastoTotalAtual, gastoTotalProximoMes);
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
    
    public List<ServicoDTO> getServicosDTOProximoMes(){
        return this.getServicosDTO(this.servicosProximoMes());
    }
    
    private List<Servico> servicosMesAtual(){       
        return repository.findByDsSituacaoNot(Situacao.INATIVO);
    }
    
    private List<Servico> servicosProximoMes(){         
        return repository.findByDsSituacao(Situacao.ATIVO);
    }
    
    private BigDecimal calculaGastoMensal(List<ServicoDTO> servicosDTO){         
        BigDecimal gastoTotal = BigDecimal.ZERO; 
        for(ServicoDTO servico : servicosDTO){
            gastoTotal = gastoTotal.add(servico.getCustoMensal()).setScale(2, RoundingMode.HALF_UP);
        }        
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
        Map<Moeda, BigDecimal> medias = cotacaoService.buscarUltimaMedia();
        long id = servico.getIdServico();
        String nome = servico.getNmServico();
        BigDecimal custoMensal = servico.getVlTotalServico().divide(BigDecimal.valueOf(servico.getDsPeriodicidade().getNumeral()), 6, RoundingMode.HALF_UP).divide(medias.get(servico.getDsSimboloMoeda()), 6, RoundingMode.HALF_UP).multiply(medias.get(Moeda.BRL)).setScale(2, RoundingMode.HALF_UP);
        return new ServicoDTO(id, nome, custoMensal);
    }

}
