package database.models;

public class Plano {
	
	/* Attributes: */
	private Modalidade modalidade;
	private String plano;
	private double valor;
	
	/* Constructor: */
	public Plano(String modalidade, String plano, double valor) {
		this.modalidade = new Modalidade(modalidade);
		this.plano = plano;
		this.valor = valor;
	}
	
	/* Getter and Setter: */
	public String getModalidade() {
		return this.modalidade.getModalidade();
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public String getPlano() {
		return this.plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%s, %s, %.2f", this.modalidade.toString(), this.plano, this.valor);
	}

}
 