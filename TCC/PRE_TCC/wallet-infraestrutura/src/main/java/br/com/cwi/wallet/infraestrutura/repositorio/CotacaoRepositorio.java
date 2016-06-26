/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.cwi.wallet.infraestrutura.repositorio;

import br.com.cwi.wallet.dominio.model.Cotacao;
import java.util.Date;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author victo
 */
public interface CotacaoRepositorio extends CrudRepository<Cotacao, Long> {
    
    public Cotacao findFirstByDtCotacaoOrderByIdCotacaoDesc(Date date);
    
}
