package pe.edu.sistemas.unayoe.core.util;

// TODO: Auto-generated Javadoc
/**
 * The Enum FormatoExcel.
 */
public enum FormatoExcel {
	
	/** The columna 1. */
	COLUMNA_1("COD_ALUMNO"), 
 /** The columna 2. */
 COLUMNA_2("APELLIDO_PAT"), 
 /** The columna 3. */
 COLUMNA_3("APELLIDO_MAT"), 
 /** The columna 4. */
 COLUMNA_4("NOMBRES"), 
 /** The columna 5. */
 COLUMNA_5(
			"COD_PLAN"), 
 /** The columna 6. */
 COLUMNA_6("COD_CURSO"), 
 /** The columna 7. */
 COLUMNA_7("NOM_CURSO"), 
 /** The columna 8. */
 COLUMNA_8("CREDITOS"), 
 /** The columna 9. */
 COLUMNA_9(
					"REPITENCIAS"), 
 /** The columna 10. */
 COLUMNA_10("COD_DOCENTE"), 
 /** The columna 11. */
 COLUMNA_11("NOMBRE_DOCENTE"), 
 /** The columna 12. */
 COLUMNA_12(
							"FRECUENCIA"), 
 /** The columna 13. */
 COLUMNA_13("DIA"), 
 /** The columna 14. */
 COLUMNA_14("HORA_INICIO"), 
 /** The columna 15. */
 COLUMNA_15("HORA_FIN");

	/** The titulo colum. */
	String tituloColum;

	/**
	 * Instantiates a new formato excel.
	 *
	 * @param tituloColum the titulo colum
	 */
	private FormatoExcel(String tituloColum) {
		this.tituloColum = tituloColum;
	}

	/**
	 * Gets the titulo colum.
	 *
	 * @return the titulo colum
	 */
	public String getTituloColum() {
		return tituloColum;
	}

	/**
	 * Sets the titulo colum.
	 *
	 * @param tituloColum the new titulo colum
	 */
	public void setTituloColum(String tituloColum) {
		this.tituloColum = tituloColum;
	}

}
