package database.models;

public class Usuario {
	
	/* attributes: */
	private String usuario;
	private String perfil;
	private String password;
	
	/* constructor: */
	public Usuario () {}

	public Usuario (String usuario, String perfil) {
		this.usuario = usuario;
		this.perfil = perfil;
	}

	public Usuario (String usuario, String perfil, String password) {
		this(usuario, perfil);
		this.password = password;
	}

	/* getter and setter: */
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/* methods: */
	public String toString() {
		return String.format("%s, %s", this.usuario, this.perfil);
	}
}