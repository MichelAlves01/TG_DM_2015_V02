package delivery.api.dao;

import delivery.model.ItensProduto;

public interface ItemProdutoDAO {

	public void cadastrarItemProdutoDAO(ItensProduto itensProduto);
	
	public void excluirItemProduto(ItensProduto itensProduto);
	
}
