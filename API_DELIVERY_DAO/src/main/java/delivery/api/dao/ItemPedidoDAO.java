package delivery.api.dao;

import java.util.List;

import delivery.model.ItemPedido;

/**
 * 
 * @author Michel
 *
 */
public interface ItemPedidoDAO {
	
	void cadastrarItemPedidoDAO(ItemPedido itemPedido);
	
	void excluirItemPedido(ItemPedido itemPedido);
	
	List<ItemPedido> getItensPedido(String idPedido);
	
}
