package br.com.crescer.wallet.service.repository;

import br.com.crescer.wallet.entity.Permissao;
import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.entity.Situacao;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author douglas.ballester
 */


public interface ServicoRepository extends PagingAndSortingRepository<Servico, Long> {

    public List<Servico> findByDsSituacaoNot(Situacao situacao);
    
    public List<Servico> findByDsSituacaoNot(Situacao situacao, Pageable pageable);

    public List<Servico> findByDsSituacao(Situacao situacao);
    
    public List<Servico> findByDsSituacao(Situacao situacao, Pageable pageable);
    
    public List<Servico> findAllByusuarioIdUsuario_idUsuario(Long idUsuario, Pageable pageable);
    
}
