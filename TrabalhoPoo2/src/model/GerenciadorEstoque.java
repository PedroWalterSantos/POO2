package model;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorEstoque {
    private List<ProdutoModel> produtosEstoque;

    public GerenciadorEstoque() {
        this.produtosEstoque = new ArrayList<>();
    }

    // Método para adicionar um produto ao estoque
    public void adicionarProduto(ProdutoModel produto, int quantidade) {
        for (ProdutoModel p : produtosEstoque) {
            if (p.getId() == produto.getId()) {
                System.out.println("Produto já existente no estoque. Atualizando quantidade...");
                p.setQuantidadeEstoque(p.getQuantidadeEstoque() + quantidade);
                return;
            }
        }
        produto.setQuantidadeEstoque(quantidade);
        produtosEstoque.add(produto);
        System.out.println("Produto adicionado ao estoque com sucesso.");
    }

    // Método para remover um produto do estoque
    public void removerProduto(int id) {
        for (ProdutoModel p : produtosEstoque) {
            if (p.getId() == id) {
                produtosEstoque.remove(p);
                System.out.println("Produto removido do estoque com sucesso.");
                return;
            }
        }
        System.out.println("Produto não encontrado no estoque.");
    }

    // Método para buscar um produto pelo ID
    public ProdutoModel buscarProduto(int id) {
        for (ProdutoModel p : produtosEstoque) {
            if (p.getId() == id) {
                return p;
            }
        }
        System.out.println("Produto não encontrado no estoque.");
        return null;
    }

    // Método para atualizar informações de um produto no estoque
    public void atualizarProduto(ProdutoModel produto) {
        for (ProdutoModel p : produtosEstoque) {
            if (p.getId() == produto.getId()) {
                
                p.setNome(produto.getNome());
                p.setDescricao(produto.getDescricao());
                p.setPrecoCusto(produto.getPrecoCusto());
                p.setPrecoVenda(produto.getPrecoVenda());
                p.setQuantidadeEstoque(produto.getQuantidadeEstoque());
                p.setCategoria(produto.getCategoria());
                System.out.println("Produto atualizado no estoque com sucesso.");
                return;
            }
        }
        System.out.println("Produto não encontrado no estoque.");
    }

    // Método para gerar um relatório de estoque
    public void gerarRelatorioEstoque() {
        System.out.println("Relatório de Estoque:");
        for (ProdutoModel p : produtosEstoque) {
            System.out.println("ID: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Descrição: " + p.getDescricao());
            System.out.println("Preço de Custo: R$" + p.getPrecoCusto());
            System.out.println("Preço de Venda: R$" + p.getPrecoVenda());
            System.out.println("Quantidade em Estoque: " + p.getQuantidadeEstoque());
            System.out.println("Categoria: " + p.getCategoria());
            System.out.println("-----------------------");
        }
    }}
