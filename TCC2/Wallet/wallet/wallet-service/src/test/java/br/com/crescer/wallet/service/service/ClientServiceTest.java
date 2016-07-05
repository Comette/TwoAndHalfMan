package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.entity.Client;
import br.com.crescer.wallet.entity.util.Permission;
import br.com.crescer.wallet.entity.util.State;
import br.com.crescer.wallet.service.dto.ClientDTO;
import br.com.crescer.wallet.service.repository.ClientRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DOUGLAS
 */

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
    
    @Mock
    ClientRepository mockClientRepository;

    @Mock
    Client mockClient;

    @Mock
    Client mockClient2;

    @Mock
    ClientDTO mockClientDTO;

    @Mock
    ClientDTO mockClientDTO2;

    @Mock
    ClientDTO mockClientDTO3;

    @InjectMocks
    ClientService mockClientService;
    
    @Before
    public void setUpClass() {
        {// MOCK CLIENT
            doReturn(1l).when(mockClient).getIdClient();
            doReturn("douglas").when(mockClient).getNmClient();
            doReturn("teste@teste.com").when(mockClient).getDsEmail();
            doReturn("usuario").when(mockClient).getDsUserName();
            doReturn(State.ACTIVE).when(mockClient).getDsState();
            doReturn(Permission.ADMINISTRATOR).when(mockClient).getTpPermission();
        }
        {// MOCK CLIENT
            doReturn(2l).when(mockClient2).getIdClient();
            doReturn(Permission.MANAGER).when(mockClient2).getTpPermission();
            doReturn(State.INACTIVE).when(mockClient2).getDsState();
        }
        {// MOCK CLIENTDTO
            doReturn(1l).when(mockClientDTO).getId();
            doReturn("douglas").when(mockClientDTO).getName();
            doReturn("teste@teste.com").when(mockClientDTO).getEmail();
            doReturn("usuario").when(mockClientDTO).getUsername();
            doReturn(State.ACTIVE).when(mockClientDTO).getState();
            doReturn(Permission.ADMINISTRATOR).when(mockClientDTO).getPermission();
        }
        {// MOCK CLIENTDTO2
            doReturn(2l).when(mockClientDTO2).getId();
            doReturn(Permission.MANAGER).when(mockClientDTO2).getPermission();
            doReturn(State.INACTIVE).when(mockClientDTO2).getState();
        }
        {// MOCK CLIENTDTO3
            doReturn(null).when(mockClientDTO3).getPermission();
        }
    }

    @Test
    public void testFindOneByDsUserName() {
        {
            doReturn(mockClient).when(mockClientRepository).findClientByDsUserName(any(String.class));
            assertEquals(mockClientService.findOneByDsUserName("usuario"), mockClient);
        }
        {
            doReturn(null).when(mockClientRepository).findClientByDsUserName(any(String.class));
            assertEquals(mockClientService.findOneByDsUserName(mockClient2.getDsUserName()), null);
        }
    }

    @Test
    public void testFindById() {
        {
            doReturn(mockClient).when(mockClientRepository).findOne(any(Long.class));
            assertEquals(mockClientService.findById(1l), mockClient);
        }
        {
            doReturn(null).when(mockClientRepository).findOne(any(Long.class));
            assertEquals(mockClientService.findById(4l), null);
        }
    }

    @Test
    public void testCheckUsernameAvailability() {
        doReturn(null).when(mockClientRepository).findClientByDsUserName(any(String.class));
        assertTrue(mockClientService.checkUsernameAvailability(mockClient.getDsUserName(), mockClient.getIdClient()));
    }

    @Test
    public void testFindByIdReturningDTO() {
        {
            doReturn(mockClient).when(mockClientRepository).findOne(any(Long.class));
            ClientDTO dto = mockClientService.findByIdReturningDTO(1l);
            assertEquals(dto.getName(), mockClientDTO.getName());
        }
        {
            doReturn(null).when(mockClientRepository).findOne(any(Long.class));
            assertEquals(mockClientDTO3.getPermission(), null);
        }
    }

    @Test
    public void testFindAllActiveReturningDTOs() {
        {
            List<Client> lista = new ArrayList<>();
            lista.add(mockClient);
            ClientDTO dto = new ClientDTO();
            dto.setId(1l);
            dto.setName("douglas");
            doReturn(lista).when(mockClientRepository).findAllByDsStateNot(any(State.class));
            boolean verifica = dto.getId().equals(lista.get(0).getIdClient());
            assertTrue(verifica);
        }
        {
            doReturn(null).when(mockClientRepository).findAllByDsStateNot(any(State.class));
            assertEquals(mockClientService.findAllActiveReturningDTOs(), null);
        }
    }

    @Test
    public void testFindAllReturningDTOs() {
        {
            ArrayList<Client> lista = new ArrayList<>();
            lista.add(mockClient);
            ClientDTO dto = new ClientDTO();
            dto.setId(1l);
            dto.setName("douglas");
            doReturn(lista).when(mockClientRepository).findAll();
            boolean verifica = dto.getId().equals(lista.get(0).getIdClient());
            assertTrue(verifica);
        }
        {
            doReturn(null).when(mockClientRepository).findAll();
            assertEquals(mockClientService.findAllReturningDTOs(), null);
        }
    }
    
    @Test
    public void testSaveClient() {
        {
            doReturn(mockClient).when(mockClientRepository).save(any(Client.class));
            boolean verifica = mockClientDTO.getId().equals(mockClient.getIdClient());
            assertTrue(verifica);
        }
        {
            doReturn(null).when(mockClientRepository).save(any(Client.class));
            assertEquals(mockClientService.saveClient(mockClientDTO), null);
        }

    }

    @Test
    public void testDeactivateClient() {
        {
            doReturn(mockClient2).when(mockClientRepository).findOne(any(Long.class));
            assertEquals(mockClientService.deactivateClient(2l), true);            
        }
        {
            doReturn(null).when(mockClientRepository).findOne(any(Long.class));
            assertEquals(mockClientService.deactivateClient(5l), false);
        }
    }
    
}
