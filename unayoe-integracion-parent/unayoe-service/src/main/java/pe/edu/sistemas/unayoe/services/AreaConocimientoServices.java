package pe.edu.sistemas.unayoe.services;

import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;

import java.util.List;

/**
 * Created by Alex on 06/11/2015
 */
public interface AreaConocimientoServices {
    public List<AreaConocimientoBO> listarTodos() throws Exception;
    public List<AreaConocimientoBO> listarAreasAprobadasTutor(String codigoTutor) throws Exception;
    AreaConocimientoBO obtnerAreaTema(int codigoTema);
}
