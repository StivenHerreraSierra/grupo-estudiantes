package co.uniquindio.grupo.model;

import java.io.Serializable;

/**
 * Clase para representar una asignatura
 * 
 * @author sonia
 * @author sergio
 */
public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Atributos de la asignatura
	 */
	private String codigo;
	private String nombre;

	public Asignatura () {
		this.codigo = "Sin código";
		this.nombre = "Sin nombre";
	}
	
	/**
	 * Constructor de la asignatura
	 * 
	 * @param codigo el codigo de la asignatura
	 * @param nombre El nombre de la asignatura
	 */
	public Asignatura(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param codigo El codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
