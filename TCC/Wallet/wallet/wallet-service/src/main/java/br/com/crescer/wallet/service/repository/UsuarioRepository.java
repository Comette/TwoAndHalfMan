package br.com.crescer.wallet.service.repository;

import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.entity.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author douglas.ballester
 */


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    
    public Usuario findUsuarioByDsUserName(String dsUserName);
    public List<Usuario> findAllByDsSituacaoNot(Situacao situacao);
    public List<Usuario> findAll();
}
