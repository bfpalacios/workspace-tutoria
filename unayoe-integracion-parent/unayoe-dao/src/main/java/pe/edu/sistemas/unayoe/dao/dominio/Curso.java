/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.sistemas.unayoe.dao.dominio;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bpalacios
 */
@Entity
@Table(name = "CURSO", catalog = "", schema = "DBTUTORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByCCodigo", query = "SELECT c FROM Curso c WHERE c.cCodigo = :cCodigo"),
    @NamedQuery(name = "Curso.findByNombre", query = "SELECT c FROM Curso c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Curso.findByCreditos", query = "SELECT c FROM Curso c WHERE c.creditos = :creditos")})
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "C_CODIGO")
    private String cCodigo;
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "CREDITOS")
    private BigInteger creditos;

    public Curso() {
    }

    public Curso(String cCodigo) {
        this.cCodigo = cCodigo;
    }

    public Curso(String cCodigo, BigInteger creditos) {
        this.cCodigo = cCodigo;
        this.creditos = creditos;
    }

    public String getCCodigo() {
        return cCodigo;
    }

    public void setCCodigo(String cCodigo) {
        this.cCodigo = cCodigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getCreditos() {
        return creditos;
    }

    public void setCreditos(BigInteger creditos) {
        this.creditos = creditos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cCodigo != null ? cCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.cCodigo == null && other.cCodigo != null) || (this.cCodigo != null && !this.cCodigo.equals(other.cCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DBTUTORIA.Curso[ cCodigo=" + cCodigo + " ]";
    }
    
}
