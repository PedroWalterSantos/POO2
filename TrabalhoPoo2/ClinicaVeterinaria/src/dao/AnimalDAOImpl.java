package dao;

import dao.AnimalDAO;
import model.Animalmodel;
import util.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimalDAOImpl implements AnimalDAO {

    public void adicionarAnimal(Animalmodel animal) {
        String sql = "INSERT INTO Animal (nomeAnimal, idade, sexo, raca, especie, peso, idProprietario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, animal.getNome());
            pst.setInt(2, animal.getIdade());
            pst.setString(3, animal.getSexo());
            pst.setString(4, animal.getRaca());
            pst.setString(5, animal.getEspecie());
            pst.setDouble(6, animal.getPeso());
            pst.setInt(7, animal.getIdProprietario());

            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar animal", e);
        }
    }

    @Override
    public List<Animalmodel> listarAnimais() {
        List<Animalmodel> animais = new ArrayList<>();
        String sql = "SELECT * FROM Animal";

        try (Connection conn = ConexaoBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Animalmodel animal = new Animalmodel(
                		rs.getInt("idAnimal"),
                		rs.getString("nomeAnimal"),
                        rs.getInt("idade"),
                        rs.getString("sexo"),
                        rs.getString("raca"),
                        rs.getString("especie"),
                        rs.getDouble("peso"),
                        rs.getInt("idProprietario")
                );
                animais.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar animais", e);
        }

        return animais;
    }

    @Override
    public Optional<Animalmodel> buscarPorNome(String nome) {
        String sql = "SELECT * FROM Animal WHERE nomeAnimal = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nome);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Animalmodel animal = new Animalmodel(
                            rs.getString("nomeAnimal"),
                            rs.getInt("idade"),
                            rs.getString("sexo"),
                            rs.getString("raca"),
                            rs.getString("especie"),
                            rs.getDouble("peso"),
                            rs.getInt("idProprietario")
                    );
                    return Optional.of(animal);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar animal por nome", e);
        }

        return Optional.empty();
    }

    @Override
    public boolean atualizarAnimal(String nome, Animalmodel animalAtualizado) {
        String sql = "UPDATE Animal SET idade = ?, sexo = ?, raca = ?, especie = ?, peso = ?, idProprietario = ? WHERE nomeAnimal = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, animalAtualizado.getIdade());
            pst.setString(2, animalAtualizado.getSexo());
            pst.setString(3, animalAtualizado.getRaca());
            pst.setString(4, animalAtualizado.getEspecie());
            pst.setDouble(5, animalAtualizado.getPeso());
            pst.setInt(6, animalAtualizado.getIdProprietario());
            pst.setString(7, nome);

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar animal", e);
        }
    }

    @Override
    public boolean removerAnimal(String nome) {
        String sql = "DELETE FROM Animal WHERE nomeAnimal = ?";

        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, nome);

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover animal", e);
        }
    }
}
