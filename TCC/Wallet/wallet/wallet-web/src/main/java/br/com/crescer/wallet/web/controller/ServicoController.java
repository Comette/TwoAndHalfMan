// Error reading included file Templates/Classes/Templates/Licenses/license-wallet-br.txt
package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.service.service.ServicoService;
import br.com.crescer.wallet.web.dto.GastoMensalDTO;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author victo
 */

@RestController
public class ServicoController {
    
    @Autowired
    ServicoService service;
    
    @RequestMapping(value = "/gasto-mensal", method = RequestMethod.GET)
    public GastoMensalDTO gastoMensal(){
        Map<Moeda, Double> gastoTotalMensal = service.gastoTotalMensal();
        GastoMensalDTO gastoMensalDTO = new GastoMensalDTO();
        gastoMensalDTO.setUsd(gastoTotalMensal.get(Moeda.USD));
        gastoMensalDTO.setBrl(gastoTotalMensal.get(Moeda.BRL));
        return gastoMensalDTO;
    }
    
    @RequestMapping(value = "/gasto-mensal-proximo-mes", method = RequestMethod.GET)
    public GastoMensalDTO gastoMensalProximoMes(){
        Map<Moeda, Double> gastoTotalMensal = service.gastoTotalMensalPrevistoProximoMes();
        GastoMensalDTO gastoMensalDTO = new GastoMensalDTO();
        gastoMensalDTO.setUsd(gastoTotalMensal.get(Moeda.USD));
        gastoMensalDTO.setBrl(gastoTotalMensal.get(Moeda.BRL));
        return gastoMensalDTO;
    }
}
