package database.models;

import java.util.Date;

public class Matricula {
	
	/* attributes: */
	private int codigo_matricula;
	private int codigo_aluno;
	private int dia_vencimento;
	private Date data_matricula;
	private Date data_encerramento;
	
	/* constructor: */
	public Matricula() {}

	public Matricula(int codigo_matricula, int codigo_aluno) {
		this.codigo_matricula = codigo_matricula;
		this.codigo_aluno = codigo_aluno;
	}

	public Matricula(int codigo_matricula, int codigo_aluno, Date data_matricula, int dia_vencimento, Date data_encerramento) {
		this.codigo_matricula = codigo_matricula;
		this.codigo_aluno = codigo_aluno;
		this.dia_vencimento = dia_vencimento;
		this.data_matricula = data_matricula;
		this.data_encerramento = data_encerramento;
	}
	
	/* getter and setter: */
	public int getCodigoMatricula() { return this.codigo_matricula; }

	public void setCodigoMatricula(int codigo_matricula) { this.codigo_matricula = codigo_matricula; }

	public int getCodigoAluno() { return this.codigo_aluno; }

	public void setCodigoAluno(int codigo_aluno) { this.codigo_aluno = codigo_aluno; }

	public int getDiaVencimento() { return this.dia_vencimento; }

	public void setDiaVencimento(int dia_vencimento) { this.dia_vencimento = dia_vencimento; }

	public Date getDataMatricula() { return this.data_matricula; }

	public void setDataMatricula(Date data_matricula) { this.data_matricula = data_matricula; }

	public Date getDataEncerramento() { return this.data_encerramento; }

	public void setDataEncerramento(Date data_encerramento) { this.data_encerramento = data_encerramento; }
	
	/* methods: */
	public String toString() {
		return String.format("%d, %d, %d, %s, %s", this.codigo_matricula, this.codigo_aluno, this.dia_vencimento,
		this.data_matricula, this.data_encerramento);
	}
}
