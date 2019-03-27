package database.models;

public class Modalidade {
	
	/* attributes: */
	private String modalidade;
	
	/* constructor: */
	public Modalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	/* getter and setter: */
	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	
	/* methods: */
	public String toString() {
		return String.format("%s", this.modalidade);
	}
}
