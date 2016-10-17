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

@Service("alumnoServices") 	
public class AlumnoServicesImpl implements AlumnoServices{
	
	@Autowired
	private AlumnoDAO alumnoDAO;
	@Autowired
	private AlumnoIDao alumnoIDao;
	@Autowired
	private AlumnoTransformerToBO alumnoTransformerToBO;	
	@Autowired
	private  AlumnoTransformerToENTIDAD  alumnoTransformerToENTIDAD;	 	
	
	private ComunDAOImpl comunDAO;
	private TutoriaDaoImpl tutoriaDAO;
	
	public AlumnoServicesImpl(){
		setComunDAO(new ComunDAOImpl());
		setTutoriaDAO(new TutoriaDaoImpl());
	}
	
	public AlumnoBO obtenerAlumno(String codigo) throws Exception {
		System.out.println("codigo  : "+ codigo);
		Alumno alumnoEntidad = alumnoIDao.obtenerAlumno(codigo);
		return alumnoTransformerToBO.transformer(alumnoEntidad);
	}
	
	public void  guardarAlumnos(List<AlumnoBO> lista)throws Exception {
		alumnoDAO.insertarLista(alumnoTransformerToENTIDAD.transformer(lista));
	}  

	public void actualizarListaAsistenciaClases(
			List<AsistenciaCAlumnoBO> lista, String curso, String fecha)
			throws Exception {
		//alumnoDAO.actualizarListaAsistencia(lista, curso, fecha);		
	} 

	public List<AsistenciaCAlumnoBO> buscarAsistenciaDeAlumnosClases(
			String fecha, String codigoCurso) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
 
	public int validarCargaNotas(int anio, int periodo, int plan, String cod_curso, String cod_alumno, int nota_final){
		int validacion = alumnoDAO.validarCargaNotas(anio, periodo, plan, cod_curso, cod_alumno, nota_final);
		return validacion;
	}
	
	public void guardarNotas(NotasAlumnoBO notasAlumno) throws Exception{
		alumnoDAO.guardarCargaNotas(notasAlumno);
	}
	
	public List<ClaseMaestra> listarCiclo(String tabla, String campo) throws Exception{
		return getComunDAO().llenarCombo(tabla, campo);
	}
	
	public List<NotasAlumnoBO> buscarNotasAlumno(int ciclo, String codAlumno) throws Exception{
		return alumnoDAO.buscarNotasAlumno(ciclo,codAlumno);
	}
	
	public String buscarCicloAcademico(int codCiclo){
		return getComunDAO().buscarCicloAcademico(codCiclo);
	}
	
	public List<CursoBO> listarCursosDocente(String usuarioDocente){
		return tutoriaDAO.listarCursosDocente(usuarioDocente);
	}
	
	public List<AlumnoBO> listarAlumnoTutoria(String usuarioDocente, String codCurso, int tipoAlumno, int modo){
		return tutoriaDAO.listarAlumnoTutoria(usuarioDocente, codCurso, tipoAlumno, modo);
	}

	public List<NotasAlumnoBO> buscarNotasAlumnoTutoria(int anio, int periodo, String codAlumno) throws Exception{
		return alumnoDAO.buscarNotasAlumnoTutoria(anio, periodo, codAlumno);
	}
	
	public ComunDAOImpl getComunDAO() {
		return comunDAO;
	}

	public void setComunDAO(ComunDAOImpl comunDAO) {
		this.comunDAO = comunDAO;
	}

	public TutoriaDaoImpl getTutoriaDAO() {
		return tutoriaDAO;
	}

	public void setTutoriaDAO(TutoriaDaoImpl tutoriaDAO) {
		this.tutoriaDAO = tutoriaDAO;
	}
}
