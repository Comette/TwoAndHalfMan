package br.com.crescer.wallet.service.repository;

import br.com.crescer.wallet.entity.Permissao;
import br.com.crescer.wallet.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author douglas.ballester
 */


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    
    public Usuario findUsuarioByDsUserName(String dsUserName);
    public Iterable<Usuario> findAllBytpPermissao(Permissao permissao);
}
