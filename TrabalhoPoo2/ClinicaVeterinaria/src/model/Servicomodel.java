package model;

public class Servicomodel {
	private int idServico;
	private String nomeServico;
	private String descricaoServico;
	private Double preco;
	
	public Servicomodel(int idServico, String nomeServico, String descricaoServico, Double preco) {
		super();
		this.idServico = idServico;
		this.nomeServico = nomeServico;
		this.descricaoServico = descricaoServico;
		this.preco = preco;
	}
	public Servicomodel(String nomeServico, String descricaoServico, Double preco) {
		super();
		this.nomeServico = nomeServico;
		this.descricaoServico = descricaoServico;
		this.preco = preco;
	}
	public int getIdServico() {
		return idServico;
	}
	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	public String getDescricaoServico() {
		return descricaoServico;
	}
	public void setDescricaoServico(String descricaoServico) {
		this.descricaoServico = descricaoServico;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	

}
