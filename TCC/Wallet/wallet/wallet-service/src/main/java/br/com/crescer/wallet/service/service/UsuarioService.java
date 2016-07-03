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
    
    public List<UsuarioDTO> findAllActiveReturningDTOs(){
        List<UsuarioDTO> list = new ArrayList<>();
        repository.findAllByTpPermissaoAndDsSituacaoNot(Permissao.GERENTE, Situacao.INATIVO).stream().map((u) -> {
            return buildDTO(u);
        }).forEach((dto) -> {
            list.add(dto);
        });
        return list;
    }
    
    public List<UsuarioDTO> findAllReturningDTOs(){
        List<UsuarioDTO> list = new ArrayList<>();
        repository.findAllByTpPermissao(Permissao.GERENTE).stream().map((u) -> {
            return buildDTO(u);
        }).forEach((dto) -> {
            list.add(dto);
        });
        return list;
    }
    
    public UsuarioDTO salvarUsuario(UsuarioDTO dto){
        Usuario user = dto.buildUsuario();        
        return new UsuarioDTO(repository.save(user));
    }

    public Usuario findById(long idUsuario) {
        return repository.findOne(idUsuario);
    }
    
    public UsuarioDTO findByIdReturningDTO(long idUsuario) {
        return buildDTO(repository.findOne(idUsuario));
    }
    
    private UsuarioDTO buildDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getIdUsuario());
        dto.setNome(usuario.getNmUsuario());
        dto.setEmail(usuario.getDsEmail());
        dto.setUsername(usuario.getDsUserName());
        dto.setSituacao(usuario.getDsSituacao());
        return dto;
    }
}
