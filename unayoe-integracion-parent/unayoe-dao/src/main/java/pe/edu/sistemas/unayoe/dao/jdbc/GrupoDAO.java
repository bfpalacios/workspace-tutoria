package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.List;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.unayoe.core.dao.jdbc.BaseDAO;
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.core.util.DAOExcepcion;
import pe.edu.sistemas.unayoe.dao.dominio.Alumno;
import pe.edu.sistemas.unayoe.unayoe.bo.GrupoBO;

@Repository("grupoDAO")
@Transactional
public class GrupoDAO extends BaseDAO {
	
	 public Alumno insertar(Alumno alumno) throws DAOExcepcion {
         String query = "insert into alumno(a_codigo,a_nombre,a_apellidos,a_fnacimiento,a_direccion,a_email,a_telefono,a_dni) values (?,?,?,?,?,?,?,?)";
         Connection con = null;
         PreparedStatement stmt = null;
         ResultSet rs = null;
         alumno.setADni(BigInteger.ZERO);
         try {
        	 		
                 con = Conexion.obtenerConexion();
                 stmt = con.prepareStatement(query);
                 stmt.setString(1, alumno.getACodigo());
                 stmt.setString(2, alumno.getANombre());
                 stmt.setString(3, alumno.getAApellidos());
                 stmt.setString(4, alumno.getAFnacimiento());
                 stmt.setString(5, alumno.getADireccion());
                 stmt.setString(6, alumno.getAEmail());
                 if(alumno.getATelefono()!=null)
                	 stmt.setLong(7,alumno.getATelefono().longValue());
                 else
                	 stmt.setNull(7, Types.INTEGER);
                 if(alumno.getADni()!=null)
                	 stmt.setLong(8,alumno.getADni().longValue());
                 else
                	 stmt.setNull(8, Types.INTEGER);

                 int i = stmt.executeUpdate();
                 if (i != 1) {
                        throw new SQLException("No se pudo insertar el alumno");
                 }
                 // Obtener el ultimo id
                 /*int id = 0;
                 query = "select last_insert_id()";
                 stmt = con.prepareStatement(query);
                 rs = stmt.executeQuery();
                 if (rs.next()) {
                        id = rs.getInt(1);
                 }
                 System.out.println("El id:"+id);*/

         } catch (SQLException e) {
                 System.err.println(e.getMessage());
                 e.printStackTrace();
                 throw new DAOExcepcion(e.getMessage());
         } finally {
                 this.cerrarResultSet(rs);
                 this.cerrarStatement(stmt);
                 this.cerrarConexion(con);
         }
         return alumno;
	 }
	 
	 public void insertarLista(List<GrupoBO>  lista){
		 for(GrupoBO grupo : lista){
			 try {
				//alumno=insertar(alumno);
				 insertarActualizar(grupo); 
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
	 
	 

	 public void insertarActualizar(GrupoBO grupobo) throws DAOExcepcion {
  
         String queryMerge="MERGE INTO grupo g " +
                 "USING (SELECT ? ANIO, ? PERIODO, ? GRUPO ,? C_CODIGO , ? P_CODIGO  FROM dual) grupo " +
                 "ON (g.ANIO = grupo.ANIO AND g.PERIODO= grupo.PERIODO AND g.GRUPO= grupo.GRUPO AND TRIM(g.C_CODIGO)= grupo.C_CODIGO   ) " +
                 "WHEN MATCHED THEN " +
                 "UPDATE SET g.P_CODIGO = grupo.P_CODIGO " +
                 "WHEN NOT MATCHED THEN " +
                 "INSERT (g.ANIO,g.PERIODO,g.GRUPO,g.C_CODIGO,g.P_CODIGO) " +
                 "VALUES (grupo.ANIO,grupo.PERIODO,grupo.GRUPO,grupo.C_CODIGO,grupo.P_CODIGO)";
         Connection con = null;
         PreparedStatement stmt = null;
         ResultSet rs = null;
         try {
                 con = Conexion.obtenerConexion();
                 stmt = con.prepareStatement(queryMerge);
                 stmt.setLong(1,Long.parseLong(grupobo.getAnio()));
                 stmt.setLong(2,Long.parseLong(grupobo.getPeriodo()));
                 stmt.setLong(3,Long.parseLong(grupobo.getGrupo()));
                 stmt.setString(4,grupobo.getcCodigo());
                 stmt.setString(5, grupobo.getpCodigo());
                
                 
                 int i = stmt.executeUpdate();
                 
                 if (i != 1) {
                        throw new SQLException("No se pudo insertar el grupo");
                 }

         } catch (SQLException e) {
                 System.err.println(e.getMessage());
                 e.printStackTrace();
                 throw new DAOExcepcion(e.getMessage());
         } finally {
                 this.cerrarResultSet(rs);
                 this.cerrarStatement(stmt);
                 this.cerrarConexion(con);
         }
	 }
	 
}
