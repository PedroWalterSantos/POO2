package model;

public class Loginmodel {
	private Integer idLogin;
	private String  usuario;
	private String  senha;
	private String roleLogin;
	

	public Loginmodel(String usuario, String senha, String roleLogin) {
		super();
		this.usuario = usuario;
		this.senha = senha;
		this.roleLogin = roleLogin;
	}

	public Loginmodel(Integer idLogin, String usuario, String senha, String roleLogin) {
		super();
		this.idLogin = idLogin;
		this.usuario = usuario;
		this.senha = senha;
		this.roleLogin = roleLogin;
	}

	public Integer getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Integer idLogin) {
		this.idLogin = idLogin;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRoleLogin() {
		return roleLogin;
	}

	public void setRoleLogin(String roleLogin) {
		this.roleLogin = roleLogin;
	}
}