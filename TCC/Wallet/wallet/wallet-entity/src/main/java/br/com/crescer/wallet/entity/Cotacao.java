package br.com.crescer.wallet.entity;

/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 *
 * @author victo
 */
@Entity
@Table(name = "COTACAO",
       indexes = {@Index(columnList = "DT_COTACAO", name = "index_dt_cotacao")}
)
public class Cotacao implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_COTACAO")
    @SequenceGenerator(name = "SEQ_COTACAO", sequenceName = "SEQ_COTACAO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_COTACAO")
    private Long idCotacao;

    @Basic(optional = false)
    @Column(name = "DT_COTACAO")
    private LocalDate dtCotacao;

    @Basic(optional = false)
    @Column(name = "DS_COTACAO_REAL")
    private BigDecimal dsCotacaoReal;
    
    @Basic(optional = false)
    @Column(name = "DS_COTACAO_EURO")
    private BigDecimal dsCotacaoEuro;    
    
    @Basic(optional = false)
    @Column(name = "DS_COTACAO_YEN")
    private BigDecimal dsCotacaoYen;         
            
    @Basic(optional = false)
    @Column(name = "DS_COTACAO_LIBRA")
    private BigDecimal dsCotacaoLibra;        

    @Basic(optional = false)
    @Column(name = "DS_COTACAO_DOLLAR_AUSTRALIANO")
    private BigDecimal dsCotacaoDollarAutraliano;

    @Basic(optional = false)
    @Column(name = "DS_COTACAO_DOLLAR_CANADENSE")
    private BigDecimal dsCotacaoDollarCanadense;

    @Basic(optional = false)
    @Column(name = "DS_COTACAO_FRANCO_SUICO")
    private BigDecimal dsCotacaoFrancoSuico;

    @Basic(optional = false)
    @Column(name = "DS_COTACAO_YUAN")
    private BigDecimal dsCotacaoYuan;

    public Cotacao() {
    }

    public Cotacao(Long idCotacao, LocalDate dtCotacao, BigDecimal dsCotacaoReal, BigDecimal dsCotacaoEuro, BigDecimal dsCotacaoYen, BigDecimal dsCotacaoLibra, BigDecimal dsCotacaoDollarAutraliano, BigDecimal dsCotacaoDollarCanadense, BigDecimal dsCotacaoFrancoSuico, BigDecimal dsCotacaoYuan) {
        this.idCotacao = idCotacao;
        this.dtCotacao = dtCotacao;
        this.dsCotacaoReal = dsCotacaoReal;
        this.dsCotacaoEuro = dsCotacaoEuro;
        this.dsCotacaoYen = dsCotacaoYen;
        this.dsCotacaoLibra = dsCotacaoLibra;
        this.dsCotacaoDollarAutraliano = dsCotacaoDollarAutraliano;
        this.dsCotacaoDollarCanadense = dsCotacaoDollarCanadense;
        this.dsCotacaoFrancoSuico = dsCotacaoFrancoSuico;
        this.dsCotacaoYuan = dsCotacaoYuan;
    }
    

    public Cotacao(BigDecimal BRL, BigDecimal EUR, BigDecimal JPY, BigDecimal GBP, BigDecimal AUD, BigDecimal CAD, BigDecimal CHF, BigDecimal CNY) {
        this.dsCotacaoReal = BRL;
        this.dsCotacaoEuro = EUR;
        this.dsCotacaoYen = JPY;
        this.dsCotacaoLibra = GBP;
        this.dsCotacaoDollarAutraliano = AUD;
        this.dsCotacaoDollarCanadense = CAD;
        this.dsCotacaoFrancoSuico = CHF;
        this.dsCotacaoYuan = CNY;
    }

    public Long getIdCotacao() {
        return idCotacao;
    }

    public void setIdCotacao(Long idCotacao) {
        this.idCotacao = idCotacao;
    }

    public LocalDate getDtCotacao() {
        return dtCotacao;
    }

    public void setDtCotacao(LocalDate dtCotacao) {
        this.dtCotacao = dtCotacao;
    }

    public BigDecimal getDsCotacaoReal() {
        return dsCotacaoReal;
    }

    public void setDsCotacaoReal(BigDecimal dsCotacaoReal) {
        this.dsCotacaoReal = dsCotacaoReal;
    }

    public BigDecimal getDsCotacaoEuro() {
        return dsCotacaoEuro;
    }

    public void setDsCotacaoEuro(BigDecimal dsCotacaoEuro) {
        this.dsCotacaoEuro = dsCotacaoEuro;
    }

    public BigDecimal getDsCotacaoYen() {
        return dsCotacaoYen;
    }

    public void setDsCotacaoYen(BigDecimal dsCotacaoYen) {
        this.dsCotacaoYen = dsCotacaoYen;
    }

    public BigDecimal getDsCotacaoLibra() {
        return dsCotacaoLibra;
    }

    public void setDsCotacaoLibra(BigDecimal dsCotacaoLibra) {
        this.dsCotacaoLibra = dsCotacaoLibra;
    }

    public BigDecimal getDsCotacaoDollarAutraliano() {
        return dsCotacaoDollarAutraliano;
    }

    public void setDsCotacaoDollarAutraliano(BigDecimal dsCotacaoDollarAutraliano) {
        this.dsCotacaoDollarAutraliano = dsCotacaoDollarAutraliano;
    }

    public BigDecimal getDsCotacaoDollarCanadense() {
        return dsCotacaoDollarCanadense;
    }

    public void setDsCotacaoDollarCanadense(BigDecimal dsCotacaoDollarCanadense) {
        this.dsCotacaoDollarCanadense = dsCotacaoDollarCanadense;
    }

    public BigDecimal getDsCotacaoFrancoSuico() {
        return dsCotacaoFrancoSuico;
    }

    public void setDsCotacaoFrancoSuico(BigDecimal dsCotacaoFrancoSuico) {
        this.dsCotacaoFrancoSuico = dsCotacaoFrancoSuico;
    }

    public BigDecimal getDsCotacaoYuan() {
        return dsCotacaoYuan;
    }

    public void setDsCotacaoYuan(BigDecimal dsCotacaoYuan) {
        this.dsCotacaoYuan = dsCotacaoYuan;
    }
}
