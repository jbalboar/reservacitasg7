package pe.gob.sunat.citas.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pe.gob.sunat.citas.bean.PacienteBean;
import pe.gob.sunat.citas.bean.UsuarioBean;
import pe.gob.sunat.citas.service.PacienteService;
import pe.gob.sunat.citas.service.UsuariosService;
import pe.gob.sunat.citas.utils.CitasUtils;

public class DashboardController {
	
	private final PacienteService pacienteService = new PacienteService();
	
	@FXML
	private TextField txtDni;
	
	@FXML
	private TextField txtNombres;
	
	@FXML
	private TextField txtPrimerApellido;
	
	@FXML
	private TextField txtSegundoApellido;
	
	@FXML
	private DatePicker txtFecNacimiento;
	
	@FXML
	private TextField txtEmail;


	@FXML
	private void initRegistrarPacientes() {
		//OCULTAR PANELES QUE NO LE PERTENECEN
	}
	
	@FXML
	private void initRegistrarCitas() {
		//OCULTAR PANELES QUE NO LE PERTENECEN
	}
	
	@FXML
	private void grabarDatosPaciente(ActionEvent event) {
		PacienteBean pBean = new PacienteBean();
		pBean.setDni(txtDni.getText());
		pBean.setNombres(txtNombres.getText());
		pBean.setPrimerApellido(txtPrimerApellido.getText());
		pBean.setSegundoApellido(txtSegundoApellido.getText());
		pBean.setFechaNacimiento(CitasUtils.formatDate(txtFecNacimiento.getValue()));
		pBean.setEmail(txtEmail.getText());
		pBean.setTieneSeguro(true);
		pBean = pacienteService.registrarPaciente(pBean);
		
		if(pBean.getId()!=null) {
			showAlert(true);
		}else {
			
		}
		
	}
	
	private void showAlert(boolean exitoso) {
		if(exitoso) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Información");
			alert.setContentText("Los datos se grabaron correctamente");
			alert.showAndWait();
		}
		
	}
	
}
