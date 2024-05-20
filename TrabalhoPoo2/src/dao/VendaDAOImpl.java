package dao;

import model.Cliente;
import model.ProdutoModel;
import model.Venda;
import model.FormaPagamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAOImpl implements VendaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/TrabalhoPOO2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void adicionarVenda(Venda venda) {
        String insertVendaSQL = "INSERT INTO vendas (cliente_cpf, forma_pagamento, valor_total) VALUES (?, ?, ?)";
        String insertVendaProdutosSQL = "INSERT INTO venda_produtos (venda_id, produto_id, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false);

            try (PreparedStatement vendaStmt = conn.prepareStatement(insertVendaSQL, Statement.RETURN_GENERATED_KEYS)) {
                vendaStmt.setString(1, venda.getCliente().getCpf());
                vendaStmt.setString(2, venda.getFormaPagamento().getTipo());
                vendaStmt.setDouble(3, venda.getValorTotal());
                vendaStmt.executeUpdate();

                ResultSet generatedKeys = vendaStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int vendaId = generatedKeys.getInt(1);
                    venda.setId(vendaId);

                    try (PreparedStatement vendaProdutosStmt = conn.prepareStatement(insertVendaProdutosSQL)) {
                        for (ProdutoModel produto : venda.getProdutosVendidos()) {
                            vendaProdutosStmt.setInt(1, vendaId);
                            vendaProdutosStmt.setInt(2, produto.getId());
                            vendaProdutosStmt.setInt(3, 1); // Assume que cada produto é adicionado uma vez
                            vendaProdutosStmt.addBatch();
                        }
                        vendaProdutosStmt.executeBatch();
                    }
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Venda obterVendaPorId(int id) {
        String selectVendaSQL = "SELECT * FROM vendas WHERE id = ?";
        String selectVendaProdutosSQL = "SELECT * FROM venda_produtos WHERE venda_id = ?";
        Venda venda = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement vendaStmt = conn.prepareStatement(selectVendaSQL);
             PreparedStatement vendaProdutosStmt = conn.prepareStatement(selectVendaProdutosSQL)) {

            vendaStmt.setInt(1, id);
            ResultSet vendaRs = vendaStmt.executeQuery();

            if (vendaRs.next()) {
                String clienteCpf = vendaRs.getString("cliente_cpf");
                FormaPagamento formaPagamento = new FormaPagamento(vendaRs.getString("forma_pagamento"));
                double valorTotal = vendaRs.getDouble("valor_total");

                Cliente cliente = new ClienteDAOImpl().obterClientePorCpf(clienteCpf);
                venda = new Venda(cliente, formaPagamento);
                venda.setId(id);
                venda.setValorTotal(valorTotal);

                vendaProdutosStmt.setInt(1, id);
                ResultSet vendaProdutosRs = vendaProdutosStmt.executeQuery();

                while (vendaProdutosRs.next()) {
                    int produtoId = vendaProdutosRs.getInt("produto_id");
                    ProdutoModel produto = new ProdutoDAOImpl().obterProdutoPorId(produtoId);
                    venda.adicionarProduto(produto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return venda;
    }

    @Override
    public List<Venda> listarVendas() {
        String selectVendasSQL = "SELECT * FROM vendas";
        List<Venda> vendas = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectVendasSQL)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String clienteCpf = rs.getString("cliente_cpf");
                FormaPagamento formaPagamento = new FormaPagamento(rs.getString("forma_pagamento"));
                double valorTotal = rs.getDouble("valor_total");

                Cliente cliente = new ClienteDAOImpl().obterClientePorCpf(clienteCpf);
                Venda venda = new Venda(cliente, formaPagamento);
                venda.setId(id);
                venda.setValorTotal(valorTotal);

                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendas;
    }
}
