package database.models;

public class Local {
	
	/* attributes: */
	private String cidade;
	private String estado;
	private String pais;
	
	/* constructor */
	public Local() {}
	
	public Local(String cidade, String estado, String pais) {
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}
	
	/* getter and setter: */
	public String getCidade() { return this.cidade; }

	public void setCidade(String cidade) { this.cidade = cidade; }

	public String getEstado() { return this.estado; }

	public void setEstado(String estado) { this.estado = estado; }

	public String getPais() { return this.pais; }

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	/* methods: */
	public String toString() {
		return String.format("%s - %s, %s", this.cidade, this.estado, this.pais);
	}
	
}
