package br.com.cwi.wallet.dominio.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author DOUGLAS
 */
@Entity
@Table(name = "USUARIO")

public class Usuario implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "ID_USUARIO")
    private long idUsuario;
  
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    
    @Basic(optional = false)
    @Column(name = "TIPO_PERMISSAO")
    private long tipoPermissao;
    
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    
    @Basic(optional = false)
    @Column(name = "SENHA")
    private String senha;
    
    @Basic(optional = false)
    @Column(name = "INATIVO")
    private long inativo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<Servico> servicoList;

    public Usuario() {
    }

    public Usuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(long idUsuario, String nome, long tipoPermissao, String email, String senha, long inativo) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.tipoPermissao = tipoPermissao;
        this.email = email;
        this.senha = senha;
        this.inativo = inativo;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getTipoPermissao() {
        return tipoPermissao;
    }

    public void setTipoPermissao(long tipoPermissao) {
        this.tipoPermissao = tipoPermissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public long getInativo() {
        return inativo;
    }

    public void setInativo(long inativo) {
        this.inativo = inativo;
    }

    public List<Servico> getServicoList() {
        return servicoList;
    }

    public void setServicoList(List<Servico> servicoList) {
        this.servicoList = servicoList;
    }


//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Usuario)) {
//            return false;
//        }
//        Usuario other = (Usuario) object;
//        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "br.com.cwi.wallet.dominio.model.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
