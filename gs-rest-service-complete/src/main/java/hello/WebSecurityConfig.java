																					package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/iniciaCadastroEmpresa").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("**/js/**").permitAll()
			.antMatchers("**/css/**").permitAll()
			.antMatchers("**/img/**").permitAll()
			.antMatchers("**/images/**").permitAll()
			.antMatchers("**/angular/**").permitAll()
			.antMatchers("**/angular/controller/**").permitAll()
			.antMatchers("**/angular/service/**").permitAll()
			.antMatchers("**/angular/directives/**").permitAll()
			.antMatchers("**/view-login/**").permitAll()
			.antMatchers("**/view-cadastro/**").permitAll()
			.antMatchers("**/view-principal/**").hasAnyRole("ADMIN")
			.antMatchers("**/getEmpresaCadastro").hasAnyRole("ADMIN")
			.antMatchers("**/cadastrarEmpresaController").hasAnyRole("ADMIN")
			.antMatchers("**/atualizarEmpresaController").hasAnyRole("ADMIN")
			.antMatchers("**/excluirEmpresaController").hasAnyRole("ADMIN")
			.antMatchers("**/definirRaioController").hasAnyRole("ADMIN")
			.antMatchers("**/getCidades").hasAnyRole("ADMIN")
			.antMatchers("**/getEstados").hasAnyRole("ADMIN")
			.antMatchers("**/cadastrarItemController").hasAnyRole("ADMIN")
			.antMatchers("**/getItensController").hasAnyRole("ADMIN")
			.antMatchers("**/excluirItemController").hasAnyRole("ADMIN")
			.antMatchers("**/atualizarItemController").hasAnyRole("ADMIN")
			.antMatchers("**/cadastrarItemProdutoController").hasAnyRole("ADMIN")
			.antMatchers("**/getItensProdutoController").hasAnyRole("ADMIN")
			.antMatchers("**/excluirItemProdutoController").hasAnyRole("ADMIN")
			.antMatchers("**/getPedidoController").hasAnyRole("ADMIN")
			.antMatchers("**/atualizarStatusPedidoController").hasAnyRole("ADMIN")
			.antMatchers("**/cadastrarProdutoController").hasAnyRole("ADMIN")
			.antMatchers("**/getProdutosController").hasAnyRole("ADMIN")
			.antMatchers("**/excluirProdutoController").hasAnyRole("ADMIN")
			.antMatchers("**/atualizarProdutoController").hasAnyRole("ADMIN")
			.antMatchers("**/cadastrarUsuarioMobileController").permitAll()
			.antMatchers("**/cadastrarPedidoController").permitAll()
			.antMatchers("**/getEmpresasPorLatLong").permitAll()
			.and()
			.httpBasic()
			.and()
			.formLogin()
			.loginPage("/")
			.permitAll();
		
		if ("true".equals(System.getProperty("httpsOnly"))) {
		      http.requiresChannel().anyRequest().requiresSecure();
		  } 
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService());
	}
	

}
