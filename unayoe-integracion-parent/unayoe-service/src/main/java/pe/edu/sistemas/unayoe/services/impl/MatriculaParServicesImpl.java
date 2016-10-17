package pe.edu.sistemas.unayoe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.sistemas.unayoe.dao.MatriculaParDao;
import pe.edu.sistemas.unayoe.services.MatriculaParServices;
import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaParBO;

/**
 * Created by alexh on 11/12/15.
 */
@Service
public class MatriculaParServicesImpl implements MatriculaParServices {

    @Autowired
    MatriculaParDao matriculaParDao;


    @Override
    public MatriculaParBO matricularAlumno(MatriculaParBO matriculaParBO) {
        return matriculaParDao.matricularAlumno(matriculaParBO);
    }

    @Override
    public MatriculaParBO buscarMatriculaPar(String codigoAlumno, int codigoActividad) {
        return matriculaParDao.buscarMatriculaPar(codigoAlumno, codigoActividad);
    }
}
