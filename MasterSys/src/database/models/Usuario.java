package database.models;

public class Usuario {
	
	/* attributes: */
	private String usuario;
	private String perfil;
	
	/* constructor: */
	public Usuario () {}

	public Usuario (String usuario, String perfil) {
		this.usuario = usuario;
		this.perfil = perfil;
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
	
	/* methods: */
	public String toString() {
		return String.format("%s, %s", this.usuario, this.perfil);
	}
}