package br.com.crescer.wallet.service.dto;

import br.com.crescer.wallet.entity.Permissao;
import br.com.crescer.wallet.entity.Situacao;
import br.com.crescer.wallet.entity.Usuario;
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
    private Permissao permissao;
    
    private Situacao situacao;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getIdUsuario();
        this.nome = usuario.getNmUsuario();
        this.email = usuario.getDsEmail();
        this.username = usuario.getDsUserName();
        this.senha = usuario.getDsSenha();
        this.permissao = usuario.getTpPermissao();
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

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }
    
    public Usuario buildUsuario() {
        Usuario user = new Usuario();
        user.setIdUsuario(id == null ? 0 : id);
        user.setDsEmail(email);
        user.setDsSenha(Criptografar(senha));
        user.setDsSituacao(situacao != null ? situacao : Situacao.ATIVO);
        user.setDsUserName(username);
        user.setNmUsuario(nome);
        user.setTpPermissao(permissao);
        return user;
    }

    private String Criptografar(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);

    }
}
