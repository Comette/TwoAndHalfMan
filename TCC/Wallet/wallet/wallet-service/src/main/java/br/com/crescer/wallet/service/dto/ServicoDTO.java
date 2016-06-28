/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.service.dto;

/**
 *
 * @author victor.ribeiro
 */
public class ServicoDTO {
    private long id;
    private String nome;
    private double custoMensal;
    private double porcentagemCustoTotal;

    public ServicoDTO(long id, String nome, double custoMensal, double porcentagemCustoTotal) {
        this.id = id;
        this.nome = nome;
        this.custoMensal = custoMensal;
        this.porcentagemCustoTotal = porcentagemCustoTotal;
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

    public double getCustoMensal() {
        return custoMensal;
    }

    public void setCustoMensal(double custoMensal) {
        this.custoMensal = custoMensal;
    }

    public double getPorcentagemCustoTotal() {
        return porcentagemCustoTotal;
    }

    public void setPorcentagemCustoTotal(double porcentagemCustoTotal) {
        this.porcentagemCustoTotal = porcentagemCustoTotal;
    }
}
