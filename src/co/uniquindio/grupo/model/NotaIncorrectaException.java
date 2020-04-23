package co.uniquindio.grupo.model;

import java.io.Serializable;

/**
 * Excepcion si se ingresa una nota fuera del rando
 * 
 * @author sonia
 * @author sergio
 */
public class NotaIncorrectaException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	public NotaIncorrectaException () {
		super();
	}
	
	/**
	 * constructor de la clase
	 * 
	 * @param mensaje El mensaje a mostrar
	 */
	public NotaIncorrectaException(String mensaje) {
		super(mensaje);
		// TODO Auto-generated constructor stub
	}
}
