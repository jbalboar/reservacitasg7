package pe.gob.sunat.citas.dao;

import pe.gob.sunat.citas.bean.PacienteBean;

public interface PacienteDao {
	PacienteBean obtenerDatosPaciente(String dni);
	PacienteBean registrarPaciente(PacienteBean bean);
}
