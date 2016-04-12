package pe.edu.sistemas.unayoe.dao;

import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaParBO;

/**
 * Created by alexh on 03/12/15.
 */
public interface MatriculaParDao {

    MatriculaParBO matricularAlumno(MatriculaParBO matriculaParBO);

    MatriculaParBO buscarMatriculaPar(String codigoAlumno, int codigoActividad);
}
