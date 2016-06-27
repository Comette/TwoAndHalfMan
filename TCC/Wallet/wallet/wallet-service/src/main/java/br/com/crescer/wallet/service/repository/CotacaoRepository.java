package br.com.crescer.wallet.service.repository;

import br.com.crescer.wallet.entity.Cotacao;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author douglas.ballester
 */


public interface CotacaoRepository extends CrudRepository<Cotacao, Long> {
    
}
