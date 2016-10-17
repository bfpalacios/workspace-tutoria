package pe.edu.sistemas.unayoe.core.util;

public enum FormatoExcel {
	COLUMNA_1("COD_ALUMNO"), COLUMNA_2("APELLIDO_PAT"), COLUMNA_3("APELLIDO_MAT"), COLUMNA_4("NOMBRES"), COLUMNA_5(
			"COD_PLAN"), COLUMNA_6("COD_CURSO"), COLUMNA_7("NOM_CURSO"), COLUMNA_8("CREDITOS"), COLUMNA_9(
					"REPITENCIAS"), COLUMNA_10("COD_DOCENTE"), COLUMNA_11("NOMBRE_DOCENTE"), COLUMNA_12(
							"FRECUENCIA"), COLUMNA_13("DIA"), COLUMNA_14("HORA_INICIO"), COLUMNA_15("HORA_FIN");

	String tituloColum;

	private FormatoExcel(String tituloColum) {
		this.tituloColum = tituloColum;
	}

	public String getTituloColum() {
		return tituloColum;
	}

	public void setTituloColum(String tituloColum) {
		this.tituloColum = tituloColum;
	}

}
