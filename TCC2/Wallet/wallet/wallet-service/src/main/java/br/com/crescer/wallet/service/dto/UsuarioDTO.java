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
public class UsuarioDTO {

    private Long id;

    @NotEmpty
    @Length(max = 255)
    private String nome;

    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @NotEmpty
    @Length(max = 255)
    private String email;

    @NotEmpty
    @Length(max = 255)
    private String username;

    @NotEmpty
    @Length(min = 8, max = 255)
    private String senha;

    @NotNull
    private Permission permissao;
    
    private State situacao;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Client usuario) {
        this.id = usuario.getIdClient();
        this.nome = usuario.getNmClient();
        this.email = usuario.getDsEmail();
        this.username = usuario.getDsUserName();
        this.senha = usuario.getDsPassword();
        this.permissao = usuario.getTpPermission();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Permission getPermissao() {
        return permissao;
    }

    public void setPermissao(Permission permissao) {
        this.permissao = permissao;
    }

    public State getSituacao() {
        return situacao;
    }

    public void setSituacao(State situacao) {
        this.situacao = situacao;
    }
    
    public Client buildUsuario() {
        Client user = new Client();
        user.setDsEmail(email);
        user.setDsPassword(Criptografar(senha));
        user.setDsState(State.ACTIVE);
        user.setDsUserName(username);
        user.setNmClient(nome);
        user.setTpPermission(permissao);
        return user;
    }

    private String Criptografar(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);

    }
}
