package br.com.crescer.wallet.service.repository;

import br.com.crescer.wallet.entity.Periodicidade;
import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.entity.Situacao;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author douglas.ballester
 */


public interface ServicoRepository extends CrudRepository<Servico, Long> {

    public List<Servico> findByDsSituacaoNot(Situacao situacao);

    public List<Servico> findByDsSituacao(Situacao situacao);
    
}
