package pe.edu.sistemas.unayoe.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.unayoe.core.dao.jdbc.BaseDAO;
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.core.util.DAOExcepcion;
import pe.edu.sistemas.unayoe.dao.dominio.Curso;
import pe.edu.sistemas.unayoe.unayoe.bo.AsistenciaCAlumnoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TutoriaBO;

@Repository("asistenciaAlumnoDAO")
@Transactional
public class AsistenciaAlumnoDAO extends BaseDAO  {

	public List<AsistenciaCAlumnoBO> buscarAsistenciaAlumnoClase(String a_codigo ,Integer anio ,Integer periodo ,String fdesde ,String fhasta ) throws DAOExcepcion {
		 List<AsistenciaCAlumnoBO> lista = new ArrayList<AsistenciaCAlumnoBO>();
        String queryAsistencia="SELECT CLA.FECHA as F, DI.DIA  as D , CLA.C_CODIGO as C, CUR.NOMBRE as N, MAT.REPITENCIAS as R , CLA.ASISTENCIA as A , CLA.OBSERVACION as O FROM ASISTENCIA_C_ALUM CLA "+ 
        					"INNER JOIN DIAS_CLASE DI "+
        					"ON  DI.ANIO = ? AND "+
        					"DI.PERIODO = ? AND "+
        					"DI.GRUPO = CLA.GRUPO AND "+
        					"TRIM(DI.C_CODIGO) = TRIM(CLA.C_CODIGO) "+
        					"INNER JOIN MATRICULA MAT "+  
        					"on "+    
        					"TRIM(mat.a_codigo) = TRIM(CLA.a_codigo)   and "+
        					"TRIM(mat.c_codigo) = TRIM(CLA.c_codigo) "+
        					"INNER JOIN CURSO CUR "+
        					"ON TRIM(CUR.C_CODIGO) = TRIM(CLA.C_CODIGO) "+
        					"WHERE TRIM(CLA.A_CODIGO) = ? AND "+
        					"mat.anio     = ?    AND "+
        					"mat.periodo  = ?      and "+
        					"to_date(CLA.FECHA,'dd/mm/yyyy') >= to_date(?,'dd/mm/yyyy') AND " +
        					"to_date(CLA.FECHA,'dd/mm/yyyy')  <= to_date(?,'dd/mm/yyyy')  "; 
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
                con = Conexion.obtenerConexion();
                stmt = con.prepareStatement(queryAsistencia);
                stmt.setInt(1, anio);
                stmt.setInt(2, periodo);
                stmt.setString(3,a_codigo);
                stmt.setInt(4, anio);
                stmt.setInt(5, periodo);
                stmt.setString(6, fdesde);
                stmt.setString(7, fhasta);
                rs = stmt.executeQuery();
                while (rs.next()) {
                	AsistenciaCAlumnoBO asistenciaCAlumnoBO =  new AsistenciaCAlumnoBO();
                	asistenciaCAlumnoBO.setaCodigo(a_codigo);
                	asistenciaCAlumnoBO.setFecha(rs.getString("F"));
                	asistenciaCAlumnoBO.setDia(rs.getString("D").trim());
                	asistenciaCAlumnoBO.setcCodigo(rs.getString("C").trim());
                	asistenciaCAlumnoBO.setcNombre(rs.getString("N").trim());
                	asistenciaCAlumnoBO.setRepitencia(rs.getString("R"));
                	asistenciaCAlumnoBO.setAsistencia(rs.getString("A"));
                	asistenciaCAlumnoBO.setObservacion(rs.getString("O"));
    				lista.add(asistenciaCAlumnoBO);
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
        return lista;
	 }
	
	public List<AsistenciaCAlumnoBO> buscarAsistenciaAlumnoTutotria(String a_codigo , String fdesde ,String fhasta ) throws DAOExcepcion {
		 List<AsistenciaCAlumnoBO> lista = new ArrayList<AsistenciaCAlumnoBO>();
       String queryAsistencia="SELECT TUT.FECHA_T as F, TU.DIA as D, TU.C_CODIGO as C, CUR.NOMBRE as N, MAT.REPITENCIAS as R, TUT.ASISTENCIA_T  as A, TUT.OBSERVACION_t as O FROM ASISTENCIA_T_ALUM TUT "+
    		   					"INNER JOIN TUTORIA TU " +
    		   					"ON TU.T_ANIO =  TUT.T_ANIO AND "+
    		   					"TU.T_PERIODO = TUT.T_PERIODO AND "+
    		   					"TU.T_CODIGO  =  TUT.T_CODIGO "+
    		   					"INNER JOIN MATRICULA MAT  "+
    		   					"on    mat.anio     = tu.t_anio     AND "+
    		   					"mat.periodo  = tu.t_periodo  and "+
    		   					"mat.a_codigo = tu.a_codigo   and "+
    		   					"mat.c_codigo = tu.c_codigo "+
    		   					"INNER JOIN CURSO CUR "+
    		   					"ON CUR.C_CODIGO = TU.C_CODIGO "+
    		   					"WHERE TRIM(TU.A_CODIGO) = ? AND "+
    		   					"to_date(TUT.FECHA_T,'dd/mm/yyyy') >= to_date(?,'dd/mm/yyyy') AND "+
    		   					"to_date(TUT.FECHA_T,'dd/mm/yyyy')  <= to_date(?,'dd/mm/yyyy')  "; 
       Connection con = null;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       try {
               con = Conexion.obtenerConexion();
               stmt = con.prepareStatement(queryAsistencia);
               stmt.setString(1,a_codigo);
               stmt.setString(2, fdesde);
               stmt.setString(3, fhasta);
               rs = stmt.executeQuery();
               while (rs.next()) {
               	AsistenciaCAlumnoBO asistenciaCAlumnoBO =  new AsistenciaCAlumnoBO();
               	
               	asistenciaCAlumnoBO.setFecha(rs.getString("F"));
               	asistenciaCAlumnoBO.setDia(rs.getString("D"));
               	asistenciaCAlumnoBO.setcCodigo(rs.getString("C"));
               	asistenciaCAlumnoBO.setcNombre(rs.getString("N"));
               	asistenciaCAlumnoBO.setRepitencia(rs.getString("R"));
               	asistenciaCAlumnoBO.setAsistencia(rs.getString("A"));
               	asistenciaCAlumnoBO.setObservacion("O");
   				
   				lista.add(asistenciaCAlumnoBO);
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
       return lista;
	 }
	
	public List<AsistenciaCAlumnoBO> buscarAsistenciaTutorTutotria(String a_codigo , String fdesde ,String fhasta ) throws DAOExcepcion {
		 List<AsistenciaCAlumnoBO> lista = new ArrayList<AsistenciaCAlumnoBO>();
      String queryAsistencia="SELECT TUT.FECHA_T as T, TU.DIA as D, TU.C_CODIGO as C, CUR.NOMBRE as N, TUT.ASISTENCIA_T as A, TUT.OBSERVACION_t as O FROM ASISTENCIA_T_PROF TUT "+
    		  					"INNER JOIN TUTORIA TU "+
    		  					"ON TU.T_ANIO =  TUT.T_ANIO AND "+
    		  					"TU.T_PERIODO = TUT.T_PERIODO AND "+
    		  					"TU.T_CODIGO  =  TUT.T_CODIGO  "+
    		  					"INNER JOIN CURSO CUR "+
    		  					"ON CUR.C_CODIGO = TU.C_CODIGO "+
    		  					"WHERE TU.A_CODIGO = ? AND "+
    		  					"to_date(TUT.FECHA_T,'dd/mm/yyyy') >= to_date(?,'dd/mm/yyyy') AND "+
    		  					"to_date(TUT.FECHA_T,'dd/mm/yyyy')  <= to_date(?,'dd/mm/yyyy') "; 
      Connection con = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      try {
              con = Conexion.obtenerConexion();
              stmt = con.prepareStatement(queryAsistencia);
              stmt.setString(1,a_codigo);
              stmt.setString(2, fdesde);
              stmt.setString(3, fhasta);
              rs = stmt.executeQuery();
              while (rs.next()) {
              	AsistenciaCAlumnoBO asistenciaCAlumnoBO =  new AsistenciaCAlumnoBO();
              	
              	asistenciaCAlumnoBO.setFecha(rs.getString("F"));
              	asistenciaCAlumnoBO.setDia(rs.getString("D"));
              	asistenciaCAlumnoBO.setcCodigo(rs.getString("C"));
              	asistenciaCAlumnoBO.setcNombre(rs.getString("N"));
              	asistenciaCAlumnoBO.setRepitencia(rs.getString("R"));
              	asistenciaCAlumnoBO.setAsistencia(rs.getString("A"));
              	asistenciaCAlumnoBO.setObservacion("O");
  				
  				lista.add(asistenciaCAlumnoBO);
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
      return lista;
	 }
	
}
