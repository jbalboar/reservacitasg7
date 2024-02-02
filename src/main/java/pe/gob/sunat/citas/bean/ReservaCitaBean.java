package pe.gob.sunat.citas.bean;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import lombok.Data;

public @Data class ReservaCitaBean {
	@BsonId
	private ObjectId id;
	private String dni;
	private List<CitasBean> cita;
}
