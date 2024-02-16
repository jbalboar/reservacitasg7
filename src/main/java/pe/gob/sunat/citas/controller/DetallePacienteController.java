package pe.gob.sunat.citas.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pe.gob.sunat.citas.bean.CitasReservadasViewBean;
import pe.gob.sunat.citas.bean.PacienteViewBean;
import pe.gob.sunat.citas.dao.ReservaDao;
import pe.gob.sunat.citas.dao.impl.ReservaDaoImpl;

public class DetallePacienteController {
	
	@FXML
	Label lblDni;

	@FXML
	Label lblNombres;
	
	@FXML
	Label lblApellidos;
	
	@FXML
	Label lblCorreo;
	
	@FXML
    private TableView<CitasReservadasViewBean> tblCitas;

    @FXML
    private TableColumn<CitasReservadasViewBean, String> colFecha;

    @FXML
    private TableColumn<CitasReservadasViewBean, String> colHora;

    @FXML
    private TableColumn<CitasReservadasViewBean, String> colMedico;
    
    @FXML
    private TableColumn<CitasReservadasViewBean, String> colEspecialidad;
    
    @FXML
    private TableColumn<CitasReservadasViewBean, String> colEstado;
    
    private final ReservaDao reservaDao = new ReservaDaoImpl();

	public void initialize(PacienteViewBean paciente) {
		lblDni.setText(paciente.getDni());	
		lblNombres.setText(paciente.getNombres());
		lblApellidos.setText(paciente.getPrimerApellido().concat(" ").concat(paciente.getSegundoApellido()));
		lblCorreo.setText(paciente.getEmail());
		
		ObservableList<CitasReservadasViewBean> lstCitasReservadas  = reservaDao.listarCitas(paciente.getDni());
		
		llenarTablaCitas(lstCitasReservadas);	
		
	}

	private void llenarTablaCitas(ObservableList<CitasReservadasViewBean> lstCitasReservadas) {
		colFecha.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
		colHora.setCellValueFactory(cellData -> cellData.getValue().horaProperty());
		colMedico.setCellValueFactory(cellData -> cellData.getValue().medicoProperty());
		colEspecialidad.setCellValueFactory(cellData -> cellData.getValue().especialidadProperty());
		colEstado.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());		
		tblCitas.setItems(lstCitasReservadas);		
	}

}
