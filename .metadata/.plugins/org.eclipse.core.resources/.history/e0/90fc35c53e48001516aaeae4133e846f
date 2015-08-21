package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.PedidoDAO;
import delivery.model.Pedido;

public class PedidoImpl {

	public void cadastrarPedidoDAO(Pedido pedido){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		PedidoDAO pedidoDao = session.getMapper(PedidoDAO.class);
		pedidoDao.cadastrarPedidoDAO(pedido);
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
	
	public Pedido getPedidoDAO(int idPedido){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		PedidoDAO pedidoDao = session.getMapper(PedidoDAO.class);
		Pedido pedido = pedidoDao.getPedidoDAO(idPedido);
		session.close();
		return pedido;
	}
	
	public List<Pedido> getPedidosDAO(){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		PedidoDAO pedidoDao = session.getMapper(PedidoDAO.class);
		List<Pedido> pedidos = pedidoDao.getPedidosDAO();
		session.close();
		return pedidos;
	}
	
}
