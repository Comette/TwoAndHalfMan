/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.crescer.wallet.entity.entity;

import br.com.crescer.wallet.entity.util.Periodicity;
import br.com.crescer.wallet.entity.util.Coin;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author victo
 */
@Entity
@Table(name = "CONTRACT_VALUE", 
        indexes = @Index(columnList = "VL_MONTHLY_USD", name = "index_vl_monthly_usd")
)
public class ContractValue implements Serializable{
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_CONTRACT_VALUE")
    @SequenceGenerator(name = "SEQ_CONTRACT_VALUE", sequenceName = "SEQ_CONTRACT_VALUE", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_CONTRACT_VALUE")
    private Long idContractValue;    
    
    @Basic(optional = false)
    @Column(name = "VL_AMOUNT_CONTRACT_VALUE", precision = 17, scale = 6)
    private BigDecimal vlAmountContractValue;
    
    @Basic(optional = false)
    @Column(name = "VL_MONTHLY_USD", precision = 17, scale = 6)
    private BigDecimal vlMonthlylUSD;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "DS_COIN")
    private Coin dsCoin;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "DS_PERIODICITY")
    private Periodicity dsPeriodicity;
    
    @OneToOne(optional = false)  
    @JoinColumn(name = "CONTRACT_ID_CONTRACT", referencedColumnName = "ID_CONTRACT")
    private Contract contractIdContract;

    public ContractValue() {
    }

    public ContractValue(Long idContractValue, BigDecimal vlAmountContractValue, BigDecimal vlMonthlylUSD, Coin dsCoin, Periodicity dsPeriodicity, Contract contractIdContract) {
        this.idContractValue = idContractValue;
        this.vlAmountContractValue = vlAmountContractValue;
        this.vlMonthlylUSD = vlMonthlylUSD;
        this.dsCoin = dsCoin;
        this.dsPeriodicity = dsPeriodicity;
        this.contractIdContract = contractIdContract;
    }

    public Long getIdContractValue() {
        return idContractValue;
    }

    public void setIdContractValue(Long idContractValue) {
        this.idContractValue = idContractValue;
    }

    public BigDecimal getVlAmountContractValue() {
        return vlAmountContractValue;
    }

    public void setVlAmountContractValue(BigDecimal vlAmountContractValue) {
        this.vlAmountContractValue = vlAmountContractValue;
    }

    public BigDecimal getVlMonthlylUSD() {
        return vlMonthlylUSD;
    }

    public void setVlMonthlylUSD(BigDecimal vlMonthlylUSD) {
        this.vlMonthlylUSD = vlMonthlylUSD;
    }

    public Coin getDsCoin() {
        return dsCoin;
    }

    public void setDsCoin(Coin dsCoin) {
        this.dsCoin = dsCoin;
    }

    public Periodicity getDsPeriodicity() {
        return dsPeriodicity;
    }

    public void setDsPeriodicity(Periodicity dsPeriodicity) {
        this.dsPeriodicity = dsPeriodicity;
    }

    public Contract getContractIdContract() {
        return contractIdContract;
    }

    public void setContractIdContract(Contract contractIdContract) {
        this.contractIdContract = contractIdContract;
    }
    
    
}
