package co.uniquindio.grupo.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import co.uniquindio.grupo.Principal;
import co.uniquindio.grupo.model.Asignatura;
import co.uniquindio.grupo.model.DivisorException;
import co.uniquindio.grupo.model.Estudiante;
import co.uniquindio.grupo.model.EstudianteException;
import co.uniquindio.grupo.model.NotaIncorrectaException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * Permite editar los datos de el estudiante
 * 
 * @author Sonia Jaramillo
 */
public class ControladorGeneral implements Initializable {	
	// VentanaPrincipal
	private Principal miVentanaPrincipal;
	String[][] data = new String[1][5];
	@FXML
	private TextField idTextField;
	@FXML
	private TextField nombreTextField;
	@FXML
	private TextField nota0TextField;
	@FXML
	private TextField nota1TextField;
	@FXML
	private TextField nota2TextField;

	@FXML
	private Label enunciadoLabel;
	@FXML
	private Label idLabel;
	@FXML
	private Label nombreLabel;
	@FXML
	private Label nota1Label;
	@FXML
	private Label nota2Label;
	@FXML
	private Label nota3Label;
	
	@FXML
	private Button guardarButton;
	@FXML
	private Button mayorButton;
	@FXML
	private Button promedioButton;
	
	@FXML
	private TableView<String[]> tv;
	@FXML
	public TableColumn<String[], String> id;
	@FXML
	public TableColumn<String[], String> nombre;
	@FXML
	public TableColumn<String[], String> nota1;
	@FXML
	public TableColumn<String[], String> nota2;
	@FXML
	public TableColumn<String[], String> nota3;

	public ControladorIdioma idioma;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idioma = new ControladorIdioma();
		
