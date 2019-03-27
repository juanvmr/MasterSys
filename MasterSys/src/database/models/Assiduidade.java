package database.models;

import java.util.Date;

public class Assiduidade {
	
	/* attributes: */
	private int codigo_matricula;
	private Date data_entrada;
	
	/* constructor: */
	public Assiduidade() {}

	public Assiduidade(int codigo_matricula, Date data_entrada) {
		this.codigo_matricula = codigo_matricula;
		this.data_entrada = data_entrada;
	}

	/* getter and setter: */
	public int getCodigoMatricula() { return this.codigo_matricula; }

	public void setCodigoMatricula(int codigo_matricula) { this.codigo_matricula = codigo_matricula; }

	public Date getDataEntrada() { return this.data_entrada; }

	public void setDataEntrada(Date data_entrada) { this.data_entrada = data_entrada; }
	
	/* methods: */
	public String toString() {
		return String.format("%s %s", this.codigo_matricula, this.data_entrada);
	}
}