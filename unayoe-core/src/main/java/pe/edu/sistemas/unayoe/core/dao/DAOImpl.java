package pe.edu.sistemas.unayoe.core.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class DAOImpl.
 *
 * @param <Entidad> the generic type
 * @param <Id> the generic type
 */
@Repository("dao")
public class DAOImpl<Entidad,Id extends Serializable> implements IDAO<Entidad, Id>{

	/** The session factory. */
	@Autowired
	protected SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.core.dao.IDAO#insertar(java.lang.Object)
	 */
	public void insertar(Entidad entidad) throws Exception {
		sessionFactory.getCurrentSession().persist(entidad);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().evict(entidad);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.core.dao.IDAO#actualizar(java.lang.Object)
	 */
	public void actualizar(Entidad entidad) throws Exception {
		sessionFactory.getCurrentSession().merge(entidad);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().evict(entidad);
	}

	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.core.dao.IDAO#eliminar(java.io.Serializable)
	 */
	public void eliminar(Serializable id) throws Exception {
		
	} 
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.core.dao.IDAO#obtenerEntidadPorId(java.lang.Class, java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public Entidad obtenerEntidadPorId(Class<Entidad> clase, Serializable id)
			throws Exception {
		return (Entidad) sessionFactory.getCurrentSession().createCriteria(clase).add(Restrictions.idEq(id)).uniqueResult();
	}
	
	/* (non-Javadoc)
	 * @see pe.edu.sistemas.unayoe.core.dao.IDAO#listarTodos(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public List<Entidad> listarTodos(Class<Entidad> clase) throws Exception {
		List<Entidad> listaEntidad = new ArrayList<Entidad>();
	    sessionFactory.getCurrentSession().flush();
	    listaEntidad = sessionFactory.getCurrentSession().createCriteria(clase).list();
	    return listaEntidad;
	}
}
