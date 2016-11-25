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

// TODO: Auto-generated Javadoc
/**
 * The Class ComunServicesImpl.
 */
@Service("comunServices")
public class ComunServicesImpl implements ComunServices{
	
	/** The comun DAO. */
	private ComunDAOImpl comunDAO;
	
	/**
	 * Instantiates a new comun services impl.
	 */
	public ComunServicesImpl(){
		setComunDAO(new ComunDAOImpl());
	}	
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ComunServices#buscarCiclo(int)
	 */
	public MatriculaBO buscarCiclo(int codCiclo){
		return comunDAO.buscarCiclo(codCiclo);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ComunServices#listarClaseMaestra(java.lang.String, java.lang.String)
	 */
	public List<ClaseMaestra> listarClaseMaestra(String tabla, String campo) {
		return getComunDAO().llenarCombo(tabla, campo);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ComunServices#actualizarHoraFin(java.lang.Integer)
	 */
	public List<ClaseMaestra> actualizarHoraFin(Integer idHoraInicio){
		return getComunDAO().actualizarHoraFin(idHoraInicio);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ComunServices#buscarDatosAreaConocimiento(java.lang.String)
	 */
	public AreaConocimientoBO buscarDatosAreaConocimiento(String codArea) throws Exception{
		return getComunDAO().buscarDatosAreaConocimiento(codArea);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ComunServices#buscarDatosCurso(java.lang.String)
	 */
	public CursoBO buscarDatosCurso(String codCurso) throws Exception{
		return getComunDAO().buscarDatosCurso(codCurso);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ComunServices#buscarCicloActual()
	 */
	public CicloBO buscarCicloActual() throws Exception{
		return getComunDAO().buscarCicloActual();
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ComunServices#obtenerDatosFrecuencia(int)
	 */
	public ClaseMaestra obtenerDatosFrecuencia(int codFrecuencia){
		return getComunDAO().obtenerDatosFrecuencia(codFrecuencia);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ComunServices#obtenerSesionTutoria(int, int, java.lang.String, java.lang.String, java.lang.String, int, int, int)
	 */
	public TutoriaBO obtenerSesionTutoria(int anio, int periodo, String codCurso, String codTutor, String codAlumno, int sesionTutoria, int modo, int tipoAlumno) throws Exception{
		return getComunDAO().obtenerSesionTutoria(anio, periodo, codCurso, codTutor, codAlumno, sesionTutoria, modo, tipoAlumno);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ComunServices#listarTablaMaestra(java.lang.String, java.lang.String)
	 */
	public List<ClaseMaestra> listarTablaMaestra(String tabla, String campo) throws Exception {
		return getComunDAO().listarTablaMaestra(tabla, campo);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ComunServices#obtenerUltimaSesionTutoria(java.lang.String)
	 */
	public int obtenerUltimaSesionTutoria(String codTutoria) throws Exception{
		return getComunDAO().obtenerUltimaSesionTutoria(codTutoria);
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.services.ComunServices#buscarIndicador(int)
	 */
	public IndicadoresBO buscarIndicador(int codIndicador) throws Exception{
		return getComunDAO().buscarIndicador(codIndicador);
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
}
