/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.security.service;

import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.entity.Usuario;
import br.com.crescer.wallet.security.enumeration.WalletRoles;
import br.com.crescer.wallet.security.extensions.UsuarioSessaoUser;
import br.com.crescer.wallet.service.service.UsuarioService;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = service.findOneByDsUserName(username);

        if (username.isEmpty() || user == null || user.getDsSituacao() == Situacao.INATIVO) {            
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", username));
        } else {
            Collection<WalletRoles> permissoes = new ArrayList<>();
            permissoes.add(WalletRoles.valueOf(user.getTpPermissao().toString()));
            return new UsuarioSessaoUser
                (user.getIdUsuario(), user.getNmUsuario(), user.getDsEmail(), user.getTpPermissao(),
                    user.getDsUserName(), user.getDsSenha(), permissoes);
        }
    }
}
