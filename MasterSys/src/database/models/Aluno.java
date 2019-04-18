package database.models;

import java.util.Date;

public class Aluno {
	
	private int codigo_aluno;
	private String aluno;
	private Date data_nascimento;
	private char sexo;
	private String telefone;
	private String celular;
	private String email;
	private String observacao;
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private Local local;
	private String cep;
	
	/* constructor: */
	public Aluno() {}

	public Aluno(int codigo_aluno) {
		this.codigo_aluno = codigo_aluno;
		this.local = new Local();
	}

	public Aluno(String aluno) {
		this.aluno = aluno;
		this.local = new Local();
	}

	public Aluno(String aluno, Date data_nascimento, char sexo, String telefone, String celular, String email,
	 String observacao, String endereco, String numero, String complemento, String bairro, Local local, String cep) {
		this.aluno = aluno;
		this.data_nascimento = data_nascimento;
		this.sexo = sexo;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
		this.observacao = observacao;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.local = local;
		this.cep = cep;
	}

	public Aluno(int codigo_aluno, String aluno, Date data_nascimento, char sexo, String telefone, String celular,
	String email, String observacao, String endereco, String numero, String complemento, String bairro, Local local, String cep) {
		this(aluno, data_nascimento, sexo, telefone, celular, email, observacao, endereco, numero, complemento, bairro, local, cep);
		this.codigo_aluno = codigo_aluno;
	}
	
	/* getter and setter: */
	public int getCodigoAluno() { return this.codigo_aluno; }

	public void setCodigoAluno(int codigo_aluno) { this.codigo_aluno = codigo_aluno; }
	
	public String getAluno() { return this.aluno; }

	public void setAluno(String aluno) { this.aluno = aluno; }
	
	public Date getDataNascimento() { return this.data_nascimento; }

	public void setDataNascimento(Date data_nascimento) { this.data_nascimento = data_nascimento; }
	
	public char getSexo() { return this.sexo; }

	public void setSexo(char sexo) { this.sexo = sexo; }
	
	public String getTelefone() { return this.telefone; }

	public void setTelefone(String telefone) { this.telefone = telefone; }
	
	public String getCelular() { return this.celular; }

	public void setCelular(String celular) { this.celular = celular; }
	
	public String getEmail() { return this.email; }

	public void setEmail(String email) { this.email = email; }
	
	public String getObs() { return this.observacao; }

	public void setObs(String observacao) { this.observacao = observacao; }
	
	public String getEndereco() { return this.endereco; }

	public void setEndereco(String endereco) { this.endereco = endereco; }
	
	public String getNumero() { return this.numero; }

	public void setNumero(String numero) { this.numero = numero; }
	
	public String getComplemento() { return this.complemento; }

	public void setComplemento(String complemento) { this.complemento = complemento; }
	
	public String getBairro() { return this.bairro; }

	public void setBairro(String bairro) { this.bairro = bairro; }
	
	public Local getLocal() { return this.local; }

	public void setLocal(Local local) { this.local = local; }
	
	public String getCEP() { return cep; }

	public void setCEP(String cep) { this.cep = cep; }
	
	/* methods: */
	public String toString() {
		return String.format("%d, %s, %s, %c, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s", this.codigo_aluno,
		this.aluno, ((this.data_nascimento != null) ? this.data_nascimento.toString() : "no data"), this.sexo, this.telefone, this.celular, this.email,
		this.observacao, this.endereco, this.numero, this.complemento, this.bairro, ((this.local != null) ? this.local.toString() : "no local"), this.cep);
	}
}
