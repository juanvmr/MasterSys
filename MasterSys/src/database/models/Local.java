package database.models;

public class Local {
	
	/* Attributes: */
	private String cidade;
	private String estado;
	private String pais;
	
	/* Constructor */
	public Local(String cidade, String estado, String pais) {
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}
	
	/* Getter and Setter: */
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	/* methods: */
	public String toString() {
		return String.format("%s - %s, %s", this.cidade, this.estado, this.pais);
	}
	
}
