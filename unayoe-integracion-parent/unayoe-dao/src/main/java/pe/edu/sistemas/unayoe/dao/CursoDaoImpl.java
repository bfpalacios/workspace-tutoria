package pe.edu.sistemas.unayoe.dao;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.unayoe.core.dao.DAOImpl;
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.dao.dominio.Curso; 
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;


@Repository("cursoDao")
@Transactional
public class CursoDaoImpl extends DAOImpl<Curso,String> implements CursoIDao{

	public List<Curso> listarCursos() throws Exception {
		//devuelve toda la informacion de los cursos
		return super.listarTodos(Curso.class);
	}
	
	public Curso obtenerCurso(String codigo) throws Exception{
		return super.obtenerEntidadPorId(Curso.class,codigo);
	}
	
	public List<CursoBO> listarCursosxDocenteRegular(String codDocente) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
			
		List<CursoBO> listaCursos = new ArrayList<CursoBO>();
			
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_CURSOSDOCENTE_REGULARES(?,?)}");	
			cstm.setString(1, codDocente);				
			cstm.registerOutParameter(2, OracleTypes.CURSOR);			
			cstm.execute();
				
			rs = (ResultSet) cstm.getObject(2);
				
			while(rs.next()){				
				CursoBO curso = new CursoBO();
				curso.setcCodigo(rs.getString(1));
				curso.setNombre(rs.getString(2));
				listaCursos.add(curso);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}				
		return listaCursos;
	 }
	
	public List<CursoBO> listarCursosDocente(String codDocente, int proceso, int modo) throws Exception{
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
			
		List<CursoBO> listaCursos = new ArrayList<CursoBO>();
			
		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_CURSOSDOCENTE_GENERICO(?,?,?,?)}");	
			cstm.setString(1, codDocente);				
			cstm.setInt(2, proceso);
			cstm.setInt(3, modo);
			cstm.registerOutParameter(4, OracleTypes.CURSOR);			
			cstm.execute();
				
			rs = (ResultSet) cstm.getObject(4);
				
			while(rs.next()){				
				CursoBO curso = new CursoBO();
				curso.setcCodigo(rs.getString(1));
				curso.setNombre(rs.getString(2));
				listaCursos.add(curso);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}				
		return listaCursos;
	}

	public List<CursoBO> listarCursosPorAreaConocimiento(String codigoAreaConocimiento) {
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;

		List<CursoBO> listaCursos = new ArrayList<CursoBO>();

		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_CURSOSXAREACONOCIMIENTO(?,?)}");
			cstm.setString(1, codigoAreaConocimiento);
			cstm.registerOutParameter(2, OracleTypes.CURSOR);
			cstm.execute();

			rs = (ResultSet) cstm.getObject(2);

			while(rs.next()){
				CursoBO curso = new CursoBO();
				curso.setcCodigo(rs.getString(1));
				curso.setNombre(rs.getString(2));

				listaCursos.add(curso);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return listaCursos;
	}

	@Override
	public List<CursoBO> listarCursosAprobadosPorAreaConocimiento( String codigoTutor,String codigoAreaConocimiento) throws Exception {
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;

		List<CursoBO> listaCursos = new ArrayList<CursoBO>();

		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_CURSO_APROBADO_TUTOR(?,?,?)}");
			cstm.setString(1,codigoTutor);
			cstm.setString(2, codigoAreaConocimiento);
			cstm.registerOutParameter(3, OracleTypes.CURSOR);
			cstm.execute();

			rs = (ResultSet) cstm.getObject(3);

			while(rs.next()){
				CursoBO curso = new CursoBO();
				curso.setcCodigo(rs.getString(1));
				curso.setNombre(rs.getString(2));

				listaCursos.add(curso);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return listaCursos;
	}

	@Override
	public CursoBO obtenerCursoTema(int codigoTema) {
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;

		CursoBO curso = new CursoBO();

		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call USP_CURSO_TEMA(:codigo_tema,:out_cursor)}");
			cstm.setInt("codigo_tema",codigoTema);
			cstm.registerOutParameter("out_cursor", OracleTypes.CURSOR);
			cstm.execute();

			rs = (ResultSet) cstm.getObject("out_cursor");

			while(rs.next()){
				curso.setcCodigo(rs.getString("C_CODIGO"));
				curso.setNombre(rs.getString("NOMBRE"));
			}
			return  curso;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}
	
	@Override
	public List<CursoBO> listarCursosPorConvocatoria(Integer idConvocatoria) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CursoBO> cursos = new ArrayList<CursoBO>();
		try {
			conn = Conexion.obtenerConexion();
			String sql = "SELECT DISTINCT CU.C_CODIGO AS C_CODIGO, CU.NOMBRE AS NOMBRE "
					+ "FROM CURSO CU, TEMAS_PAR TP, ASOC_TEMA_CONVOCATORIA_PAR TC "
					+ "WHERE CU.C_CODIGO = TP.C_CODIGO AND TP.CODIGO_TEMA = TC.CODIGO_TEMA AND TC.CODCONVOCATOR = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, idConvocatoria);
			rs = ps.executeQuery();
			while(rs.next()) {
				CursoBO curso = new CursoBO();
				curso.setcCodigo(rs.getString("C_CODIGO"));
				curso.setNombre(rs.getString("NOMBRE"));
				cursos.add(curso);
			}
		} catch (Exception e) {
			e.printStackTrace();
			cursos = null;
		}
		return cursos;
	}

	@Override
	public List<CursoBO> listarCursosTutorias() {
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;

		List<CursoBO> listaCursos = new ArrayList<CursoBO>();

		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_CURSO_TUTORIAS(?)}");
			cstm.registerOutParameter(1, OracleTypes.CURSOR);
			cstm.execute();

			rs = (ResultSet) cstm.getObject(1);

			while(rs.next()){
				CursoBO curso = new CursoBO();
				curso.setcCodigo(rs.getString(1));
				curso.setNombre(rs.getString(2));

				listaCursos.add(curso);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return listaCursos;
	}


}
