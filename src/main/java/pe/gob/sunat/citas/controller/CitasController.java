package pe.gob.sunat.citas.controller;

import java.time.LocalDate;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.StringConverter;
import pe.gob.sunat.citas.bean.CatalogoBean;
import pe.gob.sunat.citas.bean.CitasBean;
import pe.gob.sunat.citas.bean.MedicoBean;
import pe.gob.sunat.citas.bean.PacienteBean;
import pe.gob.sunat.citas.bean.ReservaCitaBean;
import pe.gob.sunat.citas.dao.impl.EspecialidadDaoImpl;
import pe.gob.sunat.citas.dao.impl.HorarioDaoImpl;
import pe.gob.sunat.citas.dao.impl.MedicoDaoImpl;
import pe.gob.sunat.citas.dao.impl.PacienteDaoImpl;
import pe.gob.sunat.citas.dao.impl.ReservaDaoImpl;
import pe.gob.sunat.citas.utils.CitasUtils;

public class CitasController {

	@FXML
	private TextField txtDniBusquedaRegistro;

	@FXML
	private TextField txtNombrePaciente;

	@FXML
	private ComboBox<CatalogoBean> cmbEspecialidad;

	@FXML
	private ComboBox<MedicoBean> cmbMedico;

	@FXML
	private ComboBox<String> cmbHorario;

	@FXML
	private DatePicker txtFechaReserva;

	private final PacienteDaoImpl pacienteService = new PacienteDaoImpl();

	private final EspecialidadDaoImpl especialidadService = new EspecialidadDaoImpl();

	private final MedicoDaoImpl medicoService = new MedicoDaoImpl();

	private final HorarioDaoImpl horarioService = new HorarioDaoImpl();

	private final ReservaDaoImpl reservaService = new ReservaDaoImpl();

	@FXML
	public void initialize() {
		ObservableList<CatalogoBean> lstEspecialidades = FXCollections
				.observableArrayList(especialidadService.getEspecialidades());
		cmbEspecialidad.setItems(lstEspecialidades);

		cmbEspecialidad.setConverter(new StringConverter<CatalogoBean>() {
			@Override
			public String toString(CatalogoBean object) {
				return object == null ? null : object.getDescripcion();
			}

			@Override
			public CatalogoBean fromString(String string) {
				return null;
			}
		});

		txtFechaReserva.setDayCellFactory(getDayCellFactory());
	}

	@FXML
	private void grabarCita(ActionEvent event) {
		ReservaCitaBean reserva = new ReservaCitaBean();
		reserva.setDni(txtDniBusquedaRegistro.getText());

		CitasBean cita = new CitasBean();
		String nombreMedico = cmbMedico.getValue().getNombres() + " " + cmbMedico.getValue().getPrimerApellido() + " "
				+ cmbMedico.getValue().getSegundoApellido();

		CatalogoBean cMedicoBean = new CatalogoBean();
		cMedicoBean.setCodigo(cmbMedico.getValue().getCodigo());
		cMedicoBean.setDescripcion(nombreMedico);

		cita.setMedico(cMedicoBean);
		cita.setEspecialidad(cmbEspecialidad.getValue());
		cita.setFecha(CitasUtils.formatDate(txtFechaReserva.getValue()));
		cita.setHora(cmbHorario.getValue());
		cita.setEstado("RESERVADO");

		reserva.setCita(Arrays.asList(cita));

		reservaService.grabarCita(reserva);
	}

	@FXML
	private void buscarPacienteRegistro(ActionEvent event) {
		PacienteBean pBean = pacienteService.obtenerDatosPaciente(txtDniBusquedaRegistro.getText());

		if (pBean != null) {
			txtNombrePaciente
					.setText(pBean.getNombres() + " " + pBean.getPrimerApellido() + " " + pBean.getSegundoApellido());
		}
	}

	@FXML
	private void handleEspecialidadChange() {
		CatalogoBean especialidadBean = cmbEspecialidad.getValue();
		ObservableList<MedicoBean> lstMedicos = FXCollections
				.observableArrayList(medicoService.getMedicosPorEspecialidad(especialidadBean.getCodigo()));
		cmbMedico.setItems(lstMedicos);

		cmbMedico.setConverter(new StringConverter<MedicoBean>() {
			@Override
			public String toString(MedicoBean object) {
				return object == null ? null
						: (object.getNombres() + " " + object.getPrimerApellido() + " " + object.getSegundoApellido());
			}

			@Override
			public MedicoBean fromString(String string) {
				return null;
			}
		});
	}

	@FXML
	private void handleDateSelection() {
		LocalDate selectedDate = txtFechaReserva.getValue();
		ObservableList<String> lstHorarioDisponible = FXCollections
				.observableArrayList(horarioService.obtenerHorarioDisponible(selectedDate, cmbMedico.getValue()));
		cmbHorario.setItems(lstHorarioDisponible);
	}

	private Callback<DatePicker, DateCell> getDayCellFactory() {
		return datePicker -> new DateCell() {
			@Override
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);

				// Deshabilitar días anteriores a la fecha actual
				if (item != null && item.isBefore(LocalDate.now())) {
					setDisable(true);
					setStyle("-fx-background-color: #eeeeee;"); // Puedes personalizar el estilo según tus necesidades
				}
			}
		};
	}
}
