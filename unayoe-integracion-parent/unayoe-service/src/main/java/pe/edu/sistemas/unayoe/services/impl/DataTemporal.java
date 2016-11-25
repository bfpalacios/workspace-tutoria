package pe.edu.sistemas.unayoe.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.PeriodoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.PostulacionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

// TODO: Auto-generated Javadoc
/**
 * The Class DataTemporal.
 */
@Component("dataTemporal")
@Scope("singleton")
public class DataTemporal {

	/** The convocatorias. */
	private List<ConvocatoriaBO> convocatorias;
	
	/** The postulaciones. */
	private List<PostulacionBO> postulaciones;
	
	/** The periodos. */
	private List<PeriodoBO> periodos;
	
	/** The temas. */
	private List<TemaBO> temas;

	/** The serial id. */
	private static Integer serial_id = 0;

	/**
	 * Gets the next id.
	 *
	 * @return the next id
	 */
	private static Integer getNextId() {
		serial_id += 1;
		return serial_id;
	}

	/**
	 * Instantiates a new data temporal.
	 */
	public DataTemporal() {
		this.convocatorias = new ArrayList<ConvocatoriaBO>();
		this.postulaciones = new ArrayList<PostulacionBO>();
		this.periodos = new ArrayList<PeriodoBO>();
		this.temas = new ArrayList<TemaBO>();

		PeriodoBO periodo1 = new PeriodoBO();
		periodo1.setId(1);
		periodo1.setDescripcion("2014-I");
		this.periodos.add(periodo1);

		PeriodoBO periodo2 = new PeriodoBO();
		periodo2.setId(2);
		periodo2.setDescripcion("2014-II");
		this.periodos.add(periodo2);

		PeriodoBO periodo3 = new PeriodoBO();
		periodo3.setId(3);
		periodo3.setDescripcion("2015-I");
		this.periodos.add(periodo3);

		PeriodoBO periodo4 = new PeriodoBO();
		periodo4.setId(4);
		periodo4.setDescripcion("2015-II");
		this.periodos.add(periodo4);
		this.temas.add(new TemaBO(15, "Algoritmos", "Algortimos", "C0202008"));
		this.temas.add(new TemaBO(16, "Programación paralela", "Algortimos", "C0202008"));
	}

	/**
	 * Gets the periodo.
	 *
	 * @param id the id
	 * @return the periodo
	 */
	/*
	 * gestion de periodos
	 */
	public PeriodoBO getPeriodo(Integer id) {
		for (PeriodoBO periodo : this.periodos) {
			if (periodo.getId() == id) {
				return periodo;
			}
		}
		return null;
	}

	/**
	 * Gets the periodos.
	 *
	 * @return the periodos
	 */
	public List<PeriodoBO> getPeriodos() {
		return periodos;
	}

	/**
	 * Sets the periodos.
	 *
	 * @param periodos the new periodos
	 */
	public void setPeriodos(List<PeriodoBO> periodos) {
		this.periodos = periodos;
	}

	/**
	 * Adds the convocatoria.
	 *
	 * @param convocatoria the convocatoria
	 * @return true, if successful
	 */
	/*
	 * gestion de convocatorias
	 */
	public boolean addConvocatoria(ConvocatoriaBO convocatoria) {
		convocatoria.setId(DataTemporal.getNextId());
		return this.convocatorias.add(convocatoria);
	}

	/**
	 * Removes the convocatoria.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean removeConvocatoria(Integer id) {
		for (ConvocatoriaBO convocatoria : convocatorias) {
			if (convocatoria.getId() == id) {
				((ArrayList<ConvocatoriaBO>) this.convocatorias).remove(convocatoria);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the convocatoria.
	 *
	 * @param id the id
	 * @return the convocatoria
	 */
	public ConvocatoriaBO getConvocatoria(Integer id) {
		for (ConvocatoriaBO convocatoria : this.convocatorias) {
			if (convocatoria.getId() == id) {
				return convocatoria;
			}
		}
		return null;
	}

	/**
	 * Gets the convocatorias.
	 *
	 * @return the convocatorias
	 */
	public List<ConvocatoriaBO> getConvocatorias() {
		return convocatorias;
	}

	/**
	 * Sets the convocatorias.
	 *
	 * @param convocatorias the new convocatorias
	 */
	public void setConvocatorias(List<ConvocatoriaBO> convocatorias) {
		this.convocatorias = convocatorias;
	}

	/**
	 * Sets the postulaciones.
	 *
	 * @param postulaciones the new postulaciones
	 */
	/*
	 * gestion de postulaciones
	 */
	public void setPostulaciones(List<PostulacionBO> postulaciones) {
		this.postulaciones = postulaciones;
	}

	/**
	 * Adds the postulacion.
	 *
	 * @param postulacion the postulacion
	 * @return true, if successful
	 */
	public boolean addPostulacion(PostulacionBO postulacion) {
		postulacion.setId(DataTemporal.getNextId());
		return this.postulaciones.add(postulacion);
	}

	/**
	 * Gets the postulaciones.
	 *
	 * @return the postulaciones
	 */
	public List<PostulacionBO> getPostulaciones() {
		return postulaciones;
	}

	/**
	 * Gets the temas.
	 *
	 * @return the temas
	 */
	/*
	 * gestion de temas
	 */
	public List<TemaBO> getTemas() {
		return temas;
	}

	/**
	 * Gets the temas.
	 *
	 * @param codigoCurso the codigo curso
	 * @return the temas
	 */
	public List<TemaBO> getTemas(String codigoCurso) {
		List<TemaBO> temas = new ArrayList<TemaBO>();
		for (TemaBO tema : this.temas) {
			if (tema.getCodigoCurso().compareTo(codigoCurso) == 0) {
				temas.add(tema);
			}
		}
		return temas;
	}

	/**
	 * Gets the tema.
	 *
	 * @param id the id
	 * @return the tema
	 */
	public TemaBO getTema(Integer id) {
		for (TemaBO tema : this.temas) {
			if (tema.getId() == id) {
				return tema;
			}
		}
		return null;
	}

	/**
	 * Adds the tema.
	 *
	 * @param tema the tema
	 * @return true, if successful
	 */
	public boolean addTema(TemaBO tema) {
		tema.setId(DataTemporal.getNextId());
		return this.temas.add(tema);
	}

	/**
	 * Delete tema.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean deleteTema(Integer id) {
		for (TemaBO tema : this.temas) {
			if (tema.getId() == id) {
				return this.temas.remove(tema);
			}
		}
		return false;
	}

	/**
	 * Sets the temas.
	 *
	 * @param temas the new temas
	 */
	public void setTemas(List<TemaBO> temas) {
		this.temas = temas;
	}

}

