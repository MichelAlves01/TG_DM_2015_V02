package delivery.service.DELIVERY_SERVICE;

import delivery.api.mapper.ItemProdutoImpl;
import delivery.model.ItemProduto;

public class ItemProdutoService {
	
	private ItemProdutoImpl itemProdutoImpl;
	
	public ItemProdutoService() {
		itemProdutoImpl = new ItemProdutoImpl();
	}


	public void cadastrarItemProdutoService(ItemProduto itemProduto){
		itemProdutoImpl.cadastrarItemProdutoDAO(itemProduto);
	}
	
}
