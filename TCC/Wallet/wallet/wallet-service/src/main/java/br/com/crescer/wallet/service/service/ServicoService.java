// Error reading included file Templates/Classes/Templates/Licenses/license-wallet-br.txt
package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.entity.Periodicidade;
import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.service.repository.ServicoRepository;
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
    
    public Map<Moeda, Double> gastoTotalMensal(){      
        List<Servico> servicosMensais = repository.findByDsPeriodicidadeAndDsSituacaoNot(Periodicidade.MENSAL, Situacao.INATIVO);
        List<Servico> servicosTrimestrais = repository.findByDsPeriodicidadeAndDsSituacaoNot(Periodicidade.TRIMESTRAL, Situacao.INATIVO);
        List<Servico> servicosSemestrais = repository.findByDsPeriodicidadeAndDsSituacaoNot(Periodicidade.SEMESTRAL, Situacao.INATIVO);
        List<Servico> servicosAnuais = repository.findByDsPeriodicidadeAndDsSituacaoNot(Periodicidade.ANUAL, Situacao.INATIVO);
        
        return calculaGastoMensal(servicosMensais, servicosTrimestrais, servicosSemestrais, servicosAnuais);
    }
    
    public Map<Moeda, Double> gastoTotalMensalPrevistoProximoMes(){      
        List<Servico> servicosMensais = repository.findByDsPeriodicidadeAndDsSituacao(Periodicidade.MENSAL, Situacao.ATIVO);
        List<Servico> servicosTrimestrais = repository.findByDsPeriodicidadeAndDsSituacao(Periodicidade.TRIMESTRAL, Situacao.ATIVO);
        List<Servico> servicosSemestrais = repository.findByDsPeriodicidadeAndDsSituacao(Periodicidade.SEMESTRAL, Situacao.ATIVO);
        List<Servico> servicosAnuais = repository.findByDsPeriodicidadeAndDsSituacao(Periodicidade.ANUAL, Situacao.ATIVO);
        
        return calculaGastoMensal(servicosMensais, servicosTrimestrais, servicosSemestrais, servicosAnuais);
    }
    
    private Map<Moeda, Double> calculaGastoMensal(List<Servico> servicosMensais, List<Servico> servicosTrimestrais, List<Servico> servicosSemestrais, List<Servico> servicosAnuais){
        Map<Moeda, Double> medias = cotacaoService.buscarUltimaMedia();        
        
        double valorServicosMensais = servicosMensais.stream().mapToDouble((servico) -> servico.getVlTotalServico() * medias.get(servico.getDsSimboloMoeda())).sum();
        double valorServicosTrimestrais = servicosTrimestrais.stream().mapToDouble((servico) -> (servico.getVlTotalServico() / 3) * medias.get(servico.getDsSimboloMoeda())).sum();
        double valorServicosSemestrais = servicosSemestrais.stream().mapToDouble((servico) -> (servico.getVlTotalServico() / 6) * medias.get(servico.getDsSimboloMoeda())).sum();
        double valorServicosAnuais = servicosAnuais.stream().mapToDouble((servico) -> (servico.getVlTotalServico() / 12) * medias.get(servico.getDsSimboloMoeda())).sum();
       
        double gastoTotalUSD = valorServicosMensais + valorServicosTrimestrais + valorServicosSemestrais + valorServicosAnuais;
        double gastoTotalBRL = gastoTotalUSD / medias.get(Moeda.BRL);
       
        Map<Moeda, Double> gastoTotal = new HashMap<>();
        
        gastoTotal.put(Moeda.USD, gastoTotalUSD);
        gastoTotal.put(Moeda.BRL, gastoTotalBRL);
        
        return gastoTotal;
    }
}
