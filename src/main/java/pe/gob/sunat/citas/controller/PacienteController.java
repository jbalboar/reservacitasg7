package pe.gob.sunat.citas.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import pe.gob.sunat.citas.bean.PacienteBean;
import pe.gob.sunat.citas.dao.impl.PacienteDaoImpl;
import pe.gob.sunat.citas.utils.CitasUtils;

public class PacienteController {
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
	
	private final PacienteDaoImpl pacienteService = new PacienteDaoImpl();
	
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
