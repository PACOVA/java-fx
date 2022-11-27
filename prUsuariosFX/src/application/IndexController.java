package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;

public class IndexController {
	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtEdad;
	@FXML
	private ChoiceBox <String> cbNacionalidad;
	@FXML
	private Button btnAnadir;
	@FXML
	private Button btnBorrar;
	/*	Tenemos que indicar que la TableView va a estar llena de objetos de la clase Usuario
	 *  Esto lo hacemos a�adiendo <Usuario> detr�s de TableView. Esto se conoce como 
	 *  "parametrizaci�n"
	 */
	@FXML
	private TableView <Usuario> tablaUsuarios;
	@FXML
	/*
	 * Para cada columa tenemos que indicar asimismo que tendremos un campo de la clase Usuario
	 * que adem�s ser� de un tipo determinado
	 */
	private TableColumn <Usuario, String> nombre;
	@FXML
	private TableColumn <Usuario, String> edad;
	@FXML
	private TableColumn <Usuario, String> nacionalidad;
	
	/*
	 * Para rellenar los datos del choice box tenemos que crear primero un ArrayList,
	 * y luego pasarle ese ArrayList a una ObservableList, que es un objeto que puede
	 * ser interpretado en la interfaz gr�fica por JavaFX
	 */
	/*ArrayList<String> nacionalidades = new ArrayList<>(
	    	Arrays.asList("Espa�ola", "Francesa", "Alemana", "Brit�nica"));
	ObservableList<String> lista_nacionalidades = 
			FXCollections.observableArrayList(nacionalidades);
	*/
	private ObservableList<String> lista_nacionalidades = FXCollections.observableArrayList(
            "Española",
            "Francesa",
            "Alemana",
            "Británica"
	);
	// Event Listener on Button[#btnAnadir].onAction
	@FXML
	public void anadirUsuario(ActionEvent event) {
		// TODO Autogenerated
		
		Usuario u = new Usuario(
				txtNombre.getText(), 
				Integer.parseInt(txtEdad.getText()), 
				cbNacionalidad.getValue().toString());
		
		usuarios.add(u);
		
		txtNombre.clear();
		txtEdad.clear();
		cbNacionalidad.getSelectionModel().clearSelection();
	}
	
	/**
	 * Elimina el usuario seleccionado
	 */
	@FXML
	private void borrarUsuario() {
		
	    int selectedIndex = tablaUsuarios.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	//	Si se ha seleccionado un elemento de la tabla, entramos aquí
        	tablaUsuarios.getItems().remove(selectedIndex);
        } else {
            //	Si no se ha seleccionado nada, entramos aquí
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
	}
	
	/*
	 * En el método initialice realizamos toda la lógica que queremos que se ejecute en el 
	 * momento de lanzar la aplicacion. En nuestro caso, queremos que la tabla esta ya llena
	 * con todos los datos que teniamos predefinidos y adem�s queremos llenar el choiceBox que
	 * hemos creado con las opciones que hemos definido en la ObservableList que hemos creado 
	 * antes
	 * 
	 * El m�todo setCellValueFactory nos permite indicar qu� campo del objeto Usuario va a ir en
	 * cada columna de la tabla, mientras que con setItems a�adimos los objetos de la clase usuario
	 * que hemos creado. Estos objetos estan creados mas abajo en el codigo
	 */
	@FXML
    private void initialize() {
    	// Initialize the choice boxes
		cbNacionalidad.setItems(lista_nacionalidades);
		
		nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        edad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        nacionalidad.setCellValueFactory(new PropertyValueFactory<>("nacionalidad"));
        tablaUsuarios.setItems(usuarios);
    }
	
	/*
	 * Aqu� creamos la lista de usuarios inicial, de nuevo mediante una ObservableList que puede
	 * ser interpretada en la interfaz de usuario por JavaFX
	 */
	private ObservableList<Usuario> usuarios = FXCollections.observableArrayList(
            new Usuario("Eren Yaeger", 19, "Alemana"),
            new Usuario("Mikasa Ackerman", 18, "Española")
	);
	
}
