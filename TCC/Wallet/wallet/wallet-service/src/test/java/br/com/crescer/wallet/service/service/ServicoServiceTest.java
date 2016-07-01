/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.entity.Periodicidade;
import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.service.repository.ServicoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * @author victor.ribeiro
 */
@RunWith(MockitoJUnitRunner.class)
public class ServicoServiceTest {

    @Mock
    private ServicoRepository repository;
    @Mock
    private CotacaoService cotacaoService;

    @InjectMocks
    private ServicoService service;

    @Mock
    Map<Moeda, BigDecimal> medias;

    @Before
    public void setUp() {
        {
            doReturn(medias).when(cotacaoService).findLastAverage();
            doReturn(BigDecimal.TEN).when(medias).get(Moeda.BRL);
            doReturn(BigDecimal.ONE).when(medias).get(Moeda.USD);
        }
    }

    /**
     * Test of geraDadosDashboard method, of class ServicoService.
     */
    @Test
    public void testGeraDadosDashboard() {
    }

    /**
     * Test of getGastoTotalAtual method, of class ServicoService.
     */
    @Test
    public void testGetGastoTotalAtual() {
    }

    /**
     * Test of getGastoTotalProximoMes method, of class ServicoService.
     */
    @Test
    public void testGetGastoTotalProximoMes() {
    }

    /**
     * Test of getServicosDTOMesAtualPaginados method, of class ServicoService.
     */
//    @Test
//    public void testGetServicosDTOMesAtualPaginados() {
//    }
//
//    /**
//     * Test of getServicosDTOProximoMesPaginados method, of class
//     * ServicoService.
//     */
//    @Test
//    public void testGetServicosDTOProximoMesPaginados() {
//        {
//            doReturn(Collections.EMPTY_LIST).when(repository).findByDsSituacao(any(Situacao.class), any(Pageable.class));
//            assertTrue("A lista não pode ter resultado", service.getServicosDTOProximoMesPaginados(new PageRequest(1, 1)).isEmpty());
//        }
//
//        {
//            final List list = new ArrayList();
//            doReturn(list).when(repository).findByDsSituacao(any(Situacao.class), any(Pageable.class));
//
//            final Servico mock = mock(Servico.class);
//            list.add(mock); 
//            {
//                doReturn(Moeda.USD).when(mock).getDsSimboloMoeda();
//                doReturn(Periodicidade.MENSAL).when(mock).getDsPeriodicidade();
//                doReturn(BigDecimal.TEN.multiply(BigDecimal.TEN)).when(mock).getVlTotalServico();
//            }
//
//            assertFalse("A lista não pode nula", service.getServicosDTOProximoMesPaginados(new PageRequest(1, 1)).isEmpty());
//            assertEquals("O custo deve ser 1k", service.getServicosDTOProximoMesPaginados(new PageRequest(1, 1)).get(0).getCustoMensal(), BigDecimal.valueOf(1000).setScale(2));
//        }
//    }

}
