package delivery.service.DELIVERY_SERVICE;

import delivery.api.mapper.PedidoImpl;
import delivery.model.Pedido;


public class PedidoService {
	final PedidoImpl pedidoImpl = new PedidoImpl();;
	
	public void cadastrarPedidoService(final Pedido pedido){
		pedidoImpl.cadastrarPedidoDAO(pedido);
	}
	
}
