package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.entity.Usuario;
import br.com.crescer.wallet.security.extensions.UsuarioSessaoUser;
import br.com.crescer.wallet.service.service.UsuarioService;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author DOUGLAS
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @InjectMocks
    LoginController loginController;

    @Mock
    HttpSession mockHttpSession;
    
    @Mock
    UsuarioService mockUsuarioService;
    
    @Mock
    UsuarioSessaoUser mockUsuarioSessaoUser;

    MockMvc mockMvc;

    @Before
    public void setUpClass() {
        {
            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
            viewResolver.setPrefix("/templates/");
            viewResolver.setSuffix(".html");
            mockMvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(viewResolver).build();
        }
        {
        }
    }

    @Test
    public void TesttoLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void Testlogout() throws Exception {
        mockHttpSession.invalidate();
        mockMvc.perform(get("/login?logout"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}
