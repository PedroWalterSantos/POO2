package dao;

import model.Cliente;
import java.util.List;

public interface ClienteDAO {
    void adicionarCliente(Cliente cliente);
    Cliente obterClientePorCpf(String cpf);
    List<Cliente> listarClientes();
    void atualizarCliente(Cliente cliente);
    void deletarCliente(String cpf);
}
