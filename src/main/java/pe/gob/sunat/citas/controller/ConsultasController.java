package pe.gob.sunat.citas.controller;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pe.gob.sunat.citas.bean.PacienteBean;
import pe.gob.sunat.citas.bean.PacienteViewBean;
import pe.gob.sunat.citas.dao.PacienteDao;
import pe.gob.sunat.citas.dao.impl.PacienteDaoImpl;

public class ConsultasController {
	
	@FXML
	private TextField txtDni;
	
	@FXML
	private TextField txtNombres;
	
	@FXML
	private TextField txtPrimerApellido;
	
	@FXML
	private TextField txtSegundoApellido;
	
	@FXML
    private TableView<PacienteViewBean> tblPacientes;

    @FXML
    private TableColumn<PacienteViewBean, String> columnDni;

    @FXML
    private TableColumn<PacienteViewBean, String> columnNombre;

    @FXML
    private TableColumn<PacienteViewBean, String> columnPrimerApellido;
    
    @FXML
    private TableColumn<PacienteViewBean, String> columnSegundoApellido;
    
    @FXML
    private TableColumn<PacienteViewBean, String> columnFechaNacimiento;
    
	
	private final PacienteDao pacienteDao = new PacienteDaoImpl();
	
	public void initialize() {
		
    }
	
	@FXML
	private void buscarPaciente(ActionEvent event) throws Exception{
		PacienteBean bean = new PacienteBean();
		bean.setDni(txtDni.getText());
		bean.setNombres(txtNombres.getText());
		bean.setPrimerApellido(txtPrimerApellido.getText());
		bean.setSegundoApellido(txtSegundoApellido.getText());
		
		ObservableList<PacienteViewBean> lstPacientes  = pacienteDao.buscarPacientes(bean);
		
		llenarTablaPacientes(lstPacientes);	
	}

	private void llenarTablaPacientes(ObservableList<PacienteViewBean> lstPacientes) {
		columnDni.setCellValueFactory(cellData -> cellData.getValue().dniProperty());
		columnNombre.setCellValueFactory(cellData -> cellData.getValue().nombresProperty());
		columnPrimerApellido.setCellValueFactory(cellData -> cellData.getValue().primerApellidoProperty());
		columnSegundoApellido.setCellValueFactory(cellData -> cellData.getValue().segundoApellidoProperty());
		columnFechaNacimiento.setCellValueFactory(cellData -> cellData.getValue().fechaNacimientoProperty());		
		tblPacientes.setItems(lstPacientes);
		
	}
}
