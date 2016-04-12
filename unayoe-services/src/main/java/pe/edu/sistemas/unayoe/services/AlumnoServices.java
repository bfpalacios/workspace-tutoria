package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.NotasAlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;


public interface AlumnoServices {	
	public AlumnoBO obtenerAlumno(String usuario) throws Exception;
	public String buscarCicloAcademico(int codCiclo) throws Exception;
	public void guardarAlumnos(List<AlumnoBO> lista) throws Exception;
	public void guardarNotas(NotasAlumnoBO notasAlumno) throws Exception;
	public List<CursoBO> listarCursosDocente(String usuarioDocente) throws Exception;
	public List<ClaseMaestra> listarCiclo(String tabla, String campo) throws Exception;
	public List<NotasAlumnoBO> buscarNotasAlumno(int ciclo, String codAlumno) throws Exception;
	public List<NotasAlumnoBO> buscarNotasAlumnoTutoria(int anio, int periodo, String codAlumno) throws Exception;	
	public List<AsistenciaCAlumnoBO>  buscarAsistenciaDeAlumnosClases(String fecha, String codigoCurso) throws Exception;
	public void actualizarListaAsistenciaClases(List<AsistenciaCAlumnoBO> lista, String curso, String fecha) throws Exception;
	public List<AlumnoBO> listarAlumnoTutoria(String usuarioDocente, String codCurso, int tipoAlumno, int modo) throws Exception;
	public int validarCargaNotas(int anio, int periodo, int plan, String cod_curso, String cod_alumno, int nota_final) throws Exception;
}
