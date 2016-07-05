/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.crescer.wallet.service.repository;

import br.com.crescer.wallet.entity.entity.CurrencyExchange;
import br.com.crescer.wallet.entity.util.Coin;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author victo
 */


public interface CurrencyExchangeRepository extends CrudRepository<CurrencyExchange, Long> {

    public CurrencyExchange findFirstByDtCurrencyExchangeOrderByIdCurrencyExchangeDesc(LocalDate now);

    public List<CurrencyExchange> findByDsCoinAndDtCurrencyExchangeBetween(Coin coin, LocalDate minusDays, LocalDate today);

    public List<CurrencyExchange> findByDtCurrencyExchangeBetween(LocalDate minusDays, LocalDate today);

    public List<CurrencyExchange> findByDtCurrencyExchange(LocalDate today);
    
}
