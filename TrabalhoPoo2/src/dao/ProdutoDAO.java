package dao;

import model.ProdutoModel;
import java.util.List;

public interface ProdutoDAO {
    void adicionarProduto(ProdutoModel produto);
    ProdutoModel obterProdutoPorId(int id);
    List<ProdutoModel> listarProdutos();
    List<ProdutoModel> buscarPorNome(String nome);
    List<ProdutoModel> buscarPorCategoria(String categoria);
    void atualizarProduto(ProdutoModel produto);
    void deletarProduto(int id);
}
