package pe.gob.sunat.citas.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pe.gob.sunat.citas.utils.CitasUtils;

public class MenuController {
	
	@FXML
	private Label lblUsuario;
	
	private static String usuario;
	
	public static String getUsuario() {
		return MenuController.usuario;
	}
	
	public static void setUsuario(String usuario) {
		MenuController.usuario = usuario;
	}
	
	@FXML
	public void initialize() {
		lblUsuario.setText(usuario);
	}

	@FXML
	private void initRegistrarPacientes(ActionEvent event) throws Exception {
		mostrarPanel(event, "pacientes");
	}
	
	@FXML
	private void initRegistrarCitas(ActionEvent event) throws Exception{
		mostrarPanel(event, "citas");
	}
	
	private void mostrarPanel(ActionEvent event, String fxml) throws IOException {
        // Crear una nueva escena
        Scene scene = new Scene(CitasUtils.loadFXML("fxml/dashboard/" + fxml));

        // Obtener la ventana principal
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena en la ventana principal
        stage.setScene(scene);
        stage.show();
		
	}
}
