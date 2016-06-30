package br.com.crescer.wallet.web.controller;

import br.com.crescer.wallet.service.dto.GerenteDTO;
import br.com.crescer.wallet.service.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Hedo
 */
@Controller
public class GerenteController {

    @Autowired
    UsuarioService service;
    
    @ResponseBody
    @RequestMapping(value = "/buscar-gerentes", method = RequestMethod.GET)
    public List<GerenteDTO> getGerentes() {
        return service.findAllReturningDTOs();
    }
}
