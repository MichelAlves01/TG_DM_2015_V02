package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.ItemPedidoDAO;
import delivery.model.ItemPedido;

public class ItensPedidoImpl {
	
	public void cadastrarItemPedidoDAO(ItemPedido itemPedido){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemPedidoDAO itensPedidoDao = session.getMapper(ItemPedidoDAO.class);
		itensPedidoDao.cadastrarItemPedidoDAO(itemPedido);
		session.commit();
		session.close();
	}
	
	public void excluirItemPedido(ItemPedido itemPedido){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemPedidoDAO itensPedidoDao = session.getMapper(ItemPedidoDAO.class);
		itensPedidoDao.excluirItemPedido(itemPedido);
		session.commit();
		session.close();
	}
	
	public List<ItemPedido> getItensPedido(String idPedido){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemPedidoDAO itensPedidoDao = session.getMapper(ItemPedidoDAO.class);
		List<ItemPedido> itensPedido = itensPedidoDao.getItensPedido(idPedido);
		session.close();
		return itensPedido;
	}
	
}
