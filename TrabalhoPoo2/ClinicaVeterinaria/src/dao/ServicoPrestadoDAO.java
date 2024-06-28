
package dao;

import java.util.List;

import model.ServicoPrestadoModel;

import java.util.List;

public interface ServicoPrestadoDAO {
    void adicionarServicoPrestado(ServicoPrestadoModel servicoPrestado);
    void atualizarServicoPrestado(ServicoPrestadoModel servicoPrestado);
    void removerServicoPrestado(int idServicoPrestado) ;
    ServicoPrestadoModel buscarServicoPrestadoPorId(int idServicoPrestado);
    List<ServicoPrestadoModel> listarTodosServicosPrestados();
}
