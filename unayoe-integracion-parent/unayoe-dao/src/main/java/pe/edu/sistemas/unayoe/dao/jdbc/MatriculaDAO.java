package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.ArrayList;
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
import pe.edu.sistemas.unayoe.unayoe.bo.AlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.GrupoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaBO;

@Repository("matriculaDAO")
@Transactional
public class MatriculaDAO extends BaseDAO {
	
	
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
	 
	 public int insertarLista(List<MatriculaBO>  lista){
		 int resul=0;
		 for(MatriculaBO matriculabo : lista){
			 try {
				//alumno=insertar(alumno);
				 resul=insertarActualizar(matriculabo); 
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 return resul;
	 }
	 
	 

	 public int insertarActualizar(MatriculaBO matriculabo) throws DAOExcepcion {
		 int resultado=1;//insert correcto
         String querySiNoExiste = "INSERT INTO matricula ( a_codigo, anio, periodo, c_codigo,  grupo,repitencias )"+
        		 		"select ?, ? , ?, ?, ? ,? from dual  where not exists("+
        				 "select * from matricula where (TRIM(a_codigo)=?  and anio=?  and periodo=?  and  TRIM(c_codigo)=? and grupo=? and  repitencias=? ))";
         
         String queryMerge="MERGE INTO MATRICULA m " +
                 "USING (SELECT ? A_CODIGO, ? REPITENCIAS, ? ANIO , ? PERIODO , ? GRUPO ,? C_CODIGO FROM dual) matricula " +
                 "ON (m.A_CODIGO = matricula.A_CODIGO AND m.REPITENCIAS= matricula.REPITENCIAS AND m.ANIO= matricula.ANIO "
                 + "  AND m.PERIODO= matricula.PERIODO AND  m.GRUPO= matricula.GRUPO AND  m.C_CODIGO= matricula.C_CODIGO AND m.REPITENCIAS= matricula.REPITENCIAS ) " +
                 "WHEN MATCHED THEN " +
                 "UPDATE SET m.REPITENCIAS= matricula.REPITENCIAS " +
                 "WHEN NOT MATCHED THEN " +
                 "INSERT (m.A_CODIGO,m.REPITENCIAS,m.ANIO,m.PERIODO,m.GRUPO ,m.C_CODIGO ) " +
                 "VALUES ( matricula.A_CODIGO,matricula.REPITENCIAS,matricula.ANIO,matricula.PERIODO ,matricula.GRUPO ,matricula.C_CODIGO )";
         
         
         Connection con = null;
         PreparedStatement stmt = null;
         ResultSet rs = null;
         try {
                 con = Conexion.obtenerConexion();
                 stmt = con.prepareStatement(querySiNoExiste);
                 stmt.setString(1,matriculabo.getaCodigo());
                 stmt.setLong(2,Long.parseLong(matriculabo.getAnio()));
                 stmt.setLong(3,Long.parseLong(matriculabo.getPeriodo()));
                 stmt.setString(4,matriculabo.getcCodigo());
                 stmt.setLong(5,Long.parseLong( matriculabo.getGrupo()));
                 stmt.setLong(6,Long.parseLong(matriculabo.getRepitencias()));
                 
                 stmt.setString(7,matriculabo.getaCodigo());
                 stmt.setLong(8,Long.parseLong(matriculabo.getAnio()));
                 stmt.setLong(9,Long.parseLong(matriculabo.getPeriodo()));
                 stmt.setString(10,matriculabo.getcCodigo());
                 stmt.setLong(11,Long.parseLong( matriculabo.getGrupo()));
                 stmt.setLong(12,Long.parseLong(matriculabo.getRepitencias()));
                 
                 
                 int i = stmt.executeUpdate();
                 resultado= i;
                 if (i != 1) {
                        //throw new SQLException("No se pudo insertar el grupo");
                	  System.err.println("No se pudo insertar la matricula , el registro ya existe.");
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
         return resultado;
	 }
	 
	 public  List<MatriculaBO> obtenerMatriculaAlumnoPorPeriodo(String cod_alumno ,Integer anio ,Integer periodo) throws Exception {
		 	MatriculaBO matri=new MatriculaBO();
			List<MatriculaBO> listaMatricula = new ArrayList<MatriculaBO>();
			String query = "SELECT 	M.A_CODIGO, M.REPITENCIAS, M.ANIO, M.PERIODO , "
					+ "M.GRUPO, M.C_CODIGO "
					+ "FROM  MATRICULA M "
					+ "WHERE TRIM(M.A_CODIGO) = ? AND M.ANIO= ? AND M.PERIODO= ? ";
			
			List<AlumnoBO> listaAsistenciaAlumnoTutoria = new ArrayList<AlumnoBO>();
			
			Connection con = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				con = Conexion.obtenerConexion();
				stmt = con.prepareStatement(query);
				stmt.setString(1, cod_alumno);
				stmt.setInt(2, anio);
				stmt.setInt(3, periodo);
				rs = stmt.executeQuery();
				while (rs.next()) {
					MatriculaBO matriculaBO = new MatriculaBO();
					matriculaBO.setaCodigo(rs.getString("A_CODIGO"));
					matriculaBO.setRepitencias(String.valueOf(rs.getInt("REPITENCIAS")));
					matriculaBO.setAnio(String.valueOf(rs.getInt("ANIO")));
					matriculaBO.setPeriodo(String.valueOf(rs.getInt("PERIODO")));
					matriculaBO.setGrupo(String.valueOf(rs.getInt("GRUPO")));
					matriculaBO.setcCodigo(rs.getString("C_CODIGO"));	 
					listaMatricula.add(matriculaBO);
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				throw new DAOExcepcion(e.getMessage());
			} finally {
				this.cerrarResultSet(rs);
				this.cerrarStatement(stmt);
				this.cerrarConexion(con);
			}
			System.out.println(listaAsistenciaAlumnoTutoria.size());
			return listaMatricula;
	 }
	 
}
