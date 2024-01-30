package pe.gob.sunat.citas.controller;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import pe.gob.sunat.citas.bean.UsuarioBean;
import pe.gob.sunat.citas.service.UsuariosService;

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
    private void validarIngreso() throws IOException {
        System.out.println("pruebaaa");
        
        UsuarioBean result = usuarioService.autenticarUsuario(txtUser.getText(), txtPassword.getText());
        showAlert(result);
    }
    
    private void showAlert(UsuarioBean result) {
    	Alert alert;
    	if(result!=null && result.getNombres()!=null) {
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setContentText("Bienvenido: " + result.getNombres());
    	}else {
    		alert = new Alert(AlertType.ERROR);
    	}


        // Mostrar y esperar a que el usuario cierre la alerta
        alert.showAndWait();
    }
}
