package pe.edu.sistemas.unayoe.model;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

import pe.edu.sistemas.unayoe.unayoe.bo.DisponibilidadTutoriaParBO;

@Component("disponibilidadTutoriaParModel")
@ViewScoped
public class DisponibilidadTutoriaParModel {

	private List<DisponibilidadTutoriaParBO> disponibilidad;
	
	public DisponibilidadTutoriaParModel() {
		this.disponibilidad = new ArrayList<DisponibilidadTutoriaParBO>();
	}
	
	public void limpiar() {
		this.disponibilidad = new ArrayList<DisponibilidadTutoriaParBO>();
	}

	private DisponibilidadTutoriaParBO buscaDisponibilidad(Integer dia, Integer turno) {
		DisponibilidadTutoriaParBO disp = null;
		for (DisponibilidadTutoriaParBO item : this.disponibilidad) {
			if (item.getDia() == dia && item.getTurno() == turno) {
				return item;
			}
		}
		return disp;
	}

	public List<DisponibilidadTutoriaParBO> getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(List<DisponibilidadTutoriaParBO> disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

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

	public boolean isLunesTarde() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.LUNES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (disp == null) {
			return false;
		}
		return true;
	}

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

	public boolean isLunesNoche() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.LUNES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (disp == null) {
			return false;
		}
		return true;
	}

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

	public boolean isMartesTarde() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MARTES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (disp == null) {
			return false;
		}
		return true;
	}

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

	public boolean isMartesNoche() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MARTES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (disp == null) {
			return false;
		}
		return true;
	}

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

	public boolean isMiercolesTarde() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MIERCOLES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (disp == null) {
			return false;
		}
		return true;
	}

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

	public boolean isMiercolesNoche() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.MIERCOLES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (disp == null) {
			return false;
		}
		return true;
	}

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

	public boolean isJuevesTarde() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.JUEVES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (disp == null) {
			return false;
		}
		return true;
	}

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

	public boolean isJuevesNoche() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.JUEVES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (disp == null) {
			return false;
		}
		return true;
	}

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

	public boolean isViernesTarde() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.VIERNES,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (disp == null) {
			return false;
		}
		return true;
	}

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

	public boolean isViernesNoche() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.VIERNES,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (disp == null) {
			return false;
		}
		return true;
	}

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

	public boolean isSabadoTarde() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.SABADO,
				DisponibilidadTutoriaParBO.TURNO_TARDE);
		if (disp == null) {
			return false;
		}
		return true;
	}

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

	public boolean isSabadoNoche() {
		DisponibilidadTutoriaParBO disp = this.buscaDisponibilidad(DisponibilidadTutoriaParBO.SABADO,
				DisponibilidadTutoriaParBO.TURNO_NOCHE);
		if (disp == null) {
			return false;
		}
		return true;
	}

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
