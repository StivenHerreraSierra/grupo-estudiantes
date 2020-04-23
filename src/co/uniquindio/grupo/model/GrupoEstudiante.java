package co.uniquindio.grupo.model;

import java.util.ArrayList;

import co.uniquindio.grupo.persistencia.Persistencia;
import co.uniquindio.grupo.view.ControladorIdioma;

import java.io.IOException;
import java.io.Serializable;

/**
 * Clase principal del mundo del problema
 * 
 * @author sonia
 * @author sergio
 */
public class GrupoEstudiante {
	private static final long serialVersionUID = 1L;
	/**
	 * Atributos de la clase
	 */
	private Asignatura misAsignaturas[];
	private ArrayList<Estudiante> misEstudiantes;
	private ArrayList<RegistroPlanilla> misPlanillas;

	/**
	 * Metodo constructor
	 */
	public GrupoEstudiante() {
		Asignatura miA;
		misAsignaturas = new Asignatura[3];
		misEstudiantes = new ArrayList<Estudiante>(3);
		misPlanillas = new ArrayList<RegistroPlanilla>();
		ArrayList<String> listado = new ArrayList<String>();
		listado.add("Paradigma");
		listado.add("Introduccion");
		listado.add("Geometria");
		for (int i = 0; i < 3; i++) {
			miA = new Asignatura("10" + i, listado.get(i));
			misAsignaturas[i] = miA;
		}
	}

	/**
	 * Permite agregar un estudiante
	 * 
	 * @param miE El estudiante
	 * @throws EstudianteException, si ya existe el estudiante
	 */
	public void agregarEstudiante(Estudiante miE) throws EstudianteException {
		int pos = obtenerEstudiante(miE.getCodigoEstudiante());
		if (pos == -1) {
			misEstudiantes.add(miE);
		} else {
			throw new EstudianteException("Ya existe el estudiante.");
		}
	}

	/**
	 * Permite crear un nuevo registro
	 * 
	 * @param idE,      el id del estudiante
	 * @param misNotas, las notas
	 * @throws EstudianteException si ya existe un registro de este estudiante
	 */
	public void crearRegistroPlanilla(String idE, ArrayList<Double> misNotas) throws EstudianteException {
		int pos = obtenerEstudiante(idE);
		RegistroPlanilla miPlanilla;
		Estudiante miEstudiante;
		if (pos != -1) {
			miEstudiante = misEstudiantes.get(pos);
			System.out.println("pos " + pos + "  codigo " + miEstudiante.getCodigoEstudiante());
			miPlanilla = new RegistroPlanilla(misNotas, misAsignaturas, miEstudiante);
			misPlanillas.add(miPlanilla);
		}
	}

	/**
	 * Permite obtener un estudiante
	 * 
	 * @param id El id del estudiante
	 * @return la posicion donde se encuentra o -1
	 */
	public int obtenerEstudiante(String id) {
		int pos = -1;
		boolean centinela = false;
		for (int i = 0; i < misEstudiantes.size() && centinela == false; i++) {
			String id2 = misEstudiantes.get(i).getCodigoEstudiante();

			if (id.equals(id2)) {
				pos = i;
				centinela = true;
			}
		}
		return pos;
	}

	/**
	 * Devuelve la planilla para el id correspondiente
	 * 
	 * @param id
	 * @return la posicion dentro de la planilla
	 */
	public int obtenerPlanilla(String id) {
		int pos = -1;
		boolean centinela = false;
		for (int i = 0; i < misPlanillas.size() && centinela == false; i++) {
			Estudiante miE = misPlanillas.get(i).getMiE();

			if (id.equals(miE.getCodigoEstudiante())) {
				pos = i;
				centinela = true;
			}
		}
		return pos;
	}

	/**
	 * Obtiene para el estudiante con el id solicitado a asignatura con mayor nota
	 * 
	 * @param id El id del estudiante
	 * @return la asignatura
	 * @throws EstudianteException
	 */
	public Asignatura obtenerAsignaturaMayorNota(String id)
			throws EstudianteException {
		Asignatura miA = null;
		int pos = obtenerPlanilla(id);
		RegistroPlanilla miPlanilla;
		if (pos == -1) {
			throw new EstudianteException("El estudiante no se encuentra en el sistema");
		}
		if (pos != -1) {
			miPlanilla = misPlanillas.get(pos);
			miA = miPlanilla.obtenerAsignaturaMayorNota();
		}
		return miA;
	}

	/**
	 * Devuelve un listado para actualizar la planilla
	 * 
	 * @return un listado
	 */
	public String[][] listariInfoPlanilla() {
		String arreglo[][] = new String[misPlanillas.size()][5];
		RegistroPlanilla miR;
		String id, nombre, nota0, nota1, nota2;
		String salida = "";
		for (int i = 0; i < misPlanillas.size(); i++) {
			miR = misPlanillas.get(i);
			id = miR.getMiE().getCodigoEstudiante();
			nombre = miR.getMiE().getNombreEstudiante();
			nota0 = "" + miR.getMisNotas().get(0);
			nota1 = "" + miR.getMisNotas().get(1);
			nota2 = "" + miR.getMisNotas().get(2);
			arreglo[i][0] = id;
			arreglo[i][1] = nombre;
			arreglo[i][2] = nota0;
			arreglo[i][3] = nota1;
			arreglo[i][4] = nota2;
			salida += id + " " + nombre + " " + nota0 + " " + nota1 + " " + nota2 + "\n";
		}
		System.out.println(salida);
		return arreglo;

	}

	/**
	 * Calcula el promedio general
	 * 
	 * @return el promedio
	 * @throws DivisorException, si el divisor es cero
	 */
	public double calcularPromGeneral() throws DivisorException {
		double prom = 0;
		if (misPlanillas.size() == 0) {
			throw new DivisorException("No hay registros en el sistema");
		}
		for (int i = 0; i < misPlanillas.size(); i++) {
			prom += misPlanillas.get(i).calcularDefinitiva();
		}
		prom = prom / misPlanillas.size();
		prom = (int)(prom * 100);
		prom = prom / 100;
		return prom;
	}
	
	public Asignatura[] getMisAsignaturas () {
		return this.misAsignaturas;
	}
	
	public void setMisAsignaturas (Asignatura[] misAsignaturas) {
		this.misAsignaturas = misAsignaturas;
	}
	
	public ArrayList<Estudiante> getMisEstudiantes (){
		return this.misEstudiantes;
	}
	
	public void setMisEstudiantes (ArrayList<Estudiante> misEstudiantes) {
		this.misEstudiantes = misEstudiantes;
	}
	
	public ArrayList<RegistroPlanilla> getMisPlanillas () {
		return this.misPlanillas;
	}

	public void setMisPlanillas (ArrayList<RegistroPlanilla> misPlanillas) {
		this.misPlanillas = misPlanillas;
	}
	
	public void guardarArchivoXML (String ruta) throws IOException {
		Persistencia.serializarXML(ruta, this);
	}
	
	public GrupoEstudiante cargarArchivoXML (String ruta)
			throws ClassNotFoundException, IOException {
		return (GrupoEstudiante) Persistencia.deserializarXML(ruta);
	}
	
	
}
