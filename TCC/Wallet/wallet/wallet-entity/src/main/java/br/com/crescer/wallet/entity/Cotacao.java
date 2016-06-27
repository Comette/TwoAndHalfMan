package br.com.crescer.wallet.entity;

/*
 * Crescer-TCC: Wallet
 * by: Hedo Eccker, Douglas Balester e Victor Comette.
 */

import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author victo
 */
@Entity
@Table(name = "COTACAO")
public class Cotacao {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_COTACAO")
    @SequenceGenerator(name = "SEQ_COTACAO", sequenceName = "SEQ_COTACAO", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID_COTACAO")
    private Long idCotacao;

    @Basic(optional = false)
    @Column(name = "DT_COTACAO")
    @Temporal(TemporalType.DATE)
    private LocalDate dtCotacao;

    @Basic(optional = false)
    @Column(name = "DS_COTACAO_REAL")
    private String dsCotacaoReal;
    
    @Basic(optional = false)
    @Column(name = "DS_COTACAO_EURO")
    private String dsCotacaoEuro;    
    
    @Basic(optional = false)
    @Column(name = "DS_COTACAO_YEN")
    private String dsCotacaoYen;         
            
    @Basic(optional = false)
    @Column(name = "DS_COTACAO_LIBRA")
    private String dsCotacaoLibra;        

    @Basic(optional = false)
    @Column(name = "DS_COTACAO_DOLLAR_AUSTRALIANO")
    private String dsCotacaoDollarAutraliano;

    @Basic(optional = false)
    @Column(name = "DS_COTACAO_DOLLAR_CANADENSE")
    private String dsCotacaoDollarCanadense;

    @Basic(optional = false)
    @Column(name = "DS_COTACAO_FRANCO_SUICO")
    private String dsCotacaoFrancoSuico;

    @Basic(optional = false)
    @Column(name = "DS_COTACAO_YUAN")
    private String dsCotacaoYuan;

    public Cotacao() {
    }

    public Cotacao(Long idCotacao, LocalDate dtCotacao, float dsCotacaoReal, float dsCotacaoEuro, float dsCotacaoYen, float dsCotacaoLibra, float dsCotacaoDollarAutraliano, float dsCotacaoDollarCanadense, float dsCotacaoFrancoSuico, float dsCotacaoYuan) {
        this.idCotacao = idCotacao;
        this.dtCotacao = dtCotacao;
        this.dsCotacaoReal = String.valueOf(dsCotacaoReal);
        this.dsCotacaoEuro = String.valueOf(dsCotacaoEuro);
        this.dsCotacaoYen = String.valueOf(dsCotacaoYen);
        this.dsCotacaoLibra = String.valueOf(dsCotacaoLibra);
        this.dsCotacaoDollarAutraliano = String.valueOf(dsCotacaoDollarAutraliano);
        this.dsCotacaoDollarCanadense = String.valueOf(dsCotacaoDollarCanadense);
        this.dsCotacaoFrancoSuico = String.valueOf(dsCotacaoFrancoSuico);
        this.dsCotacaoYuan = String.valueOf(dsCotacaoYuan);
    }

    public Cotacao(float BRL, float EUR, float JPY, float GBP, float AUD, float CAD, float CHF, float CNY) {
        this.dsCotacaoReal = String.valueOf(BRL);
        this.dsCotacaoEuro = String.valueOf(EUR);
        this.dsCotacaoYen = String.valueOf(JPY);
        this.dsCotacaoLibra = String.valueOf(GBP);
        this.dsCotacaoDollarAutraliano = String.valueOf(AUD);
        this.dsCotacaoDollarCanadense = String.valueOf(CAD);
        this.dsCotacaoFrancoSuico = String.valueOf(CHF);
        this.dsCotacaoYuan = String.valueOf(CNY);
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

    public double getDsCotacaoReal() {
        return Double.parseDouble(dsCotacaoReal);
    }

    public void setDsCotacaoReal(String dsCotacaoReal) {
        this.dsCotacaoReal = dsCotacaoReal;
    }

    public double getDsCotacaoEuro() {
        return Double.parseDouble(dsCotacaoEuro);
    }

    public void setDsCotacaoEuro(String dsCotacaoEuro) {
        this.dsCotacaoEuro = dsCotacaoEuro;
    }

    public double getDsCotacaoYen() {
        return Double.parseDouble(dsCotacaoYen);
    }

    public void setDsCotacaoYen(String dsCotacaoYen) {
        this.dsCotacaoYen = dsCotacaoYen;
    }

    public double getDsCotacaoLibra() {
        return Double.parseDouble(dsCotacaoLibra);
    }

    public void setDsCotacaoLibra(String dsCotacaoLibra) {
        this.dsCotacaoLibra = dsCotacaoLibra;
    }

    public double getDsCotacaoDollarAutraliano() {
        return Double.parseDouble(dsCotacaoDollarAutraliano);
    }

    public void setDsCotacaoDollarAutraliano(String dsCotacaoDollarAutraliano) {
        this.dsCotacaoDollarAutraliano = dsCotacaoDollarAutraliano;
    }

    public double getDsCotacaoDollarCanadense() {
        return Double.parseDouble(dsCotacaoDollarCanadense);
    }

    public void setDsCotacaoDollarCanadense(String dsCotacaoDollarCanadense) {
        this.dsCotacaoDollarCanadense = dsCotacaoDollarCanadense;
    }

    public double getDsCotacaoFrancoSuico() {
        return Double.parseDouble(dsCotacaoFrancoSuico);
    }

    public void setDsCotacaoFrancoSuico(String dsCotacaoFrancoSuico) {
        this.dsCotacaoFrancoSuico = dsCotacaoFrancoSuico;
    }

    public double getDsCotacaoYuan() {
        return Double.parseDouble(dsCotacaoYuan);
    }

    public void setDsCotacaoYuan(String dsCotacaoYuan) {
        this.dsCotacaoYuan = dsCotacaoYuan;
    }

}
