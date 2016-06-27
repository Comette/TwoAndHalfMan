package br.com.crescer.wallet.service.repository;

import br.com.crescer.wallet.entity.Cotacao;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author douglas.ballester
 */


public interface CotacaoRepository extends CrudRepository<Cotacao, Long> {

    public Cotacao findFirstByDtCotacaoOrderByIdCotacaoDesc(LocalDate date);

    public List<Cotacao> findByDtCotacaoBetween(LocalDate minusDays, LocalDate dia);
    
}
