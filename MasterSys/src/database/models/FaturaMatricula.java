package database.models;

import java.util.Date;

public class FaturaMatricula {
	
	/* attributes: */
	private int codigo_matricula;
	private double valor;
	private Date data_vencimento;
	private Date data_pagamento;
	private Date data_cancelamento;
	private String aluno;
	private boolean matricula_encerrada;
	
	/* constructor: */
	public FaturaMatricula() {}

	public FaturaMatricula(int codigo_matricula, Date data_vencimento, double valor, Date data_pagamento, Date data_cancelamento) {
		this.codigo_matricula = codigo_matricula;
		this.valor = valor;
		this.data_vencimento = data_vencimento;
		this.data_pagamento = data_pagamento;
		this.data_cancelamento = data_cancelamento;
	}

	/* getter and setter: */
	public int getCodigoMatricula() { return this.codigo_matricula; }

	public void setCodigoMatricula(int codigo_matricula) { this.codigo_matricula = codigo_matricula; }

	public double getValor() { return this.valor; }

	public void setValor(double valor) { this.valor = valor; }

	public Date getDataVencimento() { return this.data_vencimento; }

	public void setDataVencimento(Date data_vencimento) { this.data_vencimento = data_vencimento; }

	public Date getDataPagamento() { return this.data_pagamento; }

	public void setDataPagamento(Date data_pagamento) { this.data_pagamento = data_pagamento; }

	public Date getDataCancelamento() { return data_cancelamento; }

	public void setDataCancelamento(Date data_cancelamento) { this.data_cancelamento = data_cancelamento; }

	public String getAluno() {
		return this.aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public boolean isMatriculaEncerrada() {
		return this.matricula_encerrada;
	}

	public void setMatriculaEncerrada(boolean matricula_encerrada) {
		this.matricula_encerrada = matricula_encerrada;
	}

	/* methods: */
	public String toString() {
		return String.format("%d, R$ %.2f, %s, %s, %s", this.codigo_matricula, this.valor, this.data_vencimento,
		this.data_pagamento, this.data_cancelamento);
	}
	
}