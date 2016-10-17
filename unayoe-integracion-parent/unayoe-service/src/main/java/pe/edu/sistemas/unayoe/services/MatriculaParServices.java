package pe.edu.sistemas.unayoe.services;

import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaParBO;

/**
 * Created by alexh on 09/12/15.
 */
public interface MatriculaParServices {

    MatriculaParBO matricularAlumno(MatriculaParBO matriculaParBO);

    MatriculaParBO buscarMatriculaPar(String codigoAlumno, int codigoActividad);
}
