/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.crescer.wallet.entity.entity;

import br.com.crescer.wallet.entity.util.State;
import java.io.Serializable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author victo
 */
@Entity
@Table(name = "CONTRACT", 
        indexes = {@Index(columnList = "NM_CONTRACT", name = "index_name_contract"),                   
                   @Index(columnList = "CLIENT_ID_CLIENT", name = "index_client_contract"),
                   @Index(columnList = "DS_STATE", name = "index_state_contract")
        })
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_CONTRACT")
    @SequenceGenerator(name = "SEQ_CONTRACT", sequenceName = "SEQ_CONTRACT", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_CONTRACT")
    private Long idContract;
    
    @Basic(optional = false)
    @Column(name = "NM_CONTRACT")
    private String nmContract;

    @Basic(optional = false)
    @Column(name = "DS_DESCRIPTION", length = 801)
    private String dsDescription;

    @Basic(optional = false)
    @Column(name = "DS_WEBSITE")
    private String dsWebsite;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "DS_STATE")
    private State dsState;

    @JoinColumn(name = "CLIENT_ID_CLIENT", referencedColumnName = "ID_CLIENT")
    @ManyToOne(optional = false)    
    private Client clientIdClient;
    
    @OneToOne(mappedBy = "contractIdContract", cascade = CascadeType.ALL)
    private ContractValue contractValue;

    public Contract() {
    }

    public Contract(Long idContract, String nmContract, String dsDescription, String dsWebsite, State dsState, Client userIdUser, ContractValue contractValue) {
        this.idContract = idContract;
        this.nmContract = nmContract;
        this.dsDescription = dsDescription;
        this.dsWebsite = dsWebsite;
        this.dsState = dsState;
        this.clientIdClient = userIdUser;
        this.contractValue = contractValue;
    }

    public Long getIdContract() {
        return idContract;
    }

    public void setIdContract(Long idContract) {
        this.idContract = idContract;
    }

    public String getNmContract() {
        return nmContract;
    }

    public void setNmContract(String nmContract) {
        this.nmContract = nmContract;
    }

    public String getDsDescription() {
        return dsDescription;
    }

    public void setDsDescription(String dsDescription) {
        this.dsDescription = dsDescription;
    }

    public String getDsWebsite() {
        return dsWebsite;
    }

    public void setDsWebsite(String dsWebsite) {
        this.dsWebsite = dsWebsite;
    }

    public State getDsState() {
        return dsState;
    }

    public void setDsState(State dsState) {
        this.dsState = dsState;
    }

    public Client getClientIdClient() {
        return clientIdClient;
    }

    public void setClientIdClient(Client clientIdClient) {
        this.clientIdClient = clientIdClient;
    }

    public ContractValue getContractValue() {
        return contractValue;
    }

    public void setContractValue(ContractValue contractValue) {
        this.contractValue = contractValue;
    }
    
}
