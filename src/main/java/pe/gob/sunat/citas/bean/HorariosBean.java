package pe.gob.sunat.citas.bean;

import java.util.Date;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import lombok.Data;

public @Data class HorariosBean {
	@BsonId
	private ObjectId id;
	private String codigoMedico;
	private Date fecha;
	private List<String> horarios;	
}