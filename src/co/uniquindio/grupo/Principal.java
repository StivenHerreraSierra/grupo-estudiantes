package co.uniquindio.grupo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import co.uniquindio.grupo.model.Asignatura;
import co.uniquindio.grupo.model.DivisorException;
import co.uniquindio.grupo.model.Estudiante;
import co.uniquindio.grupo.model.EstudianteException;
import co.uniquindio.grupo.model.GrupoEstudiante;
import co.uniquindio.grupo.view.ControladorGeneral;
import co.uniquindio.grupo.view.ControladorIdioma;
import co.uniquindio.grupo.persistencia.Persistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Ventana principal
 * 
 * @author sonia
 *
 */
public class Principal extends Application {
	/**
	 * Atributos de la clase
	 */
	private Stage escenarioPrincipal;
	private BorderPane layoutRaiz;
	private GrupoEstudiante miGrupo;
	private String ruta = "src/Grupo.dat";
	private ControladorIdioma controladorIdioma;
	private ControladorGeneral miControlador;

	@Override
	public void start(Stage primaryStage) {
		miGrupo = new GrupoEstudiante();
		cargarArchivoBin();
		this.escenarioPrincipal = primaryStage;
		inicializarLayoutRaiz ();
		mostrarVistaIdioma ();
	}

	/**
	 * Inicializa el layout raiz
	 */
	public void inicializarLayoutRaiz() {
		try {
			// Carga el root layout desde un archivo xml
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(Principal.class.getResource("view/LayoutRaiz.fxml"));
			layoutRaiz = (BorderPane) cargador.load();
			// Muestra la escena que contiene el RootLayout
			Scene scene = new Scene(layoutRaiz);
			escenarioPrincipal.setScene(scene);
			escenarioPrincipal.show();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void mostrarVistaIdioma () {
		try {
			FXMLLoader cargador = new FXMLLoader();
			cargador.setLocation(this.getClass().getResource("view/IdiomaVista.fxml"));
			AnchorPane ventana = (AnchorPane) cargador.load();
			controladorIdioma = cargador.getController();
			controladorIdioma.modificarPrincipal(this);
			layoutRaiz.setCenter(ventana);
			this.escenarioPrincipal.setTitle("Seleccion de idioma");
			escenarioPrincipal.sizeToScene();
			escenarioPrincipal.centerOnScreen();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Muestra la vista del estudiante
	 */
	public void mostrarVistaEstudiante() {
		try {
			// Carga la vista del estudiante.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principal.class.getResource("view/GeneralVista.fxml"));
			AnchorPane vistaEstudiante = (AnchorPane) loader.load();
			this.escenarioPrincipal.setTitle(controladorIdioma.getMensajeTexto("Etiqueta_Titulo_Escenario"));
			// Fija la vista de la person en el centro del root layout.
			layoutRaiz.setCenter(vistaEstudiante);
			escenarioPrincipal.sizeToScene();
			escenarioPrincipal.centerOnScreen();
			// Acceso al controlador.
			this.miControlador = loader.getController();
			miControlador.setMiVentanaPrincipal(this);
			miControlador.actualizarTabla();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo accesor
	 * 
	 * @return escenarioPrincipal
	 */
	public Stage getPrimaryStage() {
		return escenarioPrincipal;
	}

	/**
	 * Metodo principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Permite mostrar un mensaje
	 * 
	 * @param mensaje
	 * @param miA
	 * @param titulo
	 * @param cabecera
	 * @param contenido          el mensaje
	 * @param escenarioPrincipal
	 */
	public static void mostrarMensaje(String mensaje, AlertType miA, String titulo, String cabecera, String contenido,
			Stage escenarioPrincipal) {
		Alert alert = new Alert(miA);
		alert.initOwner(escenarioPrincipal);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecera);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

	/**
	 * Devuelve el escenario
	 * 
	 * @return escenarioPrincipal
	 */
	public Stage getEscenarioPrincipal() {
		return escenarioPrincipal;
	}

	/**
	 * Metodo modificador
	 * 
	 * @param escenarioPrincipal
	 */
	public void setEscenarioPrincipal(Stage escenarioPrincipal) {
		this.escenarioPrincipal = escenarioPrincipal;
	}

	/**
	 * Permite crear un registro
	 * 
	 * @param idE      El id del estudiante
	 * @param misNotas
	 * @throws EstudianteException Si el estudiante ya tiene un registro
	 */
	public void crearRegistroPlanilla(String idE, ArrayList<Double> misNotas) throws EstudianteException {
		miGrupo.crearRegistroPlanilla(idE, misNotas);
	}

	/**
	 * Permite agregar un estudiante
	 * 
	 * @param miE El estudiante
	 * @throws EstudianteException Si ya existe el estudiante
	 */
	public void agregarEstudiante(Estudiante miE) throws EstudianteException {
		miGrupo.agregarEstudiante(miE);
	}

	/**
	 * Devuelve la informacion de la planilla
	 * 
	 * @return el listado
	 */
	public String[][] listariInfoPlanilla() {
		return miGrupo.listariInfoPlanilla();
	}

	/**
	 * Devuelve la asignatura con mayor nota
	 * 
	 * @param id El id del estudiante
	 * @return una asignatura
	 * @throws EstudianteException
	 */
	public Asignatura obtenerAsignaturaMayorNota(String id) throws EstudianteException {
		return miGrupo.obtenerAsignaturaMayorNota(id);
	}

	/**
	 * Calcula el promedio general
	 * 
	 * @return el promedio
	 * @throws DivisorException
	 */
	public double calcularPromGeneral() throws DivisorException {
		return miGrupo.calcularPromGeneral();
	}

	/**
	 * Permite leer un entero
	 * 
	 * @param mensaje
	 * @return
	 */
	public static int leerEntero(String mensaje) {
		return Integer.parseInt(leerMensaje(mensaje));
	}

	/**
	 * Permite leer un mensaje
	 * 
	 * @param mensaje
	 * @return el texto leido
	 */
	public static String leerMensaje(String mensaje) {
		String salida = "";
		// Muestra el mensaje
		TextInputDialog miDialogo = new TextInputDialog("");
		miDialogo.setTitle("Ingreso de datos");
		miDialogo.setHeaderText("");
		miDialogo.setContentText(mensaje);

		// Forma tradicional de obtener la resuesta.
		Optional<String> result = miDialogo.showAndWait();
		if (result.isPresent()) {
			salida = result.get();
		}
		return salida;
	}
	
	public void cargarArchivoBin () {
		try {
			File archivo = new File (ruta);
			if (archivo.exists() == true) {
				miGrupo = miGrupo.cargarArchivoBin(ruta);
			}
		} catch (IOException|ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void guardarArchivoBin () {
		try {
			miGrupo.guardarArchivoBin (ruta);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void stop () {
		if (miControlador != null) {
			this.miControlador.cerrarEjecucion();
			guardarArchivoBin();
		}
	}
}
