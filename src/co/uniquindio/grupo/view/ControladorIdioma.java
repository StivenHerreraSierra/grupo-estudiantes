/**
 * Controlador de la vista idioma.
 */
package co.uniquindio.grupo.view;

import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.Locale;
import java.util.ResourceBundle;
import co.uniquindio.grupo.Principal;

/**
 * @author Stiven Herrera Sierra.
 */
public class ControladorIdioma {
	@FXML
	private Button espanolButton;
	@FXML
	private Button inglesButton;
	private static ResourceBundle mensaje;
	
	private Principal ventanaPrincipal;
	
	public void modificarPrincipal (Principal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
		cargarComponentes();
	}
	
	public Principal obtenerPrincipal () {
		return this.ventanaPrincipal;
	}
	
	private EventHandler <ActionEvent> manejadorEventos =
			new EventHandler <ActionEvent> () {
		@Override
		public void handle (ActionEvent evento) {
			Button botonAux = (Button) evento.getSource();
			Locale localizacion;
			String lenguaje;
			String pais;
			
			if (botonAux.getId().equals(espanolButton.getId()) == true) {
				lenguaje = "es";
				pais = "ES";
			}
			else {
				lenguaje = "en";
				pais = "US";
			}
			localizacion = new Locale (lenguaje, pais);
			mensaje =
					ResourceBundle.getBundle("resources.Etiquetas", localizacion);
			ventanaPrincipal.mostrarVistaEstudiante();
		}
	};
	
	public void cargarComponentes () {
		espanolButton.setId("espanolButton");
		espanolButton.setOnAction(manejadorEventos);
		inglesButton.setId("inglesButton");
		inglesButton.setOnAction(manejadorEventos);
	}
	
	public String getMensajeTexto (String texto) {
		return mensaje.getString(texto);
	}
}
