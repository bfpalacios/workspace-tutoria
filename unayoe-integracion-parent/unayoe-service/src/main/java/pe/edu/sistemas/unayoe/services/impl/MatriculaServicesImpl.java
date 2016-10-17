package pe.edu.sistemas.unayoe.services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.core.util.DAOExcepcion;
import pe.edu.sistemas.unayoe.dao.CursoIDao;
import pe.edu.sistemas.unayoe.dao.dominio.Curso;
import pe.edu.sistemas.unayoe.dao.jdbc.AlumnoDAO;
import pe.edu.sistemas.unayoe.dao.jdbc.CursoDAO;
import pe.edu.sistemas.unayoe.dao.jdbc.GrupoDAO;
import pe.edu.sistemas.unayoe.dao.jdbc.MatriculaDAO;
import pe.edu.sistemas.unayoe.services.CursoServices;
import pe.edu.sistemas.unayoe.services.GrupoServices;
import pe.edu.sistemas.unayoe.services.MatriculaServices;
import pe.edu.sistemas.unayoe.services.transformer.CursoTransformerToBO;
import pe.edu.sistemas.unayoe.services.transformer.CursoTransformerToENTIDAD;
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.GrupoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;

@Service("matriculaServices") 	
public class MatriculaServicesImpl implements MatriculaServices{
	
	@Autowired
	private MatriculaDAO matriculaDAO;
	@Autowired
	private CursoIDao cursoIDao;

	
	public int  guardarMatriculas(List<MatriculaBO> lista)throws Exception {
		 return  matriculaDAO.insertarLista(lista);
	}
	
	public List<MatriculaBO> obtenerMatriculaAlumnoPorPeriodo(String cod_alumno ,Integer anio ,Integer periodo) throws Exception{
		List<MatriculaBO> listaMatriculas=  new ArrayList<MatriculaBO>();
		Curso cursoEntidad = new Curso();
		listaMatriculas=matriculaDAO.obtenerMatriculaAlumnoPorPeriodo(cod_alumno ,anio , periodo );
		
		for(MatriculaBO mbo : listaMatriculas ){
			cursoEntidad = cursoIDao.obtenerCurso(mbo.getcCodigo());
			mbo.setcNombre(cursoEntidad.getNombre());
		}
		 
		
		return listaMatriculas ;
	}

}
