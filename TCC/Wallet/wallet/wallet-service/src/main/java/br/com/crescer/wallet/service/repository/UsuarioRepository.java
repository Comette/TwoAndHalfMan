package br.com.crescer.wallet.service.repository;

import br.com.crescer.wallet.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author douglas.ballester
 */


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    
    Usuario findOneByDsUserName(String dsUserName);
}
