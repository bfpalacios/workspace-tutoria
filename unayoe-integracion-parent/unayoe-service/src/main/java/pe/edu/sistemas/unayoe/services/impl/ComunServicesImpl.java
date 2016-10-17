package pe.edu.sistemas.unayoe.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.services.ComunServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CicloBO;
import pe.edu.sistemas.unayoe.unayoe.bo.ClaseMaestra;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.IndicadoresBO;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;
import pe.edu.sistemas.unayoe.dao.jdbc.ComunDAOImpl;

@Service("comunServices")
public class ComunServicesImpl implements ComunServices{
	
	private ComunDAOImpl comunDAO;
	
	public ComunServicesImpl(){
		setComunDAO(new ComunDAOImpl());
	}	
	
	public MatriculaBO buscarCiclo(int codCiclo){
		return comunDAO.buscarCiclo(codCiclo);
	}

	public List<ClaseMaestra> listarClaseMaestra(String tabla, String campo) {
		return getComunDAO().llenarCombo(tabla, campo);
	}
	
	public List<ClaseMaestra> actualizarHoraFin(Integer idHoraInicio){
		return getComunDAO().actualizarHoraFin(idHoraInicio);
	}
	
	public AreaConocimientoBO buscarDatosAreaConocimiento(String codArea) throws Exception{
		return getComunDAO().buscarDatosAreaConocimiento(codArea);
	}
	
	public CursoBO buscarDatosCurso(String codCurso) throws Exception{
		return getComunDAO().buscarDatosCurso(codCurso);
	}
	
	public CicloBO buscarCicloActual() throws Exception{
		return getComunDAO().buscarCicloActual();
	}
	
	public ClaseMaestra obtenerDatosFrecuencia(int codFrecuencia){
		return getComunDAO().obtenerDatosFrecuencia(codFrecuencia);
	}
	
	public TutoriaBO obtenerSesionTutoria(int anio, int periodo, String codCurso, String codTutor, String codAlumno, int sesionTutoria, int modo, int tipoAlumno) throws Exception{
		return getComunDAO().obtenerSesionTutoria(anio, periodo, codCurso, codTutor, codAlumno, sesionTutoria, modo, tipoAlumno);
	}

	public List<ClaseMaestra> listarTablaMaestra(String tabla, String campo) throws Exception {
		return getComunDAO().listarTablaMaestra(tabla, campo);
	}

	public int obtenerUltimaSesionTutoria(String codTutoria) throws Exception{
		return getComunDAO().obtenerUltimaSesionTutoria(codTutoria);
	}
	
	public IndicadoresBO buscarIndicador(int codIndicador) throws Exception{
		return getComunDAO().buscarIndicador(codIndicador);
	}
	
	public ComunDAOImpl getComunDAO() {
		return comunDAO;
	}

	public void setComunDAO(ComunDAOImpl comunDAO) {
		this.comunDAO = comunDAO;
	}
}
