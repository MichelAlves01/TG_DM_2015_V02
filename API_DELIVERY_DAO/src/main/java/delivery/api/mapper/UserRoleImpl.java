package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.UserRoleDAO;
import delivery.model.UserRole;

public class UserRoleImpl {
	
	public void cadastrarUserRoleImpl(UserRole userRole){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		UserRoleDAO userRoleDao = session.getMapper(UserRoleDAO.class);
		userRoleDao.cadastrarUserRoleDao(userRole);
		session.commit();
		session.close();
	}
	
	public UserRole getUserRoleImpl(String username){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		UserRoleDAO userRoleDao = session.getMapper(UserRoleDAO.class);
		UserRole userRole = userRoleDao.getUserRoleDao(username);
		session.commit();
		session.close();
		return userRole; 
	}
}
