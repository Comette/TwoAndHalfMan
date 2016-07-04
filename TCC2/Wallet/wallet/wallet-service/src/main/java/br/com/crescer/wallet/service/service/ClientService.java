package br.com.crescer.wallet.service.service;
import br.com.crescer.wallet.entity.util.Permission;
import br.com.crescer.wallet.entity.util.State;
import br.com.crescer.wallet.entity.entity.Client;
import br.com.crescer.wallet.service.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.crescer.wallet.service.repository.ClientRepository;

/**
 *
 * @author Hedo
 */

@Service
public class ClientService {
    
    @Autowired
    ClientRepository repository;
    
    public Client findOneByDsUserName(String dsUserName){
        return repository.findClientByDsUserName(dsUserName);
    }
    
    public List<UsuarioDTO> findAllActiveReturningDTOs(){
        List<UsuarioDTO> list = new ArrayList<>();
        repository.findAllByTpPermissionAndDsStateNot(Permission.MANAGER, State.INACTIVE).stream().map((u) -> {
            return buildDTO(u);
        }).forEach((dto) -> {
            list.add(dto);
        });
        return list;
    }
    
    public List<UsuarioDTO> findAllReturningDTOs(){
        List<UsuarioDTO> list = new ArrayList<>();
        repository.findAllByTpPermission(Permission.MANAGER).stream().map((u) -> {
            return buildDTO(u);
        }).forEach((dto) -> {
            list.add(dto);
        });
        return list;
    }
    
    public UsuarioDTO salvarUsuario(UsuarioDTO dto){
        Client user = dto.buildUsuario();        
        return new UsuarioDTO(repository.save(user));
    }

    public Client findById(long idUsuario) {
        return repository.findOne(idUsuario);
    }
    
    public UsuarioDTO findByIdReturningDTO(long idUsuario) {
        return buildDTO(repository.findOne(idUsuario));
    }
    
    private UsuarioDTO buildDTO(Client usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getIdClient());
        dto.setNome(usuario.getNmClient());
        dto.setEmail(usuario.getDsEmail());
        dto.setUsername(usuario.getDsUserName());
        dto.setSituacao(usuario.getDsState());
        return dto;
    }

    public boolean checkUsernameAvailability(String username) {
        return repository.findClientByDsUserName(username) == null;
    }
}
