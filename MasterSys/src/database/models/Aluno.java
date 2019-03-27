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
	
	/* Constructor: */
	public Aluno(int codigo_aluno, String aluno, Date data_nascimento, char sexo, String telefone, String celular,
	String email, String observacao, String endereco, String numero, String complemento, String bairro, Local local, String cep) {
		this.codigo_aluno = codigo_aluno;
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
	
	public Aluno(String aluno) {
		this();
		this.aluno = aluno;
	}
	
	public Aluno() {
		this(0, null, null, '0', null, null, null, null, null, null, null, null, new Local(), null);
	}
	
	/* Getter and Setter: */
	public int getCodigoAluno() {
		return codigo_aluno;
	}
	public void setCodigoAluno(int codigo_aluno) {
		this.codigo_aluno = codigo_aluno;
	}
	
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
	
	public Date getDataNascimento() {
		return data_nascimento;
	}
	public void setDataNascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getObs() {
		return observacao;
	}
	public void setObs(String observacao) {
		this.observacao = observacao;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public Local getLocal() {
		return this.local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%d, %s, %s, %c, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s", this.codigo_aluno,
		this.aluno, this.data_nascimento.toString(), this.sexo, this.telefone, this.celular,
		this.email, this.observacao, this.endereco, this.numero, this.complemento, this.bairro,
		this.local.toString(), this.cep);
	}
}
