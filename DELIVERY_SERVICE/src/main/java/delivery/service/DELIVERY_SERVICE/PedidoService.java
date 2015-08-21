package delivery.service.DELIVERY_SERVICE;

import delivery.api.mapper.PedidoImpl;
import delivery.model.Pedido;


public class PedidoService {
	final PedidoImpl pedidoImpl = new PedidoImpl();;
	
	public void cadastrarPedidoService(Pedido pedido){
		pedidoImpl.cadastrarPedidoDAO(pedido);
	}
	
	public void atualizarItemService(Pedido pedido){
		
	}
	
	public void enviarMensagemPedidoAceito(String mensagem){
		
	}
	
	public void enviarMensagemPedidoRejeitado(String mensagem){
		
	}
}
