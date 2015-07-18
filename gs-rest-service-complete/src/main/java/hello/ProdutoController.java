package hello;

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
	
	@RequestMapping(value="/cadastrarProdutoController",method=RequestMethod.POST)
	public void cadastrarProduto(	@RequestParam(value="descricao") String descricao,
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
	}
	
	
}
