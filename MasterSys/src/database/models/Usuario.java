package database.models;

public class Usuario {
	
	/* attributes: */
	private String username;
	private String password;
	private String perfil;
	
	/* constructor: */
	public Usuario () {}

	public Usuario (String username, String perfil) {
		this.username = username;
		this.perfil = perfil;
	}

	/* getter and setter: */
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
		updatePerfil();
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
		updatePerfil();
	}

	/* methods: */
	private void updatePerfil() {
		if ((this.username == null) || (this.password == null)) return;
		if (this.username.equals("admin") && this.password.equals("admin")) {
			this.perfil = "Completo";
		}
	}

	public String toString() {
		return String.format("%s (%s) - %s", this.username, this.perfil, this.password);
	}
}