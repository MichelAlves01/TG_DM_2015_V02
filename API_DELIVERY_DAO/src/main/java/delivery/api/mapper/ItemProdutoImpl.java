package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.ItemDAO;
import delivery.api.dao.ItemProdutoDAO;
import delivery.api.dao.ProdutoDAO;
import delivery.model.Item;
import delivery.model.ItemProduto;
import delivery.model.Produto;

public class ItemProdutoImpl {
	
	private Item item;
	
	private Produto produto;
	
	private static ItemDAO itemDao;
	
	private static ProdutoDAO produtoDao;
	
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
	
	public List<ItemProduto> getItemProdutoDAO(ItemProduto itemProduto){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemProdutoDAO itemProdutoDAO = session.getMapper(ItemProdutoDAO.class);
		List<ItemProduto> itensProduto = itemProdutoDAO.getItensProdutoDAO(itemProduto);
		
		for(ItemProduto itens : itensProduto){
			itemDao = session.getMapper(ItemDAO.class);
			item = itemDao.getItemDAO(itens.getItem().getId());
			itens.setItem(item);
			produtoDao = session.getMapper(ProdutoDAO.class);
			produto = produtoDao.getProdutoDAO(itens.getProduto().getId());
			itens.setProduto(produto);
		}
		session.commit();
		session.close();
		
		return itensProduto;
	}
	
	public List<ItemProduto> getItensProdutoPorProduto(Produto produto){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemProdutoDAO itemProdutoDAO = session.getMapper(ItemProdutoDAO.class);
		List<ItemProduto> itensProduto = itemProdutoDAO.getItensProdutoPorProduto(produto);
		session.commit();
		session.close();
		return itensProduto;
	}
	
}
