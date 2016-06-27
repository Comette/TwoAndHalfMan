package br.com.crescer.wallet.entity;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author DOUGLAS
 */
@Entity
@Table(name = "USUARIO")

public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private long idUsuario;
  
    @Basic(optional = false)
    @Column(name = "NM_NOME")
    private String nmNome;
    
    @Basic(optional = false)
    @Column(name = "TP_PERMISSAO")
    private String tpPermissao;
    
    @Basic(optional = false)
    @Column(name = "DS_EMAIL")
    private String dsEmail;
    
    @Basic(optional = false)
    @Column(name = "DS_SENHA")
    private String dsSenha;
    
    @Basic(optional = false)
    @Column(name = "DS_SITUACAO")
    private String dsSituacao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<Servico> servicoList;

    public Usuario(long idUsuario, String nmNome, String dsTipoPermissao, String dsEmail, String dsSenha, String dsSituacao, List<Servico> servicoList) {
        this.idUsuario = idUsuario;
        this.nmNome = nmNome;
        this.tpPermissao = dsTipoPermissao;
        this.dsEmail = dsEmail;
        this.dsSenha = dsSenha;
        this.dsSituacao = dsSituacao;
        this.servicoList = servicoList;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNmNome() {
        return nmNome;
    }

    public void setNmNome(String nmNome) {
        this.nmNome = nmNome;
    }

    public String getTpPermissao() {
        return tpPermissao;
    }

    public void setTpPermissao(String tpPermissao) {
        this.tpPermissao = tpPermissao;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }

    public String getDsSituacao() {
        return dsSituacao;
    }

    public void setDsSituacao(String dsSituacao) {
        this.dsSituacao = dsSituacao;
    }

    public List<Servico> getServicoList() {
        return servicoList;
    }

    public void setServicoList(List<Servico> servicoList) {
        this.servicoList = servicoList;
    }
}
