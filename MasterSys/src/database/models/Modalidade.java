package database.models;

public class Modalidade {
	
	/* Attributes: */
	private String modalidade;
	
	/* Constructor: */
	public Modalidade(String modalidade) {
		this.modalidade = modalidade;
	}

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
