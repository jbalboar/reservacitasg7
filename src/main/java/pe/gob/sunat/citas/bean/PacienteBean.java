package pe.gob.sunat.citas.bean;

import java.util.Date;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import lombok.Data;

public @Data class PacienteBean {
	@BsonId
	private ObjectId id;
	private String dni;
	private String nombres;
	private String primerApellido;
	private String segundoApellido;
	private Date fechaNacimiento;
	private int edad;
	private String email;
	private boolean tieneSeguro;
}
