package delivery.api.dao;

import java.util.List;

import delivery.model.Pedido;

public interface PedidoDAO {
	
	void cadastrarPedidoDAO(Pedido pedido);
	
	void atualizarStatusPedidoDAO(Pedido pedido);
	
	Pedido getPedidoDAO(int idPedido);
	
	List<Pedido> getPedidosDAO(String cpfCnpj);
	
}
