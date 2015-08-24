package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.PedidoDAO;
import delivery.model.ItemPedido;
import delivery.model.ItemProduto;
import delivery.model.Pedido;
import delivery.model.Produto;

public class PedidoImpl {

	public void cadastrarPedidoDAO(Pedido pedido){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		PedidoDAO pedidoDao = session.getMapper(PedidoDAO.class);
		pedidoDao.cadastrarPedidoDAO(pedido);
		final ItensPedidoImpl itemPedidoImpl = new ItensPedidoImpl();
		final List<ItemPedido> itensPedido = pedido.getItensPedido();
		for(ItemPedido itens : itensPedido){
			itens.setPedido(pedido);
			itemPedidoImpl.cadastrarItemPedidoDAO(itens);
		}
		
		
		session.commit();
		session.close();
	}
	
	public void atualizarStatusPedidoDAO(Pedido pedido){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		PedidoDAO pedidoDao = session.getMapper(PedidoDAO.class);
		pedidoDao.atualizarStatusPedidoDAO(pedido);
		session.commit();
		session.close();
	}
	
	public Pedido getPedidoDAO(String idPedido){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		PedidoDAO pedidoDao = session.getMapper(PedidoDAO.class);
		Pedido pedido = pedidoDao.getPedidoDAO(idPedido);
		session.close();
		return pedido;
	}
	
	public List<Pedido> getPedidosDAO(String cpfCnpj){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		PedidoDAO pedidoDao = session.getMapper(PedidoDAO.class);
		List<Pedido> pedidos = pedidoDao.getPedidosDAO(cpfCnpj);
		ItensPedidoImpl itemPedidoImpl = new ItensPedidoImpl();
		ProdutoImpl produtoImpl = new ProdutoImpl();
		for(Pedido pedido : pedidos){
			List<ItemPedido> itens = itemPedidoImpl.getItensPedido(pedido.getId());
				for(ItemPedido item : itens){
					Produto produto = produtoImpl.getProdutoDAO(item.getProduto().getId());
					item.setProduto(produto);
				}
			pedido.setItensPedido(itens);
		}
		session.close();
		return pedidos;
	}
	
}
