package delivery.api.dao;

import java.util.List;

import delivery.model.ItemProduto;
import delivery.model.Produto;

public interface ItemProdutoDAO {

	public void cadastrarItemProdutoDAO(ItemProduto itemProduto);
	
	public void excluirItemProduto(ItemProduto itemProduto);
	
	public List<ItemProduto> getItensProdutoDAO(ItemProduto itemProduto);
	
	public List<ItemProduto> getItensProdutoPorProduto(Produto produto);
}
