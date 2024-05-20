package dao;

import model.Venda;
import java.util.List;

public interface VendaDAO {
    void adicionarVenda(Venda venda);
    Venda obterVendaPorId(int id);
    List<Venda> listarVendas();
}
