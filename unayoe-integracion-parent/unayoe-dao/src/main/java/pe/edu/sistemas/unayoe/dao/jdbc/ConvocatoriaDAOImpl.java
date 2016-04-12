package pe.edu.sistemas.unayoe.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO;

@Component("convocatoriaDao")
public class ConvocatoriaDAOImpl implements ConvocatoriaDAO {

	@Override
	public boolean createConvocatoria(ConvocatoriaBO convocatoria) {
		boolean ok = true;
		Connection conn = null;
		try {
			conn = Conexion.obtenerConexion();
			conn.setAutoCommit(false);
			// registrar la convocatoria
			String query = "INSERT INTO CONVOCATORIA_PAR (CODCONVOCATOR, ID_CICLO, FEC_INI, FEC_FIN, NOMBRE) VALUES (SEQ_NUMCONV.NEXTVAL, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, convocatoria.getIdPeriodo());
			ps.setDate(2, new Date(convocatoria.getFechaInicio().getTime()));
			ps.setDate(3, new Date(convocatoria.getFechaFinal().getTime()));
			ps.setString(4, convocatoria.getNombre());
			ps.executeUpdate();

			// registrar los temas de la convocatoria
			String query2 = "INSERT INTO ASOC_TEMA_CONVOCATORIA_PAR (CODIGO_TEMA, CODCONVOCATOR) VALUES (?, SEQ_NUMCONV.CURRVAL)";
			PreparedStatement ps2 = conn.prepareStatement(query2);
			for (Integer idTema : convocatoria.getIdTemas()) {
				ps2.setInt(1, idTema);
				ps2.executeUpdate();
			}

			conn.commit();
			ps.close();
			ps2.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
			ok = false;

			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
		return ok;
	}

	@Override
	public boolean deleteConvocatoria(Integer id) {
		boolean ok = true;
		try {
			Connection conn = Conexion.obtenerConexion();
			conn.setAutoCommit(false);
			String sql_deltemasconv = "DELETE FROM ASOC_TEMA_CONVOCATORIA_PAR WHERE CODCONVOCATOR = ?";
			PreparedStatement ps_deltemasconv = conn.prepareStatement(sql_deltemasconv);
			ps_deltemasconv.setInt(1, id);
			ps_deltemasconv.executeUpdate();
			String query = "DELETE FROM CONVOCATORIA_PAR WHERE CODCONVOCATOR = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();

			conn.commit();
			ps.close();
			ps_deltemasconv.close();
			conn.close();
		} catch (Exception err) {
			ok = false;
			err.printStackTrace();
		}
		return ok;
	}

	@Override
	public ConvocatoriaBO getConvocatoria(Integer id) {
		ConvocatoriaBO convocatoria = new ConvocatoriaBO();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT ID_CICLO, FEC_INI, FEC_FIN, NOMBRE FROM CONVOCATORIA_PAR WHERE CODCONVOCATOR = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				convocatoria.setId(id);
				convocatoria.setIdPeriodo(rs.getInt("ID_CICLO"));
				convocatoria.setFechaInicio(new java.util.Date(rs.getDate("FEC_INI").getTime()));
				convocatoria.setFechaFinal(new java.util.Date(rs.getDate("FEC_FIN").getTime()));
				convocatoria.setNombre(rs.getString("NOMBRE"));
			} else {
				convocatoria = null;
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			convocatoria = null;
			err.printStackTrace();
		}
		return convocatoria;
	}

	@Override
	public List<ConvocatoriaBO> getConvocatorias() {
		List<ConvocatoriaBO> convocatorias = new ArrayList<ConvocatoriaBO>();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT CODCONVOCATOR, ID_CICLO, FEC_INI, FEC_FIN, NOMBRE FROM CONVOCATORIA_PAR";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			ConvocatoriaBO convocatoria = null;
			while (rs.next()) {
				convocatoria = new ConvocatoriaBO();
				convocatoria.setId(rs.getInt("CODCONVOCATOR"));
				convocatoria.setIdPeriodo(rs.getInt("ID_CICLO"));
				convocatoria.setFechaInicio(new java.util.Date(rs.getDate("FEC_INI").getTime()));
				convocatoria.setFechaFinal(new java.util.Date(rs.getDate("FEC_FIN").getTime()));
				convocatoria.setNombre(rs.getString("NOMBRE"));
				convocatorias.add(convocatoria);
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			convocatorias = null;
			err.printStackTrace();
		}
		return convocatorias;
	}

	@Override
	public boolean updateConvocatoria(ConvocatoriaBO convocatoria) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ConvocatoriaBO getConvocatoriaActual() {
		ConvocatoriaBO convocatoria = new ConvocatoriaBO();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT CODCONVOCATOR, ID_CICLO, FEC_INI, FEC_FIN, NOMBRE FROM CONVOCATORIA_PAR WHERE FEC_INI <= SYSDATE AND FEC_FIN >= SYSDATE";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				convocatoria.setId(rs.getInt("CODCONVOCATOR"));
				convocatoria.setIdPeriodo(rs.getInt("ID_CICLO"));
				convocatoria.setFechaInicio(new java.util.Date(rs.getDate("FEC_INI").getTime()));
				convocatoria.setFechaFinal(new java.util.Date(rs.getDate("FEC_FIN").getTime()));
				convocatoria.setNombre(rs.getString("NOMBRE"));
			} else {
				convocatoria = null;
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			convocatoria = null;
			err.printStackTrace();
		}
		return convocatoria;
	}

}
