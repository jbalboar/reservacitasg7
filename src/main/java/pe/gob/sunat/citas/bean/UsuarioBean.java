package pe.gob.sunat.citas.bean;

import lombok.Data;

public @Data class UsuarioBean {
	private String dni;
	private String nombres;
	private String primerApellido;
	private String segundoApellido;
	private String password;
	private boolean activo;
}
