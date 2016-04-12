package pe.edu.sistemas.unayoe.dao.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.unayoe.core.dao.jdbc.BaseDAO;
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.core.util.DAOExcepcion;
import pe.edu.sistemas.unayoe.dao.dominio.Curso;
import pe.edu.sistemas.unayoe.unayoe.bo.CursoBO;

@Repository("cursoDAO")
@Transactional
public class CursoDAO extends BaseDAO {	
	 public boolean existe(Curso curso) throws DAOExcepcion {
		 boolean existe=false;
         String query = "select  C_CODIGO, NOMBRE , CREDITOS  from  CURSO  WHERE TRIM(C_CODIGO) = ?";
         Connection con = null;
         PreparedStatement stmt = null;
         ResultSet rs = null;
         try {
                 con = Conexion.obtenerConexion();
                 stmt = con.prepareStatement(query);
                stmt.setString(1, curso.getCCodigo());
                 rs = stmt.executeQuery();	
                 
                 if (rs.next()) {
                     existe=true;   
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
         return existe;
	 }
	 
	 public void insertarLista(List<Curso>  lista){
		 for(Curso curso : lista){
			 try {
				//curso=insertar(curso);
				 //if(!existe(curso))
				 	curso=insertarActualizar(curso); 
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }
	 public Curso insertarActualizar(Curso curso) throws DAOExcepcion {
		 curso.setCCodigo(curso.getCCodigo()+"  ");
         String queryMerge="MERGE INTO curso c " +
                 "USING (SELECT ? c_codigo, ? nombre, ? creditos  FROM dual) curso " +
                 "ON (c.c_codigo = curso.c_codigo ) " +
                 "WHEN MATCHED THEN " +
                 "UPDATE SET c.nombre = curso.nombre, c.creditos = curso.creditos "+
                 "WHEN NOT MATCHED THEN " +
                 "INSERT (c.c_codigo,c.nombre,c.creditos) VALUES (curso.c_codigo,curso.nombre,curso.creditos)";
         Connection con = null;
         PreparedStatement stmt = null;
         ResultSet rs = null;
         try {
                 con = Conexion.obtenerConexion();
                 stmt = con.prepareStatement(queryMerge);
                 stmt.setString(1, curso.getCCodigo());
                 stmt.setString(2, curso.getNombre());
                 stmt.setLong(3,curso.getCreditos().longValue());
                 
                 int i = stmt.executeUpdate();
                 
                 if (i != 1) {
                        throw new SQLException("No se pudo el curso");
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
         return curso;
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
			finally{
				this.cerrarResultSet(rs);
				this.cerrarStatement(cstm);
				this.cerrarConexion(con);
			}		
			return listaCursos;
	 }
}
