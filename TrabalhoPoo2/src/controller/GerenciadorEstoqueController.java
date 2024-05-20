package controller;

import model.ProdutoModel;
import model.GerenciadorEstoque;

public class GerenciadorEstoqueController {
    private GerenciadorEstoque gerenciadorEstoque;

    public GerenciadorEstoqueController() {
        this.gerenciadorEstoque = new GerenciadorEstoque();
    }

    public void adicionarProdutoAoEstoque(ProdutoModel produto, int quantidade) {
        gerenciadorEstoque.adicionarProduto(produto, quantidade);
    }

    public void removerProdutoDoEstoque(int id) {
        gerenciadorEstoque.removerProduto(id);
    }

    public ProdutoModel buscarProdutoNoEstoque(int id) {
        return gerenciadorEstoque.buscarProduto(id);
    }

    public void atualizarProdutoNoEstoque(ProdutoModel produto) {
        gerenciadorEstoque.atualizarProduto(produto);
    }

    public void gerarRelatorioEstoque() {
        gerenciadorEstoque.gerarRelatorioEstoque();
    }
}
