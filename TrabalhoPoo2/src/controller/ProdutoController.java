package controller;

import dao.ProdutoDAO;
import dao.ProdutoDAOImpl;
import model.ProdutoModel;

import java.util.List;

public class ProdutoController {
    private ProdutoDAO produtoDAO;

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAOImpl();
    }

    public void cadastrarProduto(ProdutoModel produto) {
        produtoDAO.adicionarProduto(produto);
    }

    public ProdutoModel consultarProdutoPorId(int id) {
        return produtoDAO.obterProdutoPorId(id);
    }

    public List<ProdutoModel> consultarProdutoPorNome(String nome) {
        return produtoDAO.buscarPorNome(nome);
    }

    public List<ProdutoModel> consultarProdutoPorCategoria(String categoria) {
        return produtoDAO.buscarPorCategoria(categoria);
    }

    public void atualizarProduto(ProdutoModel produto) {
        produtoDAO.atualizarProduto(produto);
    }

    public void removerProduto(int id) {
        produtoDAO.deletarProduto(id);
    }

    public List<ProdutoModel> gerarRelatorioEstoque() {
        return produtoDAO.listarProdutos();
    }
}
