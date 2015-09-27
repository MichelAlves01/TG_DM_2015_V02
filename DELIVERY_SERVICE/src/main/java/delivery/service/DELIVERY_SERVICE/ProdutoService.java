package delivery.service.DELIVERY_SERVICE;

import java.util.List;

import delivery.api.mapper.ProdutoImpl;
import delivery.model.Empresa;
import delivery.model.Produto;


public class ProdutoService {
	
	private static ProdutoImpl produtoImpl;
	
	public void cadastrarProdutoService(final Produto produto){
		produtoImpl = new ProdutoImpl();
		produtoImpl.cadastrarProdutoDAO(produto);
	}
	
	public void atualizarProdutoService(final Produto produto){
		produtoImpl = new ProdutoImpl();
		produtoImpl.atualizarProdutoDAO(produto);
	}
	
	public List<Produto> getProdutosService(final Empresa empresa){
		produtoImpl = new ProdutoImpl();
		final List<Produto> produtos = produtoImpl.getProdutosDAO(empresa);
		return produtos;
	}
	
}
