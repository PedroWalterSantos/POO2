package controller;

import dao.ClienteDAO;
import dao.ClienteDAOImpl;
import model.Cliente;

import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAOImpl();
    }

    public void cadastrarCliente(Cliente cliente) {
        clienteDAO.adicionarCliente(cliente);
    }

    public Cliente consultarClientePorCpf(String cpf) {
        return clienteDAO.obterClientePorCpf(cpf);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }

    public void atualizarCliente(Cliente cliente) {
        clienteDAO.atualizarCliente(cliente);
    }

    public void removerCliente(String cpf) {
        clienteDAO.deletarCliente(cpf);
    }
}
