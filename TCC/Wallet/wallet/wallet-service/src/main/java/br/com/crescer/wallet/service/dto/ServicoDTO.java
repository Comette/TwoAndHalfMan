/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.service.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author victor.ribeiro
 */
public class ServicoDTO {
    private long id;
    private String nome;
    private BigDecimal custoMensal;
    private BigDecimal porcentagemCustoTotal;

    public ServicoDTO(long id, String nome, BigDecimal custoMensal) {
        this.id = id;
        this.nome = nome;
        this.custoMensal = custoMensal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getCustoMensal() {
        return custoMensal;
    }

    public void setCustoMensal(BigDecimal custoMensal) {
        this.custoMensal = custoMensal;
    }

    public BigDecimal getPorcentagemCustoTotal() {
        return porcentagemCustoTotal;
    }

    public void setPorcentagemCustoTotal(BigDecimal gastoTotal) {
        BigDecimal porcentCustoTotal = this.custoMensal.multiply(BigDecimal.valueOf(100)).divide(gastoTotal, 6, RoundingMode.HALF_UP);
        this.porcentagemCustoTotal = porcentCustoTotal;
    }
}
