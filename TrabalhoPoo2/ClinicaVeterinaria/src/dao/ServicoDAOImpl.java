package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Servicomodel;
import util.ConexaoBD;

public class ServicoDAOImpl implements ServicoDAO {

   
    @Override
    public boolean adicionarServico(Servicomodel servico) {
        String sql = "INSERT INTO servico (nomeServico, descricaoServico, preco) VALUES (?, ?, ?)";
        try (
                Connection conn = ConexaoBD.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, servico.getNomeServico());
            stmt.setString(2, servico.getDescricaoServico());
            stmt.setDouble(3, servico.getPreco());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   
    @Override
    public boolean atualizarServico(int idServico, Servicomodel servico) {
        String sql = "UPDATE servico SET nome_servico = ?, descricao_servico = ?, preco = ? WHERE id_servico = ?";
        try (
                Connection conn = ConexaoBD.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, servico.getNomeServico());
            stmt.setString(2, servico.getDescricaoServico());
            stmt.setDouble(3, servico.getPreco());
            stmt.setInt(4, idServico);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removerServico(int idServico) {
        String sql = "DELETE FROM servico WHERE idServico = ?";
        try (
            Connection conn = ConexaoBD.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, idServico);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Servicomodel buscarServicoPorId(int idServico) {
        String sql = "SELECT * FROM servico WHERE id_servico = ?";
        try (
            Connection conn = ConexaoBD.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, idServico);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nomeServico = rs.getString("nomeServico");
                String descricaoServico = rs.getString("descricaoServico");
                Double preco = rs.getDouble("preco");
                return new Servicomodel(idServico, nomeServico, descricaoServico, preco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

 
    @Override
    public List<Servicomodel> listarServicos() {
        List<Servicomodel> listaServicos = new ArrayList<>();
        String sql = "SELECT * FROM servico";
        try (
                Connection conn = ConexaoBD.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idServico = rs.getInt("idServico");
                String nomeServico = rs.getString("nomeServico");
                String descricaoServico = rs.getString("descricaoServico");
                Double preco = rs.getDouble("preco");
                Servicomodel servico = new Servicomodel(idServico, nomeServico, descricaoServico, preco);
                listaServicos.add(servico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaServicos;
    }
}

