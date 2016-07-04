package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.entity.Permissao;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.service.dto.UsuarioDTO;
import br.com.crescer.wallet.service.service.UsuarioService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 *
 * @author DOUGLAS
 */
@RunWith(MockitoJUnitRunner.class)
public class UsuarioControllerTest {

    @Mock
    UsuarioService mockUsuarioServico;

    @Mock
    UsuarioDTO mockUsuarioDTO;

    @InjectMocks
    UsuarioController usuarioController;

    MockMvc mockMvc;

    @Before
    public void setUpClass() {

        {// MOCK USUARIODTO Gerente
            doReturn(3l).when(mockUsuarioDTO).getId();
            doReturn("Gerente gerente").when(mockUsuarioDTO).getNome();
            doReturn("douglas").when(mockUsuarioDTO).getUsername();
            doReturn(Permissao.GERENTE).when(mockUsuarioDTO).getPermissao();
            doReturn(Situacao.ATIVO).when(mockUsuarioDTO).getSituacao();
        }

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testGetUsuariosQualquerStatus() throws Exception {
        List<UsuarioDTO> lista = new ArrayList<>();
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(1L);
        dto.setNome("teste");
        lista.add(dto);
        when(mockUsuarioServico.findAllReturningDTOs()).thenReturn(lista);
        mockMvc.perform(get("/buscar-todos-usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nome", is("teste")));
    }

    @Test
    public void testGetUsuariosAtivos() throws Exception {
        List<UsuarioDTO> lista = new ArrayList<>();
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDTO dto2 = new UsuarioDTO();
        dto.setId(1L);
        dto.setNome("teste");
        dto.setSituacao(Situacao.ATIVO);

        dto2.setId(2L);
        dto2.setNome("douglas");
        dto2.setSituacao(Situacao.ATIVO);
        lista.add(dto);
        lista.add(dto2);

        when(mockUsuarioServico.findAllActiveReturningDTOs()).thenReturn(lista);
        mockMvc.perform(get("/buscar-usuarios-ativos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].nome", is("teste")))
                .andExpect(jsonPath("$[0].situacao", is("ATIVO")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].nome", is("douglas")))
                .andExpect(jsonPath("$[1].situacao", is("ATIVO")));

    }

    @Test
    public void testListUsuarios() throws Exception {
        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(view().name("usuarios"));
    }

    @Test
    public void testGetUsuario() throws Exception {
        
        doReturn(mockUsuarioDTO).when(mockUsuarioServico).findByIdReturningDTO(any(Long.class));
        mockMvc.perform(get("/usuario").param("idUsuario", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("usuario"));
    }

    @Test
    public void testSalvarUsuario() {
 
    }

    @Test
    public void testInativarUsuario() {
    }

    @Test
    public void testEditarUsuario() {
    }

    @Test
    public void testCheckUsername() {
    }

}
