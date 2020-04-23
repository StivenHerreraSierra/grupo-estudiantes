package co.uniquindio.grupo.model;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * @version 1.0
 * @author Sonia Jaramillo Valbuena
 * @author Sergio A. Cardona
 * 
 *         Maneja la informacion referente a la asignatura
 * 
 */

public class RegistroPlanilla implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Atributos de la clase
	 */
	private ArrayList<Double> misNotas;
	private Asignatura misAsignatura[];
	private Estudiante miE;

	public RegistroPlanilla () {
		misNotas = new ArrayList<Double> ();
		misAsignatura = null;
		miE = null;
	}
	/**
	 * Constructor de la clase
	 * 
	 * @param misNotas,      las notas del estudiante
	 * @param misAsignatura, las asignaturas
	 * @param miE,           El estudiante
	 */
	public RegistroPlanilla(ArrayList<Double> misNotas, Asignatura[] misAsignatura, Estudiante miE) {
		super();
		this.misNotas = misNotas;
		this.misAsignatura = misAsignatura;
		this.miE = miE;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return misNotas
	 */
	public ArrayList<Double> getMisNotas() {
		return misNotas;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param misNotas
	 */
	public void setMisNotas(ArrayList<Double> misNotas) {
		this.misNotas = misNotas;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return misAsignatura
	 */
	public Asignatura[] getMisAsignatura() {
		return misAsignatura;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param misAsignatura
	 */
	public void setMisAsignatura(Asignatura[] misAsignatura) {
		this.misAsignatura = misAsignatura;
	}

	/**
	 * Metodo accesor
	 * 
	 * @return miE
	 */
	public Estudiante getMiE() {
		return miE;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param miE
	 */
	public void setMiE(Estudiante miE) {
		this.miE = miE;
	}

	/**
	 * permite calcular la definitiva
	 * 
	 * @return
	 */
	public double calcularDefinitiva() {
		double prom = 0;
		for (int i = 0; i < misNotas.size(); i++) {
			prom += misNotas.get(i);
		}
		prom = prom / misNotas.size();
		return prom;
	}

	/**
	 * Informa si gano el semesntre
	 * 
	 * @return true si gano, false en caso contrario
	 */
	public boolean isGanador() {
		boolean ganador = false;
		double def = calcularDefinitiva();
		if (def >= 3) {
			ganador = true;
		}
		return ganador;
	}

	/**
	 * Devuelve la asignatura con mayor nota
	 * 
	 * @return la asignatura
	 */
	public Asignatura obtenerAsignaturaMayorNota() {
		Asignatura miA = null;
		double mayor = 0, notaActual;

		for (int i = 0; i < getMisNotas().size(); i++) {
			notaActual = getMisNotas().get(i);
			if (notaActual > mayor) {
				mayor = notaActual;
				miA = getMisAsignatura()[i];
			}
		}
		return miA;
	}
}
