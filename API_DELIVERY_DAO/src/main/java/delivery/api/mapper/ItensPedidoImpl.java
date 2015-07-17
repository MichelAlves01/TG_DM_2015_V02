package delivery.api.mapper;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.ItemPedidoDAO;
import delivery.model.ItensPedido;

public class ItensPedidoImpl {
	
	public void cadastrarItemPedidoDAO(ItensPedido itemPedido){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemPedidoDAO itensPedidoDao = session.getMapper(ItemPedidoDAO.class);
		itensPedidoDao.cadastrarItemPedidoDAO(itemPedido);
		session.commit();
		session.close();
	}
	
	public void excluirItemPedido(ItensPedido itemPedido){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemPedidoDAO itensPedidoDao = session.getMapper(ItemPedidoDAO.class);
		itensPedidoDao.excluirItemPedido(itemPedido);
		session.commit();
		session.close();
	}
	
	public ItensPedido getItensPedido(int idPedido){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemPedidoDAO itensPedidoDao = session.getMapper(ItemPedidoDAO.class);
		ItensPedido itensPedido = itensPedidoDao.getItensPedido(idPedido);
		session.close();
		return itensPedido;
	}
	
}
