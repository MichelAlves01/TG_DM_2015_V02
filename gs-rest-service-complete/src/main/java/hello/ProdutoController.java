package hello;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import delivery.api.mapper.EmpresaImpl;
import delivery.api.mapper.ProdutoImpl;
import delivery.model.Empresa;
import delivery.model.Produto;
import delivery.service.DELIVERY_SERVICE.ProdutoService;

@RestController
public class ProdutoController {
	
	private static ProdutoService produtoService;
	
	private Produto produto;
	
	private static EmpresaImpl empresaImpl;
	
	private Empresa empresa;
	
	private static ProdutoImpl produtoImpl;
	
	@RequestMapping(value="/cadastrarProdutoController",method=RequestMethod.POST)
	public Produto cadastrarProduto(	@RequestParam(value="descricao") String descricao,
									@RequestParam(value="preco") double preco,
									@RequestParam(value="cpfCnpj") String cpfCnpj){
		
		empresaImpl = new EmpresaImpl();
		empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		
		produto = new Produto();
		produto.setDescricao(descricao);
		produto.setPreco(preco);
		produto.setEmpresa(empresa);
		
		produtoService = new ProdutoService();
		produtoService.cadastrarProdutoService(produto);
		
		return produto;
	}
	
	@RequestMapping(value="/getProdutosController" , method=RequestMethod.GET)
	public List<Produto> getProdutos(@RequestParam(value="cpfCnpj") String cpfCnpj){
		produtoService = new ProdutoService();
		empresaImpl = new EmpresaImpl();
		Empresa empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		List<Produto> produtos = produtoService.getProdutosService(empresa);
		return produtos;
	}
	
	@RequestMapping(value="/excluirProdutoController" , method= RequestMethod.GET)
	public List<Produto> excluirProduto(@RequestParam(value="id") int id,
										@RequestParam(value="cpfCnpj") String cpfCnpj) {
		produtoImpl = new ProdutoImpl();
		produtoImpl.excluirProdutoDAO(id);
		produtoService = new ProdutoService();
		empresaImpl = new EmpresaImpl();
		Empresa empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		List<Produto> produtos = produtoService.getProdutosService(empresa);
		return produtos;
	}
	
	@RequestMapping(value="/atualizarProdutoController",method=RequestMethod.GET)
	public void atualizarProduto(	@RequestParam(value="id") int id,
									@RequestParam(value="descricao") String descricao,
									@RequestParam(value="preco") double preco,
									@RequestParam(value="cpfCnpj") String cpfCnpj){
		
		empresaImpl = new EmpresaImpl();
		empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		
		produto = new Produto();
		produto.setId(id);
		produto.setDescricao(descricao);
		produto.setPreco(preco);
		produto.setEmpresa(empresa);
		
		produtoService = new ProdutoService();
		produtoService.atualizarProdutoService(produto);
	}
	
	
}
