package delivery.api.dao;

import delivery.model.ItemPedido;

public interface ItemPedidoDAO {
	
	void cadastrarItemPedidoDAO(ItemPedido itemPedido);
	
	void excluirItemPedido(ItemPedido itemPedido);
	
	ItemPedido getItensPedido(int idPedido);
	
}
