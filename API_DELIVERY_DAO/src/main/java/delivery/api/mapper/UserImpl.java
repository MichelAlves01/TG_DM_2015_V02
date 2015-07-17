package delivery.api.mapper;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.UserDAO;
import delivery.model.Users;

public class UserImpl {

	public void cadastrarUsuarioDAO(Users user){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		UserDAO userDao = session.getMapper(UserDAO.class);
		userDao.cadastrarUsuarioDAO(user);
		session.commit();
		session.close();
	}
	
	public void atualizarUserDAO(Users user){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		UserDAO userDao = session.getMapper(UserDAO.class);
		userDao.atualizarUserDAO(user);
		session.commit();
		session.close();
	}
	
	public void excluirUserDAO(String username){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		UserDAO userDao = session.getMapper(UserDAO.class);
		userDao.excluirUserDAO(username);
		session.commit();
		session.close();
	}
	
	public Users getUserDAO(String username){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		UserDAO userDao = session.getMapper(UserDAO.class);
		Users user = userDao.getUserDAO(username);
		session.close();
		return user;
	}
	
}
