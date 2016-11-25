package pe.edu.sistemas.unayoe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.AreaConocimientoDao;
import pe.edu.sistemas.unayoe.services.AreaConocimientoServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Created by Alex on 06/11/2015.
 */
@Service("areaConocimientoServices")
public class AreaConocimientoServicesImp implements AreaConocimientoServices {

    /** The area conocimiento dao. */
    @Autowired
    private AreaConocimientoDao areaConocimientoDao;

    /* (non-Javadoc)
     * @see pe.edu.sistemas.unayoe.services.AreaConocimientoServices#listarTodos()
     */
    public List<AreaConocimientoBO> listarTodos() throws Exception {
        return areaConocimientoDao.listarTodos();
    }

    /* (non-Javadoc)
     * @see pe.edu.sistemas.unayoe.services.AreaConocimientoServices#listarAreasAprobadasTutor(java.lang.String)
     */
    @Override
    public List<AreaConocimientoBO> listarAreasAprobadasTutor(String codigoTutor) throws Exception {
        return  areaConocimientoDao.listarAreasAprobadasTutor(codigoTutor);
    }

    /* (non-Javadoc)
     * @see pe.edu.sistemas.unayoe.services.AreaConocimientoServices#obtnerAreaTema(int)
     */
    @Override
    public AreaConocimientoBO obtnerAreaTema(int codigoTema) {
        return areaConocimientoDao.obtnerAreaTema(codigoTema);
    }
}
