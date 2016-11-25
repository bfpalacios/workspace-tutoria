/*
 * 
 */
package pe.edu.sistemas.unayoe.dao;

import pe.edu.sistemas.unayoe.unayoe.bo.MatriculaParBO;

// TODO: Auto-generated Javadoc
/**
 * Created by alexh on 03/12/15.
 */
public interface MatriculaParDao {

    /**
     * Matricular alumno.
     *
     * @param matriculaParBO the matricula par BO
     * @return the matricula par BO
     */
    MatriculaParBO matricularAlumno(MatriculaParBO matriculaParBO);

    /**
     * Buscar matricula par.
     *
     * @param codigoAlumno the codigo alumno
     * @param codigoActividad the codigo actividad
     * @return the matricula par BO
     */
    MatriculaParBO buscarMatriculaPar(String codigoAlumno, int codigoActividad);
}
