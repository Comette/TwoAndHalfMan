// Error reading included file Templates/Classes/Templates/Licenses/license-wallet-br.txt
package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Moeda;
import static br.com.crescer.wallet.entity.Moeda.BRL;
import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.service.dto.DashboardDTO;
import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.repository.ServicoRepository;
import static br.com.crescer.wallet.service.service.CalculationUtils.CALC_SCALE;
import static br.com.crescer.wallet.service.service.CalculationUtils.PRES_SCALE;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.math.RoundingMode.HALF_UP;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author victo
 */
@Service
public class ServicoService {

    @Autowired
    ServicoRepository repository;

    @Autowired
    CotacaoService cotacaoService;

    public DashboardDTO geraDadosDashboard(Pageable pageable) {
        List<ServicoDTO> servicosDTOMesAtualPaginados = this.getServicosDTOMesAtualPaginados(pageable);
        List<ServicoDTO> servicosDTOProximoMesPaginados = this.getServicosDTOProximoMesPaginados(pageable);

        BigDecimal gastoTotalAtual = this.getGastoTotalAtual();
        servicosDTOMesAtualPaginados.stream().forEach((servico) -> {
            servico.setPorcentagemCustoTotal(gastoTotalAtual);
        });

        BigDecimal gastoTotalProximoMes = this.getGastoTotalProximoMes();
        servicosDTOProximoMesPaginados.stream().forEach((servico) -> {
            servico.setPorcentagemCustoTotal(gastoTotalProximoMes);
        });

        return new DashboardDTO(servicosDTOMesAtualPaginados, servicosDTOProximoMesPaginados, gastoTotalAtual, gastoTotalProximoMes);
    }

    public BigDecimal getGastoTotalAtual() {
        List<ServicoDTO> servicosDTOMesAtual = this.getServicosDTO(this.servicosMesAtual());
        BigDecimal gastoTotalAtual = this.calculaGastoMensal(servicosDTOMesAtual);
        return gastoTotalAtual;
    }

    public BigDecimal getGastoTotalProximoMes() {
        List<ServicoDTO> servicosDTOProximoMes = this.getServicosDTO(this.servicosProximoMes());
        BigDecimal gastoTotalProximoMes = this.calculaGastoMensal(servicosDTOProximoMes);
        return gastoTotalProximoMes;
    }

    public List<ServicoDTO> getServicosDTOMesAtualPaginados(Pageable pageable) {
        return this.getServicosDTO(servicosMesAtualPaginados(pageable));
    }

    public List<ServicoDTO> getTodosServicosDTOMesAtual() {
        return this.getServicosDTO(this.servicosMesAtual());
    }
    
    public List<ServicoDTO> getTodosServicosDTOProximoMes(){
        return this.getServicosDTO(this.servicosProximoMes());
    }

    public List<ServicoDTO> getServicosDTOProximoMesPaginados(Pageable pageable) {
        return this.getServicosDTO(this.servicosProximoMesPaginados(pageable));
    }

    private List<Servico> servicosMesAtual() {
        return repository.findByDsSituacaoNot(Situacao.INATIVO);
    }

    private List<Servico> servicosMesAtualPaginados(Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), 4, Sort.Direction.DESC, "vlServicoUSD");
        return repository.findByDsSituacaoNot(Situacao.INATIVO, pageable);
    }

    private List<Servico> servicosProximoMes() {
        return repository.findByDsSituacao(Situacao.ATIVO);
    }

    private List<Servico> servicosProximoMesPaginados(Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), 4, Sort.Direction.DESC, "vlServicoUSD");
        return repository.findByDsSituacao(Situacao.ATIVO, pageable);
    }

    private BigDecimal calculaGastoMensal(List<ServicoDTO> servicosDTO) {
        BigDecimal gastoTotal = BigDecimal.ZERO;
        for (ServicoDTO servico : servicosDTO) {
            gastoTotal = gastoTotal.add(servico.getCustoMensal()).setScale(2, RoundingMode.HALF_UP);
        }
        return gastoTotal;
    }

    private List<ServicoDTO> getServicosDTO(List<Servico> servicos) {
        List<ServicoDTO> servicosDTO = new ArrayList<>();
        servicos.stream().forEach((servico) -> {
            servicosDTO.add(this.buildDTO(servico));
        });
        return servicosDTO;
    }   

    private ServicoDTO buildDTO(Servico servico) {
        final BigDecimal vlrCusto;
        {
            final Map<Moeda, BigDecimal> medias = cotacaoService.findLastAverage();
            final BigDecimal periodicidade = BigDecimal.valueOf(servico.getDsPeriodicidade().getNumeral());
            final BigDecimal media = medias.get(servico.getDsSimboloMoeda());
            final BigDecimal taxa = medias.get(BRL);

            vlrCusto = servico.getVlTotalServico()
                    .divide(periodicidade, CALC_SCALE, HALF_UP)
                    .divide(media, CALC_SCALE, HALF_UP)
                    .multiply(taxa).setScale(PRES_SCALE, HALF_UP);
        }
        return new ServicoDTO(servico.getIdServico(), servico.getNmServico(), vlrCusto);
    }
    
    @Scheduled(cron = "0 1 0 1 1/1 ?")
    public void atualizaStatusServicosCancelados(){
        List<Servico> servicosCancelados = repository.findByDsSituacao(Situacao.CANCELADO);
        servicosCancelados.stream().forEach((servico) -> {
            servico.setDsSituacao(Situacao.INATIVO);
        });
        repository.save(servicosCancelados);
    }
}
