package co.uniquindio.grupo.model;

import java.io.Serializable;

/**
 * Clase para construir una excepcion si el estudiante esta repetido
 * 
 * @author sonia
 * @author sergio
 */
public class EstudianteException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;

	public EstudianteException () {
		super();
	}
	
	/**
	 * Constructor de la clase
	 * 
	 * @param mensaje Es el mensaje a mostrar
	 */
	public EstudianteException(String mensaje) {
		super(mensaje);
		// TODO Auto-generated constructor stub
	}
}
