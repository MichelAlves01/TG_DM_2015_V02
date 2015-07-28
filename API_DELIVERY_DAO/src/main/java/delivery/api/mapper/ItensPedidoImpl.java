package delivery.api.mapper;

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
	
	public ItemPedido getItensPedido(int idPedido){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemPedidoDAO itensPedidoDao = session.getMapper(ItemPedidoDAO.class);
		ItemPedido itensPedido = itensPedidoDao.getItensPedido(idPedido);
		session.close();
		return itensPedido;
	}
	
}
