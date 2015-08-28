package hello;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import delivery.api.mapper.EmpresaImpl;
import delivery.api.mapper.UserImpl;
import delivery.model.Empresa;
import delivery.model.User;

@RestController
public class LoginController {

	  @RequestMapping(value="/", method=RequestMethod.GET)
	    public ModelAndView getTerms() {
	        return new ModelAndView(new RedirectView("index.html", true));
	    }
	  
	  @RequestMapping(value="/login", method=RequestMethod.GET)
	  public Empresa login(@RequestParam(value="username") String username,
			  			@RequestParam(value="password") String password){
		  MyUserDetailsService myUserDetailService = new MyUserDetailsService();
		  SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken(username, password, AuthorityUtils.createAuthorityList("ADMIN")));
		  myUserDetailService.loadUserByUsername(username);
		  System.out.println("Deu bom login");
		  
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	      UserImpl userImpl = new UserImpl();
	      User user = new User();
	      if(!username.equals(null) && !password.equals(null)){
	    	  user = userImpl.getUserDAO(username);
	      } else {
	    	  return null;
	      } 
	      EmpresaImpl empresaImpl = new EmpresaImpl();
	      Empresa empresa = new Empresa();
	      if(user != null && user.getPassword().equals(password)){
	    	  empresa = empresaImpl.getEmpresaDAO(user.getEmpresa().getCpfCnpj());
	      } else {
	    	  return null;
	      }
	      
	      System.out.println("user logged: " + name);
	      return empresa;
	  }
}
