
package br.com.crescer.wallet.entity;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author DOUGLAS
 */
@Entity
@Table(name = "SERVICO", 
        indexes = {@Index(columnList = "NM_SERVICO", name = "index_nome_servico"),
                   @Index(columnList = "VL_SERVICO", name = "index_valor_servico"),
                   @Index(columnList = "USUARIO_ID_USUARIO", name = "index_gerente")
        })
public class Servico implements Serializable {
    
   
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_SERVICO")
    @SequenceGenerator(name = "SEQ_SERVICO", sequenceName = "SEQ_SERVICO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_SERVICO")
    private long idServico;
    
    @Basic(optional = false)
    @Column(name = "NM_SERVICO")
    private String nmServico;

    @Basic(optional = false)
    @Column(name = "DS_DESCRICAO")
    private String dsDescricao;

    @Basic(optional = false)
    @Column(name = "DS_WEBSITE")
    private String dsWebsite;

    @Basic(optional = false)
    @Column(name = "VL_SERVICO")
    private long vlServico;

    @Basic(optional = false)
    @Column(name = "DS_SIMBOLO_MOEDA")
    private String dsSimboloMoeda;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "DS_PERIODICIDADE")
    private Periodicidade dsPeriodicidade;

    @JoinColumn(name = "USUARIO_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;

    public Servico(long idServico, String nmServico, String dsDescricao, String dsWebsite, long valor, String dsSimboloMoeda, Periodicidade dsPeriodicidade, Usuario usuarioIdUsuario) {
        this.idServico = idServico;
        this.nmServico = nmServico;
        this.dsDescricao = dsDescricao;
        this.dsWebsite = dsWebsite;
        this.vlServico = valor;
        this.dsSimboloMoeda = dsSimboloMoeda;
        this.dsPeriodicidade = dsPeriodicidade;
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    public long getIdServico() {
        return idServico;
    }

    public void setIdServico(long idServico) {
        this.idServico = idServico;
    }

    public String getNmServico() {
        return nmServico;
    }

    public void setNmServico(String nmServico) {
        this.nmServico = nmServico;
    }

    public String getDsDescricao() {
        return dsDescricao;
    }

    public void setDsDescricao(String dsDescricao) {
        this.dsDescricao = dsDescricao;
    }

    public String getDsWebsite() {
        return dsWebsite;
    }

    public void setDsWebsite(String dsWebsite) {
        this.dsWebsite = dsWebsite;
    }

    public long getVlServico() {
        return vlServico;
    }

    public void setVlServico(long vlServico) {
        this.vlServico = vlServico;
    }

    public String getDsSimboloMoeda() {
        return dsSimboloMoeda;
    }

    public void setDsSimboloMoeda(String dsSimboloMoeda) {
        this.dsSimboloMoeda = dsSimboloMoeda;
    }

    public Periodicidade getDsPeriodicidade() {
        return dsPeriodicidade;
    }

    public void setDsPeriodicidade(Periodicidade dsPeriodicidade) {
        this.dsPeriodicidade = dsPeriodicidade;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }
}
