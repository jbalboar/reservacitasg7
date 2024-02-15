package pe.gob.sunat.citas.dao;

import java.time.LocalDate;
import java.util.List;

import pe.gob.sunat.citas.bean.EstadisticaBean;
import pe.gob.sunat.citas.bean.MedicoBean;

public interface HorarioDao {
	List<String> obtenerHorarioDisponible(LocalDate localDate, MedicoBean bean);
	
	List<EstadisticaBean> obtenerEstadisticas();
}
