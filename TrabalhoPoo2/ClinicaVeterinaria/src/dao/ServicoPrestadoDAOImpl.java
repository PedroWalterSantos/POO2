package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ServicoPrestadoModel;
import util.ConexaoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoPrestadoDAOImpl implements ServicoPrestadoDAO {

    @Override
    public void adicionarServicoPrestado(ServicoPrestadoModel servicoPrestado) {
        String sql = "INSERT INTO ServicoPrestado (observacoes, valor, dataServicoPrestado, idServico, idAnimal) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, servicoPrestado.getObservacoes());
            stmt.setDouble(2, servicoPrestado.getValor());
            stmt.setString(3, servicoPrestado.getDataServicoPrestado());
            stmt.setInt(4, servicoPrestado.getIdServico());
            stmt.setInt(5, servicoPrestado.getIdAnimal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar serviço prestado: " + e.getMessage());
        }
    }

    @Override
    public void atualizarServicoPrestado(ServicoPrestadoModel servicoPrestado) {
        String sql = "UPDATE ServicoPrestado SET observacoes = ?, valor = ?, dataServicoPrestado = ?, idServico = ?, idAnimal = ? WHERE idServicoPrestado = ?";
        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, servicoPrestado.getObservacoes());
            stmt.setDouble(2, servicoPrestado.getValor());
            stmt.setString(3, servicoPrestado.getDataServicoPrestado());
            stmt.setInt(4, servicoPrestado.getIdServico());
            stmt.setInt(5, servicoPrestado.getIdAnimal());
            stmt.setInt(6, servicoPrestado.getIdServicoPrestado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar serviço prestado: " + e.getMessage());
        }
    }

    @Override
    public void removerServicoPrestado(int idServicoPrestado) {
        String sql = "DELETE FROM ServicoPrestado WHERE idServicoPrestado = ?";
        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idServicoPrestado);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao remover serviço prestado: " + e.getMessage());
        }
    }

    @Override
    public ServicoPrestadoModel buscarServicoPrestadoPorId(int idServicoPrestado) {
        String sql = "SELECT * FROM ServicoPrestado WHERE idServicoPrestado = ?";
        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idServicoPrestado);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ServicoPrestadoModel(
                        rs.getInt("idServicoPrestado"),
                        rs.getString("observacoes"),
                        rs.getDouble("valor"),
                        rs.getString("dataServicoPrestado"),
                        rs.getInt("idServico"),
                        rs.getInt("idAnimal")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar serviço prestado por ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<ServicoPrestadoModel> listarTodosServicosPrestados() {
        String sql = "SELECT * FROM ServicoPrestado";
        List<ServicoPrestadoModel> servicos = new ArrayList<>();
        try (Connection connection = ConexaoBD.conectar();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ServicoPrestadoModel servicoPrestado = new ServicoPrestadoModel(
                    rs.getInt("idServicoPrestado"),
                    rs.getString("observacoes"),
                    rs.getDouble("valor"),
                    rs.getString("dataServicoPrestado"),
                    rs.getInt("idServico"),
                    rs.getInt("idAnimal")
                );
                servicos.add(servicoPrestado);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar todos os serviços prestados: " + e.getMessage());
        }
        return servicos;
    }
}
