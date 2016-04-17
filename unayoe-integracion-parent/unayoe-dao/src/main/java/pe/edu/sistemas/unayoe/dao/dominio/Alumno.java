/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.sistemas.unayoe.dao.dominio;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bpalacios
 */
@Entity
@Table(name = "ALUMNO", catalog = "", schema = "DBTUTORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll", query = "SELECT a FROM Alumno a"),
    @NamedQuery(name = "Alumno.findByACodigo", query = "SELECT a FROM Alumno a WHERE a.aCodigo = :aCodigo"),
    @NamedQuery(name = "Alumno.findByANombre", query = "SELECT a FROM Alumno a WHERE a.aNombre = :aNombre"),
    @NamedQuery(name = "Alumno.findByAApellidos", query = "SELECT a FROM Alumno a WHERE a.aApellidos = :aApellidos"),
    @NamedQuery(name = "Alumno.findByAFnacimiento", query = "SELECT a FROM Alumno a WHERE a.aFnacimiento = :aFnacimiento"),
    @NamedQuery(name = "Alumno.findByADireccion", query = "SELECT a FROM Alumno a WHERE a.aDireccion = :aDireccion"),
    @NamedQuery(name = "Alumno.findByAEmail", query = "SELECT a FROM Alumno a WHERE a.aEmail = :aEmail"),
    @NamedQuery(name = "Alumno.findByATelefono", query = "SELECT a FROM Alumno a WHERE a.aTelefono = :aTelefono"),
    @NamedQuery(name = "Alumno.findByADni", query = "SELECT a FROM Alumno a WHERE a.aDni = :aDni")})
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "A_CODIGO")
    private String aCodigo;
    @Column(name = "A_NOMBRE")
    private String aNombre;
    @Column(name = "A_APELLIDOS")
    private String aApellidos;
    @Column(name = "A_FNACIMIENTO")
    private String aFnacimiento;
    @Basic(optional = false)
    @Column(name = "A_DIRECCION")
    private String aDireccion;
    @Column(name = "A_EMAIL")
    private String aEmail;
    @Column(name = "A_TELEFONO")
    private BigInteger aTelefono;
    @Column(name = "A_DNI")
    private BigInteger aDni;
    @OneToMany(mappedBy = "aCodigo")
    private List<Tutoria> tutoriaList;

    public Alumno() {
    }

    public Alumno(String aCodigo) {
        this.aCodigo = aCodigo;
    }

    public Alumno(String aCodigo, String aDireccion) {
        this.aCodigo = aCodigo;
        this.aDireccion = aDireccion;
    }

    public String getACodigo() {
        return aCodigo;
    }

    public void setACodigo(String aCodigo) {
        this.aCodigo = aCodigo;
    }

    public String getANombre() {
        return aNombre;
    }

    public void setANombre(String aNombre) {
        this.aNombre = aNombre;
    }

    public String getAApellidos() {
        return aApellidos;
    }

    public void setAApellidos(String aApellidos) {
        this.aApellidos = aApellidos;
    }

    public String getAFnacimiento() {
        return aFnacimiento;
    }

    public void setAFnacimiento(String aFnacimiento) {
        this.aFnacimiento = aFnacimiento;
    }

    public String getADireccion() {
        return aDireccion;
    }

    public void setADireccion(String aDireccion) {
        this.aDireccion = aDireccion;
    }

    public String getAEmail() {
        return aEmail;
    }

    public void setAEmail(String aEmail) {
        this.aEmail = aEmail;
    }

    public BigInteger getATelefono() {
        return aTelefono;
    }

    public void setATelefono(BigInteger aTelefono) {
        this.aTelefono = aTelefono;
    }

    public BigInteger getADni() {
        return aDni;
    }

    public void setADni(BigInteger aDni) {
        this.aDni = aDni;
    }

    @XmlTransient
    public List<Tutoria> getTutoriaList() {
        return tutoriaList;
    }

    public void setTutoriaList(List<Tutoria> tutoriaList) {
        this.tutoriaList = tutoriaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aCodigo != null ? aCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.aCodigo == null && other.aCodigo != null) || (this.aCodigo != null && !this.aCodigo.equals(other.aCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DBTUTORIA.Alumno[ aCodigo=" + aCodigo + " ]";
    }
    
}
