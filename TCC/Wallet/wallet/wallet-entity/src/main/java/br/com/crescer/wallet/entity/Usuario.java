package br.com.crescer.wallet.entity;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/**
 *
 * @author DOUGLAS
 */
@Entity
@Table(name = "USUARIO", 
        uniqueConstraints = @UniqueConstraint(columnNames={"DS_USER_NAME"}),
        indexes = {@Index(columnList = "DS_SITUACAO", name = "index_situacao_usuario")}
        )
        
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private long idUsuario;
  
    @Basic(optional = false)
    @Column(name = "NM_USUARIO")
    private String nmUsuario;
    
    @Basic(optional = false)
    @Column(name = "DS_USER_NAME")
    private String dsUserName;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "TP_PERMISSAO")
    private Permissao tpPermissao;
    
    @Basic(optional = false)
    @Column(name = "DS_EMAIL")
    private String dsEmail;
    
    @Basic(optional = false)
    @Column(name = "DS_SENHA")
    private String dsSenha;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "DS_SITUACAO")
    private Situacao dsSituacao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<Servico> servicoList;

    public Usuario(long idUsuario, String nmUsuario, String dsUserName, Permissao tpPermissao, String dsEmail, String dsSenha, Situacao dsSituacao, List<Servico> servicoList) {
        this.idUsuario = idUsuario;
        this.nmUsuario = nmUsuario;
        this.dsUserName = dsUserName;
        this.tpPermissao = tpPermissao;
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

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getDsUserName() {
        return dsUserName;
    }

    public void setDsUserName(String dsUserName) {
        this.dsUserName = dsUserName;
    }

    public Permissao getTpPermissao() {
        return tpPermissao;
    }

    public void setTpPermissao(Permissao tpPermissao) {
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

    public Situacao getDsSituacao() {
        return dsSituacao;
    }

    public void setDsSituacao(Situacao dsSituacao) {
        this.dsSituacao = dsSituacao;
    }

    public List<Servico> getServicoList() {
        return servicoList;
    }

    public void setServicoList(List<Servico> servicoList) {
        this.servicoList = servicoList;
    }

   
}
