package br.com.crescer.wallet.web.utils;

import static br.com.crescer.wallet.entity.util.Permission.ADMINISTRATOR;
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
    
    public static boolean checkIfUserIsAdmin(){
        return ((UsuarioSessaoUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPermission().equals(ADMINISTRATOR);
    }
    
}
