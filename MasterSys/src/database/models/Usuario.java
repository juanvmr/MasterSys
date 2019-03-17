package database.models;

public class Usuario {
	
	/* Attributes: */
	private String usuario;
	private String perfil;
	
	/* Constructor: */
	public Usuario (String usuario, String perfil) {
		this.usuario = usuario;
		this.perfil = perfil;
	}

	/* Getter and Setter: */
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	/* Methods: */
	public String toString() {
		return String.format("%s, %s",  this.usuario, this.perfil);
	}
}