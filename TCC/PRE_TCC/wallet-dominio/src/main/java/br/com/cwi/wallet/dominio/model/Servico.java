/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cwi.wallet.dominio.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author DOUGLAS
 */
@Entity
@Table(name = "SERVICO")
public class Servico implements Serializable {
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_SERVICO")
    private long idServico;
    
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;

    @Basic(optional = false)
    @Column(name = "DESCRICAO")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "WEBSITE")
    private String website;

    @Basic(optional = false)
    @Column(name = "VALOR")
    private long valor;

    @Basic(optional = false)
    @Column(name = "SIMBOLO_MOEDA")
    private String simboloMoeda;

    @Basic(optional = false)
    @Column(name = "PERIODICIDADE")
    private long periodicidade;

    @JoinColumn(name = "USUARIO_ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;

    public Servico() {
    }

    public Servico(long idServico) {
        this.idServico = idServico;
    }

    public Servico(long idServico, String nome, String descricao, String website, long valor, String simboloMoeda, long periodicidade) {
        this.idServico = idServico;
        this.nome = nome;
        this.descricao = descricao;
        this.website = website;
        this.valor = valor;
        this.simboloMoeda = simboloMoeda;
        this.periodicidade = periodicidade;
    }

    public long getIdServico() {
        return idServico;
    }

    public void setIdServico(long idServico) {
        this.idServico = idServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public String getSimboloMoeda() {
        return simboloMoeda;
    }

    public void setSimboloMoeda(String simboloMoeda) {
        this.simboloMoeda = simboloMoeda;
    }

    public long getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(long periodicidade) {
        this.periodicidade = periodicidade;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Servico)) {
//            return false;
//        }
//        Servico other = (Servico) object;
//        if ((this.idServico == null && other.idServico != null) || (this.idServico != null && !this.idServico.equals(other.idServico))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "br.com.cwi.wallet.dominio.model.Servico[ idServico=" + idServico + " ]";
    }
    
}
