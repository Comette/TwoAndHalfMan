// Error reading included file Templates/Classes/Templates/Licenses/license-wallet-br.txt
package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.entity.Periodicidade;
import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.repository.ServicoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    public void geraDadosDashboard(){
        
    }
    
    public Map<Periodicidade, List<Servico>> servicosMesAtual(){   
        Map<Periodicidade, List<Servico>> servicos = new HashMap<>();
        servicos.put(Periodicidade.MENSAL, repository.findByDsPeriodicidadeAndDsSituacaoNot(Periodicidade.MENSAL, Situacao.INATIVO));
        servicos.put(Periodicidade.TRIMESTRAL, repository.findByDsPeriodicidadeAndDsSituacaoNot(Periodicidade.TRIMESTRAL, Situacao.INATIVO));
        servicos.put(Periodicidade.SEMESTRAL, repository.findByDsPeriodicidadeAndDsSituacaoNot(Periodicidade.SEMESTRAL, Situacao.INATIVO));
        servicos.put(Periodicidade.ANUAL, repository.findByDsPeriodicidadeAndDsSituacaoNot(Periodicidade.ANUAL, Situacao.INATIVO));        
        return servicos;
    }
    
    public Map<Periodicidade, List<Servico>> servicosProximoMes(){ 
        Map<Periodicidade, List<Servico>> servicos = new HashMap<>();
        servicos.put(Periodicidade.MENSAL, repository.findByDsPeriodicidadeAndDsSituacao(Periodicidade.MENSAL, Situacao.ATIVO));
        servicos.put(Periodicidade.TRIMESTRAL, repository.findByDsPeriodicidadeAndDsSituacao(Periodicidade.TRIMESTRAL, Situacao.ATIVO));
        servicos.put(Periodicidade.SEMESTRAL, repository.findByDsPeriodicidadeAndDsSituacao(Periodicidade.SEMESTRAL, Situacao.ATIVO));
        servicos.put(Periodicidade.ANUAL, repository.findByDsPeriodicidadeAndDsSituacao(Periodicidade.ANUAL, Situacao.ATIVO));        
        return servicos;
    }
    
    private BigDecimal calculaGastoMensal(Map<Periodicidade, List<Servico>> servicos){
        
        List<Servico> servicosMensais = servicos.get(Periodicidade.MENSAL);
        List<Servico> servicosTrimestrais = servicos.get(Periodicidade.TRIMESTRAL);
        List<Servico> servicosSemestrais = servicos.get(Periodicidade.SEMESTRAL);
        List<Servico> servicosAnuais = servicos.get(Periodicidade.ANUAL);
        
        Map<Moeda, BigDecimal> medias = cotacaoService.buscarUltimaMedia();        
        
        BigDecimal custoMensalServicosMensais = servicosMensais.stream().map(servico -> servico.getVlTotalServico().divide(medias.get(servico.getDsSimboloMoeda()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal custoMensalServicosTrimestrais = servicosTrimestrais.stream().map(servico -> (servico.getVlTotalServico().divide(BigDecimal.valueOf(3))).divide(medias.get(servico.getDsSimboloMoeda()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal custoMensalServicosSemestrais = servicosSemestrais.stream().map(servico -> (servico.getVlTotalServico().divide(BigDecimal.valueOf(6))).divide(medias.get(servico.getDsSimboloMoeda()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal custoMensalServicosAnuais = servicosAnuais.stream().map(servico -> (servico.getVlTotalServico().divide(BigDecimal.valueOf(12))).divide(medias.get(servico.getDsSimboloMoeda()))).reduce(BigDecimal.ZERO, BigDecimal::add);
       
        BigDecimal gastoTotalUSD = custoMensalServicosMensais.add(custoMensalServicosTrimestrais).add(custoMensalServicosSemestrais).add(custoMensalServicosAnuais);
        BigDecimal gastoTotalBRL = gastoTotalUSD.multiply(medias.get(Moeda.BRL));
        
        return gastoTotalBRL;
    }
    
    private List<ServicoDTO> getCustoServicos(Map<Periodicidade, List<Servico>> servicos, Map<Moeda, Double> medias, double gastoTotal){          
        
        List<Servico> servicosMensais = servicos.get(Periodicidade.MENSAL);
        List<Servico> servicosTrimestrais = servicos.get(Periodicidade.TRIMESTRAL);
        List<Servico> servicosSemestrais = servicos.get(Periodicidade.SEMESTRAL);
        List<Servico> servicosAnuais = servicos.get(Periodicidade.ANUAL);
        
        List<ServicoDTO> servicosDTO = new ArrayList<>();
                
//        for (Servico servico: servicosMensais){            
//            double custoMensal = servico.getVlTotalServico() / medias.get(servico.getDsSimboloMoeda());
//            double porcentagemCustoTotal = custoMensal * 100 / gastoTotal;
//            servicosDTO.add(new ServicoDTO(servico.getIdServico(), servico.getNmServico(), custoMensal, porcentagemCustoTotal));
//        }
              
        return servicosDTO;
    }
}
