package delivery.service.DELIVERY_SERVICE;

import java.util.ArrayList;
import java.util.List;

import delivery.api.mapper.ProdutoImpl;
import delivery.model.Empresa;
import delivery.model.Produto;


public class ProdutoService {
	
	private static ProdutoImpl produtoImpl;
	
	public void cadastrarProdutoService(Produto produto){
		produtoImpl = new ProdutoImpl();
		produtoImpl.cadastrarProdutoDAO(produto);
	}
	
	public void atualizarProdutoService(Produto produto){
		System.out.println("1- deu bom");
		produtoImpl = new ProdutoImpl();
		produtoImpl.atualizarProdutoDAO(produto);
	}
	
	public List<Produto> getProdutosService(Empresa empresa){
		produtoImpl = new ProdutoImpl();
		List<Produto> produtos = produtoImpl.getProdutosDAO(empresa);
		return produtos;
	}
	
}
