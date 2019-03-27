package database.models;

public class Graduacao {
	
	/* Attributes: */
	private Modalidade modalidade;
	private String graduacao;
	
	/* Constructor: */
	public Graduacao(String modalidade, String graduacao) {
		this.modalidade = new Modalidade(modalidade);
		this.graduacao = graduacao;
	}
	
	public Graduacao(String graduacao, Modalidade modalidade) {
		this.modalidade = modalidade;
		this.graduacao = graduacao;
	}
	
	/* Getter and Setter: */
	public Modalidade getModalidade() {
		return this.modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public String getGraduacao() {
		return graduacao;
	}

	public void setGraduacao(String graduacao) {
		this.graduacao = graduacao;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%s, %s", this.modalidade.toString(), this.graduacao);
	}
}
