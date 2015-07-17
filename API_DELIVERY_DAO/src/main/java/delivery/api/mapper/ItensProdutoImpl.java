package delivery.api.mapper;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.ItemProdutoDAO;
import delivery.model.ItensProduto;

public class ItensProdutoImpl {
	
	public void cadastrarItemProdutoDAO(ItensProduto itensProduto){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemProdutoDAO itemProdutoDao = session.getMapper(ItemProdutoDAO.class);
		itemProdutoDao.cadastrarItemProdutoDAO(itensProduto);
		session.commit();
		session.close();
	}
	
	public void excluirItemProduto(ItensProduto itensProduto){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemProdutoDAO itemProdutoDao = session.getMapper(ItemProdutoDAO.class);
		itemProdutoDao.excluirItemProduto(itensProduto);
		session.commit();
		session.close();
	}
	
}
