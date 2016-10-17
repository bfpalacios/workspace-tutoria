package pe.edu.sistemas.unayoe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.sistemas.unayoe.dao.AreaConocimientoDao;
import pe.edu.sistemas.unayoe.services.AreaConocimientoServices;
import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;

import java.util.List;

/**
 * Created by Alex on 06/11/2015
 */
@Service("areaConocimientoServices")
public class AreaConocimientoServicesImp implements AreaConocimientoServices {

    @Autowired
    private AreaConocimientoDao areaConocimientoDao;

    public List<AreaConocimientoBO> listarTodos() throws Exception {
        return areaConocimientoDao.listarTodos();
    }

    @Override
    public List<AreaConocimientoBO> listarAreasAprobadasTutor(String codigoTutor) throws Exception {
        return  areaConocimientoDao.listarAreasAprobadasTutor(codigoTutor);
    }

    @Override
    public AreaConocimientoBO obtnerAreaTema(int codigoTema) {
        return areaConocimientoDao.obtnerAreaTema(codigoTema);
    }
}
