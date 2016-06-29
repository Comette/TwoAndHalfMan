// Error reading included file Templates/Classes/Templates/Licenses/license-wallet-br.txt
package br.com.crescer.wallet.service.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author victo
 */
public class DashboardDTO {
    private List<ServicoDTO> servicosMesAtual;
    private List<ServicoDTO> servicosProximoMes;
    private BigDecimal gastoTotalAtual;
    private BigDecimal gastoTotalProximoMes;

    public DashboardDTO() {
    }
        
    public DashboardDTO(List<ServicoDTO> servicosMesAtual, List<ServicoDTO> servicosProximoMes, BigDecimal gastoTotalAtual, BigDecimal gastoTotalProximoMes) {
        this.servicosMesAtual = servicosMesAtual;
        this.servicosProximoMes = servicosProximoMes;
        this.gastoTotalAtual = gastoTotalAtual;
        this.gastoTotalProximoMes = gastoTotalProximoMes;
    }

    public List<ServicoDTO> getServicosMesAtual() {
        return servicosMesAtual;
    }

    public void setServicosMesAtual(List<ServicoDTO> servicosMesAtual) {
        this.servicosMesAtual = servicosMesAtual;
    }

    public List<ServicoDTO> getServicosProximoMes() {
        return servicosProximoMes;
    }

    public void setServicosProximoMes(List<ServicoDTO> servicosProximoMes) {
        this.servicosProximoMes = servicosProximoMes;
    }

    public BigDecimal getGastoTotalAtual() {
        return gastoTotalAtual;
    }

    public void setGastoTotalAtual(BigDecimal gastoTotalAtual) {
        this.gastoTotalAtual = gastoTotalAtual;
    }

    public BigDecimal getGastoTotalProximoMes() {
        return gastoTotalProximoMes;
    }

    public void setGastoTotalProximoMes(BigDecimal gastoTotalProximoMes) {
        this.gastoTotalProximoMes = gastoTotalProximoMes;
    }
    
}
