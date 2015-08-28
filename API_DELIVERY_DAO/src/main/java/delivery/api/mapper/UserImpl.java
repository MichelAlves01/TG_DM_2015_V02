package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.UserDAO;
import delivery.model.User;
import delivery.model.UserRole;

public class UserImpl {

	public void cadastrarUsuarioDAO(User user){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		UserDAO userDao = session.getMapper(UserDAO.class);
		userDao.cadastrarUsuarioDAO(user);
		
		UserRole userRole = new UserRole();
		UserRoleImpl userRoleImpl = new UserRoleImpl();
		userRole.setUserRole("ADMIN");
		userRole.setUsername(user.getUsername());
		userRoleImpl.cadastrarUserRoleImpl(userRole);
		
		session.commit();
		session.close();
	}
	
	public void atualizarUserDAO(User user){
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
	
	public User getUserDAO(String username){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		UserDAO userDao = session.getMapper(UserDAO.class);
		User user = userDao.getUserDAO(username);
		UserRoleImpl userRoleImpl = new UserRoleImpl();
		if(user != null){
			UserRole userRole = userRoleImpl.getUserRoleImpl(user.getUsername());
			user.setUserRole(userRole);
		}
		session.close();
		return user;
	}
	
}
