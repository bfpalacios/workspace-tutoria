package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.CicloBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.IndicadoresBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;

public interface ComunServices {
	CicloBO buscarCicloActual() throws Exception;
	MatriculaBO buscarCiclo(int codCiclo) throws Exception;
	CursoBO buscarDatosCurso(String codCurso) throws Exception;
	IndicadoresBO buscarIndicador(int codIndicador) throws Exception;
	int obtenerUltimaSesionTutoria(String codTutoria) throws Exception;
	ClaseMaestra obtenerDatosFrecuencia(int codFrecuencia) throws Exception;
	List<ClaseMaestra> actualizarHoraFin(Integer idHoraInicio) throws Exception;
	AreaConocimientoBO buscarDatosAreaConocimiento(String codArea) throws Exception;
	List<ClaseMaestra> listarClaseMaestra(String tabla, String campo) throws Exception;
	TutoriaBO obtenerSesionTutoria(int anio, int periodo, String codCurso, String codTutor, String codAlumno, int sesionTutoria, int modo, int tipoAlumno) throws Exception;
	List<ClaseMaestra> listarTablaMaestra(String tabla, String campo) throws Exception;
}
