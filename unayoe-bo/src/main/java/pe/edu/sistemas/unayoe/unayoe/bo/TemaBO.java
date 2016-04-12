package pe.edu.sistemas.unayoe.unayoe.bo;

public class TemaBO {

	private Integer id;
	private String nombre;
	private String descripcion;
	private String codigoCurso;

	public TemaBO() {
		this.id = null;
		this.nombre = null;
		this.descripcion = null;
		this.codigoCurso = null;
	}

	public TemaBO(Integer id, String nombre, String descripcion, String codigoCurso) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigoCurso = codigoCurso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
}
