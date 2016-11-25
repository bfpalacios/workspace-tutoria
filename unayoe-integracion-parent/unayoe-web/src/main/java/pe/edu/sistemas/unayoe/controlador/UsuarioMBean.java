package pe.edu.sistemas.unayoe.controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pe.edu.sistemas.unayoe.model.UsuarioModel;
import pe.edu.sistemas.unayoe.services.ComunServices;
import pe.edu.sistemas.unayoe.services.UsuarioServices;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;

// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioMBean.
 * Esta clase se encarga de gestionar el mantenimiento de usuarios
 * a traves de los services
 * desde todos los modulos observados, regulares y pares
 * por parte de los admin ADMINOBS, ADMINREG y ADMINPAR
 * 
 */
@Controller("usuarioMBean")
@ViewScoped
public class UsuarioMBean {

	/** The usuario model. */
	@Autowired
	private UsuarioModel usuarioModel;
	
	/** The usuario services. */
	@Autowired
	private UsuarioServices usuarioServices;
	
	/** The comun services. */
	@Autowired
	private ComunServices comunServices;

	/** The btn guardar. */
	private UIComponent btnGuardar;
	
	/** The usuario model select. */
	private UsuarioModel usuarioModelSelect;

	/** The modo usuario. */
	private int MODO_USUARIO;
	
	/** The modo admin. */
	private static int MODO_ADMIN = 1;
	
	/** The modo ocaa. */
	private static int MODO_OCAA = 2;
	
	/** The proceso. */
	private int PROCESO;
	
	/** The proceso observados. */
	private static int PROCESO_OBSERVADOS = 1;
	
	/** The proceso regulares. */
	private static int PROCESO_REGULARES = 2;
	
	/** The proceso par. */
	private static int PROCESO_PAR = 3;
	
	/** The rol alumno regular. */
	private static int ROL_ALUMNO_REGULAR = 11;

	/** The Constant PATTERN_EMAIL. */
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	/** The Constant PATTERN_STRING. */
	private static final String PATTERN_STRING = "([a-z]|[A-Z]|\\s)+";

	/** The es alumno. */
	private boolean esAlumno = true;

	/**
	 * Instantiates a new usuario M bean.
	 */
	public UsuarioMBean() {
		usuarioModel = new UsuarioModel();
	}

	/**
	 * Limpiar campos.
	 */
	private void limpiarCampos() {
		if (getUsuarioModel() != null) {
			setUsuarioModel(null);
			setUsuarioModel(new UsuarioModel());
		}
	}

