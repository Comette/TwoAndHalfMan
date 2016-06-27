/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.security.enumeration;

import java.util.Arrays;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author victor.ribeiro
 */
public enum WalletRoles implements GrantedAuthority{
    ROLE_USER;

    @Override
    public String getAuthority() {
        return toString();
    }

    public static List<WalletRoles> valuesToList() {
        return Arrays.asList(WalletRoles.values());
    }
}
