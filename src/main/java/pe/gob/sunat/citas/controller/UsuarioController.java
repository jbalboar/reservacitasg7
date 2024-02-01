package pe.gob.sunat.citas.controller;

import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import pe.gob.sunat.citas.bean.UsuarioBean;
import pe.gob.sunat.citas.service.UsuariosService;
import pe.gob.sunat.citas.utils.CitasUtils;

public class UsuarioController {
	@FXML
	private TextField txtUser;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private ImageView imgLogin;

	private final UsuariosService usuarioService = new UsuariosService();

	@FXML
	public void initialize() {
		InputStream is = getClass().getResourceAsStream("/pe/gob/sunat/citas/png/logo.png");
		Image image = new Image(is);
		imgLogin = new ImageView(image);
	}

	@FXML
	private void validarIngreso(ActionEvent event) throws IOException {
		UsuarioBean result = usuarioService.autenticarUsuario(txtUser.getText(), txtPassword.getText());

		if (result != null) {
			mostrarDashboard(event);
		} else {
			showAlert();
		}
	}

	private void mostrarDashboard(ActionEvent event) throws IOException {
        // Crear una nueva escena
        Scene scene = new Scene(CitasUtils.loadFXML("fxml/dashboard/dashboard"));

        // Obtener la ventana principal
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena en la ventana principal
        stage.setScene(scene);
        stage.show();
		
	}

	private void showAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Información");
		alert.setContentText("Credenciales inválidas");
		alert.showAndWait();
	}
}
