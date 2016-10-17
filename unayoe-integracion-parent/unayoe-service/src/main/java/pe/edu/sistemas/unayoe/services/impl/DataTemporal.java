package pe.edu.sistemas.unayoe.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.ConvocatoriaBO;
import pe.edu.sistemas.unayoe.unayoe.bo.PeriodoBO;
import pe.edu.sistemas.unayoe.unayoe.bo.PostulacionBO;
import pe.edu.sistemas.unayoe.unayoe.bo.TemaBO;

@Component("dataTemporal")
@Scope("singleton")
public class DataTemporal {

	private List<ConvocatoriaBO> convocatorias;
	private List<PostulacionBO> postulaciones;
	private List<PeriodoBO> periodos;
	private List<TemaBO> temas;

	private static Integer serial_id = 0;

	private static Integer getNextId() {
		serial_id += 1;
		return serial_id;
	}

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

	public List<PeriodoBO> getPeriodos() {
		return periodos;
	}

	public void setPeriodos(List<PeriodoBO> periodos) {
		this.periodos = periodos;
	}

	/*
	 * gestion de convocatorias
	 */
	public boolean addConvocatoria(ConvocatoriaBO convocatoria) {
		convocatoria.setId(DataTemporal.getNextId());
		return this.convocatorias.add(convocatoria);
	}

	public boolean removeConvocatoria(Integer id) {
		for (ConvocatoriaBO convocatoria : convocatorias) {
			if (convocatoria.getId() == id) {
				((ArrayList<ConvocatoriaBO>) this.convocatorias).remove(convocatoria);
				return true;
			}
		}
		return false;
	}

	public ConvocatoriaBO getConvocatoria(Integer id) {
		for (ConvocatoriaBO convocatoria : this.convocatorias) {
			if (convocatoria.getId() == id) {
				return convocatoria;
			}
		}
		return null;
	}

	public List<ConvocatoriaBO> getConvocatorias() {
		return convocatorias;
	}

	public void setConvocatorias(List<ConvocatoriaBO> convocatorias) {
		this.convocatorias = convocatorias;
	}

	/*
	 * gestion de postulaciones
	 */
	public void setPostulaciones(List<PostulacionBO> postulaciones) {
		this.postulaciones = postulaciones;
	}

	public boolean addPostulacion(PostulacionBO postulacion) {
		postulacion.setId(DataTemporal.getNextId());
		return this.postulaciones.add(postulacion);
	}

	public List<PostulacionBO> getPostulaciones() {
		return postulaciones;
	}

	/*
	 * gestion de temas
	 */
	public List<TemaBO> getTemas() {
		return temas;
	}

	public List<TemaBO> getTemas(String codigoCurso) {
		List<TemaBO> temas = new ArrayList<TemaBO>();
		for (TemaBO tema : this.temas) {
			if (tema.getCodigoCurso().compareTo(codigoCurso) == 0) {
				temas.add(tema);
			}
		}
		return temas;
	}

	public TemaBO getTema(Integer id) {
		for (TemaBO tema : this.temas) {
			if (tema.getId() == id) {
				return tema;
			}
		}
		return null;
	}

	public boolean addTema(TemaBO tema) {
		tema.setId(DataTemporal.getNextId());
		return this.temas.add(tema);
	}

	public boolean deleteTema(Integer id) {
		for (TemaBO tema : this.temas) {
			if (tema.getId() == id) {
				return this.temas.remove(tema);
			}
		}
		return false;
	}

	public void setTemas(List<TemaBO> temas) {
		this.temas = temas;
	}

}

