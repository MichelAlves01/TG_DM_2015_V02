package hello;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import scala.annotation.meta.getter;
import delivery.api.mapper.UserImpl;
import delivery.model.User;
import delivery.model.User;
import delivery.model.UserRole;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	
	UserImpl userImpl = new UserImpl();
	
	@Override
	public UserDetails loadUserByUsername(final String username){
		User user = new User();
		user = userImpl.getUserDAO(username);
		if(user != null){
			List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
			return buildUserForAthentication(user , authorities);
		}
		
		return null;
	}
	
	private User buildUserForAthentication(User user , List<GrantedAuthority> authorities){
		return new User(user.getUsername(), user.getPassword(), 
				user.isEnabled(), true, true, true, authorities);
	
	}
	
	private List<GrantedAuthority> buildUserAuthority(UserRole userRole){
		
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		

			setAuths.add(new SimpleGrantedAuthority(userRole.getUserRole()));

		
		List<GrantedAuthority> results = new ArrayList<GrantedAuthority>(setAuths);
		
		return results;
	}
	
}