		// make sure the property value factory should be exactly same as the e.g
		// getStudentId from your model class
		// data[0] = new String[]{"Jon Skeet","876k","","",""};
		id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
				String[] x = p.getValue();
				if (x != null && x.length > 0) {
					return new SimpleStringProperty(x[0]);
				} else {
					return new SimpleStringProperty("<no name>");
				}
			}
		});
		id.setText(idioma.getMensajeTexto("Etiqueta_Columna_Id"));
		
		nombre.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
						String[] x = p.getValue();
						if (x != null && x.length > 1) {
							return new SimpleStringProperty(x[1]);
						} else {
							return new SimpleStringProperty("<no value>");
						}
					}
				});
		nombre.setText(idioma.getMensajeTexto("Etiqueta_Columna_Nombre"));
		
		nota1.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
						String[] x = p.getValue();
						if (x != null && x.length > 0) {
							return new SimpleStringProperty(x[2]);
						} else {
							return new SimpleStringProperty("<no name>");
						}
					}
				});
		nota1.setText(idioma.getMensajeTexto("Etiqueta_Columna_Paradigma"));

		nota2.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
						String[] x = p.getValue();
						if (x != null && x.length > 1) {
							return new SimpleStringProperty(x[3]);
						} else {
							return new SimpleStringProperty("<no value>");
						}
					}
				});
		nota2.setText(idioma.getMensajeTexto("Etiqueta_Columna_Introduccion"));
		
		nota3.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
						String[] x = p.getValue();
						if (x != null && x.length > 0) {
							return new SimpleStringProperty(x[4]);
						} else {
							return new SimpleStringProperty("<no name>");
						}
					}
				});
		nota3.setText(idioma.getMensajeTexto("Etiqueta_Columna_Geometria"));

		// add your data to the table here.

		// Add Data
		tv.getItems().addAll(Arrays.asList(data));
		
		enunciadoLabel.setText(idioma.getMensajeTexto("Etiqueta_Label_Enunciado"));
		idLabel.setText(idioma.getMensajeTexto("Etiqueta_Label_Id"));
		nombreLabel.setText(idioma.getMensajeTexto("Etiqueta_Label_Nombre"));
		nota1Label.setText(idioma.getMensajeTexto("Etiqueta_Label_Paradigma"));
		nota2Label.setText(idioma.getMensajeTexto("Etiqueta_Label_Introduccion"));
		nota3Label.setText(idioma.getMensajeTexto("Etiqueta_Label_Geometria"));
		
		guardarButton.setText(idioma.getMensajeTexto("Etiqueta_Boton_Guardar"));
		mayorButton.setText(idioma.getMensajeTexto("Etiqueta_Boton_Mayor"));
		promedioButton.setText(idioma.getMensajeTexto("Etiqueta_Boton_Promedio"));
	}

	/**
	 * Metodo modificador
	 * 
	 * @param dialogo
	 */

	public Principal getMiVentanaPrincipal() {
		return miVentanaPrincipal;
	}

	public void setMiVentanaPrincipal(Principal miVentanaPrincipal) {
		this.miVentanaPrincipal = miVentanaPrincipal;
	}
	
	/**
	 * Metodo para modificar el estudiante
	 */
	@FXML
	public void crearRegistro() {
		try {
			String id = idTextField.getText();
			String nombre = nombreTextField.getText();
			double nota0 = Double.parseDouble(nota0TextField.getText());
			double nota1 = Double.parseDouble(nota1TextField.getText());
			double nota2 = Double.parseDouble(nota2TextField.getText());
			validarNota(nota0);
			validarNota(nota1);
			validarNota(nota2);
			Estudiante miE = new Estudiante(id, nombre);
			miVentanaPrincipal.agregarEstudiante(miE);
			ArrayList<Double> notas = new ArrayList<Double>();
			notas.add(nota0);
			notas.add(nota1);
			notas.add(nota2);

			miVentanaPrincipal.crearRegistroPlanilla(id, notas);
			actualizarTabla ();
			limpiarCampos();
			miVentanaPrincipal.guardarArchivoXML();
		} catch (EstudianteException | NotaIncorrectaException e) {
			// TODO Auto-generated catch block
			miVentanaPrincipal.mostrarMensaje("Error: ", AlertType.ERROR, "", "", e.getMessage(),
					miVentanaPrincipal.getPrimaryStage());

		} catch (Exception e) {
			miVentanaPrincipal.mostrarMensaje("Error: ", AlertType.ERROR, "", "", e.getMessage(),
					miVentanaPrincipal.getPrimaryStage());
		}
	}

	public void limpiarCampos () {
		idTextField.setText("");
		nombreTextField.setText("");
		nota0TextField.setText("");
		nota1TextField.setText("");
		nota2TextField.setText("");
	}
	
	/**
	 * Permite validar los valores ingresados en la nota
	 * 
	 * @param nota0
	 * @throws NotaIncorrectaException si se sale de 0-5
	 */
	public void validarNota(double nota0) throws NotaIncorrectaException {
		if (!(nota0 >= 0 && nota0 <= 5)) {
			throw new NotaIncorrectaException(idioma.getMensajeTexto("Etiqueta_Mensaje_Error_Nota"));
		}

	}

	/**
	 * Obtiene la asignatura de mayor nota
	 */
	public void obtenerAsignaturaMayorNota() {
		String id = miVentanaPrincipal.leerMensaje(idioma.getMensajeTexto("Etiqueta_Peticion_Id"));
		Asignatura miA;
		try {
			miA = miVentanaPrincipal.obtenerAsignaturaMayorNota(id);
			String asignatura = idioma.getMensajeTexto("Etiqueta_Nombre_Curso_" + miA.getNombre());
			Principal.mostrarMensaje(" ", AlertType.INFORMATION, "", idioma.getMensajeTexto("Etiqueta_Mensaje_Mejor_Nota"),
					asignatura, miVentanaPrincipal.getPrimaryStage());

		} catch (EstudianteException e) {
			// TODO Auto-generated catch block
			String error = idioma.getMensajeTexto("Etiqueta_Error_Registro");
			Principal.mostrarMensaje(" ", AlertType.ERROR, "", "", "" + error,
					miVentanaPrincipal.getPrimaryStage());
		}

	}

	/**
	 * Calcula el promedio general
	 */
	public void calcularPromGeneral() {
		double prom;
		try {
			prom = miVentanaPrincipal.calcularPromGeneral();
			Principal.mostrarMensaje(" ", AlertType.INFORMATION, "", "",
					idioma.getMensajeTexto("Etiqueta_Mensaje_Promedio") + prom,
					miVentanaPrincipal.getPrimaryStage());

		} catch (DivisorException e) {
			// TODO Auto-generated catch block
			String error = idioma.getMensajeTexto("Etiqueta_Error_Divisor");
			Principal.mostrarMensaje(" ", AlertType.ERROR, "", "", "" + error,
					miVentanaPrincipal.getPrimaryStage());

		}
	}
	
	public void actualizarTabla () {
		tv.getItems().clear();
		String data[][] = miVentanaPrincipal.listariInfoPlanilla();
		this.data = data;
		tv.getItems().addAll(Arrays.asList(data));
	}
	
	public void cerrarEjecucion () {
		if (this.idTextField.getText().equals("")==false &&
			this.nombreTextField.getText().equals("")==false &&
			this.nota0TextField.getText().equals("")==false &&
			this.nota1TextField.getText().equals("")==false &&
			this.nota2TextField.getText().equals("")==false) {
			crearRegistro();
		}
	}
}