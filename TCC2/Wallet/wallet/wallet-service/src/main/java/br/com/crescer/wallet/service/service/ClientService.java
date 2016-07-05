package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.util.Permission;
import static br.com.crescer.wallet.entity.util.State.*;
import br.com.crescer.wallet.entity.entity.Client;
import br.com.crescer.wallet.entity.util.Coin;
import br.com.crescer.wallet.service.dto.ClientDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.crescer.wallet.service.repository.ClientRepository;
import org.apache.log4j.Logger;

/**
 *
 * @author Hedo
 */
@Service
public class ClientService {

    private static final Logger LOG = Logger.getLogger(ClientService.class.getName());

    @Autowired
    ClientRepository repository;

    public Client findOneByDsUserName(String dsUserName) {
        return repository.findClientByDsUserName(dsUserName);
    }

    public List<ClientDTO> findAllActiveReturningDTOs() {
        List<ClientDTO> list = new ArrayList<>();
        try {
            repository.findAllByDsStateNot(INACTIVE).stream().forEach((client) -> {
                list.add(this.buildDTO(client));
            });
            return list;
        } catch (NullPointerException e) {
            LOG.info(e.getMessage());
            return null;
        }
    }

    public List<ClientDTO> findAllReturningDTOs() {
        try {
            List<ClientDTO> list = new ArrayList<>();
            repository.findAll().stream().forEach((client) -> {
                list.add(this.buildDTO(client));
            });
            return list;
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());
            return null;
        }
    }

    public ClientDTO saveClient(ClientDTO dto) {
        try {
            if (dto.getId() != null && dto.getId() > 0) {
                Client found = repository.findOne(dto.getId());
                found.setDsEmail(dto.getEmail());
                found.setDsUserName(dto.getUsername());
                found.setNmClient(dto.getName());
                found.setTpPermission(dto.getPermission());
                found.setDsPreferredCoin(dto.getPreferredCoin() != null ? dto.getPreferredCoin() : Coin.BRL);
                return new ClientDTO(repository.save(found));
            }
            Client client = dto.buildClient();
            return new ClientDTO(repository.save(client));
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());
            return null;
        }
    }

    public boolean deactivateClient(Long idClient) {
        try {
            Client client = repository.findOne(idClient);
            client.setDsState(INACTIVE);
            repository.save(client);
            return true;
        } catch (Exception e) {
            //TODO: EXCEPTION
            LOG.error(e);
            return false;
        }
    }

    public Client findById(long idClient) {
        return repository.findOne(idClient);
    }

    public ClientDTO findByIdReturningDTO(long idClient) {
        return buildDTO(repository.findOne(idClient));
    }

    private ClientDTO buildDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getIdClient());
        dto.setName(client.getNmClient());
        dto.setEmail(client.getDsEmail());
        dto.setUsername(client.getDsUserName());
        dto.setState(client.getDsState());
        dto.setPermission(client.getTpPermission());
        dto.setPreferredCoin(client.getDsPreferredCoin());
        return dto;
    }

    public boolean checkUsernameAvailability(String username, long id) {
        Client client = repository.findOne(id);
        if (client == null) {
            return repository.findClientByDsUserName(username) == null;
        } else {
            return client.getDsUserName().equals(username) ? true : repository.findClientByDsUserName(username) == null;
        }
    }
}
