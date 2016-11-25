package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.NotasAlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;


// TODO: Auto-generated Javadoc
/**
 * The Interface AlumnoServices.
 */
public interface AlumnoServices {	
	
	/**
	 * Obtener alumno.
	 *
	 * @param usuario the usuario
	 * @return the alumno BO
	 * @throws Exception the exception
	 */
	public AlumnoBO obtenerAlumno(String usuario) throws Exception;
	
	/**
	 * Buscar ciclo academico.
	 *
	 * @param codCiclo the cod ciclo
	 * @return the string
	 * @throws Exception the exception
	 */
	public String buscarCicloAcademico(int codCiclo) throws Exception;
	
	/**
	 * Guardar alumnos.
	 *
	 * @param lista the lista
	 * @throws Exception the exception
	 */
	public void guardarAlumnos(List<AlumnoBO> lista) throws Exception;
	
	/**
	 * Guardar notas.
	 *
	 * @param notasAlumno the notas alumno
	 * @throws Exception the exception
	 */
	public void guardarNotas(NotasAlumnoBO notasAlumno) throws Exception;
	
	/**
	 * Listar cursos docente.
	 *
	 * @param usuarioDocente the usuario docente
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<CursoBO> listarCursosDocente(String usuarioDocente) throws Exception;
	
	/**
	 * Listar ciclo.
	 *
	 * @param tabla the tabla
	 * @param campo the campo
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ClaseMaestra> listarCiclo(String tabla, String campo) throws Exception;
	
	/**
	 * Buscar notas alumno.
	 *
	 * @param ciclo the ciclo
	 * @param codAlumno the cod alumno
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<NotasAlumnoBO> buscarNotasAlumno(int ciclo, String codAlumno) throws Exception;
	
	/**
	 * Buscar notas alumno tutoria.
	 *
	 * @param anio the anio
	 * @param periodo the periodo
	 * @param codAlumno the cod alumno
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<NotasAlumnoBO> buscarNotasAlumnoTutoria(int anio, int periodo, String codAlumno) throws Exception;	
	
	/**
	 * Buscar asistencia de alumnos clases.
	 *
	 * @param fecha the fecha
	 * @param codigoCurso the codigo curso
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<AsistenciaCAlumnoBO>  buscarAsistenciaDeAlumnosClases(String fecha, String codigoCurso) throws Exception;
	
	/**
	 * Actualizar lista asistencia clases.
	 *
	 * @param lista the lista
	 * @param curso the curso
	 * @param fecha the fecha
	 * @throws Exception the exception
	 */
	public void actualizarListaAsistenciaClases(List<AsistenciaCAlumnoBO> lista, String curso, String fecha) throws Exception;
	
	/**
	 * Listar alumno tutoria.
	 *
	 * @param usuarioDocente the usuario docente
	 * @param codCurso the cod curso
	 * @param tipoAlumno the tipo alumno
	 * @param modo the modo
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<AlumnoBO> listarAlumnoTutoria(String usuarioDocente, String codCurso, int tipoAlumno, int modo) throws Exception;
	
	/**
	 * Validar carga notas.
	 *
	 * @param anio the anio
	 * @param periodo the periodo
	 * @param plan the plan
	 * @param cod_curso the cod curso
	 * @param cod_alumno the cod alumno
	 * @param nota_final the nota final
	 * @return the int
	 * @throws Exception the exception
	 */
	public int validarCargaNotas(int anio, int periodo, int plan, String cod_curso, String cod_alumno, int nota_final) throws Exception;
	
	/**
	 * Listar alumno disponibilidad.
	 *
	 * @param codCurso the cod curso
	 * @return the list
	 */
	public List<AlumnoBO> listarAlumnoDisponibilidad(String codCurso);
}
