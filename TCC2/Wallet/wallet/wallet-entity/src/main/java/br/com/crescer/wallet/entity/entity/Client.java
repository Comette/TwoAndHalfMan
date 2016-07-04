package br.com.crescer.wallet.entity.entity;


import br.com.crescer.wallet.entity.util.State;
import br.com.crescer.wallet.entity.util.Permission;
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
@Table(name = "CLIENT", 
        uniqueConstraints = @UniqueConstraint(columnNames={"DS_USER_NAME"}),
        indexes = {@Index(columnList = "DS_STATE", name = "index_state_client")}
        )        
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_CLIENT")
    @SequenceGenerator(name = "SEQ_CLIENT", sequenceName = "SEQ_CLIENT", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_CLIENT")
    private Long idClient;
  
    @Basic(optional = false)
    @Column(name = "NM_CLIENT")
    private String nmClient;
    
    @Basic(optional = false)
    @Column(name = "DS_USER_NAME")
    private String dsUserName;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "TP_PERMISSION")
    private Permission tpPermission;
    
    @Basic(optional = false)
    @Column(name = "DS_EMAIL")
    private String dsEmail;
    
    @Basic(optional = false)
    @Column(name = "DS_PASSWORD")
    private String dsPassword;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "DS_STATE")
    private State dsState;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientIdClient")
    private List<Contract> contractList;

    public Client() {
    }

    public Client(Long idClient, String nmClient, String dsUserName, Permission tpPermission, String dsEmail, String dsPassword, State dsState, List<Contract> contractList) {
        this.idClient = idClient;
        this.nmClient = nmClient;
        this.dsUserName = dsUserName;
        this.tpPermission = tpPermission;
        this.dsEmail = dsEmail;
        this.dsPassword = dsPassword;
        this.dsState = dsState;
        this.contractList = contractList;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getNmClient() {
        return nmClient;
    }

    public void setNmClient(String nmClient) {
        this.nmClient = nmClient;
    }

    public String getDsUserName() {
        return dsUserName;
    }

    public void setDsUserName(String dsUserName) {
        this.dsUserName = dsUserName;
    }

    public Permission getTpPermission() {
        return tpPermission;
    }

    public void setTpPermission(Permission tpPermission) {
        this.tpPermission = tpPermission;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public String getDsPassword() {
        return dsPassword;
    }

    public void setDsPassword(String dsPassword) {
        this.dsPassword = dsPassword;
    }

    public State getDsState() {
        return dsState;
    }

    public void setDsState(State dsState) {
        this.dsState = dsState;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }
    
    
}
