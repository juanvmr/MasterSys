package database.models;

public class Assiduidade {
	
	/* Attributes: */
	private int cod_matricula;
	private String dt_entrada;
	
	/* Constructor: */
	public Assiduidade(int cod_matricula, String dt_entrada) {
		this.cod_matricula = cod_matricula;
		this.dt_entrada = dt_entrada;
	}

	/* Getter and Setter: */
	public int getCod_matricula() {
		return cod_matricula;
	}

	public void setCod_matricula(int cod_matricula) {
		this.cod_matricula = cod_matricula;
	}

	public String getDt_entrada() {
		return dt_entrada;
	}

	public void setDt_entrada(String dt_entrada) {
		this.dt_entrada = dt_entrada;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%s %s", this.cod_matricula, this.dt_entrada);
	}
}