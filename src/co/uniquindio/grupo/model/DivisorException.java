package co.uniquindio.grupo.model;

import java.io.Serializable;

/**
 * Clase para representar una excepcion
 * 
 * @author sonia
 * @author sergio
 */
public class DivisorException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	public DivisorException () {
		super();
	}
	
	/**
	 * Constructor de la clase
	 * 
	 * @param mensaje El mensaje a mostrar
	 */
	public DivisorException(String mensaje) {
		super(mensaje);
		// TODO Auto-generated constructor stub
	}
}
