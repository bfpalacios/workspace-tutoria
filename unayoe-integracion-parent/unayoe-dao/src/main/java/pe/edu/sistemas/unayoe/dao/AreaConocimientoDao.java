/*
 * 
 */
package pe.edu.sistemas.unayoe.dao;

import pe.edu.sistemas.unayoe.unayoe.bo.AreaConocimientoBO;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Created by Alex on 06/11/2015.
 */
public interface AreaConocimientoDao {
    
    /**
     * Listar todos.
     *
     * @return the list
     * @throws Exception the exception
     */
    public List<AreaConocimientoBO> listarTodos() throws Exception;
    
    /**
     * Listar areas aprobadas tutor.
     *
     * @param codigoTutor the codigo tutor
     * @return the list
     * @throws Exception the exception
     */
    public List<AreaConocimientoBO> listarAreasAprobadasTutor(String codigoTutor) throws Exception;

    /**
     * Obtner area tema.
     *
     * @param codigoTema the codigo tema
     * @return the area conocimiento BO
     */
    AreaConocimientoBO obtnerAreaTema(int codigoTema);
}
