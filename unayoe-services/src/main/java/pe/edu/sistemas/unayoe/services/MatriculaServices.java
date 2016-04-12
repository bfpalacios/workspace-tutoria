package pe.edu.sistemas.unayoe.services;

import java.util.List;

import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.GrupoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;

public interface MatriculaServices {
	
	public int  guardarMatriculas(List<MatriculaBO> lista) throws Exception;
	
	public List<MatriculaBO> obtenerMatriculaAlumnoPorPeriodo(String alumno ,Integer anio ,Integer periodo) throws Exception;

}
