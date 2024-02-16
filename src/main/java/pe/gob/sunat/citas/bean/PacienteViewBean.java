package pe.gob.sunat.citas.bean;

import javafx.beans.property.SimpleStringProperty;

public class PacienteViewBean {
	private SimpleStringProperty dni;
	private SimpleStringProperty nombres;
	private SimpleStringProperty primerApellido;
	private SimpleStringProperty segundoApellido;
	private SimpleStringProperty fechaNacimiento;
	private SimpleStringProperty email;

	public PacienteViewBean(String dni, String nombres, String primerApellido, String segundoApellido,
			String fechaNacimiento, String email) {
		this.dni = new SimpleStringProperty(dni);
		this.nombres = new SimpleStringProperty(nombres);
		this.primerApellido = new SimpleStringProperty(primerApellido);
		this.segundoApellido = new SimpleStringProperty(segundoApellido);
		this.fechaNacimiento = new SimpleStringProperty(fechaNacimiento);
		this.email = new SimpleStringProperty(email);
	}

	public String getDni() {
		return dni.get();
	}

	public SimpleStringProperty dniProperty() {
		return dni;
	}

	public SimpleStringProperty nombresProperty() {
		return nombres;
	}

	public String getNombres() {
		return nombres.get();
	}

	public SimpleStringProperty primerApellidoProperty() {
		return primerApellido;
	}

	public String getPrimerApellido() {
		return primerApellido.get();
	}

	public SimpleStringProperty segundoApellidoProperty() {
		return segundoApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido.get();
	}

	public SimpleStringProperty fechaNacimientoProperty() {
		return fechaNacimiento;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento.get();
	}
	
	public String getEmail() {
		return email.get();
	}

	public SimpleStringProperty emailProperty() {
		return email;
	}
}
