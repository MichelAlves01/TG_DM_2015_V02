package delivery.api.dao;

import java.util.List;

import delivery.model.UserRole;
/**
 * @author Michel
 *
 */
public interface UserRoleDAO {
	
	public UserRole getUserRoleDao(String username);
	
	public void cadastrarUserRoleDao(UserRole userRole);
	
}
