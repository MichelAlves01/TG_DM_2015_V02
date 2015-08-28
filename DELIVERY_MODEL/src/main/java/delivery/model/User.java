package delivery.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
	
	private String username;
	private String password;
	private Empresa empresa;
	private UserRole userRole;
	private boolean enabled;
	private int status; 
	
	
	public User(){
	}
	
	public User(String username, String password, Empresa empresa,
			UserRole userRole, int status) {
		super();
		this.username = username;
		this.password = password;
		this.empresa = empresa;
		this.userRole = userRole;
		this.status = status;
	}
	
	public User(String username, String password, 
			boolean enabled, UserRole userRole) {
			this.username = username;
			this.password = password;
			this.setEnabled(enabled);
			this.userRole = userRole;
	}
	

	public User(String username2, String password2, boolean enabled2,
			boolean b, boolean c, boolean d, List<GrantedAuthority> authorities) {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
