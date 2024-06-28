package model;

public class Proprietariomodel {
	private int idProprietario;
	private String nomeProprietario;
	private String email;
	private String telefone;
	private String endereco;
	public Proprietariomodel(String nomeProprietario, String email, String telefone, String endereco) {
		super();
		this.nomeProprietario = nomeProprietario;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	
	

	public Proprietariomodel(int idProprietario, String nomeProprietario, String email, String telefone,
			String endereco) {
		super();
		this.idProprietario = idProprietario;
		this.nomeProprietario = nomeProprietario;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}




	public int getIdProprietario() {
		return idProprietario;
	}
	public void setIdProprietario(int idProprietario) {
		this.idProprietario = idProprietario;
	}
	public String getNomeProprietario() {
		return nomeProprietario;
	}
	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	

}
