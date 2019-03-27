package database.models;

import java.util.Date;

public class MatriculaModalidade {
	
	/* Attributes: */
	private int codigo_matricula;
	private String modalidade;
	private String graduacao;
	private String plano;
	private Date data_inicio;
	private Date data_fim;
	
	/* Constructor: */
	public MatriculaModalidade(int codigo_matricula, String modalidade, String graduacao, String plano, Date data_inicio, Date data_fim) {
		this.codigo_matricula = codigo_matricula;
		this.modalidade = modalidade;
		this.graduacao = graduacao;
		this.plano = plano;
		this.data_inicio = data_inicio;
		this.data_fim = data_fim;
	}
	
	/* Getter and Setter: */
	public int getCodigoMatricula() {
		return this.codigo_matricula;
	}

	public void setCodigoMatricula(int codigo_matricula) {
		this.codigo_matricula = codigo_matricula;
	}
	
	public String getModalidade() {
		return this.modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getGraduacao() {
		return this.graduacao;
	}

	public void setGraduacao(String graduacao) {
		this.graduacao = graduacao;
	}

	public String getPlano() {
		return this.plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public Date getDataInicio() {
		return this.data_inicio;
	}

	public void setDataInicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getDataFim() {
		return this.data_fim;
	}

	public void setDataFim(Date data_fim) {
		this.data_fim = data_fim;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%d, %s, %s, %s, %s, %s", this.codigo_matricula, this.modalidade, this.graduacao, this.plano,
		this.data_inicio.toString(), this.data_fim.toString());
	}
	
}
