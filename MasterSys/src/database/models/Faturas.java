package database.models;

import java.util.Date;

public class Faturas {
	
	/* Attributes: */
	private int cod_matricula;
	private float valor;
	private Date dt_vencimento;
	private Date dt_pagamento;
	private Date dt_cancelamento;
	
	/* Constructor: */
	public Faturas(int cod_matricula, float valor, Date dt_vencimento, Date dt_pagamento, Date dt_cancelamento) {
		this.cod_matricula = cod_matricula;
		this.valor = valor;
		this.dt_vencimento = dt_vencimento;
		this.dt_pagamento = dt_pagamento;
		this.dt_cancelamento = dt_cancelamento;
	}
	
	/* Getter and Setter: */
	public int getCod_matricula() {
		return cod_matricula;
	}

	public void setCod_matricula(int cod_matricula) {
		this.cod_matricula = cod_matricula;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getDt_vencimento() {
		return dt_vencimento;
	}

	public void setDt_vencimento(Date dt_vencimento) {
		this.dt_vencimento = dt_vencimento;
	}

	public Date getDt_pagamento() {
		return dt_pagamento;
	}

	public void setDt_pagamento(Date dt_pagamento) {
		this.dt_pagamento = dt_pagamento;
	}

	public Date getDt_cancelamento() {
		return dt_cancelamento;
	}

	public void setDt_cancelamento(Date dt_cancelamento) {
		this.dt_cancelamento = dt_cancelamento;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%d, R$ %.2f, %s, %s, %s", this.cod_matricula, this.valor, this.dt_vencimento,
		this.dt_pagamento, this.dt_cancelamento);
	}
	
}