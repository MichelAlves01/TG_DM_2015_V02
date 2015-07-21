package delivery.api.dao;

import java.util.List;

import delivery.model.Empresa;
import delivery.model.Produto;


public interface ProdutoDAO {
	
	void cadastrarProdutoDAO(Produto produto);
	
	void atualizarProdutoDAO(Produto produto);
	
	void excluirProdutoDAO(int idProduto);
	
	Produto getProdutoDAO(int idProduto);
	
	List<Produto> getProdutosDAO(Empresa empresa);
	
}
