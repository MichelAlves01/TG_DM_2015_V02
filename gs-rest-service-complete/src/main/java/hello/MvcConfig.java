package hello;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/").setViewName("index");
		registry.addViewController("**/login").setViewName("index");
		registry.addViewController("**/iniciaCadastroEmpresa").setViewName("index");
		registry.addViewController("**/getEmpresaCadastro").setViewName("index");
		registry.addViewController("**/cadastrarEmpresaController").setViewName("index");
		registry.addViewController("**/atualizarEmpresaController").setViewName("index");
		registry.addViewController("**/excluirEmpresaController").setViewName("index");
		registry.addViewController("**/definirRaioController").setViewName("index");
		registry.addViewController("**/getEmpresasPorLatLong").setViewName("index");
		registry.addViewController("**/getCidades").setViewName("index");
		registry.addViewController("**/getEstados").setViewName("index");
		registry.addViewController("**/cadastrarItemController").setViewName("index");
		registry.addViewController("**/getItensController").setViewName("index");
		registry.addViewController("**/excluirItemController").setViewName("index");
		registry.addViewController("**/atualizarItemController").setViewName("index");
		registry.addViewController("**/cadastrarItemProdutoController").setViewName("index");
		registry.addViewController("**/getItensProdutoController").setViewName("index");
		registry.addViewController("**/excluirItemProdutoController").setViewName("index");
		registry.addViewController("**/getPedidoController").setViewName("index");
		registry.addViewController("**/cadastrarPedidoController").setViewName("index");
		registry.addViewController("**/atualizarStatusPedidoController").setViewName("index");
		registry.addViewController("**/cadastrarProdutoController").setViewName("index");
		registry.addViewController("**/getProdutosController").setViewName("index");
		registry.addViewController("**/excluirProdutoController").setViewName("index");
		registry.addViewController("**/atualizarProdutoController").setViewName("index");
		registry.addViewController("**/cadastrarUsuarioMobileController").setViewName("index");
	}
}
