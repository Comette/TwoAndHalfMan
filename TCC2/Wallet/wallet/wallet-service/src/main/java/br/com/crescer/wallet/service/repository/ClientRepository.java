package br.com.crescer.wallet.service.repository;

import br.com.crescer.wallet.entity.util.State;
import br.com.crescer.wallet.entity.entity.Client;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author douglas.ballester
 */


public interface ClientRepository extends CrudRepository<Client, Long> {
    
    public Client findClientByDsUserName(String username);
    public List<Client> findAllByDsStateNot(State state);
    public List<Client> findAll();
}
