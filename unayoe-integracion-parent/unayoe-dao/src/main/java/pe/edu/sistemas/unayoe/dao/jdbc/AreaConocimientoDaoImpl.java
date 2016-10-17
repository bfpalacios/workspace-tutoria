package pe.edu.sistemas.unayoe.dao.jdbc;

import oracle.jdbc.OracleTypes;
import org.springframework.stereotype.Repository;
import pe.edu.sistemas.unayoe.core.dao.jdbc.BaseDAO;
import pe.edu.sistemas.unayoe.core.dao.jdbc.Conexion;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 06/11/2015
 */
@Repository("areaConocimientoDao")
public class AreaConocimientoDaoImpl extends BaseDAO implements pe.edu.sistemas.unayoe.dao.AreaConocimientoDao {

    public List<AreaConocimientoBO> listarTodos() throws Exception {
        Connection con = null;
        CallableStatement cstm = null;
        ResultSet rs = null;

        List<AreaConocimientoBO> listaAreaConocimiento = new ArrayList<AreaConocimientoBO>();

        try{
            con = Conexion.obtenerConexion();
            cstm = con.prepareCall("{call LISTAR_AREACONOCIMIENTO(?)}");
            cstm.registerOutParameter(1, OracleTypes.CURSOR);
            cstm.execute();

            rs = (ResultSet) cstm.getObject(1);

            while(rs.next()){
                AreaConocimientoBO areaConocimiento = new AreaConocimientoBO();
                areaConocimiento.setCodAreaConocimiento(rs.getString(1));
                areaConocimiento.setNomAreaConocimiento(rs.getString(2));

                listaAreaConocimiento.add(areaConocimiento);
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
        return listaAreaConocimiento;
    }

    @Override
    public List<AreaConocimientoBO> listarAreasAprobadasTutor(String codigoTutor) throws Exception {
        Connection con = null;
        CallableStatement cstm = null;
        ResultSet rs = null;

        List<AreaConocimientoBO> listaAreaConocimiento = new ArrayList<AreaConocimientoBO>();

        try{
            con = Conexion.obtenerConexion();
            cstm = con.prepareCall("{call LISTAR_AREA_APROBADO_TUTOR(?,?)}");
            cstm.setString(1, codigoTutor);
            cstm.registerOutParameter(2, OracleTypes.CURSOR);
            cstm.execute();

            rs = (ResultSet) cstm.getObject(2);

            while(rs.next()){
                AreaConocimientoBO areaConocimiento = new AreaConocimientoBO();
                areaConocimiento.setCodAreaConocimiento(rs.getString(1));
                areaConocimiento.setNomAreaConocimiento(rs.getString(2));

                listaAreaConocimiento.add(areaConocimiento);
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
        return listaAreaConocimiento;
    }

    @Override
    public AreaConocimientoBO obtnerAreaTema(int codigoTema) {
        Connection con = null;
        CallableStatement cstm = null;
        ResultSet rs = null;

        AreaConocimientoBO area = new AreaConocimientoBO();

        try{
            con = Conexion.obtenerConexion();
            cstm = con.prepareCall("{call USP_AREA_TEMA(:codigo_tema,:out_cursor)}");
            cstm.setInt("codigo_tema",codigoTema);
            cstm.registerOutParameter("out_cursor", OracleTypes.CURSOR);
            cstm.execute();

            rs = (ResultSet) cstm.getObject("out_cursor");

            while(rs.next()){
                area.setCodAreaConocimiento(rs.getString("COD_AREA"));
                area.setNomAreaConocimiento(rs.getString("NOMBRE_AREA"));

            }
            return  area;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally{
            this.cerrarResultSet(rs);
            this.cerrarStatement(cstm);
            this.cerrarConexion(con);
        }
    }
}
