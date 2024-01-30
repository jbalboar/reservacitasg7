package pe.gob.sunat.citas.bean;

import lombok.Data;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public @Data class UsuarioBean {
	@BsonId
	private ObjectId id;
	private String dni;
	private String nombres;
	private String primerApellido;
	private String segundoApellido;
	private String password;
	private boolean activo;
}
