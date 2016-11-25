package pe.edu.sistemas.unayoe.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadTutoriaParBO;

// TODO: Auto-generated Javadoc
/**
 * The Class DisponibilidadTutoriaParModel.
 */
@Component("disponibilidadTutoriaParModel")
@ViewScoped
public class DisponibilidadTutoriaParModel {

	/** The disponibilidad. */
	private List<DisponibilidadTutoriaParBO> disponibilidad;
	
	/**
	 * Instantiates a new disponibilidad tutoria par model.
	 */
	public DisponibilidadTutoriaParModel() {
		this.disponibilidad = new ArrayList<DisponibilidadTutoriaParBO>();
	}
	
	/**
	 * Limpiar.
	 */
	public void limpiar() {
		this.disponibilidad = new ArrayList<DisponibilidadTutoriaParBO>();
	}

	/**
	 * Busca disponibilidad.
	 *
	 * @param dia the dia
	 * @param turno the turno
	 * @return the disponibilidad tutoria par BO
	 */
	private DisponibilidadTutoriaParBO buscaDisponibilidad(Integer dia, Integer turno) {
		DisponibilidadTutoriaParBO disp = null;
		for (DisponibilidadTutoriaParBO item : this.disponibilidad) {
			if (item.getDia() == dia && item.getTurno() == turno) {
				return item;
			}
		}
		return disp;
	}

	/**
	 * Gets the disponibilidad.
	 *
	 * @return the disponibilidad
	 */
	public List<DisponibilidadTutoriaParBO> getDisponibilidad() {
		return disponibilidad;
	}

