package pe.edu.sistemas.unayoe.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadTutoriaParBO;

@Component("disponibilidadParDao")
public class DisponibilidadParDAOImpl implements DisponibilidadParDAO {

	public DisponibilidadParDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<DisponibilidadTutoriaParBO> getDisponibilidad(Integer idPostulacion) {
		List<DisponibilidadTutoriaParBO> disponibilidad = new ArrayList<DisponibilidadTutoriaParBO>();
		try {
			Connection conn = Conexion.obtenerConexion();
			String sql = "SELECT ID_DISPONIBILIDAD, DIA, TURNO FROM DISPONIBILIDAD_PAR WHERE COD_POSTULACION = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, idPostulacion);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DisponibilidadTutoriaParBO dispo = new DisponibilidadTutoriaParBO();
				dispo.setId(rs.getInt("ID_DISPONIBILIDAD"));

				String dia = rs.getString("DIA");
				// System.out.println("dia:" + dia);
				dispo.setNombreDia(dia);

				String turno = rs.getString("TURNO");
				// System.out.println("turno:" + turno);
				dispo.setNombreTurno(turno);

				disponibilidad.add(dispo);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (Exception err) {
			err.printStackTrace();
			disponibilidad = null;
		}
		return disponibilidad;
	}

}
