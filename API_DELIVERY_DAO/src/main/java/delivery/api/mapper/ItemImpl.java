package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.ItemDAO;
import delivery.model.Empresa;
import delivery.model.Item;

public class ItemImpl {
	
	Item item = null;
	
	public void cadastrarItemDAO(Item item){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemDAO itemdao = session.getMapper(ItemDAO.class);
		itemdao.cadastrarItemDAO(item);
		session.commit();
		session.close();
	} 
	
	public void atualizarItemDAO(Item item){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemDAO itemdao = session.getMapper(ItemDAO.class);
		itemdao.atualizarItemDAO(item);
		session.commit();
		session.close();
	} 
	
	public void excluirProdutoDAO(int idItem){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemDAO itemdao = session.getMapper(ItemDAO.class);
		itemdao.excluirProdutoDAO(idItem);
		session.commit();
		session.close();
	}
	
	public Item getItemDAO(int idItem){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemDAO itemdao = session.getMapper(ItemDAO.class);
		item = itemdao.getItemDAO(idItem);
		session.close();
		return item;
	}
	
	public List<Item> getItensDAO(Empresa empresa){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ItemDAO itemdao = session.getMapper(ItemDAO.class);
		List<Item> itensdao = itemdao.getItensDAO(empresa);
		session.close();
		return itensdao;
	}
	
}
