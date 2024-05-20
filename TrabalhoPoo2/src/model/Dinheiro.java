package model;

public class Dinheiro extends FormaPagamento {
    public Dinheiro(double valorPago) {
        super("Dinheiro", valorPago);
    }

    @Override
    public void pagar() {
        System.out.println("Pagamento efetuado em dinheiro no valor de R$" + valorPago);
        // Lógica específica para pagamento em dinheiro
    }
}