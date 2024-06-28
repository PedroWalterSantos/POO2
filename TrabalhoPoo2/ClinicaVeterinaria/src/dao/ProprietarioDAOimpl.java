package dao;

import dao.ProprietarioDAO;
import model.Proprietariomodel;
import util.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioDAOimpl implements ProprietarioDAO {

	 @Override
	    public void adicionarProprietario(Proprietariomodel proprietario) {
	        String sql = "INSERT INTO Proprietario (nomeProprietario, email, telefone, endereco) VALUES (?, ?, ?, ?)";
	        try (Connection conn = ConexaoBD.conectar(); PreparedStatement pst = conn.prepareStatement(sql)) {
	            pst.setString(1, proprietario.getNomeProprietario());
	            pst.setString(2, proprietario.getEmail());
	            pst.setString(3, proprietario.getTelefone());
	            pst.setString(4, proprietario.getEndereco());
	            pst.executeUpdate();
	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao adicionar proprietário", e);
	        }
	    }

    @Override
    public void atualizarProprietario(Proprietariomodel proprietario) {
        String sql = "UPDATE Proprietario SET nomeProprietario = ?, email = ?, telefone = ?, endereco = ? WHERE idProprietario = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, proprietario.getNomeProprietario());
            pst.setString(2, proprietario.getEmail());
            pst.setString(3, proprietario.getTelefone());
            pst.setString(4, proprietario.getEndereco());
            pst.setInt(5, proprietario.getIdProprietario());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar proprietário", e);
        }
    }

    @Override
    public void deletarProprietario(int id) {
        String sql = "DELETE FROM Proprietario WHERE idProprietario = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar proprietário", e);
        }
    }

    @Override
    public Proprietariomodel obterProprietarioPorId(int id) {
        String sql = "SELECT * FROM Proprietario WHERE idProprietario = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Proprietariomodel(
                            rs.getString("nomeProprietario"),
                            rs.getString("email"),
                            rs.getString("telefone"),
                            rs.getString("endereco")
                           
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter proprietário por ID", e);
        }

        return null;
    }

    @Override
    public List<Proprietariomodel> listarProprietarios(String filtro) {
        List<Proprietariomodel> proprietarios = new ArrayList<>();
        String sql = "SELECT * FROM Proprietario WHERE nomeProprietario LIKE ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, "%" + filtro + "%");

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Proprietariomodel proprietario = new Proprietariomodel( 
                    		rs.getInt("idProprietario"),
                    		rs.getString("nomeProprietario"),
                    		rs.getString("email"),
                    		rs.getString("telefone"),
                            rs.getString("endereco")
);

                    proprietarios.add(proprietario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar proprietários", e);
        }

        return proprietarios;
    }
}
