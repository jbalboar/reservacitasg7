package pe.gob.sunat.citas.bean;

import java.util.Date;

import lombok.Data;

public @Data class CitasBean {
	private CatalogoBean medico;
	private CatalogoBean especialidad;
	private Date fecha;
	private String hora;
	private String estado;
}
