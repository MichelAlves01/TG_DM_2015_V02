package delivery.api.dao;

import java.util.List;

import delivery.model.Pedido;
/**
 * 
 * @author Michel
 *
 */
public interface PedidoDAO {
	
	void cadastrarPedidoDAO(Pedido pedido);
	
	void atualizarStatusPedidoDAO(Pedido pedido);
	
	Pedido getPedidoDAO(String idPedido);
	
	List<Pedido> getPedidosDAO(String cpfCnpj);
	
	List<Pedido> getPedidosPorEmail(String email);
	
}
