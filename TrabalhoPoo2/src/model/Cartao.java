package model;

public class Cartao extends FormaPagamento {
    private String numeroCartao;
    private String tipoCartao;

    public Cartao(double valorPago, String numeroCartao, String tipoCartao) {
        super("Cartão", valorPago);
        this.setNumeroCartao(numeroCartao);
        this.setTipoCartao(tipoCartao);
    }

    @Override
    public void pagar() {
        System.out.println("Pagamento efetuado com cartão no valor de R$" + valorPago);
        
    }

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getTipoCartao() {
		return tipoCartao;
	}

	public void setTipoCartao(String tipoCartao) {
		this.tipoCartao = tipoCartao;
	}
}