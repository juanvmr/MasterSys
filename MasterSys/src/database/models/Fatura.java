package database.models;

import java.util.Date;

public class Fatura {
	
	/* Attributes: */
	private int codigo_matricula;
	private double valor;
	private Date data_vencimento;
	private Date data_pagamento;
	private Date data_cancelamento;
	
	/* Constructor: */
	public Fatura(int codigo_matricula, Date data_vencimento, double valor, Date data_pagamento, Date data_cancelamento) {
		this.codigo_matricula = codigo_matricula;
		this.valor = valor;
		this.data_vencimento = data_vencimento;
		this.data_pagamento = data_pagamento;
		this.data_cancelamento = data_cancelamento;
	}
	
	public Fatura() {
		this(0, null, 0, null, null);
	}
	
	/* Getter and Setter: */
	public int getCodigoMatricula() {
		return codigo_matricula;
	}

	public void setCodigoMatricula(int codigo_matricula) {
		this.codigo_matricula = codigo_matricula;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataVencimento() {
		return data_vencimento;
	}

	public void setDataVencimento(Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public Date getDataPagamento() {
		return data_pagamento;
	}

	public void setDataPagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public Date getDataCancelamento() {
		return data_cancelamento;
	}

	public void setDataCancelamento(Date data_cancelamento) {
		this.data_cancelamento = data_cancelamento;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%d, R$ %.2f, %s, %s, %s", this.codigo_matricula, this.valor, this.data_vencimento,
		this.data_pagamento, this.data_cancelamento);
	}
	
}