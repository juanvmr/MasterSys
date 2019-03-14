package database.models;

public class Planos {
	
	/* Attributes: */
	private Modalidades modalidade;
	private String plano;
	private float valor;
	
	/* Constructor: */
	public Planos(String plano, float valor, String modalidade) {
		this.modalidade = new Modalidades(modalidade);
		this.plano = plano;
		this.valor = valor;
	}
	
	/* Getter and Setter: */
	public Modalidades getModalidade() {
		return this.modalidade;
	}

	public void setModalidade(Modalidades modalidade) {
		this.modalidade = modalidade;
	}

	public String getPlano() {
		return this.plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public float getValor() {
		return this.valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%s, %s, %.2f", this.modalidade.toString(), this.plano, this.valor);
	}

}
 