package br.com.crescer.wallet.security.extensions;

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
    
    public UsuarioSessaoUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UsuarioSessaoUser(Long idUsuario, String nmUsuario, String emUsuario, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.idUsuario = idUsuario;
        this.nmUsuario = nmUsuario;
        this.emUsuario = emUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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
    
}
