package database.models;

import java.util.Date;

public class MatriculaModalidade {
	
	/* Attributes: */
	private int cod_matricula;
	private Graduacao graduacao;	// cont�m uma modalidade e uma string
	private Plano plano;			// cont�m uma modalidade, um plano e um valor
	private Date dt_inicio;
	private Date dt_fim;
	
	/* Constructor: */
	public MatriculaModalidade(int cod_matricula, String modalidade, String graduacao, String plano, Date dt_inicio, Date dt_fim) {
		this.cod_matricula = cod_matricula;
		this.graduacao = new Graduacao(modalidade, graduacao);
		this.plano = new Plano(modalidade, plano, 0);
		this.dt_inicio = dt_inicio;
		this.dt_fim = dt_fim;
	}
	
	/* Getter and Setter: */
	public int getCod_matricula() {
		return this.cod_matricula;
	}

	public void setCod_matricula(int cod_matricula) {
		this.cod_matricula = cod_matricula;
	}

	public Graduacao getGraduacao() {
		return this.graduacao;
	}

	public void setGraduacao(Graduacao graduacao) {
		this.graduacao = graduacao;
	}

	public Plano getPlano() {
		return this.plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Date getDt_inicio() {
		return this.dt_inicio;
	}

	public void setDt_inicio(Date dt_inicio) {
		this.dt_inicio = dt_inicio;
	}

	public Date getDt_fim() {
		return this.dt_fim;
	}

	public void setDt_fim(Date dt_fim) {
		this.dt_fim = dt_fim;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%d, %s, %s, %s, %s", this.cod_matricula, this.graduacao.getGraduacao(), this.plano.toString(),
		this.dt_inicio.toString(), this.dt_fim.toString());
	}
	
}
