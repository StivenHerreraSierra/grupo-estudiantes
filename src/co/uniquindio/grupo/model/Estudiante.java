package co.uniquindio.grupo.model;

import java.io.Serializable;

/**
 * @version 1.0
 * @author Sonia Jaramillo Valbuena
 * @author Sergio A. Cardona
 * 
 *         Esta es la clase principal del mundo
 * 
 */
public class Estudiante implements Serializable {
	private static final long serialVersionUID = 1L;
//Se definen los atributos
	private String codigoEstudiante;
	private String nombreEstudiante;

	public Estudiante () {
		this.codigoEstudiante = "Sin código";
		this.nombreEstudiante = "Sin nombre";
	}
	
	/**
	 * Constructor del la clase Estudiante
	 * 
	 * @param codigoEstudiante codigo del estudiante solo puede tomar valores
	 *                         numericos
	 * @param nombreEstudiante solo puede tener letras
	 * @param Fecha            miFechaNacimiento
	 */
	public Estudiante(String codigoEstudiante, String nombreEstudiante) {
		this.codigoEstudiante = codigoEstudiante;
		this.nombreEstudiante = nombreEstudiante;

	}

	/**
	 * Metodo accesor
	 * 
	 * @return String el codigo del Estudiante
	 */
	public String getCodigoEstudiante() {
		return codigoEstudiante;
	}

	public void setCodigoEstudiante (String codigoEstudiante) {
		this.codigoEstudiante = codigoEstudiante;
	}
	
	/**
	 * Metodo accesor
	 * 
	 * @return String el nombre del estudiante
	 */
	public String getNombreEstudiante() {
		return nombreEstudiante;
	}

	public void setNombreEstudiante (String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}
}
