package pe.gob.sunat.citas.bean;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import lombok.Data;

public @Data class MedicoBean {
	@BsonId
	private ObjectId id;
	private String codigo;
	private String dni;
	private String nombres;
	private String primerApellido;
	private String segundoApellido;
	private List<CatalogoBean> especialidad;				  
}