	/**
	 * Llenar roles observados.
	 */
	private void llenarRolesObservados() {
		List<UsuarioBO> usuarioRoles = new ArrayList<UsuarioBO>();
		try {
			usuarioRoles = usuarioServices.obtenerRoles(PROCESO_OBSERVADOS);
			getUsuarioModel().setRolesUsuario(usuarioRoles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Llenar roles pares.
	 */
	private void llenarRolesPares() {
		List<UsuarioBO> usuarioRoles = new ArrayList<UsuarioBO>();
		try {
			usuarioRoles = usuarioServices.obtenerRoles(PROCESO_PAR);
			getUsuarioModel().setRolesUsuario(usuarioRoles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Llenar roles regulares.
	 */
	private void llenarRolesRegulares() {
		List<UsuarioBO> usuarioRoles = new ArrayList<UsuarioBO>();
		try {
			usuarioRoles = usuarioServices.obtenerRoles(PROCESO_REGULARES);
			getUsuarioModel().setRolesUsuario(usuarioRoles);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listar planes.
	 *
	 * @throws Exception the exception
	 */
	public void listarPlanes() throws Exception {
		System.out.println("Listando los ciclos:");

		List<ClaseMaestra> listaPlanes = null;
		try {
			String tabla = "PLAN";
			String campo = "PLAN_ALUMNO";
			listaPlanes = comunServices.listarClaseMaestra(tabla, campo);
			usuarioModel.setListaPlanes(listaPlanes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Valida numero.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 */
	public boolean validaNumero(String valor) {
		boolean esNumerico = false;
		try {
			Integer.parseInt(valor);
			esNumerico = true;
		} catch (NumberFormatException nfe) {
			esNumerico = false;
		}
		return esNumerico;
	}

	/**
	 * Valida cadena.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 */
	public boolean validaCadena(String valor) {
		boolean esCadena = false;
		try {
			if (valor.matches(PATTERN_STRING)) {
				esCadena = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return esCadena;
	}

	/**
	 * Valida correo.
	 *
	 * @param correo the correo
	 * @return true, if successful
	 */
	public boolean validaCorreo(String correo) {
		boolean correoValido = false;
		try {
			Pattern pattern = Pattern.compile(PATTERN_EMAIL);
			Matcher matcher = pattern.matcher(correo);
			correoValido = matcher.matches();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return correoValido;
	}

	/**
	 * Activar alumno.
	 *
	 * @param e the e
	 * @throws Exception the exception
	 */
	public void activarAlumno(ValueChangeEvent e) throws Exception {
		int rolUsuario = Integer.parseInt((String) (e.getNewValue() == null ? "0" : e.getNewValue()));
		if (rolUsuario == ROL_ALUMNO_REGULAR) {
			setEsAlumno(false);
		} else {
			setEsAlumno(true);
		}
	}

	/**
	 * Buscar usuario.
	 *
	 * @param usuario the usuario
	 * @return the string
	 */
	private String buscarUsuario(String usuario) {
		String codUsuario = "";
		try {
			codUsuario = usuarioServices.buscarUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codUsuario;
	}

	/**
	 * Guardar nuevo usuario MO.
	 *
	 * @return the string
	 */
	public String guardarNuevoUsuarioMO() {
		String pagina = "";
		try {
			if (buscarUsuario(getUsuarioModel().getIdUsuario() == null ? "0" : getUsuarioModel().getIdUsuario())
					.equals("")) {
				String nuevoUsuario = getUsuarioModel().getIdUsuario() == null ? "0" : getUsuarioModel().getIdUsuario();
				String contrasenia = getUsuarioModel().getClave() == null ? "0" : getUsuarioModel().getClave();
				int idRol = Integer.parseInt(usuarioModelSelect.getRol() == null ? "0" : usuarioModelSelect.getRol());
				String nombres = getUsuarioModel().getNombres() == null ? ""
						: validaCadena(getUsuarioModel().getNombres()) == true ? getUsuarioModel().getNombres()
								: "invalido";
				String apellidoPaterno = getUsuarioModel().getPaterno() == null ? ""
						: validaCadena(getUsuarioModel().getPaterno()) == true ? getUsuarioModel().getPaterno()
								: "invalido";
				String apellidoMaterno = getUsuarioModel().getMaterno() == null ? ""
						: validaCadena(getUsuarioModel().getMaterno()) == true ? getUsuarioModel().getMaterno()
								: "invalido";
				String correo = getUsuarioModel().getCorreo() == null ? ""
						: validaCorreo(getUsuarioModel().getCorreo()) == true ? getUsuarioModel().getCorreo()
								: "invalido";
				String direccion = getUsuarioModel().getDireccion() == null ? "" : getUsuarioModel().getDireccion();
				String telefono = getUsuarioModel().getTelefono() == null ? ""
						: validaNumero(getUsuarioModel().getTelefono()) == true ? getUsuarioModel().getTelefono()
								: "invalido";

				if (validarCampos(nombres, apellidoPaterno, apellidoMaterno, correo, telefono, "", 0) == true) {
					UsuarioBO usuarioNuevo = new UsuarioBO();
					usuarioNuevo.setIdUsuario(nuevoUsuario);
					usuarioNuevo.setContrasenia(contrasenia);
					usuarioNuevo.setNombres(nombres);
					usuarioNuevo.setApellidoPaterno(apellidoPaterno);
					usuarioNuevo.setApellidoMaterno(apellidoMaterno);
					usuarioNuevo.setCorreo(correo);
					usuarioNuevo.setDireccion(direccion);
					usuarioNuevo.setTelefono(telefono);
					usuarioNuevo.setIdRol(String.valueOf(idRol));

					usuarioServices.grabarUsuarioObservados(usuarioNuevo);
					limpiarCampos();
					mostrarMensaje(8);
				}
			} else {
				mostrarMensaje(7);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mostrarMensaje(9);
		}
		limpiarObjetos();
		llenarRolesObservados();

		switch (PROCESO) {
		case 1:
			switch (MODO_USUARIO) {
			case 1:
				pagina = "/paginas/ModuloObservados/admin/mantenimiento/usuario/nuevoUsuarioMO.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloObservados/ocaa/mantenimiento/usuario/nuevoUsuarioMO.xhtml";
				break;
			}

		case 2:
			switch (MODO_USUARIO) {
			case 1:
				pagina = "/paginas/ModuloRegulares/admin/mantenimiento/usuario/nuevoUsuarioMR.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/ocaa/mantenimiento/usuario/nuevoUsuarioMR.xhtml";
				break;
			}
		case 3:
			switch (MODO_USUARIO) {
			case 1:
				pagina = "/paginas/ModuloPares/";
				break;
			}
		}
		return pagina;
	}

	/**
	 * Guardar nuevo usuario MR.
	 *
	 * @return the string
	 * @throws Exception the exception
	 */
	public String guardarNuevoUsuarioMR() throws Exception {
		String pagina = "";
		try {
			if (buscarUsuario(getUsuarioModel().getIdUsuario() == null ? "0" : getUsuarioModel().getIdUsuario())
					.equals("")) {
				String codAlumno = "0";
				int planAlumno = 0;

				String nuevoUsuario = getUsuarioModel().getIdUsuario() == null ? "0" : getUsuarioModel().getIdUsuario();
				String contrasenia = getUsuarioModel().getClave() == null ? "0" : getUsuarioModel().getClave();
				int idRol = Integer.parseInt(usuarioModelSelect.getRol() == null ? "0" : usuarioModelSelect.getRol());
				String nombres = getUsuarioModel().getNombres() == null ? ""
						: validaCadena(getUsuarioModel().getNombres()) == true ? getUsuarioModel().getNombres()
								: "invalido";
				String apellidoPaterno = getUsuarioModel().getPaterno() == null ? ""
						: validaCadena(getUsuarioModel().getPaterno()) == true ? getUsuarioModel().getPaterno()
								: "invalido";
				String apellidoMaterno = getUsuarioModel().getMaterno() == null ? ""
						: validaCadena(getUsuarioModel().getMaterno()) == true ? getUsuarioModel().getMaterno()
								: "invalido";
				String correo = getUsuarioModel().getCorreo() == null ? ""
						: validaCorreo(getUsuarioModel().getCorreo()) == true ? getUsuarioModel().getCorreo()
								: "invalido";
				String direccion = getUsuarioModel().getDireccion() == null ? "" : getUsuarioModel().getDireccion();
				String telefono = getUsuarioModel().getTelefono() == null ? ""
						: validaNumero(getUsuarioModel().getTelefono()) == true ? getUsuarioModel().getTelefono()
								: "invalido";

				if (idRol == ROL_ALUMNO_REGULAR) {
					codAlumno = getUsuarioModel().getCodAlumno() == null ? "0"
							: validaNumero(getUsuarioModel().getCodAlumno()) == true ? getUsuarioModel().getCodAlumno()
									: "invalido";
					planAlumno = usuarioModelSelect.getPlanAlumno() == 0 ? 0 : usuarioModelSelect.getPlanAlumno();
				}

				if (validarCampos(nombres, apellidoPaterno, apellidoMaterno, correo, telefono, codAlumno,
						idRol) == true) {
					UsuarioBO usuarioNuevo = new UsuarioBO();
					usuarioNuevo.setIdUsuario(nuevoUsuario);
					usuarioNuevo.setContrasenia(contrasenia);
					usuarioNuevo.setNombres(nombres);
					usuarioNuevo.setApellidoPaterno(apellidoPaterno);
					usuarioNuevo.setApellidoMaterno(apellidoMaterno);
					usuarioNuevo.setCorreo(correo);
					usuarioNuevo.setDireccion(direccion);
					usuarioNuevo.setTelefono(telefono);
					usuarioNuevo.setIdRol(String.valueOf(idRol));
					if (idRol == ROL_ALUMNO_REGULAR) {
						usuarioNuevo.setCodAlumno(codAlumno);
						usuarioNuevo.setPlanAlumno(planAlumno);
					}

					usuarioServices.grabarUsuarioRegulares(usuarioNuevo);
					limpiarCampos();
					mostrarMensaje(8);
				}
			} else {
				mostrarMensaje(7);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mostrarMensaje(9);
		}
		limpiarObjetos();
		listarPlanes();
		llenarRolesRegulares();

		switch (PROCESO) {
		case 1:
			switch (MODO_USUARIO) {
			case 1:
				pagina = "/paginas/ModuloObservados/admin/mantenimiento/usuario/nuevoUsuarioMO.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloObservados/ocaa/mantenimiento/usuario/nuevoUsuarioMO.xhtml";
				break;
			}

		case 2:
			switch (MODO_USUARIO) {
			case 1:
				pagina = "/paginas/ModuloRegulares/admin/mantenimiento/usuario/nuevoUsuarioMR.xhtml";
				break;
			case 2:
				pagina = "/paginas/ModuloRegulares/ocaa/mantenimiento/usuario/nuevoUsuarioMR.xhtml";
				break;
			}
		}
		return pagina;
	}

	/**
	 * Guardar nuevo usuario MP.
	 *
	 * @return the string
	 */
	public String guardarNuevoUsuarioMP() {
		String pagina = "";
		try {
			if (buscarUsuario(getUsuarioModel().getIdUsuario() == null ? "0" : getUsuarioModel().getIdUsuario())
					.equals("")) {
				String nuevoUsuario = getUsuarioModel().getIdUsuario() == null ? "0" : getUsuarioModel().getIdUsuario();
				String contrasenia = getUsuarioModel().getClave() == null ? "0" : getUsuarioModel().getClave();
				int idRol = Integer.parseInt(usuarioModelSelect.getRol() == null ? "0" : usuarioModelSelect.getRol());
				String nombres = getUsuarioModel().getNombres() == null ? ""
						: validaCadena(getUsuarioModel().getNombres()) == true ? getUsuarioModel().getNombres()
								: "invalido";
				String apellidoPaterno = getUsuarioModel().getPaterno() == null ? ""
						: validaCadena(getUsuarioModel().getPaterno()) == true ? getUsuarioModel().getPaterno()
								: "invalido";
				String apellidoMaterno = getUsuarioModel().getMaterno() == null ? ""
						: validaCadena(getUsuarioModel().getMaterno()) == true ? getUsuarioModel().getMaterno()
								: "invalido";
				String correo = getUsuarioModel().getCorreo() == null ? ""
						: validaCorreo(getUsuarioModel().getCorreo()) == true ? getUsuarioModel().getCorreo()
								: "invalido";
				String direccion = getUsuarioModel().getDireccion() == null ? "" : getUsuarioModel().getDireccion();
				String telefono = getUsuarioModel().getTelefono() == null ? ""
						: validaNumero(getUsuarioModel().getTelefono()) == true ? getUsuarioModel().getTelefono()
								: "invalido";

				if (validarCampos(nombres, apellidoPaterno, apellidoMaterno, correo, telefono, "", 0) == true) {
					UsuarioBO usuarioNuevo = new UsuarioBO();
					usuarioNuevo.setIdUsuario(nuevoUsuario);
					usuarioNuevo.setContrasenia(contrasenia);
					usuarioNuevo.setNombres(nombres);
					usuarioNuevo.setApellidoPaterno(apellidoPaterno);
					usuarioNuevo.setApellidoMaterno(apellidoMaterno);
					usuarioNuevo.setCorreo(correo);
					usuarioNuevo.setDireccion(direccion);
					usuarioNuevo.setTelefono(telefono);
					usuarioNuevo.setIdRol(String.valueOf(idRol));

					usuarioServices.grabarUsuarioObservados(usuarioNuevo);
					limpiarCampos();
					mostrarMensaje(8);
				}
			} else {
				mostrarMensaje(7);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mostrarMensaje(9);
		}
		limpiarObjetos();
		llenarRolesPares();

		switch (PROCESO) {
		case 3:
			switch (MODO_USUARIO) {
			case 1:
				pagina = "/paginas/ModuloPares/admin/mantenimiento/usuario/nuevoUsuarioMP.xhtml";
				break;
			}
		}
		return pagina;
	}

	/**
	 * Validar campos.
	 *
	 * @param nombres the nombres
	 * @param apellidoPaterno the apellido paterno
	 * @param apellidoMaterno the apellido materno
	 * @param correo the correo
	 * @param telefono the telefono
	 * @param codAlumno the cod alumno
	 * @param idRol the id rol
	 * @return true, if successful
	 */
	private boolean validarCampos(String nombres, String apellidoPaterno, String apellidoMaterno, String correo,
			String telefono, String codAlumno, int idRol) {
		boolean apto = true;

		if (nombres == "invalido") {
			mostrarMensaje(1);
			apto = false;
		}

		if (apellidoPaterno == "invalido") {
			mostrarMensaje(2);
			apto = false;
		}

		if (apellidoMaterno == "invalido") {
			mostrarMensaje(3);
			apto = false;
		}

		if (correo == "invalido") {
			mostrarMensaje(4);
			apto = false;
		}

		if (telefono == "invalido") {
			mostrarMensaje(5);
			apto = false;
		}

		if (idRol == ROL_ALUMNO_REGULAR) {
			if (codAlumno == "invalido") {
				mostrarMensaje(6);
				apto = false;
			}
		}
		setEsAlumno(true);
		return apto;
	}

	/**
	 * Mostrar mensaje.
	 *
	 * @param opcionMensaje the opcion mensaje
	 */
	private void mostrarMensaje(int opcionMensaje) {
		FacesMessage message = null;

		switch (opcionMensaje) {
		case 1:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Debe ingresar sólo caracteres en el campo - " + "Nombres");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 2:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Debe ingresar sólo caracteres en el campo - " + "Apellido Paterno");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 3:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Debe ingresar sólo caracteres en el campo - " + "Apellido Materno");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 4:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Debe ingresar un correo válido en el campo - " + "Correo");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 5:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Debe ingresar sólo números en el campo - " + "Teléfono");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 6:
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
					"Debe ingresar sólo números en el campo - " + "Código del alumno");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 7:
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", "El usuario ingresado ya ha sido registrado");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 8:
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", "El usuario se guardo correctamente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		case 9:
			message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "Hubo un error al guardar el usuario");
			FacesContext.getCurrentInstance().addMessage(null, message);
			break;
		}
	}

	/**
	 * Limpiar objetos.
	 */
	private void limpiarObjetos() {
		setUsuarioModel(null);
		setUsuarioModel(new UsuarioModel());
	}

	/**
	 * Selector registro usuarios.
	 *
	 * @param proceso the proceso
	 * @param modo the modo
	 * @return the string
	 * @throws Exception the exception
	 */
	public String selectorRegistroUsuarios(int proceso, int modo) throws Exception {
		String pagina = "";
		setUsuarioModelSelect(new UsuarioModel());
		switch (proceso) {
		case 1:
			switch (modo) {
			case 1:
				PROCESO = PROCESO_OBSERVADOS;
				MODO_USUARIO = MODO_ADMIN;
				llenarRolesObservados();
				pagina = "/paginas/ModuloObservados/admin/mantenimiento/usuario/nuevoUsuarioMO.xhtml";
				break;

			case 2:
				PROCESO = PROCESO_OBSERVADOS;
				MODO_USUARIO = MODO_OCAA;
				llenarRolesObservados();
				pagina = "/paginas/ModuloObservados/ocaa/mantenimiento/usuario/nuevoUsuarioMO.xhtml";
				break;
			}
			break;
		case 2:
			switch (modo) {
			case 1:
				PROCESO = PROCESO_REGULARES;
				MODO_USUARIO = MODO_ADMIN;
				llenarRolesRegulares();
				listarPlanes();
				setEsAlumno(true);
				pagina = "/paginas/ModuloRegulares/admin/mantenimiento/usuario/nuevoUsuarioMR.xhtml";
				break;

			case 2:
				MODO_USUARIO = MODO_OCAA;
				llenarRolesRegulares();
				listarPlanes();
				setEsAlumno(true);
				pagina = "/paginas/ModuloRegulares/ocaa/mantenimiento/usuario/nuevoUsuarioMR.xhtml";
				break;
			}
			break;
		case 3:
			switch (modo) {
			case 1:
				PROCESO = PROCESO_PAR;
				MODO_USUARIO = MODO_ADMIN;
				llenarRolesPares();
				pagina = "/paginas/ModuloPares/admin/mantenimiento/usuario/nuevoUsuarioMP.xhtml";
				break;
			}
			break;
		}
		return pagina;
	}

	/**
	 * Gets the usuario model.
	 *
	 * @return the usuario model
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * Sets the usuario model.
	 *
	 * @param usuarioModel the new usuario model
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	/**
	 * Gets the usuario model select.
	 *
	 * @return the usuario model select
	 */
	public UsuarioModel getUsuarioModelSelect() {
		return usuarioModelSelect;
	}

	/**
	 * Sets the usuario model select.
	 *
	 * @param usuarioModelSelect the new usuario model select
	 */
	public void setUsuarioModelSelect(UsuarioModel usuarioModelSelect) {
		this.usuarioModelSelect = usuarioModelSelect;
	}

	/**
	 * Checks if is es alumno.
	 *
	 * @return true, if is es alumno
	 */
	public boolean isEsAlumno() {
		return esAlumno;
	}

	/**
	 * Sets the es alumno.
	 *
	 * @param esAlumno the new es alumno
	 */
	public void setEsAlumno(boolean esAlumno) {
		this.esAlumno = esAlumno;
	}

	/**
	 * Gets the btn guardar.
	 *
	 * @return the btn guardar
	 */
	public UIComponent getBtnGuardar() {
		return btnGuardar;
	}

	/**
	 * Sets the btn guardar.
	 *
	 * @param btnGuardar the new btn guardar
	 */
	public void setBtnGuardar(UIComponent btnGuardar) {
		this.btnGuardar = btnGuardar;
	}
}
