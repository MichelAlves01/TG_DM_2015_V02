package delivery.service.DELIVERY_SERVICE;

import java.util.ArrayList;
import java.util.List;

import delivery.api.mapper.ProdutoImpl;
import delivery.model.Produto;


public class ProdutoService {
	
	private static ProdutoImpl produtoImpl;
	
	public void cadastrarProdutoService(Produto produto){
		produtoImpl = new ProdutoImpl();
		produtoImpl.cadastrarProdutoDAO(produto);
	}
	
	public void atualizarProdutoService(){
		
	}
	
	public List<Produto> getProdutosService(){
		final List<Produto> produtos = new ArrayList<Produto>();
		return produtos;
	}
	
}
