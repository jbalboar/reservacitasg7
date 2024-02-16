package pe.gob.sunat.citas.controller;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import pe.gob.sunat.citas.App;
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
	
	private void openDialog(PacienteViewBean paciente) {
        try {
            // Cargar el FXML que define el Pane
        	FXMLLoader loader = new FXMLLoader(App.class.getResource("fxml/dashboard/detallePaciente.fxml"));
            Pane personDetailsPane = loader.load();

            // Obtener el controlador del FXML
            DetallePacienteController controller = loader.getController();

            // Inicializar el controlador con los datos de la persona
            controller.initialize(paciente);

            // Crear un diálogo con el Pane personalizado
            Dialog<Void> dialog = new Dialog<>();
            dialog.getDialogPane().setContent(personDetailsPane);

            // Configurar el botón de cierre del diálogo
            ButtonType closeButton = new ButtonType("Cerrar", ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().add(closeButton);
            //dialog.getDialogPane().getButtonTypes().add(DialogPane.ButtonType.CANCEL);

            // Mostrar el diálogo de manera modal
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
		
		tblPacientes.setRowFactory(tv -> {
            TableRow<PacienteViewBean> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && !row.isEmpty()) {
                	PacienteViewBean selectedPaciente = row.getItem();
                    openDialog(selectedPaciente);
                }
            });
            return row;
        });
		
	}
}
