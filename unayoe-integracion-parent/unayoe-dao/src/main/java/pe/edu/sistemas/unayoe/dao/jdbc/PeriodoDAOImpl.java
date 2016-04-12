package pe.edu.sistemas.unayoe.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.unayoe.bo.PeriodoBO;

@Component("periodoDao")
public class PeriodoDAOImpl implements PeriodoDAO {

	public PeriodoDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public PeriodoBO getPeriodo(Integer id) {
		PeriodoBO periodo = new PeriodoBO();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT (ANIO || '-' || PERIODO) AS DESCRIPCION FROM CICLO WHERE ID_CICLO = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				periodo.setId(id);
				periodo.setDescripcion(rs.getString("DESCRIPCION"));
			} else {
				periodo = null;
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
			periodo = null;
		}
		return periodo;
	}

	@Override
	public PeriodoBO getPeriodoActual() {
		PeriodoBO periodo = new PeriodoBO();
		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT ID_CICLO, (ANIO || '-' || PERIODO) AS DESCRIPCION FROM CICLO WHERE ACTIVO = 1";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				periodo.setId(rs.getInt("ID_CICLO"));
				periodo.setDescripcion(rs.getString("DESCRIPCION"));
			} else {
				periodo = null;
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
			periodo = null;
		}
		return periodo;
	}

	@Override
	public List<PeriodoBO> getPeriodos() {
		List<PeriodoBO> periodos = new ArrayList<PeriodoBO>();

		try {
			Connection conn = Conexion.obtenerConexion();
			String query = "SELECT ID_CICLO, (ANIO || '-' || PERIODO) AS DESCRIPCION FROM CICLO";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PeriodoBO periodo = new PeriodoBO();
				periodo.setId(rs.getInt("ID_CICLO"));
				periodo.setDescripcion(rs.getString("DESCRIPCION"));
				periodos.add(periodo);
			}
			ps.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
			periodos = null;
		}
		return periodos;
	}

}
