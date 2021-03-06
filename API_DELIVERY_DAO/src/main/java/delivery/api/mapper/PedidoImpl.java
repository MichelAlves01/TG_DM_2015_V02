package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.PedidoDAO;
import delivery.model.ItemPedido;
import delivery.model.ItemProduto;
import delivery.model.Pedido;
import delivery.model.Produto;
import delivery.model.UsuarioMob;
/**
 * Implenta a interface PedidoDAO
 * @author Michel
 *
 */
public class PedidoImpl {

	public void cadastrarPedidoDAO(final Pedido pedido){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final PedidoDAO pedidoDao = session.getMapper(PedidoDAO.class);
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
	
	public void atualizarStatusPedidoDAO(final Pedido pedido){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final PedidoDAO pedidoDao = session.getMapper(PedidoDAO.class);
		pedidoDao.atualizarStatusPedidoDAO(pedido);
		session.commit();
		session.close();
	}
	
	public Pedido getPedidoDAO(final String idPedido){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final PedidoDAO pedidoDao = session.getMapper(PedidoDAO.class);
		final Pedido pedido = pedidoDao.getPedidoDAO(idPedido);
		session.close();
		return pedido;
	}
	
	public List<Pedido> getPedidosDAO(final String cpfCnpj){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final PedidoDAO pedidoDao = session.getMapper(PedidoDAO.class);
		final List<Pedido> pedidos = pedidoDao.getPedidosDAO(cpfCnpj);
		ItensPedidoImpl itemPedidoImpl = new ItensPedidoImpl();
		final ProdutoImpl produtoImpl = new ProdutoImpl();
		final UsuariosMobImpl usuarioMobImpl = new UsuariosMobImpl();
		for(Pedido pedido : pedidos){
			final List<ItemPedido> itens = itemPedidoImpl.getItensPedido(pedido.getId());
				for(ItemPedido item : itens){
					final Produto produto = produtoImpl.getProdutoDAO(item.getProduto().getId());
					item.setProduto(produto);
				}
			pedido.setItensPedido(itens);
			UsuarioMob usuario = new UsuarioMob();
			usuario = usuarioMobImpl.getUsuarioMobDAO(pedido.getUsuariosMob().getEmail());
			pedido.setUsuariosMob(usuario);
		}
		session.close();
		return pedidos;
	}
	
	public List<Pedido> getPedidosPorUsuario(final String email){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final PedidoDAO pedidoDao = session.getMapper(PedidoDAO.class);
		final List<Pedido> pedidos = pedidoDao.getPedidosPorEmail(email);
		session.commit();
		session.close();
		return pedidos;
	}
	
}
