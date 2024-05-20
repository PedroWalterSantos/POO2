package dao;

import model.ProdutoModel;
import model.ProdutoLimpeza;
import model.ProdutoEletronico;
import model.ProdutoAlimenticio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {
    private String url = "jdbc:mysql://localhost:3306/TrabalhoPOO2";
    private String user = "root";
    private String password = "";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void adicionarProduto(ProdutoModel produto) {
        String sql = "INSERT INTO produtos (nome, descricao, precoCusto, precoVenda, quantidadeEstoque, categoria) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getDescricao());
            pstmt.setDouble(3, produto.getPrecoCusto());
            pstmt.setDouble(4, produto.getPrecoVenda());
            pstmt.setInt(5, produto.getQuantidadeEstoque());
            pstmt.setString(6, produto.getCategoria());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProdutoModel obterProdutoPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return criarProduto(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProdutoModel> listarProdutos() {
        List<ProdutoModel> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                produtos.add(criarProduto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    @Override
    public List<ProdutoModel> buscarPorNome(String nome) {
        List<ProdutoModel> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE nome LIKE ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nome + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                produtos.add(criarProduto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    @Override
    public List<ProdutoModel> buscarPorCategoria(String categoria) {
        List<ProdutoModel> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE categoria = ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                produtos.add(criarProduto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    @Override
    public void atualizarProduto(ProdutoModel produto) {
        String sql = "UPDATE produtos SET nome = ?, descricao = ?, precoCusto = ?, precoVenda = ?, quantidadeEstoque = ?, categoria = ? WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getDescricao());
            pstmt.setDouble(3, produto.getPrecoCusto());
            pstmt.setDouble(4, produto.getPrecoVenda());
            pstmt.setInt(5, produto.getQuantidadeEstoque());
            pstmt.setString(6, produto.getCategoria());
            pstmt.setInt(7, produto.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletarProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ProdutoModel criarProduto(ResultSet rs) throws SQLException {
        String categoria = rs.getString("categoria");
        ProdutoModel produto;
        switch (categoria) {
            case "Limpeza":
                produto = new ProdutoLimpeza(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("precoCusto"),
                        rs.getDouble("precoVenda"),
                        rs.getInt("quantidadeEstoque"),
                        categoria);
                break;
            case "Eletronico":
                produto = new ProdutoEletronico(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("precoCusto"),
                        rs.getDouble("precoVenda"),
                        rs.getInt("quantidadeEstoque"),
                        categoria);
                break;
            case "Alimenticio":
                produto = new ProdutoAlimenticio(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("precoCusto"),
                        rs.getDouble("precoVenda"),
                        rs.getInt("quantidadeEstoque"),
                        categoria);
                break;
            default:
                throw new IllegalArgumentException("Categoria desconhecida: " + categoria);
        }
        produto.setId(rs.getInt("id"));
        return produto;
    }
}
