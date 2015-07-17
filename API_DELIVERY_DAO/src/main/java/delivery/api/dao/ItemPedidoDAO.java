package delivery.api.dao;

import delivery.model.ItensPedido;

public interface ItemPedidoDAO {
	
	void cadastrarItemPedidoDAO(ItensPedido itemPedido);
	
	void excluirItemPedido(ItensPedido itemPedido);
	
	ItensPedido getItensPedido(int idPedido);
	
}
