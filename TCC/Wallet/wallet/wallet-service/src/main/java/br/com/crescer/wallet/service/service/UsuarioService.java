package br.com.crescer.wallet.service.service;
import br.com.crescer.wallet.entity.Usuario;
import br.com.crescer.wallet.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hedo
 */

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository repository;
    
    public Usuario findOneByDsUserName(String dsUserName){
        return repository.findUsuarioByDsUserName(dsUserName);
    }
}
