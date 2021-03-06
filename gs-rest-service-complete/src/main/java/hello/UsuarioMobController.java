package hello;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import delivery.api.mapper.UsuariosMobImpl;
import delivery.model.UsuarioMob;
/**
 * Spring controller usuario mobile
 * @author Michel
 *
 */
@Controller
@RestController
public class UsuarioMobController {
	
	private static UsuariosMobImpl usuarioMobImpl;
	
	private UsuarioMob usuarioMob;
	
	
	@RequestMapping(value="/cadastrarUsuarioMobileController" , method=RequestMethod.GET)
	public UsuarioMob cadastrarUsuarioMob(	@RequestParam(value="nomeUsuario")final String nome,
											@RequestParam(value="email") final String email,
											@RequestParam(value="senha") final String senha){
		usuarioMob = new UsuarioMob();
		usuarioMob.setNome(nome);
		usuarioMob.setEmail(email);
		usuarioMob.setSenha(senha);
		
		usuarioMobImpl = new UsuariosMobImpl();
		usuarioMobImpl.cadastrarUsuarioMobDAO(usuarioMob);
		return usuarioMob;
		
	}
	
	@RequestMapping(value="/atualizarUsuarioMobileController" , method=RequestMethod.GET)
	public UsuarioMob atualizarUsuarioMob(	@RequestParam(value="nomeUsuario") final String nome,
											@RequestParam(value="email") final String email,
											@RequestParam(value="senha") final String senha){
		
		usuarioMobImpl = new UsuariosMobImpl();
		UsuarioMob usuarioMob = usuarioMobImpl.getUsuarioMobDAO(email);
		usuarioMob.setNome(nome);
		usuarioMob.setEmail(email);
		usuarioMob.setSenha(senha);
		
		usuarioMobImpl.atualizarUsuarioMobDAO(usuarioMob);
		return usuarioMob;
		
	}
}
