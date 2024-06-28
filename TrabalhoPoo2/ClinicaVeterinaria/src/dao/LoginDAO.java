package dao;

import model.Loginmodel;

public interface LoginDAO {
	void adicionarLogin(Loginmodel Login);
	void excluirLogin(Integer idLogin);
	void atualizarLogin(Integer idLogin);
	boolean Logar(String usuario, String senha);

}
