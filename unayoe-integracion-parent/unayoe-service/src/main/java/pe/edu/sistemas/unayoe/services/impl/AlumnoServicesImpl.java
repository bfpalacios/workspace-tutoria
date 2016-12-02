package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import pe.edu.sistemas.unayoe.dao.AlumnoIDao;
import pe.edu.sistemas.unayoe.dao.dominio.Alumno;
import pe.edu.sistemas.unayoe.dao.jdbc.AlumnoDAO;
import pe.edu.sistemas.unayoe.dao.jdbc.TutoriaDaoImpl;
import pe.edu.sistemas.unayoe.services.AlumnoServices; 
import pe.edu.sistemas.unayoe.services.transformer.AlumnoTransformerToBO;
import pe.edu.sistemas.unayoe.services.transformer.AlumnoTransformerToENTIDAD; 
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO; 
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO; 
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.NotasAlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.dao.jdbc.ComunDAOImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class AlumnoServicesImpl.
 */
@Service("alumnoServices") 	
public class AlumnoServicesImpl implements AlumnoServices{
	
	/** The alumno DAO. */
	@Autowired
	private AlumnoDAO alumnoDAO;
	
	/** The alumno I dao. */
	@Autowired
	private AlumnoIDao alumnoIDao;
	
	/** The alumno transformer to BO. */
	@Autowired
	private AlumnoTransformerToBO alumnoTransformerToBO;	
	
	/** The alumno transformer to ENTIDAD. */
	@Autowired
	private  AlumnoTransformerToENTIDAD  alumnoTransformerToENTIDAD;	 	
	
	/** The comun DAO. */
	private ComunDAOImpl comunDAO;
	
	/** The tutoria DAO. */
	private TutoriaDaoImpl tutoriaDAO;
	
	/**
	 * Instantiates a new alumno services impl.
	 */
	public AlumnoServicesImpl(){
		setComunDAO(new ComunDAOImpl());
		setTutoriaDAO(new TutoriaDaoImpl());
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#obtenerAlumno(java.lang.String)
	 */
	public AlumnoBO obtenerAlumno(String codigo) throws Exception {
		System.out.println("codigo  : "+ codigo);
		Alumno alumnoEntidad = alumnoIDao.obtenerAlumno(codigo);
		return alumnoTransformerToBO.transformer(alumnoEntidad);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#guardarAlumnos(java.util.List)
	 */
	public void  guardarAlumnos(List<AlumnoBO> lista)throws Exception {
		alumnoDAO.insertarLista(alumnoTransformerToENTIDAD.transformer(lista));
	}  

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#actualizarListaAsistenciaClases(java.util.List, java.lang.String, java.lang.String)
	 */
	public void actualizarListaAsistenciaClases(
			List<AsistenciaCAlumnoBO> lista, String curso, String fecha)
			throws Exception {
		//alumnoDAO.actualizarListaAsistencia(lista, curso, fecha);		
	} 

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#buscarAsistenciaDeAlumnosClases(java.lang.String, java.lang.String)
	 */
	public List<AsistenciaCAlumnoBO> buscarAsistenciaDeAlumnosClases(
			String fecha, String codigoCurso) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
 
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#validarCargaNotas(int, int, int, java.lang.String, java.lang.String, int)
	 */
	public int validarCargaNotas(int anio, int periodo, int plan, String cod_curso, String cod_alumno, int nota_final){
		int validacion = alumnoDAO.validarCargaNotas(anio, periodo, plan, cod_curso, cod_alumno, nota_final);
		return validacion;
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#guardarNotas(pe.edu.sistemas.unayoe.unayoe.bo.NotasAlumnoBO)
	 */
	public void guardarNotas(NotasAlumnoBO notasAlumno) throws Exception{
		alumnoDAO.guardarCargaNotas(notasAlumno);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#listarCiclo(java.lang.String, java.lang.String)
	 */
	public List<ClaseMaestra> listarCiclo(String tabla, String campo) throws Exception{
		return getComunDAO().llenarCombo(tabla, campo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#buscarNotasAlumno(int, java.lang.String)
	 */
	public List<NotasAlumnoBO> buscarNotasAlumno(int ciclo, String codAlumno) throws Exception{
		return alumnoDAO.buscarNotasAlumno(ciclo,codAlumno);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#buscarCicloAcademico(int)
	 */
	public String buscarCicloAcademico(int codCiclo){
		return getComunDAO().buscarCicloAcademico(codCiclo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#listarCursosDocente(java.lang.String)
	 */
	public List<CursoBO> listarCursosDocente(String usuarioDocente){
		return tutoriaDAO.listarCursosDocente(usuarioDocente);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#listarAlumnoTutoria(java.lang.String, java.lang.String, int, int)
	 */
	public List<AlumnoBO> listarAlumnoTutoria(String usuarioDocente, String codCurso, int tipoAlumno, int modo){
		if(modo==5){
			modo=1;
		}
		return tutoriaDAO.listarAlumnoTutoria(usuarioDocente, codCurso, tipoAlumno, modo);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#buscarNotasAlumnoTutoria(int, int, java.lang.String)
	 */
	public List<NotasAlumnoBO> buscarNotasAlumnoTutoria(int anio, int periodo, String codAlumno) throws Exception{
		return alumnoDAO.buscarNotasAlumnoTutoria(anio, periodo, codAlumno);
	}
	
	/**
	 * Gets the comun DAO.
	 *
	 * @return the comun DAO
	 */
	public ComunDAOImpl getComunDAO() {
		return comunDAO;
	}

	/**
	 * Sets the comun DAO.
	 *
	 * @param comunDAO the new comun DAO
	 */
	public void setComunDAO(ComunDAOImpl comunDAO) {
		this.comunDAO = comunDAO;
	}

	/**
	 * Gets the tutoria DAO.
	 *
	 * @return the tutoria DAO
	 */
	public TutoriaDaoImpl getTutoriaDAO() {
		return tutoriaDAO;
	}

	/**
	 * Sets the tutoria DAO.
	 *
	 * @param tutoriaDAO the new tutoria DAO
	 */
	public void setTutoriaDAO(TutoriaDaoImpl tutoriaDAO) {
		this.tutoriaDAO = tutoriaDAO;
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.AlumnoServices#listarAlumnoDisponibilidad(java.lang.String)
	 */
	@Override
	public List<AlumnoBO> listarAlumnoDisponibilidad(String codCurso) {
		return alumnoDAO.listarAlumnoDisponibilidad(codCurso);
	}
}
