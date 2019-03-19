package database.models;

import java.util.Date;

public class Assiduidade {
	
	/* Attributes: */
	private int codigo_matricula;
	private Date data_entrada;
	
	/* Constructor: */
	public Assiduidade(int codigo_matricula, Date data_entrada) {
		this.codigo_matricula = codigo_matricula;
		this.data_entrada = data_entrada;
	}
	
	public Assiduidade() {
		this(0, null);
	}

	/* Getter and Setter: */
	public int getCodigoMatricula() {
		return codigo_matricula;
	}

	public void setCodigoMatricula(int codigo_matricula) {
		this.codigo_matricula = codigo_matricula;
	}

	public Date getDataEntrada() {
		return data_entrada;
	}

	public void setDataEntrada(Date data_entrada) {
		this.data_entrada = data_entrada;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%s %s", this.codigo_matricula, this.data_entrada);
	}
}