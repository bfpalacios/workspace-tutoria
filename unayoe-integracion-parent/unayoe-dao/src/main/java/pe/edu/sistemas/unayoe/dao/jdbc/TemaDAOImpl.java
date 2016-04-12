package pe.edu.sistemas.unayoe.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import oracle.jdbc.OracleTypes;
import pe.edu.sistemas.unayoe.core.dao.jdbc.BaseDAO;
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

@Component("temaDAO")
public class TemaDAOImpl extends BaseDAO implements TemaDAO {

	public TemaDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean createTema(TemaBO tema) {
		boolean ok = true;

		System.out.println("Curso: " + tema.getCodigoCurso());
		System.out.println("Nombre: " + tema.getNombre());
		System.out.println("Descripcion: " + tema.getDescripcion());

		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "INSERT INTO TEMAS_PAR (CODIGO_TEMA, C_CODIGO, NOMBRE_TEMA, DESCRIPCION_TEMA) VALUES (SEC_COD_TEMA.NEXTVAL, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, tema.getCodigoCurso());
			ps.setString(2, tema.getNombre());
			ps.setString(3, tema.getDescripcion());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
			ok = false;
		}
		return ok;
	}


	@Override
	public boolean deleteTema(Integer id) {
		boolean ok = true;
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "DELETE FROM TEMAS_PAR WHERE CODIGO_TEMA = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception err) {
			ok = false;
		}
		return ok;
	}

	@Override
	public boolean updateTema(TemaBO tema) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TemaBO getTema(Integer id) {
		TemaBO tema = new TemaBO();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT CODIGO_TEMA, C_CODIGO, NOMBRE_TEMA, DESCRIPCION_TEMA FROM TEMAS_PAR WHERE CODIGO_TEMA = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tema.setId(rs.getInt("CODIGO_TEMA"));
				tema.setCodigoCurso(rs.getString("C_CODIGO"));
				tema.setNombre(rs.getString("NOMBRE_TEMA"));
				tema.setDescripcion(rs.getString("DESCRIPCION_TEMA"));
			} else {
				tema = null;
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			tema = null;
		}
		return tema;
	}

	@Override
	public List<TemaBO> getTemas() {
		List<TemaBO> temas = new ArrayList<TemaBO>();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT CODIGO_TEMA, C_CODIGO, NOMBRE_TEMA, DESCRIPCION_TEMA FROM TEMAS_PAR";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TemaBO tema = new TemaBO();
				tema.setId(rs.getInt("CODIGO_TEMA"));
				tema.setCodigoCurso(rs.getString("C_CODIGO"));
				tema.setNombre(rs.getString("NOMBRE_TEMA"));
				tema.setDescripcion(rs.getString("DESCRIPCION_TEMA"));
				temas.add(tema);
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			temas = null;
		}
		return temas;
	}

	@Override
	public List<TemaBO> getTemasPorConvocatoria(Integer idConvocatoria) {
		List<TemaBO> temas = new ArrayList<TemaBO>();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT " + "A.CODIGO_TEMA AS CODIGO_TEMA, " + "A.C_CODIGO AS C_CODIGO, "
					+ "A.NOMBRE_TEMA AS NOMBRE_TEMA, " + "A.DESCRIPCION_TEMA AS DESCRIPCION_TEMA "
					+ "FROM TEMAS_PAR A, ASOC_TEMA_CONVOCATORIA_PAR B "
					+ "WHERE A.CODIGO_TEMA = B.CODIGO_TEMA AND B.CODCONVOCATOR = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idConvocatoria);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TemaBO tema = new TemaBO();
				tema.setId(rs.getInt("CODIGO_TEMA"));
				tema.setCodigoCurso(rs.getString("C_CODIGO"));
				tema.setNombre(rs.getString("NOMBRE_TEMA"));
				tema.setDescripcion(rs.getString("DESCRIPCION_TEMA"));
				temas.add(tema);
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			temas = null;
		}
		return temas;
	}

	@Override
	public List<TemaBO> getTemasPorCurso(String codigoCurso) {
		List<TemaBO> temas = new ArrayList<TemaBO>();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT CODIGO_TEMA, C_CODIGO, NOMBRE_TEMA, DESCRIPCION_TEMA FROM TEMAS_PAR WHERE C_CODIGO = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, codigoCurso);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TemaBO tema = new TemaBO();
				tema.setId(rs.getInt("CODIGO_TEMA"));
				tema.setCodigoCurso(rs.getString("C_CODIGO"));
				tema.setNombre(rs.getString("NOMBRE_TEMA"));
				tema.setDescripcion(rs.getString("DESCRIPCION_TEMA"));
				temas.add(tema);
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			temas = null;
		}
		return temas;
	}

	@Override
	public List<TemaBO> listarTemasAprobadosPorCurso(String codigoTutor,String codigoCurso) {
		Connection con = null;
		CallableStatement cstm = null;
		ResultSet rs = null;

		List<TemaBO> temas = new ArrayList<TemaBO>();

		try{
			con = Conexion.obtenerConexion();
			cstm = con.prepareCall("{call LISTAR_TEMA_APROBADO_TUTOR(?,?,?)}");
			cstm.setString(1, codigoTutor);
			cstm.setString(2, codigoCurso);
			cstm.registerOutParameter(3, OracleTypes.CURSOR);
			cstm.execute();

			rs = (ResultSet) cstm.getObject(3);

			while(rs.next()){
				TemaBO tema = new TemaBO();
				tema.setId(rs.getInt("CODIGO_TEMA"));
				tema.setCodigoCurso(rs.getString("C_CODIGO"));
				tema.setNombre(rs.getString("NOMBRE_TEMA"));
				tema.setDescripcion(rs.getString("DESCRIPCION_TEMA"));
				temas.add(tema);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			this.cerrarResultSet(rs);
			this.cerrarStatement(cstm);
			this.cerrarConexion(con);
		}
		return temas;
	}
	
	@Override
	public List<TemaBO> getTemasPorConvocatoriaCurso(Integer idConvocatoria, String codigoCurso) {
		List<TemaBO> temas = new ArrayList<TemaBO>();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT " + "A.CODIGO_TEMA AS CODIGO_TEMA, " + "A.C_CODIGO AS C_CODIGO, "
					+ "A.NOMBRE_TEMA AS NOMBRE_TEMA, " + "A.DESCRIPCION_TEMA AS DESCRIPCION_TEMA "
					+ "FROM TEMAS_PAR A, ASOC_TEMA_CONVOCATORIA_PAR B "
					+ "WHERE A.CODIGO_TEMA = B.CODIGO_TEMA AND B.CODCONVOCATOR = ? AND A.C_CODIGO = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, idConvocatoria);
			ps.setString(2, codigoCurso);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TemaBO tema = new TemaBO();
				tema.setId(rs.getInt("CODIGO_TEMA"));
				tema.setCodigoCurso(rs.getString("C_CODIGO"));
				tema.setNombre(rs.getString("NOMBRE_TEMA"));
				tema.setDescripcion(rs.getString("DESCRIPCION_TEMA"));
				temas.add(tema);
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			temas = null;
		}
		return temas;
	}
}
