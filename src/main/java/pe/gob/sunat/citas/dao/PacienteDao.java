package pe.gob.sunat.citas.dao;

import java.util.List;

import javafx.collections.ObservableList;
import pe.gob.sunat.citas.bean.PacienteBean;
import pe.gob.sunat.citas.bean.PacienteViewBean;

public interface PacienteDao {
	PacienteBean obtenerDatosPaciente(String dni);

	PacienteBean registrarPaciente(PacienteBean bean);

	ObservableList<PacienteViewBean> buscarPacientes(PacienteBean bean);
}
