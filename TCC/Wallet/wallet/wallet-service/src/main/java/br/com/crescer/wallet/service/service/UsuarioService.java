package br.com.crescer.wallet.service.service;
import br.com.crescer.wallet.entity.Permissao;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.entity.Usuario;
import br.com.crescer.wallet.service.dto.UsuarioDTO;
import br.com.crescer.wallet.service.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<UsuarioDTO> findAllReturningDTOs(){
        List<UsuarioDTO> list = new ArrayList<>();
        for ( Usuario u : repository.findAllByTpPermissaoAndDsSituacaoNot(Permissao.GERENTE, Situacao.INATIVO)){
            UsuarioDTO dto = new UsuarioDTO();
            dto.setId(u.getIdUsuario());
            dto.setNome(u.getNmUsuario());
            list.add(dto);
        }
        return list;
    }
    
    public UsuarioDTO salvarUsuario(UsuarioDTO dto){
        Usuario user = dto.buildUsuario();        
        return new UsuarioDTO(repository.save(user));
    }
}
