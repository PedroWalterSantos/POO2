package controller;

import dao.ClienteDAOImpl;
import dao.ProdutoDAOImpl;
import dao.VendaDAO;
import dao.VendaDAOImpl;
import model.Cliente;
import model.ProdutoModel;
import model.Venda;
import model.FormaPagamento;

import java.util.List;

public class VendaController {
    private Venda venda;
    private VendaDAO vendaDAO;
    private ClienteDAOImpl clienteDAO;
    private ProdutoDAOImpl produtoDAO;

    public VendaController() {
        this.vendaDAO = new VendaDAOImpl();
        this.clienteDAO = new ClienteDAOImpl();
        this.produtoDAO = new ProdutoDAOImpl();
    }

    public void iniciarVenda(String clienteCpf, String formaPagamentoTipo) {
        Cliente cliente = clienteDAO.obterClientePorCpf(clienteCpf);
        if (cliente != null) {
            this.venda = new Venda(cliente, null);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado com o CPF fornecido.");
        }
    }

    public void adicionarProduto(int produtoId, int quantidade) {
        if (venda != null) {
            ProdutoModel produto = produtoDAO.obterProdutoPorId(produtoId);
            if (produto != null) {
                for (int i = 0; i < quantidade; i++) {
                    venda.adicionarProduto(produto);
                }
            } else {
                throw new IllegalArgumentException("Produto não encontrado com o ID fornecido.");
            }
        } else {
            throw new IllegalStateException("Venda não iniciada. Por favor, inicie uma venda antes de adicionar produtos.");
        }
    }

    public void aplicarDesconto(double percentualDesconto) {
        if (venda != null) {
            venda.aplicarDesconto(percentualDesconto);
        } else {
            throw new IllegalStateException("Venda não iniciada. Por favor, inicie uma venda antes de aplicar desconto.");
        }
    }

    public void finalizarVenda() {
        if (venda != null) {
            venda.finalizarVenda();
            vendaDAO.adicionarVenda(venda);
            venda = null; 
        } else {
            throw new IllegalStateException("Venda não iniciada. Por favor, inicie uma venda antes de finalizá-la.");
        }
    }

    public List<Venda> listarVendas() {
        return vendaDAO.listarVendas();
    }

    public Venda consultarVendaPorId(int id) {
        return vendaDAO.obterVendaPorId(id);
    }
}
