/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.entity.Periodicidade;
import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.entity.Usuario;
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
    @Mock
    private Servico mockServico;
    @Mock
    private Servico mockServico2;
    @Mock
    private Usuario mockUsuario;

    @InjectMocks
    private ServicoService service;

    @Mock
    Map<Moeda, BigDecimal> medias;

    @Before
    public void setUp() {
        {// MOCK MEDIAS(MOEDA)
            doReturn(medias).when(cotacaoService).findLastAverage();
            doReturn(BigDecimal.TEN).when(medias).get(Moeda.BRL);
            doReturn(BigDecimal.ONE).when(medias).get(Moeda.USD);
        }
        {// MOCK USUARIO
            doReturn("usuario").when(mockUsuario).getNmUsuario();
            doReturn(1l).when(mockUsuario).getIdUsuario();
        }

        {// MOCK SERVICO  
            doReturn(1l).when(mockServico).getIdServico();
            doReturn("servico").when(mockServico).getNmServico();
            doReturn(mockUsuario).when(mockServico).getUsuarioIdUsuario();
            doReturn("meusite.com").when(mockServico).getDsWebsite();
            doReturn("minha Descricao").when(mockServico).getDsDescricao();
            doReturn(Moeda.USD).when(mockServico).getDsSimboloMoeda();
            doReturn(Periodicidade.MENSAL).when(mockServico).getDsPeriodicidade();
            doReturn(BigDecimal.valueOf(100)).when(mockServico).getVlTotalServico();
        }
        {//MOCK SERVICO2
            doReturn(1l).when(mockServico2).getIdServico();
            doReturn("servico2").when(mockServico2).getNmServico();
            doReturn(mockUsuario).when(mockServico2).getUsuarioIdUsuario();
            doReturn("meusite2.com").when(mockServico2).getDsWebsite();
            doReturn("minha Descricao2").when(mockServico2).getDsDescricao();
            doReturn(Moeda.USD).when(mockServico2).getDsSimboloMoeda();
            doReturn(Periodicidade.MENSAL).when(mockServico2).getDsPeriodicidade();
            doReturn(BigDecimal.valueOf(50)).when(mockServico2).getVlTotalServico();
        }
    }

    @Test
    public void testGeraDadosDashboard() {
        {
            doReturn(Collections.EMPTY_LIST).when(repository).findByDsSituacaoNot(any(Situacao.class), any(Pageable.class));
            doReturn(Collections.EMPTY_LIST).when(repository).findByDsSituacaoNot(any(Situacao.class));
            
            doReturn(Collections.EMPTY_LIST).when(repository).findByDsSituacaoNot(any(Situacao.class), any(Pageable.class));
            doReturn(Collections.EMPTY_LIST).when(repository).findByDsSituacaoNot(any(Situacao.class));
            
            assertTrue(service.geraDadosDashboard(new PageRequest(1, 1)).getServicosMesAtual().isEmpty());
            assertTrue(service.geraDadosDashboard(new PageRequest(1, 1)).getServicosProximoMes().isEmpty());
            assertEquals(BigDecimal.ZERO, service.geraDadosDashboard(new PageRequest(1, 1)).getGastoTotalAtual());
            assertEquals(BigDecimal.ZERO, service.geraDadosDashboard(new PageRequest(1, 1)).getGastoTotalProximoMes());
            assertEquals(null, service.geraDadosDashboard(new PageRequest(1, 1)).getServicoMaisCaroContratado());
        }
        
        {
            List listServico = new ArrayList();
            listServico.add(mockServico);
            listServico.add(mockServico2);
            doReturn(listServico).when(repository).findByDsSituacaoNot(any(Situacao.class), any(Pageable.class));
            doReturn(listServico).when(repository).findByDsSituacaoNot(any(Situacao.class));

            List listServico2 = new ArrayList();
            listServico2.add(mockServico);
            doReturn(listServico2).when(repository).findByDsSituacao(any(Situacao.class), any(Pageable.class));
            doReturn(listServico2).when(repository).findByDsSituacao(any(Situacao.class));

            assertEquals(2, service.geraDadosDashboard(new PageRequest(1, 1)).getServicosMesAtual().size());
            assertEquals(1, service.geraDadosDashboard(new PageRequest(1, 1)).getServicosProximoMes().size());
            assertEquals(BigDecimal.valueOf(1500).setScale(2), service.geraDadosDashboard(new PageRequest(1, 1)).getGastoTotalAtual());
            assertEquals(BigDecimal.valueOf(1000).setScale(2), service.geraDadosDashboard(new PageRequest(1, 1)).getGastoTotalProximoMes());
            assertEquals("meusite.com", service.geraDadosDashboard(new PageRequest(1, 1)).getServicoMaisCaroContratado().getDsWebsite());
        }
    }

    @Test
    public void testGetGastoTotalAtual() {
        {
            final List listServico = new ArrayList();
            listServico.add(mockServico);
            listServico.add(mockServico2);
            doReturn(listServico).when(repository).findByDsSituacaoNot(any(Situacao.class));

            assertEquals(service.getGastoTotalAtual(), BigDecimal.valueOf(1500).setScale(2));
        }
    }

    @Test
    public void testGetGastoTotalProximoMes() {
        final List listServico = new ArrayList();
        listServico.add(mockServico);

        doReturn(listServico).when(repository).findByDsSituacao(any(Situacao.class));

        assertEquals(service.getGastoTotalProximoMes(), BigDecimal.valueOf(1000).setScale(2));
    }

    @Test
    public void testGetServicosDTOMesAtualPaginados() {
        {
            doReturn(Collections.EMPTY_LIST).when(repository).findByDsSituacaoNot(any(Situacao.class), any(Pageable.class));
            assertTrue(service.getServicosDTOMesAtualPaginados(new PageRequest(1, 1)).isEmpty());
        }

        {
            List list = new ArrayList();
            list.add(mockServico);
            doReturn(list).when(repository).findByDsSituacaoNot(any(Situacao.class), any(Pageable.class));

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
            List list = new ArrayList();
            list.add(mockServico);
            doReturn(list).when(repository).findByDsSituacao(any(Situacao.class), any(Pageable.class));

            assertFalse(service.getServicosDTOProximoMesPaginados(new PageRequest(1, 1)).isEmpty());
            assertEquals(service.getServicosDTOProximoMesPaginados(new PageRequest(1, 1)).get(0).getCustoMensal(), BigDecimal.valueOf(1000).setScale(2));
        }
    }
}
