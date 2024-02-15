package pe.gob.sunat.citas.bean;

public class EstadisticaBean {

	private String medico;
	private int citas;
	
	public EstadisticaBean(String medico, int citas) {
		this.medico = medico;
		this.citas = citas;
	}
	
	public EstadisticaBean() {

	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public int getCitas() {
		return citas;
	}

	public void setCitas(int citas) {
		this.citas = citas;
	}

}
