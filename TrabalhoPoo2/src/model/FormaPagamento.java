package model;

public abstract class FormaPagamento {
    protected String tipo;
    protected double valorPago;

  public FormaPagamento(String tipo, double valorPago) {
		
	}

	public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
    
    public abstract void pagar();
}
