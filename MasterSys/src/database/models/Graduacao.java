package database.models;

public class Graduacao {
	
	/* attributes: */
	private String modalidade;
	private String graduacao;
	
	/* constructor: */
	public Graduacao() {}

	public Graduacao(String modalidade, String graduacao) {
		this.modalidade = modalidade;
		this.graduacao = graduacao;
	}
	
	/* getter and setter: */
	public String getModalidade() { return this.modalidade; }

	public void setModalidade(String modalidade) { this.modalidade = modalidade; }

	public String getGraduacao() { return graduacao; }

	public void setGraduacao(String graduacao) { this.graduacao = graduacao; }
	
	/* methods: */
	public String toString() {
		return String.format("%s, %s", this.modalidade, this.graduacao);
	}
}
