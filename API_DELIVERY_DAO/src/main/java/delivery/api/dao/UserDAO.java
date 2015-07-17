package delivery.api.dao;

import delivery.model.Users;

public interface UserDAO {
	
	void cadastrarUsuarioDAO(Users user);
	
	void atualizarUserDAO(Users user);
	
	void excluirUserDAO(String username);
	
	Users getUserDAO(String username);
	
}
