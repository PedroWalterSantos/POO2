package dao;
import java.util.List;
import java.util.Optional;

import model.Animalmodel;

public interface AnimalDAO {
	void adicionarAnimal(Animalmodel animal); // Create
    List<Animalmodel> listarAnimais(); // Read
    Optional<Animalmodel> buscarPorNome(String nome); // Read
    boolean atualizarAnimal(String nome, Animalmodel animalAtualizado); // Update
    boolean removerAnimal(String nome); // Delete

}
