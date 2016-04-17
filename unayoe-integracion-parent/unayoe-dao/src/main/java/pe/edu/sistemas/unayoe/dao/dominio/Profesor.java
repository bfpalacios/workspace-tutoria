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

/**
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
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "P_CODIGO")
    private String pCodigo;
    @Column(name = "P_NOMBRE")
    private String pNombre;
    @Column(name = "P_APELLIDOS")
    private String pApellidos;
    @Column(name = "P_FNACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pFnacimiento;
    @Column(name = "P_DIRECCION")
    private String pDireccion;
    @Column(name = "P_EMAIL")
    private String pEmail;
    @Column(name = "P_TELEFONO")
    private BigInteger pTelefono;
    @Basic(optional = false)
    @Column(name = "P_DNI")
    private BigInteger pDni;
    @OneToMany(mappedBy = "pCodigo")
    private List<Tutoria> tutoriaList;

    public Profesor() {
    }

    public Profesor(String pCodigo) {
        this.pCodigo = pCodigo;
    }

    public Profesor(String pCodigo, BigInteger pDni) {
        this.pCodigo = pCodigo;
        this.pDni = pDni;
    }

    public String getPCodigo() {
        return pCodigo;
    }

    public void setPCodigo(String pCodigo) {
        this.pCodigo = pCodigo;
    }

    public String getPNombre() {
        return pNombre;
    }

    public void setPNombre(String pNombre) {
        this.pNombre = pNombre;
    }

    public String getPApellidos() {
        return pApellidos;
    }

    public void setPApellidos(String pApellidos) {
        this.pApellidos = pApellidos;
    }

    public Date getPFnacimiento() {
        return pFnacimiento;
    }

    public void setPFnacimiento(Date pFnacimiento) {
        this.pFnacimiento = pFnacimiento;
    }

    public String getPDireccion() {
        return pDireccion;
    }

    public void setPDireccion(String pDireccion) {
        this.pDireccion = pDireccion;
    }

    public String getPEmail() {
        return pEmail;
    }

    public void setPEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public BigInteger getPTelefono() {
        return pTelefono;
    }

    public void setPTelefono(BigInteger pTelefono) {
        this.pTelefono = pTelefono;
    }

    public BigInteger getPDni() {
        return pDni;
    }

    public void setPDni(BigInteger pDni) {
        this.pDni = pDni;
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
        hash += (pCodigo != null ? pCodigo.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "DBTUTORIA.Profesor[ pCodigo=" + pCodigo + " ]";
    }
    
}
