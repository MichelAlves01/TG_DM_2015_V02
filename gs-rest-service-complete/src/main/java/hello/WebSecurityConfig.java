																					package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
			.antMatchers(HttpMethod.GET , "/login").permitAll()
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
			.antMatchers("**/view-principal/**").permitAll()
			.antMatchers("/getEmpresaCadastro").permitAll()
			.antMatchers("/getEmpresaController").permitAll()
			.antMatchers("/cadastrarEmpresaController").permitAll()
			.antMatchers("/atualizarEmpresaController").permitAll()
			.antMatchers("/excluirEmpresaController").permitAll()
			.antMatchers("/definirRaioController").permitAll()
			.antMatchers("/getCidades").permitAll()
			.antMatchers("/getEstados").permitAll()
			.antMatchers("/cadastrarItemController").permitAll()
			.antMatchers("/getItensController").permitAll()
			.antMatchers("/excluirItemController").permitAll()
			.antMatchers("/atualizarItemController").permitAll()
			.antMatchers("/cadastrarItemProdutoController").permitAll()
			.antMatchers("/getItensProdutoController").permitAll()
			.antMatchers("/excluirItemProdutoController").permitAll()
			.antMatchers(HttpMethod.GET, "/getPedidoController").permitAll()
			.antMatchers("/atualizarStatusPedidoController").permitAll()
			.antMatchers("/cadastrarProdutoController").permitAll()
			.antMatchers("/getProdutosController").permitAll()
			.antMatchers("/excluirProdutoController").permitAll()
			.antMatchers("/atualizarProdutoController").permitAll()
			.antMatchers("/cadastrarUsuarioMobileController").permitAll()
			.antMatchers("/cadastrarPedidoController").permitAll()
			.antMatchers("/getEmpresasPorLatLong").permitAll()
			.and()
			.logout();
		if ("true".equals(System.getProperty("httpsOnly"))) {
		      http.requiresChannel().anyRequest().requiresSecure();
		  } 
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService());
	}
	

}
