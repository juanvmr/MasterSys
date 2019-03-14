package database.models;

import java.util.Date;

public class Matriculas {
	
	/* Attributes: */
	private int cod_matricula;
	private int cod_aluno;
	private int dia_vencimento;
	private Date dt_matricula;
	private Date dt_encerramento;
	
	/* Constructor: */
	public Matriculas(int cod_matricula, int cod_aluno, int dia_vencimento, Date dt_matricula, Date dt_encerramento) {
		this.cod_matricula = cod_matricula;
		this.cod_aluno = cod_aluno;
		this.dia_vencimento = dia_vencimento;
		this.dt_matricula = dt_matricula;
		this.dt_encerramento = dt_encerramento;
	}
	
	public Matriculas(int cod_aluno, int dia_vencimento, Date dt_matricula, Date dt_encerramento) {
		this(0, cod_aluno, dia_vencimento, dt_matricula, dt_encerramento);
	}
	
	/* Getter and Setter: */
	public int getCod_matricula() {
		return this.cod_matricula;
	}

	public void setCod_matricula(int cod_matricula) {
		this.cod_matricula = cod_matricula;
	}

	public int getCod_aluno() {
		return this.cod_aluno;
	}

	public void setCod_aluno(int cod_aluno) {
		this.cod_aluno = cod_aluno;
	}

	public int getDia_vencimento() {
		return this.dia_vencimento;
	}

	public void setDia_vencimento(int dia_vencimento) {
		this.dia_vencimento = dia_vencimento;
	}

	public Date getDt_matricula() {
		return this.dt_matricula;
	}

	public void setDt_matricula(Date dt_matricula) {
		this.dt_matricula = dt_matricula;
	}

	public Date getDt_encerramento() {
		return this.dt_encerramento;
	}

	public void setDt_encerramento(Date dt_encerramento) {
		this.dt_encerramento = dt_encerramento;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%d, %d, %d, %s, %s", this.cod_matricula, this.cod_aluno, this.dia_vencimento,
		this.dt_matricula, this.dt_encerramento);
	}
}
