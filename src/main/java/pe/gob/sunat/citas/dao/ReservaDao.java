package pe.gob.sunat.citas.dao;

import javafx.collections.ObservableList;
import pe.gob.sunat.citas.bean.CitasReservadasViewBean;
import pe.gob.sunat.citas.bean.ReservaCitaBean;

public interface ReservaDao {

	ReservaCitaBean grabarCita(ReservaCitaBean reserva);

	ObservableList<CitasReservadasViewBean> listarCitas(String dni);
}
