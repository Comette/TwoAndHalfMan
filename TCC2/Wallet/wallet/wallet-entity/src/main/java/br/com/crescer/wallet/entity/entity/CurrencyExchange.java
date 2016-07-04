/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */
package br.com.crescer.wallet.entity.entity;

import br.com.crescer.wallet.entity.util.Coin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author victo
 */
@Entity
@Table(name = "CURRENCY_EXCHANGE",
       indexes = {
           @Index(columnList = "DT_CURRENCY_EXCHANGE", name = "index_dt_currency_exchange"),
           @Index(columnList = "DS_COIN", name = "index_ds_coin_currency_exchange")
       }
)
public class CurrencyExchange implements Serializable{
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_CURRENCY_EXCHANGE")
    @SequenceGenerator(name = "SEQ_CURRENCY_EXCHANGE", sequenceName = "SEQ_CURRENCY_EXCHANGE", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_CURRENCY_EXCHANGE")
    private Long idCurrencyExchange;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "DS_COIN")
    private Coin dsCoin;
    
    @Basic(optional = false)
    @Column(name = "VL_RATE", precision = 17, scale = 6)
    private BigDecimal vlRate;

    @Basic(optional = false)
    @Column(name = "DT_CURRENCY_EXCHANGE")
    private LocalDate dtCurrencyExchange;

    public CurrencyExchange() {
    }

    public CurrencyExchange(Long idCurrencyExchange, Coin dsCoin, BigDecimal vlRate, LocalDate dtCurrencyExchange) {
        this.idCurrencyExchange = idCurrencyExchange;
        this.dsCoin = dsCoin;
        this.vlRate = vlRate;
        this.dtCurrencyExchange = dtCurrencyExchange;
    }

    public Long getIdCurrencyExchange() {
        return idCurrencyExchange;
    }

    public void setIdCurrencyExchange(Long idCurrencyExchange) {
        this.idCurrencyExchange = idCurrencyExchange;
    }

    public Coin getDsCoin() {
        return dsCoin;
    }

    public void setDsCoin(Coin dsCoin) {
        this.dsCoin = dsCoin;
    }

    public BigDecimal getVlRate() {
        return vlRate;
    }

    public void setVlRate(BigDecimal vlRate) {
        this.vlRate = vlRate;
    }

    public LocalDate getDtCurrencyExchange() {
        return dtCurrencyExchange;
    }

    public void setDtCurrencyExchange(LocalDate dtCurrencyExchange) {
        this.dtCurrencyExchange = dtCurrencyExchange;
    }
    
}
