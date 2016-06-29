/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.DashboardDTO;
import br.com.crescer.wallet.service.service.ServicoService;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
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

    @Before
    public void setUp() {
        Mockito.doReturn(dashboardDTO).when(service).geraDadosDashboard(Mockito.any(Pageable.class));
        Mockito.doReturn(BigDecimal.valueOf(100)).when(dashboardDTO).getGastoTotalAtual();
    }

    /**
     * Test of dashboard method, of class ServicoController.
     */
    @Test
    public void testDashboard() {
        DashboardDTO dashboard = controller.dashboard(new PageRequest(1, 1));
        Assert.assertNotNull(dashboardDTO);
        Assert.assertEquals(dashboardDTO.getGastoTotalAtual(), BigDecimal.valueOf(100));
    }

    /**
     * Test of gastoTotalAtual method, of class ServicoController.
     */
    @Test
    public void testGastoTotalAtual() {
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
