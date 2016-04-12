package pe.edu.sistemas.unayoe.dao;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CicloBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.IndicadoresBO;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.UsuarioBO;

public interface ComunIDAO {
	public List<ClaseMaestra> llenarCombo(String tabla, String campo) throws Exception;
	public String buscarCicloAcademico(Integer idCiclo) throws Exception;
	public List<UsuarioBO> obtenerRoles(int proceso) throws Exception;
	public MatriculaBO buscarCiclo(Integer idCiclo) throws Exception;
	public List<ClaseMaestra> actualizarHoraFin(Integer idHoraInicio) throws Exception;
	public AreaConocimientoBO buscarDatosAreaConocimiento(String codArea) throws Exception;
	public CursoBO buscarDatosCurso(String codCurso) throws Exception;
	public CicloBO buscarCicloActual() throws Exception;
	public ClaseMaestra obtenerDatosFrecuencia(int codFrecuencia) throws Exception;
	public TutoriaBO obtenerSesionTutoria(int anio, int periodo, String codCurso, String codTutor, 
			                              String codAlumno, int sesionTutoria, int modo, int tipoAlumno) throws Exception;
	public int obtenerUltimaSesionTutoria(String codTutoria) throws Exception;
	public IndicadoresBO buscarIndicador(int codIndicador) throws Exception;
	public List<ClaseMaestra> listarTablaMaestra(String tabla, String campo) throws Exception;
}
