package database.models;

import java.util.Date;

public class Matricula {
	
	/* Attributes: */
	private int cod_matricula;
	private int cod_aluno;
	private int dia_vencimento;
	private Date dt_matricula;
	private Date dt_encerramento;
	
	/* Constructor: */
	public Matricula(int cod_matricula, int cod_aluno, Date dt_matricula, int dia_vencimento, Date dt_encerramento) {
		this.cod_matricula = cod_matricula;
		this.cod_aluno = cod_aluno;
		this.dia_vencimento = dia_vencimento;
		this.dt_matricula = dt_matricula;
		this.dt_encerramento = dt_encerramento;
	}
	
	public Matricula(int cod_aluno, Date dt_matricula, int dia_vencimento, Date dt_encerramento) {
		this(0, cod_aluno, dt_matricula, dia_vencimento, dt_encerramento);
	}
	
	/* Getter and Setter: */
	public int getCodigoMatricula() {
		return this.cod_matricula;
	}

	public void setCodigoMatricula(int cod_matricula) {
		this.cod_matricula = cod_matricula;
	}

	public int getCodigoAluno() {
		return this.cod_aluno;
	}

	public void setCodigoAluno(int cod_aluno) {
		this.cod_aluno = cod_aluno;
	}

	public int getDiaVencimento() {
		return this.dia_vencimento;
	}

	public void setDiaVencimento(int dia_vencimento) {
		this.dia_vencimento = dia_vencimento;
	}

	public Date getDataMatricula() {
		return this.dt_matricula;
	}

	public void setDataMatricula(Date dt_matricula) {
		this.dt_matricula = dt_matricula;
	}

	public Date getDataEncerramento() {
		return this.dt_encerramento;
	}

	public void setDataEncerramento(Date dt_encerramento) {
		this.dt_encerramento = dt_encerramento;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%d, %d, %d, %s, %s", this.cod_matricula, this.cod_aluno, this.dia_vencimento,
		this.dt_matricula, this.dt_encerramento);
	}
}
