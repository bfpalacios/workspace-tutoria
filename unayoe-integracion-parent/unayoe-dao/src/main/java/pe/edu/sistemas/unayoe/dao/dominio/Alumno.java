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

// TODO: Auto-generated Javadoc
/**
 * The Class Alumno.
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
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The a codigo. */
    @Id
    @Basic(optional = false)
    @Column(name = "A_CODIGO")
    private String aCodigo;
    
    /** The a nombre. */
    @Column(name = "A_NOMBRE")
    private String aNombre;
    
    /** The a apellidos. */
    @Column(name = "A_APELLIDOS")
    private String aApellidos;
    
    /** The a fnacimiento. */
    @Column(name = "A_FNACIMIENTO")
    private String aFnacimiento;
    
    /** The a direccion. */
    @Basic(optional = false)
    @Column(name = "A_DIRECCION")
    private String aDireccion;
    
    /** The a email. */
    @Column(name = "A_EMAIL")
    private String aEmail;
    
    /** The a telefono. */
    @Column(name = "A_TELEFONO")
    private BigInteger aTelefono;
    
    /** The a dni. */
    @Column(name = "A_DNI")
    private BigInteger aDni;
    
    /** The tutoria list. */
    @OneToMany(mappedBy = "aCodigo")
    private List<Tutoria> tutoriaList;

    /**
     * Instantiates a new alumno.
     */
    public Alumno() {
    }

    /**
     * Instantiates a new alumno.
     *
     * @param aCodigo the a codigo
     */
    public Alumno(String aCodigo) {
        this.aCodigo = aCodigo;
    }

    /**
     * Instantiates a new alumno.
     *
     * @param aCodigo the a codigo
     * @param aDireccion the a direccion
     */
    public Alumno(String aCodigo, String aDireccion) {
        this.aCodigo = aCodigo;
        this.aDireccion = aDireccion;
    }

    /**
     * Gets the a codigo.
     *
     * @return the a codigo
     */
    public String getACodigo() {
        return aCodigo;
    }

    /**
     * Sets the a codigo.
     *
     * @param aCodigo the new a codigo
     */
    public void setACodigo(String aCodigo) {
        this.aCodigo = aCodigo;
    }

    /**
     * Gets the a nombre.
     *
     * @return the a nombre
     */
    public String getANombre() {
        return aNombre;
    }

    /**
     * Sets the a nombre.
     *
     * @param aNombre the new a nombre
     */
    public void setANombre(String aNombre) {
        this.aNombre = aNombre;
    }

    /**
     * Gets the a apellidos.
     *
     * @return the a apellidos
     */
    public String getAApellidos() {
        return aApellidos;
    }

    /**
     * Sets the a apellidos.
     *
     * @param aApellidos the new a apellidos
     */
    public void setAApellidos(String aApellidos) {
        this.aApellidos = aApellidos;
    }

    /**
     * Gets the a fnacimiento.
     *
     * @return the a fnacimiento
     */
    public String getAFnacimiento() {
        return aFnacimiento;
    }

    /**
     * Sets the a fnacimiento.
     *
     * @param aFnacimiento the new a fnacimiento
     */
    public void setAFnacimiento(String aFnacimiento) {
        this.aFnacimiento = aFnacimiento;
    }

    /**
     * Gets the a direccion.
     *
     * @return the a direccion
     */
    public String getADireccion() {
        return aDireccion;
    }

    /**
     * Sets the a direccion.
     *
     * @param aDireccion the new a direccion
     */
    public void setADireccion(String aDireccion) {
        this.aDireccion = aDireccion;
    }

    /**
     * Gets the a email.
     *
     * @return the a email
     */
    public String getAEmail() {
        return aEmail;
    }

    /**
     * Sets the a email.
     *
     * @param aEmail the new a email
     */
    public void setAEmail(String aEmail) {
        this.aEmail = aEmail;
    }

    /**
     * Gets the a telefono.
     *
     * @return the a telefono
     */
    public BigInteger getATelefono() {
        return aTelefono;
    }

    /**
     * Sets the a telefono.
     *
     * @param aTelefono the new a telefono
     */
    public void setATelefono(BigInteger aTelefono) {
        this.aTelefono = aTelefono;
    }

    /**
     * Gets the a dni.
     *
     * @return the a dni
     */
    public BigInteger getADni() {
        return aDni;
    }

    /**
     * Sets the a dni.
     *
     * @param aDni the new a dni
     */
    public void setADni(BigInteger aDni) {
        this.aDni = aDni;
    }

    /**
     * Gets the tutoria list.
     *
     * @return the tutoria list
     */
    @XmlTransient
    public List<Tutoria> getTutoriaList() {
        return tutoriaList;
    }

    /**
     * Sets the tutoria list.
     *
     * @param tutoriaList the new tutoria list
     */
    public void setTutoriaList(List<Tutoria> tutoriaList) {
        this.tutoriaList = tutoriaList;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aCodigo != null ? aCodigo.hashCode() : 0);
        return hash;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DBTUTORIA.Alumno[ aCodigo=" + aCodigo + " ]";
    }
    
}
