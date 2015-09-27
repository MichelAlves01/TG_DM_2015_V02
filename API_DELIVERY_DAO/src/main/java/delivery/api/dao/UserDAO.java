package delivery.api.dao;

import delivery.model.User;
/**
 * 
 * @author Michel
 *
 */
public interface UserDAO {
	
	void cadastrarUsuarioDAO(User user);
	
	void atualizarUserDAO(User user);
	
	void excluirUserDAO(String username);
	
	User getUserDAO(String username);
	
}
