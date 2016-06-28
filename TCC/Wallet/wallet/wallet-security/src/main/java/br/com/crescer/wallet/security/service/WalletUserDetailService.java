/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.security.service;

import br.com.crescer.wallet.entity.Usuario;
import br.com.crescer.wallet.security.enumeration.WalletRoles;
import br.com.crescer.wallet.service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author victor.ribeiro
 */
@Service
public class WalletUserDetailService implements UserDetailsService {

    private static final String CRESCER = "crescer";
    @Autowired
    UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = service.findOneByDsUserName(username);

        if (username.isEmpty() || user == null) {
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        } else {
            return new User(user.getDsUserName(), user.getDsSenha(), WalletRoles.valuesToList());
        }
    }
}
