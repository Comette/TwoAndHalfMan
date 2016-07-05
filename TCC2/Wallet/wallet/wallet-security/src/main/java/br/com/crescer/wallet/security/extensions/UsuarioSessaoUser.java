package br.com.crescer.wallet.security.extensions;

import br.com.crescer.wallet.entity.util.Coin;
import br.com.crescer.wallet.entity.util.Permission;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Hedo
 */


public class UsuarioSessaoUser extends User {
    
    private Long idUsuario;
    private String nmUsuario;
    private String emUsuario;
    private Permission permission;
    private Coin preferredCoin;
    
    public UsuarioSessaoUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UsuarioSessaoUser(Long idUsuario, String nmUsuario, String emUsuario,Permission permission, Coin preferredCoin, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.idUsuario = idUsuario;
        this.nmUsuario = nmUsuario;
        this.emUsuario = emUsuario;
        this.permission = permission;
        this.preferredCoin = preferredCoin;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }    

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getEmUsuario() {
        return emUsuario;
    }

    public void setEmUsuario(String emUsuario) {
        this.emUsuario = emUsuario;
    }

    public Coin getPreferredCoin() {
        return preferredCoin;
    }

    public void setPreferredCoin(Coin preferredCoin) {
        this.preferredCoin = preferredCoin;
    }
    
    
}
