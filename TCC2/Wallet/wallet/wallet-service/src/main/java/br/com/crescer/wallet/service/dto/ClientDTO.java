package br.com.crescer.wallet.service.dto;

import br.com.crescer.wallet.entity.util.Permission;
import br.com.crescer.wallet.entity.util.State;
import br.com.crescer.wallet.entity.entity.Client;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Hedo
 */
public class ClientDTO {

    private Long id;

    @NotEmpty
    @Length(max = 255)
    private String name;

    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @NotEmpty
    @Length(max = 255)
    private String email;

    @NotEmpty
    @Length(max = 255)
    private String username;

    @NotEmpty
    @Length(min = 8, max = 255)
    private String password;

    @NotNull
    private Permission permission;
    
    private State state;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getIdClient();
        this.name = client.getNmClient();
        this.email = client.getDsEmail();
        this.username = client.getDsUserName();
        this.password = client.getDsPassword();
        this.permission = client.getTpPermission();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    
    public Client buildUsuario() {
        Client user = new Client();
        user.setDsEmail(email);
        user.setDsPassword(Encode(password));
        user.setDsState(State.ACTIVE);
        user.setDsUserName(username);
        user.setNmClient(name);
        user.setTpPermission(permission);
        return user;
    }

    private String Encode(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);

    }
}
