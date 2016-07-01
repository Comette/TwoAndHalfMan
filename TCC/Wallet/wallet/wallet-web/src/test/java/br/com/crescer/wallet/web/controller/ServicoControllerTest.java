/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.DashboardDTO;
import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.service.ServicoService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author victor.ribeiro
 */
@RunWith(MockitoJUnitRunner.class)
public class ServicoControllerTest {

    @Mock
    private ServicoService service;

    @InjectMocks
    private ServicoController controller;

    @Mock
    private DashboardDTO dashboardDTO;
    
    @Mock
    private ServicoDTO servicoDTO;

    @Mock
    private List<ServicoDTO> servicosDTO;

    @Before
    public void setUp() {
        Mockito.doReturn(dashboardDTO).when(service).geraDadosDashboard(Mockito.any(Pageable.class));
        Mockito.doReturn(BigDecimal.valueOf(100)).when(dashboardDTO).getGastoTotalAtual();
        Mockito.doReturn(BigDecimal.valueOf(100)).when(dashboardDTO).getGastoTotalProximoMes();
        Mockito.doReturn(servicosDTO).when(dashboardDTO).getServicosMesAtual();
        Mockito.doReturn(servicosDTO).when(dashboardDTO).getServicosProximoMes();
        Mockito.doReturn(servicoDTO).when(dashboardDTO).getServicoMaisCaroContratado();
        
        
        Mockito.doReturn(BigDecimal.valueOf(100)).when(service).getGastoTotalAtual();
        Mockito.doReturn(BigDecimal.valueOf(100)).when(service).getGastoTotalProximoMes();
    }

    /**
     * Test of dashboard method, of class ServicoController.
     */
    @Test
    public void testDashboard() {
        DashboardDTO dashboard = controller.dashboard(new PageRequest(1, 1));
        Assert.assertNotNull(dashboard);
        Assert.assertEquals(dashboard.getGastoTotalAtual(), BigDecimal.valueOf(100));
        Assert.assertEquals(dashboard.getGastoTotalProximoMes(), BigDecimal.valueOf(100));
        Assert.assertEquals(dashboard.getServicosMesAtual(), servicosDTO);
        Assert.assertEquals(dashboard.getServicosProximoMes(), servicosDTO);
        Assert.assertEquals(dashboard.getServicoMaisCaroContratado(), servicoDTO);
    }

    /**
     * Test of gastoTotalAtual method, of class ServicoController.
     */
    @Test
    public void testGastoTotalAtual() {
        BigDecimal valor = service.getGastoTotalAtual();
        Assert.assertEquals(valor,BigDecimal.valueOf(100));
        
    }

    /**
     * Test of gastoTotalProximoMes method, of class ServicoController.
     */
    @Test
    public void testGastoTotalProximoMes() {
        
    }

    /**
     * Test of servicosMesAtual method, of class ServicoController.
     */
    @Test
    public void testServicosMesAtual() {
    }

    /**
     * Test of servicosProximosMes method, of class ServicoController.
     */
    @Test
    public void testServicosProximosMes() {
    }

}
