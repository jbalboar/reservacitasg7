package pe.gob.sunat.citas.dao;

import java.util.List;

import pe.gob.sunat.citas.bean.MedicoBean;

public interface MedicoDao {
	List<MedicoBean> getMedicosPorEspecialidad(String codigo);
}
