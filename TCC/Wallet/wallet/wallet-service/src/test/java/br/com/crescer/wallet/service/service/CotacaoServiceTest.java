package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Cotacao;
import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.service.repository.CotacaoRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Hedo
 */
@RunWith(MockitoJUnitRunner.class)
public class CotacaoServiceTest {

    public CotacaoServiceTest() {
    }

    @Mock
    private Cotacao mockCotacao;

    @Mock
    private Cotacao mockCotacao1;

    @Mock
    private Cotacao mockCotacao2;

    @Mock
    private CotacaoRepository repository;

    @InjectMocks
    private CotacaoService service;

    @Before
    public void setUp() {
        //cotacoes
        doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoReal();

        //cotacao
        doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoReal();
        doReturn(BigDecimal.TEN).when(mockCotacao1).getDsCotacaoReal();
        doReturn(BigDecimal.TEN).when(mockCotacao2).getDsCotacaoReal();
    }

    /**
     * Test of findLastExchangeRate method, of class CotacaoService.
     */
    @Test
    public void testFindLastExchangeRate() {
        
        doReturn(mockCotacao).when(repository).findFirstByDtCotacaoOrderByIdCotacaoDesc(any(LocalDate.class));
        
        assertFalse(service.findLastExchangeRate() == null);
        assertEquals(BigDecimal.TEN, service.findLastExchangeRate().getDsCotacaoReal());
    }

    /**
     * Test of findLastCurrencyAverage method, of class CotacaoService.
     */
    @Test
    public void testFindLastCurrencyAverage() {
        List listaCotacoes = new ArrayList<>();

        listaCotacoes.add(mockCotacao);
        listaCotacoes.add(mockCotacao1);
        listaCotacoes.add(mockCotacao2);

        doReturn(listaCotacoes).when(repository).findByDtCotacaoBetween(any(LocalDate.class), any(LocalDate.class));

        assertNotNull(service.findLastCurrencyAverage(Moeda.BRL));
    }

    /**
     * Test of findLastAverage method, of class CotacaoService.
     */
    @Test
    public void testFindLastAverage() {
        {
            List listaCotacoes = new ArrayList<>();
            
            doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoDollarAutraliano();
            doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoDollarCanadense();
            doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoEuro();
            doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoFrancoSuico();
            doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoLibra();
            doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoReal();
            doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoYen();
            doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoYuan();
            
            listaCotacoes.add(mockCotacao);
            
            doReturn(listaCotacoes).when(repository).findByDtCotacaoBetween(any(LocalDate.class), any(LocalDate.class));

            assertNotNull(service.findLastAverage());
        }
    }
}
