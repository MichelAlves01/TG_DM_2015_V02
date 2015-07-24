package delivery.api.mapper;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.ItemProdutoDAO;
import delivery.model.ItemProduto;

public class ItemProdutoImpl {
	
	public void cadastrarItemProdutoDAO(ItemProduto itemProduto){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemProdutoDAO itemProdutoDao = session.getMapper(ItemProdutoDAO.class);
		itemProdutoDao.cadastrarItemProdutoDAO(itemProduto);
		session.commit();
		session.close();
	}
	
	public void excluirItemProduto(ItemProduto itemProduto){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemProdutoDAO itemProdutoDao = session.getMapper(ItemProdutoDAO.class);
		itemProdutoDao.excluirItemProduto(itemProduto);
		session.commit();
		session.close();
	}
	
}