	/**
	 * Sets the disponibilidad.
	 *
	 * @param disponibilidad the new disponibilidad
	 */
	public void setDisponibilidad(List<DisponibilidadTutoriaParBO> disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	/**
	 * Checks if is lunes manana.
	 *
	 * @return true, if is lunes manana
	 */
	/*
	 * Lunes
	 */
	public boolean isLunesManana() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.LUNES,
				DisponibilidadTutoriaParBO.TURNO_MANANA);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the lunes manana.
	 *
	 * @param value the new lunes manana
	 */
	public void setLunesManana(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.LUNES,
				DisponibilidadTutoriaParBO.TURNO_MANANA);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.LUNES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_MANANA);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is lunes tarde.
	 *
	 * @return true, if is lunes tarde
	 */
	public boolean isLunesTarde() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.LUNES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the lunes tarde.
	 *
	 * @param value the new lunes tarde
	 */
	public void setLunesTarde(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.LUNES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.LUNES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_TARDE);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is lunes noche.
	 *
	 * @return true, if is lunes noche
	 */
	public boolean isLunesNoche() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.LUNES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the lunes noche.
	 *
	 * @param value the new lunes noche
	 */
	public void setLunesNoche(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.LUNES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.LUNES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_NOCHE);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is martes manana.
	 *
	 * @return true, if is martes manana
	 */
	/*
	 * Martes
	 */
	public boolean isMartesManana() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MARTES,
				DisponibilidadTutoriaParBO.TURNO_MANANA);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the martes manana.
	 *
	 * @param value the new martes manana
	 */
	public void setMartesManana(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MARTES,
				DisponibilidadTutoriaParBO.TURNO_MANANA);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.MARTES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_MANANA);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is martes tarde.
	 *
	 * @return true, if is martes tarde
	 */
	public boolean isMartesTarde() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MARTES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the martes tarde.
	 *
	 * @param value the new martes tarde
	 */
	public void setMartesTarde(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MARTES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.MARTES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_TARDE);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is martes noche.
	 *
	 * @return true, if is martes noche
	 */
	public boolean isMartesNoche() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MARTES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the martes noche.
	 *
	 * @param value the new martes noche
	 */
	public void setMartesNoche(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MARTES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.MARTES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_NOCHE);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is miercoles manana.
	 *
	 * @return true, if is miercoles manana
	 */
	/*
	 * Miercoles
	 */
	public boolean isMiercolesManana() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MIERCOLES,
				DisponibilidadTutoriaParBO.TURNO_MANANA);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the miercoles manana.
	 *
	 * @param value the new miercoles manana
	 */
	public void setMiercolesManana(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MIERCOLES,
				DisponibilidadTutoriaParBO.TURNO_MANANA);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.MIERCOLES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_MANANA);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is miercoles tarde.
	 *
	 * @return true, if is miercoles tarde
	 */
	public boolean isMiercolesTarde() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MIERCOLES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the miercoles tarde.
	 *
	 * @param value the new miercoles tarde
	 */
	public void setMiercolesTarde(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MIERCOLES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.MIERCOLES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_TARDE);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is miercoles noche.
	 *
	 * @return true, if is miercoles noche
	 */
	public boolean isMiercolesNoche() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MIERCOLES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the miercoles noche.
	 *
	 * @param value the new miercoles noche
	 */
	public void setMiercolesNoche(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MIERCOLES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.MIERCOLES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_NOCHE);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is jueves manana.
	 *
	 * @return true, if is jueves manana
	 */
	/*
	 * Jueves
	 */
	public boolean isJuevesManana() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.JUEVES,
				DisponibilidadTutoriaParBO.TURNO_MANANA);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the jueves manana.
	 *
	 * @param value the new jueves manana
	 */
	public void setJuevesManana(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.JUEVES,
				DisponibilidadTutoriaParBO.TURNO_MANANA);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.JUEVES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_MANANA);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is jueves tarde.
	 *
	 * @return true, if is jueves tarde
	 */
	public boolean isJuevesTarde() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.JUEVES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the jueves tarde.
	 *
	 * @param value the new jueves tarde
	 */
	public void setJuevesTarde(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.JUEVES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.JUEVES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_TARDE);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is jueves noche.
	 *
	 * @return true, if is jueves noche
	 */
	public boolean isJuevesNoche() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.JUEVES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the jueves noche.
	 *
	 * @param value the new jueves noche
	 */
	public void setJuevesNoche(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.JUEVES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.JUEVES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_NOCHE);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is viernes manana.
	 *
	 * @return true, if is viernes manana
	 */
	/*
	 * Viernes
	 */
	public boolean isViernesManana() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.VIERNES,
				DisponibilidadTutoriaParBO.TURNO_MANANA);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the viernes manana.
	 *
	 * @param value the new viernes manana
	 */
	public void setViernesManana(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.VIERNES,
				DisponibilidadTutoriaParBO.TURNO_MANANA);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.VIERNES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_MANANA);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is viernes tarde.
	 *
	 * @return true, if is viernes tarde
	 */
	public boolean isViernesTarde() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.VIERNES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the viernes tarde.
	 *
	 * @param value the new viernes tarde
	 */
	public void setViernesTarde(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.VIERNES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.VIERNES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_TARDE);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is viernes noche.
	 *
	 * @return true, if is viernes noche
	 */
	public boolean isViernesNoche() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.VIERNES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the viernes noche.
	 *
	 * @param value the new viernes noche
	 */
	public void setViernesNoche(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.VIERNES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.VIERNES);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_NOCHE);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is sabado manana.
	 *
	 * @return true, if is sabado manana
	 */
	/*
	 * Sabado
	 */
	public boolean isSabadoManana() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.SABADO,
				DisponibilidadTutoriaParBO.TURNO_MANANA);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the sabado manana.
	 *
	 * @param value the new sabado manana
	 */
	public void setSabadoManana(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.SABADO,
				DisponibilidadTutoriaParBO.TURNO_MANANA);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.SABADO);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_MANANA);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is sabado tarde.
	 *
	 * @return true, if is sabado tarde
	 */
	public boolean isSabadoTarde() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.SABADO,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the sabado tarde.
	 *
	 * @param value the new sabado tarde
	 */
	public void setSabadoTarde(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.SABADO,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.SABADO);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_TARDE);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

	/**
	 * Checks if is sabado noche.
	 *
	 * @return true, if is sabado noche
	 */
	public boolean isSabadoNoche() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.SABADO,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (disp == null) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the sabado noche.
	 *
	 * @param value the new sabado noche
	 */
	public void setSabadoNoche(boolean value) {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.SABADO,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (value == true && disp == null) {
			disp = new DisponibilidadTutoriaParBO();
			disp.setDia(DisponibilidadTutoriaParBO.SABADO);
			disp.setTurno(DisponibilidadTutoriaParBO.TURNO_NOCHE);
			this.disponibilidad.add(disp);
		} else if (value == false && disp != null) {
			this.disponibilidad.remove(disp);
		}
	}

}
