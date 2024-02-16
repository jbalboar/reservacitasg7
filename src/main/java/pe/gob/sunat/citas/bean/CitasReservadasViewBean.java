package pe.gob.sunat.citas.bean;

import javafx.beans.property.SimpleStringProperty;

public class CitasReservadasViewBean {
	private SimpleStringProperty fecha;
	private SimpleStringProperty hora;
	private SimpleStringProperty medico;
	private SimpleStringProperty especialidad;
	private SimpleStringProperty estado;
	
	public CitasReservadasViewBean(String fecha, String hora, String medico, String especialidad,
			String estado) {
		this.fecha = new SimpleStringProperty(fecha);
		this.hora = new SimpleStringProperty(hora);
		this.medico = new SimpleStringProperty(medico);
		this.especialidad = new SimpleStringProperty(especialidad);
		this.estado = new SimpleStringProperty(estado);
	}
	
	public String getFecha() {
		return fecha.get();
	}

	public SimpleStringProperty fechaProperty() {
		return fecha;
	}

	public SimpleStringProperty horaProperty() {
		return hora;
	}

	public String getHora() {
		return hora.get();
	}

	public SimpleStringProperty medicoProperty() {
		return medico;
	}

	public String getMedico() {
		return medico.get();
	}

	public SimpleStringProperty especialidadProperty() {
		return especialidad;
	}

	public String getEspecialidad() {
		return especialidad.get();
	}

	public SimpleStringProperty estadoProperty() {
		return estado;
	}

	public String getEstado() {
		return estado.get();
	}
	
}
