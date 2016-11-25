/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.edu.sistemas.unayoe.dao.dominio;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

// TODO: Auto-generated Javadoc
/**
 * The Class Profesor.
 *
 * @author bpalacios
 */
@Entity
@Table(name = "PROFESOR", catalog = "", schema = "DBTUTORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p"),
    @NamedQuery(name = "Profesor.findByPCodigo", query = "SELECT p FROM Profesor p WHERE p.pCodigo = :pCodigo"),
    @NamedQuery(name = "Profesor.findByPNombre", query = "SELECT p FROM Profesor p WHERE p.pNombre = :pNombre"),
    @NamedQuery(name = "Profesor.findByPApellidos", query = "SELECT p FROM Profesor p WHERE p.pApellidos = :pApellidos"),
    @NamedQuery(name = "Profesor.findByPFnacimiento", query = "SELECT p FROM Profesor p WHERE p.pFnacimiento = :pFnacimiento"),
    @NamedQuery(name = "Profesor.findByPDireccion", query = "SELECT p FROM Profesor p WHERE p.pDireccion = :pDireccion"),
    @NamedQuery(name = "Profesor.findByPEmail", query = "SELECT p FROM Profesor p WHERE p.pEmail = :pEmail"),
    @NamedQuery(name = "Profesor.findByPTelefono", query = "SELECT p FROM Profesor p WHERE p.pTelefono = :pTelefono"),
    @NamedQuery(name = "Profesor.findByPDni", query = "SELECT p FROM Profesor p WHERE p.pDni = :pDni")})
public class Profesor implements Serializable {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The p codigo. */
    @Id
    @Basic(optional = false)
    @Column(name = "P_CODIGO")
    private String pCodigo;
    
    /** The p nombre. */
    @Column(name = "P_NOMBRE")
    private String pNombre;
    
    /** The p apellidos. */
    @Column(name = "P_APELLIDOS")
    private String pApellidos;
    
    /** The p fnacimiento. */
    @Column(name = "P_FNACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pFnacimiento;
    
    /** The p direccion. */
    @Column(name = "P_DIRECCION")
    private String pDireccion;
    
    /** The p email. */
    @Column(name = "P_EMAIL")
    private String pEmail;
    
    /** The p telefono. */
    @Column(name = "P_TELEFONO")
    private BigInteger pTelefono;
    
    /** The p dni. */
    @Basic(optional = false)
    @Column(name = "P_DNI")
    private BigInteger pDni;
    
    /** The tutoria list. */
    @OneToMany(mappedBy = "pCodigo")
    private List<Tutoria> tutoriaList;

    /**
     * Instantiates a new profesor.
     */
    public Profesor() {
    }

    /**
     * Instantiates a new profesor.
     *
     * @param pCodigo the codigo
     */
    public Profesor(String pCodigo) {
        this.pCodigo = pCodigo;
    }

    /**
     * Instantiates a new profesor.
     *
     * @param pCodigo the codigo
     * @param pDni the dni
     */
    public Profesor(String pCodigo, BigInteger pDni) {
        this.pCodigo = pCodigo;
        this.pDni = pDni;
    }

    /**
     * Gets the p codigo.
     *
     * @return the p codigo
     */
    public String getPCodigo() {
        return pCodigo;
    }

    /**
     * Sets the p codigo.
     *
     * @param pCodigo the new p codigo
     */
    public void setPCodigo(String pCodigo) {
        this.pCodigo = pCodigo;
    }

    /**
     * Gets the p nombre.
     *
     * @return the p nombre
     */
    public String getPNombre() {
        return pNombre;
    }

    /**
     * Sets the p nombre.
     *
     * @param pNombre the new p nombre
     */
    public void setPNombre(String pNombre) {
        this.pNombre = pNombre;
    }

    /**
     * Gets the p apellidos.
     *
     * @return the p apellidos
     */
    public String getPApellidos() {
        return pApellidos;
    }

    /**
     * Sets the p apellidos.
     *
     * @param pApellidos the new p apellidos
     */
    public void setPApellidos(String pApellidos) {
        this.pApellidos = pApellidos;
    }

    /**
     * Gets the p fnacimiento.
     *
     * @return the p fnacimiento
     */
    public Date getPFnacimiento() {
        return pFnacimiento;
    }

    /**
     * Sets the p fnacimiento.
     *
     * @param pFnacimiento the new p fnacimiento
     */
    public void setPFnacimiento(Date pFnacimiento) {
        this.pFnacimiento = pFnacimiento;
    }

    /**
     * Gets the p direccion.
     *
     * @return the p direccion
     */
    public String getPDireccion() {
        return pDireccion;
    }

    /**
     * Sets the p direccion.
     *
     * @param pDireccion the new p direccion
     */
    public void setPDireccion(String pDireccion) {
        this.pDireccion = pDireccion;
    }

    /**
     * Gets the p email.
     *
     * @return the p email
     */
    public String getPEmail() {
        return pEmail;
    }

    /**
     * Sets the p email.
     *
     * @param pEmail the new p email
     */
    public void setPEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    /**
     * Gets the p telefono.
     *
     * @return the p telefono
     */
    public BigInteger getPTelefono() {
        return pTelefono;
    }

    /**
     * Sets the p telefono.
     *
     * @param pTelefono the new p telefono
     */
    public void setPTelefono(BigInteger pTelefono) {
        this.pTelefono = pTelefono;
    }

    /**
     * Gets the p dni.
     *
     * @return the p dni
     */
    public BigInteger getPDni() {
        return pDni;
    }

    /**
     * Sets the p dni.
     *
     * @param pDni the new p dni
     */
    public void setPDni(BigInteger pDni) {
        this.pDni = pDni;
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
        hash += (pCodigo != null ? pCodigo.hashCode() : 0);
        return hash;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.pCodigo == null && other.pCodigo != null) || (this.pCodigo != null && !this.pCodigo.equals(other.pCodigo))) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DBTUTORIA.Profesor[ pCodigo=" + pCodigo + " ]";
    }
    
}
