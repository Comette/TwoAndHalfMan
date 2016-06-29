package br.com.crescer.wallet.web.dto;

import br.com.crescer.wallet.service.dto.ServicoDTO;
import java.util.List;

/**
 *
 * @author Hedo
 */


public class GraficoDTO {
    
    private List<ServicoDTO> servicosDesteMes;
    private List<ServicoDTO> servicosProximoMes;

    public List<ServicoDTO> getServicosDesteMes() {
        return servicosDesteMes;
    }

    public void setServicosDesteMes(List<ServicoDTO> servicosDesteMes) {
        this.servicosDesteMes = servicosDesteMes;
    }

    public List<ServicoDTO> getServicosProximoMes() {
        return servicosProximoMes;
    }

    public void setServicosProximoMes(List<ServicoDTO> servicosProximoMes) {
        this.servicosProximoMes = servicosProximoMes;
    }
    
    
    
}
