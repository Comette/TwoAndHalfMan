/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.crescer.wallet.service.repository;

import br.com.crescer.wallet.entity.entity.Contract;
import br.com.crescer.wallet.entity.util.State;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author victo
 */


public interface ContractRepository extends CrudRepository<Contract, Long>{    

    public List<Contract> findByDsState(State state);
    
    public List<Contract> findByDsStateNot(State state);
    
    public List<Contract> findByDsState(State state, Pageable page);    
    
    public List<Contract> findByDsStateNot(State state, Pageable pageable);
    
    public List<Contract> findByClientIdClient_idClientAndDsState(Long idClient, State state, Pageable page);

    public List<Contract> findByClientIdClient_idClientAndDsStateNot(Long idClient, State state, Pageable pageable);

}
