package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Cotacao;
import br.com.crescer.wallet.entity.Moeda;
import static br.com.crescer.wallet.entity.Moeda.BRL;
import br.com.crescer.wallet.service.repository.CotacaoRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
        assertEquals(BigDecimal.TEN, service.findLastExchangeRate().get(BRL));
    }

    /**
     * Test of findLastCurrencyAverage method, of class CotacaoService.
     */
    @Test
    public void testFindLastCurrencyAverage() {
        List listaCotacoes = new ArrayList<>();

        doReturn(BigDecimal.valueOf(7).setScale(6,RoundingMode.HALF_UP)).when(mockCotacao).getDsCotacaoDollarAutraliano();
        doReturn(BigDecimal.valueOf(8).setScale(6,RoundingMode.HALF_UP)).when(mockCotacao).getDsCotacaoDollarCanadense();
        doReturn(BigDecimal.valueOf(9).setScale(6,RoundingMode.HALF_UP)).when(mockCotacao).getDsCotacaoEuro();
        doReturn(BigDecimal.valueOf(7).setScale(6,RoundingMode.HALF_UP)).when(mockCotacao1).getDsCotacaoDollarAutraliano();
        doReturn(BigDecimal.valueOf(8).setScale(6,RoundingMode.HALF_UP)).when(mockCotacao1).getDsCotacaoDollarCanadense();
        doReturn(BigDecimal.valueOf(9).setScale(6,RoundingMode.HALF_UP)).when(mockCotacao1).getDsCotacaoEuro();

        listaCotacoes.add(mockCotacao);
        listaCotacoes.add(mockCotacao1);

        doReturn(listaCotacoes).when(repository).findByDtCotacaoBetween(any(LocalDate.class), any(LocalDate.class));

        assertEquals(BigDecimal.valueOf(7).setScale(6,RoundingMode.HALF_UP), service.findLastCurrencyAverage(Moeda.AUD));
        assertEquals(BigDecimal.valueOf(8).setScale(6,RoundingMode.HALF_UP), service.findLastCurrencyAverage(Moeda.CAD));
        assertEquals(BigDecimal.valueOf(9).setScale(6,RoundingMode.HALF_UP), service.findLastCurrencyAverage(Moeda.EUR));
    }

    /**
     * Test of findLastAverage method, of class CotacaoService.
     */
    @Test
    public void testFindLastAverage() {
        {
            List listaCotacoes = new ArrayList<>();

            {
                doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoDollarAutraliano();
                doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoDollarCanadense();
                doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoEuro();
                doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoFrancoSuico();
                doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoLibra();
                doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoReal();
                doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoYen();
                doReturn(BigDecimal.TEN).when(mockCotacao).getDsCotacaoYuan();
                doReturn(BigDecimal.valueOf(5)).when(mockCotacao1).getDsCotacaoDollarAutraliano();
                doReturn(BigDecimal.valueOf(5)).when(mockCotacao1).getDsCotacaoDollarCanadense();
                doReturn(BigDecimal.valueOf(5)).when(mockCotacao1).getDsCotacaoEuro();
                doReturn(BigDecimal.valueOf(5)).when(mockCotacao1).getDsCotacaoFrancoSuico();
                doReturn(BigDecimal.valueOf(12)).when(mockCotacao1).getDsCotacaoLibra();
                doReturn(BigDecimal.valueOf(5)).when(mockCotacao1).getDsCotacaoReal();
                doReturn(BigDecimal.valueOf(10)).when(mockCotacao1).getDsCotacaoYen();
                doReturn(BigDecimal.valueOf(5)).when(mockCotacao1).getDsCotacaoYuan();

                listaCotacoes.add(mockCotacao);
                listaCotacoes.add(mockCotacao1);

                doReturn(listaCotacoes).when(repository).findByDtCotacaoBetween(any(LocalDate.class), any(LocalDate.class));

                assertNotNull(service.findLastAverage());
                assertEquals(BigDecimal.valueOf(7.5).setScale(6, RoundingMode.HALF_UP), service.findLastAverage().get(Moeda.BRL));
                assertEquals(BigDecimal.valueOf(11).setScale(6, RoundingMode.HALF_UP), service.findLastAverage().get(Moeda.GBP));
                assertEquals(BigDecimal.valueOf(10).setScale(6, RoundingMode.HALF_UP), service.findLastAverage().get(Moeda.JPY));
            }

        }
    }
}
