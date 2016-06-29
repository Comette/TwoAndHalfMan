package br.com.crescer.wallet.web.utils;

import br.com.crescer.wallet.security.extensions.UsuarioSessaoUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Hedo
 */


public class LoggedInUserUtils {
    
    public static UsuarioSessaoUser getLoggedInUser(){
        return (UsuarioSessaoUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
}
