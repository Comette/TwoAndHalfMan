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

    
    @Test
    public void testGeraDadosDashboard() {
    }

    
    @Test
    public void testGetGastoTotalAtual() {
    }

    @Test
    public void testGetGastoTotalProximoMes() {
    }

    @Test
    public void testGetServicosDTOMesAtualPaginados() {
        {
            doReturn(Collections.EMPTY_LIST).when(repository).findByDsSituacaoNot(any(Situacao.class), any(Pageable.class));
            assertTrue(service.getServicosDTOMesAtualPaginados(new PageRequest(1, 1)).isEmpty());
        }

        {
            final List list = new ArrayList();
            doReturn(list).when(repository).findByDsSituacao(any(Situacao.class), any(Pageable.class));

            final Servico mock = mock(Servico.class);
            list.add(mock); 
            {
                doReturn(Moeda.USD).when(mock).getDsSimboloMoeda();
                doReturn(Periodicidade.MENSAL).when(mock).getDsPeriodicidade();
                doReturn(BigDecimal.TEN.multiply(BigDecimal.TEN)).when(mock).getVlTotalServico();
            }

            assertFalse(service.getServicosDTOMesAtualPaginados(new PageRequest(1, 1)).isEmpty());
            assertEquals(service.getServicosDTOMesAtualPaginados(new PageRequest(1, 1)).get(0).getCustoMensal(), BigDecimal.valueOf(1000).setScale(2));
        }
        
    }

    
    @Test
    public void testGetServicosDTOProximoMesPaginados() {
        {
            doReturn(Collections.EMPTY_LIST).when(repository).findByDsSituacao(any(Situacao.class), any(Pageable.class));
            assertTrue(service.getServicosDTOProximoMesPaginados(new PageRequest(1, 1)).isEmpty());
        }

        {
            final List list = new ArrayList();
            doReturn(list).when(repository).findByDsSituacao(any(Situacao.class), any(Pageable.class));

            final Servico mock = mock(Servico.class);
            list.add(mock); 
            {
                doReturn(Moeda.USD).when(mock).getDsSimboloMoeda();
                doReturn(Periodicidade.MENSAL).when(mock).getDsPeriodicidade();
                doReturn(BigDecimal.TEN.multiply(BigDecimal.TEN)).when(mock).getVlTotalServico();
            }

            assertFalse(service.getServicosDTOProximoMesPaginados(new PageRequest(1, 1)).isEmpty());
            assertEquals(service.getServicosDTOProximoMesPaginados(new PageRequest(1, 1)).get(0).getCustoMensal(), BigDecimal.valueOf(1000).setScale(2));
        }
    }
}
