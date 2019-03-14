package database.models;

public class Modalidades {
	
	/* Attributes: */
	private String modalidade;
	
	/* Constructor: */
	public Modalidades() {}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%s", this.modalidade);
	}
}
