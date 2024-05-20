package model;

public class Cheque extends FormaPagamento {
    private String numeroCheque;
    private String banco;

    public Cheque(double valorPago, String numeroCheque, String banco) {
        super("Cheque", valorPago);
        this.setNumeroCheque(numeroCheque);
        this.setBanco(banco);
    }

    @Override
    public void pagar() {
        System.out.println("Pagamento efetuado com cheque no valor de R$" + valorPago);
        
    }

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}
    }