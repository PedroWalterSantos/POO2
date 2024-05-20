package model;

import java.util.ArrayList;
import java.util.List;

public class Venda {
    private List<ProdutoModel> produtosVendidos;
    private Cliente cliente;
    private FormaPagamento formaPagamento;
    private double valorTotal;
    private int id;

    public Venda(Cliente cliente, FormaPagamento formaPagamento) {
        this.cliente = cliente;
        this.formaPagamento = formaPagamento;
        this.produtosVendidos = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    // Método para adicionar um produto à venda
    public void adicionarProduto(ProdutoModel produto) {
        produtosVendidos.add(produto);
        valorTotal += produto.getPrecoVenda();
    }

    // Método para calcular o valor total da venda
    public double calcularValorTotal() {
        return valorTotal;
    }

    // Método para aplicar desconto na venda
    public void aplicarDesconto(double percentualDesconto) {
        double desconto = (percentualDesconto / 100.0) * valorTotal;
        valorTotal -= desconto;
    }

    // Método para finalizar a venda
    public void finalizarVenda() {
        // Aqui poderíamos implementar a lógica de registrar a venda em um sistema ou banco de dados,
        // enviar recibo por e-mail, etc.
        System.out.println("Venda finalizada com sucesso!");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Valor total da venda: R$" + valorTotal);
        System.out.println("Forma de pagamento: " + formaPagamento.getTipo());
    }

    // Getters e Setters

    public List<ProdutoModel> getProdutosVendidos() {
        return produtosVendidos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public void setValorTotal(double valorTotal) {
		this.valorTotal= valorTotal;
		
	}




	}

