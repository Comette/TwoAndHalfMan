// Error reading included file Templates/Classes/Templates/Licenses/license-wallet-br.txt
package br.com.crescer.wallet.service.service;

import br.com.crescer.wallet.entity.Moeda;
import static br.com.crescer.wallet.entity.Moeda.BRL;
import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.service.dto.DashboardDTO;
import br.com.crescer.wallet.service.dto.GraficoDTO;
import br.com.crescer.wallet.service.dto.ServicoDTO;
import br.com.crescer.wallet.service.repository.ServicoRepository;
import static br.com.crescer.wallet.service.service.ServiceUtils.CALC_SCALE;
import static br.com.crescer.wallet.service.service.ServiceUtils.PRES_SCALE;
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
import static br.com.crescer.wallet.service.service.ServiceUtils.PAGE_SIZE;

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
        DashboardDTO dashboard = new DashboardDTO();
        {
            List<ServicoDTO> servicosDTOMesAtualPaginados = this.getServicosDTOMesAtualPaginados(pageable);
            dashboard.setServicosMesAtual(servicosDTOMesAtualPaginados);
            dashboard.setServicosProximoMes(this.getServicosDTOProximoMesPaginados(pageable));
            dashboard.setServicoMaisCaroContratado(servicosDTOMesAtualPaginados.isEmpty() ? null : servicosDTOMesAtualPaginados.get(0));
            dashboard.setGastoTotalAtual(this.getGastoTotalAtual());
            dashboard.setGastoTotalProximoMes(this.getGastoTotalProximoMes());
        }
        return dashboard;
    }

    public BigDecimal getGastoTotalAtual() {
        List<ServicoDTO> servicosDTOMesAtual = this.getServicosDTO(this.servicosMesAtual());
        BigDecimal gastoTotalAtual = this.calculaGastoTotalMes(servicosDTOMesAtual);
        return gastoTotalAtual;
    }

    public BigDecimal getGastoTotalProximoMes() {
        List<ServicoDTO> servicosDTOProximoMes = this.getServicosDTO(this.servicosProximoMes());
        BigDecimal gastoTotalProximoMes = this.calculaGastoTotalMes(servicosDTOProximoMes);
        return gastoTotalProximoMes;
    }

    public List<ServicoDTO> getServicosDTOMesAtualPaginados(Pageable pageable) {
        return this.getServicosDTO(servicosMesAtualPaginados(pageable));
    }

    public List<ServicoDTO> getServicosDTOProximoMesPaginados(Pageable pageable) {
        return this.getServicosDTO(this.servicosProximoMesPaginados(pageable));
    }

    public List<ServicoDTO> getServicosDTOMesAtualFiltradosPorGerentePaginados(Long idGerente, Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "vlMensalUSD");
        return this.getServicosDTO(this.servicosMesAtualPaginadosFiltradosPorGerente(idGerente, pageable));
    }

    public List<ServicoDTO> getServicosDTOProximoMesFiltradosPorGerentePaginados(Long idGerente, Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "vlMensalUSD");
        return this.getServicosDTO(this.servicosProximoMesPaginadosFiltradosPorGerente(idGerente, pageable));
    }

    public GraficoDTO getDadosGraficoServicos() {
        List<ServicoDTO> servicosDesteMes = this.getServicosDTO(this.servicosMesAtual());
        {
            BigDecimal gastoTotalMesAtual = this.calculaGastoTotalMes(servicosDesteMes);
            servicosDesteMes.stream().forEach((servico) -> {
                servico.setPorcentagemCustoTotal(gastoTotalMesAtual);
            });
        }

        List<ServicoDTO> servicosProximoMes = this.getServicosDTO(this.servicosProximoMes());
        {
            BigDecimal gastoTotalProximoMes = this.calculaGastoTotalMes(servicosProximoMes);
            servicosProximoMes.stream().forEach((servico) -> {
                servico.setPorcentagemCustoTotal(gastoTotalProximoMes);
            });
        }
        GraficoDTO graficoDTO = new GraficoDTO();
        {
            graficoDTO.setServicosDesteMes(servicosDesteMes);
            graficoDTO.setServicosProximoMes(servicosProximoMes);
        }
        return graficoDTO;
    }

    public ServicoDTO getServicoDTO(Long idServico) {
        final Map<Moeda, BigDecimal> medias = cotacaoService.findLastAverage();
        return this.buildDTO(repository.findOne(idServico), medias);
    }

    private List<Servico> servicosMesAtual() {
        return repository.findByDsSituacaoNot(Situacao.INATIVO);
    }

    private List<Servico> servicosProximoMes() {
        return repository.findByDsSituacao(Situacao.ATIVO);
    }

    private List<Servico> servicosMesAtualPaginados(Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "vlMensalUSD");
        return repository.findByDsSituacaoNot(Situacao.INATIVO, pageable);
    }

    private List<Servico> servicosProximoMesPaginados(Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "vlMensalUSD");
        return repository.findByDsSituacao(Situacao.ATIVO, pageable);
    }

    private List<Servico> servicosMesAtualPaginadosFiltradosPorGerente(Long idGerente, Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "vlMensalUSD");
        return repository.findAllByusuarioIdUsuario_idUsuarioAndDsSituacaoNot(idGerente, Situacao.INATIVO, pageable);
    }

    private List<Servico> servicosProximoMesPaginadosFiltradosPorGerente(Long idGerente, Pageable pageable) {
        pageable = new PageRequest(pageable.getPageNumber(), PAGE_SIZE, Sort.Direction.DESC, "vlMensalUSD");
        return repository.findAllByusuarioIdUsuario_idUsuarioAndDsSituacao(idGerente, Situacao.ATIVO, pageable);
    }

    private BigDecimal calculaGastoTotalMes(List<ServicoDTO> servicosDTO) {
        BigDecimal gastoTotal = BigDecimal.ZERO;
        for (ServicoDTO servico : servicosDTO) {
            gastoTotal = gastoTotal.add(servico.getCustoMensal()).setScale(PRES_SCALE, RoundingMode.HALF_UP);
        }
        return gastoTotal;
    }

    private List<ServicoDTO> getServicosDTO(List<Servico> servicos) {
        final Map<Moeda, BigDecimal> medias = cotacaoService.findLastAverage();
        List<ServicoDTO> servicosDTO = new ArrayList<>();
        servicos.stream().forEach((servico) -> {
            servicosDTO.add(this.buildDTO(servico, medias));
        });
        return servicosDTO;
    }

    private ServicoDTO buildDTO(Servico servico, Map<Moeda, BigDecimal> medias) {
        BigDecimal vlrCusto;
        {
            BigDecimal periodicidade = BigDecimal.valueOf(servico.getDsPeriodicidade().getNumeral());
            BigDecimal media = medias.get(servico.getDsSimboloMoeda());
            BigDecimal taxa = medias.get(BRL);

            vlrCusto = servico.getVlTotalServico()
                    .divide(periodicidade, CALC_SCALE, HALF_UP)
                    .divide(media, CALC_SCALE, HALF_UP)
                    .multiply(taxa).setScale(PRES_SCALE, HALF_UP);
        }
        return new ServicoDTO(servico.getIdServico(), servico.getNmServico(),
                vlrCusto, servico.getUsuarioIdUsuario().getNmUsuario(),
                servico.getUsuarioIdUsuario().getIdUsuario(), servico.getDsWebsite(),
                servico.getDsDescricao());
    }

    @Scheduled(cron = "0 1 0 1 1/1 ?")
    public void atualizaStatusServicosCancelados() {
        List<Servico> servicosCancelados = repository.findByDsSituacao(Situacao.CANCELADO);
        servicosCancelados.stream().forEach((servico) -> {
            servico.setDsSituacao(Situacao.INATIVO);
        });
        repository.save(servicosCancelados);
    }

}
