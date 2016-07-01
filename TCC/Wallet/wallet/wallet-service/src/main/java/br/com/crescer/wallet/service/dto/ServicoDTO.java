/*
 *  Crescer-TCC: Wallet
 *  by: Douglas Ballester, Hedo Eccker e Victor Comette.
 */
package br.com.crescer.wallet.service.dto;

import br.com.crescer.wallet.entity.Moeda;
import br.com.crescer.wallet.entity.Periodicidade;
import br.com.crescer.wallet.entity.Servico;
import br.com.crescer.wallet.entity.Usuario;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author victor.ribeiro
 */
public class ServicoDTO {
    
    
    private long id;
    private String custoMensalFormatado;
    private BigDecimal custoMensal;
    private BigDecimal porcentagemCustoTotal;
    private Long idUsuario;
    
    
    @Length(min = 1, max = 255)
    @NotEmpty
    private String nome;
    
    @Length(min = 1, max = 255)
    @NotEmpty
    private String nmUsuario;
    
    @Length(min = 1, max = 255)
    @NotEmpty
    @Pattern(regexp = "/^(https?:\\/\\/)([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$/")
    private String dsWebsite;
    
    @Length(min = 1, max = 255)
    @NotEmpty
    private String dsDescricao;
    
    @NotNull
    private Periodicidade periodicidade;
    
    @NotNull
    private Moeda moeda;
  
    @NotNull
    private Usuario gerenteResponsavel;
    
    @NotNull
    @Range(min = 0)
    private BigDecimal valorTotal;
    
    public ServicoDTO(long id, String nome, BigDecimal custoMensal) {
        this.id = id;
        this.nome = nome;
        this.custoMensal = custoMensal;
    }

    public ServicoDTO(long id, String nome, BigDecimal custoMensal, String nmUsuario, Long idUsuario, String dsWebsite, String dsDescricao) {
        this.id = id;
        this.nome = nome;
        this.custoMensal = custoMensal;
        this.nmUsuario = nmUsuario;
        this.idUsuario = idUsuario;
        this.dsWebsite = dsWebsite;
        this.dsDescricao = dsDescricao;
        this.custoMensalFormatado = NumberFormat.getCurrencyInstance().format(custoMensal);
    }

    public ServicoDTO(String nome, String dsWebsite, String dsDescricao, Periodicidade periodicidade, Moeda moeda, Usuario gerenteResponsavel, BigDecimal valorTotal) {
        this.nome = nome;
        this.dsWebsite = dsWebsite;
        this.dsDescricao = dsDescricao;
        this.periodicidade = periodicidade;
        this.moeda = moeda;
        this.gerenteResponsavel = gerenteResponsavel;
        this.valorTotal = valorTotal;
    }
    

    public String getCustoMensalFormatado() {
        return custoMensalFormatado;
    }

    public void setCustoMensalFormatado(String custoMensalFormatado) {
        this.custoMensalFormatado = custoMensalFormatado;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDsWebsite() {
        return dsWebsite;
    }

    public void setDsWebsite(String dsWebsite) {
        this.dsWebsite = dsWebsite;
    }

    public String getDsDescricao() {
        return dsDescricao;
    }

    public void setDsDescricao(String dsDescricao) {
        this.dsDescricao = dsDescricao;
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
    
    public Servico buildServico(){
        Servico servico = new Servico();
        servico.setNmServico(this.nome);
        servico.setDsWebsite(this.dsWebsite);
        servico.setDsPeriodicidade(this.periodicidade);
        servico.setDsDescricao(this.dsDescricao);
        servico.setDsSimboloMoeda(this.moeda);
        servico.setVlTotalServico(this.valorTotal);
        servico.setUsuarioIdUsuario(this.gerenteResponsavel);
        
        return servico;
    }
}
