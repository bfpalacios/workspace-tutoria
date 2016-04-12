package pe.edu.sistemas.unayoe.unayoe.bo;

import java.util.Date;

public class AlumnoParBO {

	private String codigo;
	private String nombre;
	private String apellidos;
	private Date fecha_nac;
	private String direccion;
	private String email;
	private String telefono;
	private String dni;
	private Integer plan;
	private boolean flag_tutor;

	public AlumnoParBO() {
		
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getDni() {
		return dni;
	}

	public String getEmail() {
		return email;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getPlan() {
		return plan;
	}

	public String getTelefono() {
		return telefono;
	}

	public boolean isFlag_tutor() {
		return flag_tutor;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public void setFlag_tutor(boolean flag_tutor) {
		this.flag_tutor = flag_tutor;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPlan(Integer plan) {
		this.plan = plan;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombreCompleto(){
		return nombre+" "+ apellidos;
	}
}
