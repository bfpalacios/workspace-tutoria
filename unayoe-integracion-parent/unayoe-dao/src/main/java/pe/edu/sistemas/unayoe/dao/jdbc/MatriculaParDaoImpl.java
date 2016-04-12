package pe.edu.sistemas.unayoe.dao.jdbc;

import oracle.jdbc.OracleTypes;
import org.springframework.stereotype.Repository;
import pe.edu.sistemas.unayoe.core.dao.jdbc.BaseDAO;
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.dao.MatriculaParDao;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaParBO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexh on 03/12/15.
 */
@Repository
public class MatriculaParDaoImpl extends BaseDAO implements MatriculaParDao {

    @Override
    public MatriculaParBO matricularAlumno(MatriculaParBO matriculaParBO) {
        Connection con = null;
        try {
            con =  Conexion.obtenerConexion();

            System.out.println(matriculaParBO.toString());
            CallableStatement cstm =
                    con.prepareCall("{call USP_GUARDAR_MATRICULA_PAR(:cod_alumno,:cod_prog,:out_cod_matricula)}");

            cstm.setString("cod_alumno", matriculaParBO.getCodigoAlumno());
            cstm.setInt("cod_prog", matriculaParBO.getCodigoProgramacion());

            cstm.registerOutParameter("out_cod_matricula", OracleTypes.INTEGER);

            cstm.execute();

            int codigoMatricula= cstm.getInt("out_cod_matricula"); //El codigo generado ...

            matriculaParBO.setCodigoMatricula(codigoMatricula);

            return  matriculaParBO;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            this.cerrarConexion(con);
        }
    }

    @Override
    public MatriculaParBO buscarMatriculaPar(String codigoAlumno, int codigoActividad) {
        Connection con = null;
        ResultSet rs = null;
        MatriculaParBO matriculaParBO = null;
        try {
            con =  Conexion.obtenerConexion();

            CallableStatement cstm =
                    con.prepareCall("{call USP_BUSCAR_MATRICULA_PAR(:cod_alumno,:cod_prog,:out_cursor_matricula)}");

            cstm.setString("cod_alumno", codigoAlumno);
            cstm.setInt("cod_prog", codigoActividad);

            cstm.registerOutParameter("out_cursor_matricula", OracleTypes.CURSOR);

            cstm.execute();

            rs = (ResultSet) cstm.getObject("out_cursor_matricula");

            while (rs.next()){
                matriculaParBO = new MatriculaParBO();
                matriculaParBO.setCodigoMatricula(rs.getInt("CODMATRICULA"));
                matriculaParBO.setCodigoAlumno(rs.getString("A_CODIGO"));
                matriculaParBO.setCodigoProgramacion(rs.getInt("COD_PROGR"));
            }

            return  matriculaParBO;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            this.cerrarConexion(con);
        }
    }

}
