package dao;

import java.util.List;
import model.Proprietariomodel;

public interface ProprietarioDAO {
    void adicionarProprietario(Proprietariomodel proprietario);
    void atualizarProprietario(Proprietariomodel proprietario);
    void deletarProprietario(int id);
    Proprietariomodel obterProprietarioPorId(int id);
    List<Proprietariomodel> listarProprietarios(String filtro);
}
