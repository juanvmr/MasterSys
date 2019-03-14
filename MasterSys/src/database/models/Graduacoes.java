package database.models;

public class Graduacoes {
	
	/* Attributes: */
	private Modalidades modalidade;
	private String graduacao;
	
	/* Constructor: */
	public Graduacoes(String modalidade, String graduacao) {
		this.modalidade = new Modalidades(modalidade);
		this.graduacao = graduacao;
	}
	
	public Graduacoes(Modalidades modalidade, String graduacao) {
		this.modalidade = modalidade;
		this.graduacao = graduacao;
	}
	
	/* Getter and Setter: */
	public Modalidades getModalidade() {
		return this.modalidade;
	}

	public void setModalidade(Modalidades modalidade) {
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
