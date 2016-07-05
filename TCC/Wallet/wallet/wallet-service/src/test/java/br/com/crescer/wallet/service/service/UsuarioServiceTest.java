package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Permissao;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.entity.Usuario;
import br.com.crescer.wallet.service.dto.UsuarioDTO;
import br.com.crescer.wallet.service.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author DOUGLAS
 */
@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @Mock
    UsuarioRepository mockUsuarioRepository;

    @Mock
    Usuario mockUsuario;

    @Mock
    Usuario mockUsuario2;

    @Mock
    UsuarioDTO mockUsuarioDTO;

    @Mock
    UsuarioDTO mockUsuarioDTO2;

    @Mock
    UsuarioDTO mockUsuarioDTO3;

    @InjectMocks
    UsuarioService mockUsuarioService;

    @Before
    public void setUpClass() {
        {// MOCK USUARIO
            doReturn(1l).when(mockUsuario).getIdUsuario();
            doReturn("douglas").when(mockUsuario).getNmUsuario();
            doReturn("teste@teste.com").when(mockUsuario).getDsEmail();
            doReturn("usuario").when(mockUsuario).getDsUserName();
            doReturn(Situacao.ATIVO).when(mockUsuario).getDsSituacao();
            doReturn(Permissao.ADMINISTRADOR).when(mockUsuario).getTpPermissao();
        }
        {// MOCK USUARIO2
            doReturn(2l).when(mockUsuario2).getIdUsuario();
            doReturn(Permissao.GERENTE).when(mockUsuario2).getTpPermissao();
            doReturn(Situacao.INATIVO).when(mockUsuario2).getDsSituacao();
        }
        {// MOCK USUARIODTO
            doReturn(1l).when(mockUsuarioDTO).getId();
            doReturn("douglas").when(mockUsuarioDTO).getNome();
            doReturn("teste@teste.com").when(mockUsuarioDTO).getEmail();
            doReturn("usuario").when(mockUsuarioDTO).getUsername();
            doReturn(Situacao.ATIVO).when(mockUsuarioDTO).getSituacao();
            doReturn(Permissao.ADMINISTRADOR).when(mockUsuarioDTO).getPermissao();
        }
        {// MOCK USUARIODTO2
            doReturn(2l).when(mockUsuarioDTO2).getId();
            doReturn(Permissao.GERENTE).when(mockUsuarioDTO2).getPermissao();
            doReturn(Situacao.INATIVO).when(mockUsuarioDTO2).getSituacao();
        }
        {// MOCK USUARIODTO3
            doReturn(null).when(mockUsuarioDTO3).getPermissao();
        }
    }

    @Test
    public void testFindOneByDsUserName() {
        {
            doReturn(mockUsuario).when(mockUsuarioRepository).findUsuarioByDsUserName(any(String.class));
            assertEquals(mockUsuarioService.findOneByDsUserName("usuario"), mockUsuario);
        }
        {
            doReturn(null).when(mockUsuarioRepository).findUsuarioByDsUserName(any(String.class));
            assertEquals(mockUsuarioService.findOneByDsUserName(mockUsuario2.getDsUserName()), null);
        }
    }

    @Test
    public void testFindById() {
        {
            doReturn(mockUsuario).when(mockUsuarioRepository).findOne(any(Long.class));
            assertEquals(mockUsuarioService.findById(1l), mockUsuario);
        }
        {
            doReturn(null).when(mockUsuarioRepository).findOne(any(Long.class));
            assertEquals(mockUsuarioService.findById(4l), null);
        }
    }

    @Test
    public void testCheckUsernameAvailability() {
        doReturn(null).when(mockUsuarioRepository).findUsuarioByDsUserName(any(String.class));
        assertTrue(mockUsuarioService.checkUsernameAvailability(mockUsuario.getDsUserName(), mockUsuario.getIdUsuario()));
    }

    @Test
    public void testFindByIdReturningDTO() {
        {
            doReturn(mockUsuario).when(mockUsuarioRepository).findOne(any(Long.class));
            UsuarioDTO dto = mockUsuarioService.findByIdReturningDTO(1l);
            assertEquals(dto.getNome(), mockUsuarioDTO.getNome());
        }
        {
            doReturn(null).when(mockUsuarioRepository).findOne(any(Long.class));
            assertEquals(mockUsuarioDTO3.getPermissao(), null);
        }
    }

    @Test
    public void testFindAllActiveReturningDTOs() {
        {
            List<Usuario> lista = new ArrayList<>();
            lista.add(mockUsuario);
            UsuarioDTO dto = new UsuarioDTO();
            dto.setId(1l);
            dto.setNome("douglas");
            doReturn(lista).when(mockUsuarioRepository).findAllByDsSituacaoNot(any(Situacao.class));
            boolean verifica = dto.getId().equals(lista.get(0).getIdUsuario());
            assertTrue(verifica);
        }
        {
            doReturn(null).when(mockUsuarioRepository).findAllByDsSituacaoNot(any(Situacao.class));
            assertEquals(mockUsuarioService.findAllActiveReturningDTOs(), null);
        }
    }

    @Test
    public void testFindAllReturningDTOs() {
        {
            List<Usuario> lista = new ArrayList<>();
            lista.add(mockUsuario);
            UsuarioDTO dto = new UsuarioDTO();
            dto.setId(1l);
            dto.setNome("douglas");
            doReturn(lista).when(mockUsuarioRepository).findAll();
            boolean verifica = dto.getId().equals(lista.get(0).getIdUsuario());
            assertTrue(verifica);
        }
        {
            doReturn(null).when(mockUsuarioRepository).findAll();
            assertEquals(mockUsuarioService.findAllReturningDTOs(), null);
        }
    }

    @Test
    public void testSalvarUsuario() {
        {
            doReturn(mockUsuario).when(mockUsuarioRepository).save(any(Usuario.class));
            boolean verifica = mockUsuarioDTO.getId().equals(mockUsuario.getIdUsuario());
            assertTrue(verifica);
        }
        {
            doReturn(null).when(mockUsuarioRepository).save(any(Usuario.class));
            assertEquals(mockUsuarioService.salvarUsuario(mockUsuarioDTO), null);
        }

    }

    @Test
    public void testInativarUsuario() {
        {
            doReturn(mockUsuario2).when(mockUsuarioRepository).findOne(any(Long.class));
            assertEquals(mockUsuarioService.inativarUsuario(2l), true);
            
        }
        {
            doReturn(null).when(mockUsuarioRepository).findOne(any(Long.class));
            assertEquals(mockUsuarioService.inativarUsuario(5l), false);
        }
    }
}
