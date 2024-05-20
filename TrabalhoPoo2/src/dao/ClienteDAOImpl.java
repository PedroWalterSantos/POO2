package dao;

import model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {
    private String url = "jdbc:mysql://localhost:3306/TrabalhoPOO2";
    private String user = "root";
    private String password = "";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, cpf, dataNascimento, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
            pstmt.setString(4, cliente.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente obterClientePorCpf(String cpf) {
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("dataNascimento").toLocalDate(),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("dataNascimento").toLocalDate(),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, dataNascimento = ?, email = ? WHERE cpf = ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setDate(2, Date.valueOf(cliente.getDataNascimento()));
            pstmt.setString(3, cliente.getEmail());
            pstmt.setString(4, cliente.getCpf());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletarCliente(String cpf) {
        String sql = "DELETE FROM clientes WHERE cpf = ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
