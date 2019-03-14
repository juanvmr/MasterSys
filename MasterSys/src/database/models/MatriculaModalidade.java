package database.models;

import java.util.Date;

public class MatriculaModalidade {
	
	/* Attributes: */
	private int cod_matricula;
	private Graduacoes graduacao;	// contém uma modalidade e uma string
	private Planos plano;			// contém uma modalidade, um plano e um valor
	private Date dt_inicio;
	private Date dt_fim;
	
	/* Constructor: */
	public MatriculaModalidade(int cod_matricula, String modalidade, String graduacao, String plano, Date dt_inicio, Date dt_fim) {
		this.cod_matricula = cod_matricula;
		this.graduacao = new Graduacoes(graduacao, modalidade);
		this.plano = new Planos(plano, 0, modalidade);
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

	public Graduacoes getGraduacao() {
		return this.graduacao;
	}

	public void setGraduacao(Graduacoes graduacao) {
		this.graduacao = graduacao;
	}

	public Planos getPlano() {
		return this.plano;
	}

	public void setPlano(Planos plano) {
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
