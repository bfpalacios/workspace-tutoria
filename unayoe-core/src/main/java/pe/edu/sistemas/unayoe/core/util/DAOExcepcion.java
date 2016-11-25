package pe.edu.sistemas.unayoe.core.util;

// TODO: Auto-generated Javadoc
/**
 * The Class DAOExcepcion.
 */
public class DAOExcepcion extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new DAO excepcion.
	 */
	public DAOExcepcion() {
		super();
	}

	/**
	 * Instantiates a new DAO excepcion.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	public DAOExcepcion(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Instantiates a new DAO excepcion.
	 *
	 * @param arg0 the arg 0
	 */
	public DAOExcepcion(String arg0) {
		super(arg0);
	}

	/**
	 * Instantiates a new DAO excepcion.
	 *
	 * @param arg0 the arg 0
	 */
	public DAOExcepcion(Throwable arg0) {
		super(arg0);
	}	
}

