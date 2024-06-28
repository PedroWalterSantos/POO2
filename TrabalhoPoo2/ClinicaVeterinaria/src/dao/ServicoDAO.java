package dao;

import java.util.List;
import model.Servicomodel;

public interface ServicoDAO {
    boolean adicionarServico(Servicomodel servico);
    boolean atualizarServico(int idServico, Servicomodel servico);
    boolean removerServico(int idServico);
    Servicomodel buscarServicoPorId(int idServico);
    List<Servicomodel> listarServicos();
   }
    
   
    
    
    
   
