package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.ItemDAO;
import delivery.model.Empresa;
import delivery.model.Item;
/**
 * Essa classe implementa a inteface ItemDAO e cria os relacionamentos de dependências
 * @author Michel
 *
 */
public class ItemImpl {
	
	Item item = null;
	
	public void cadastrarItemDAO(final Item item){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final ItemDAO itemdao = session.getMapper(ItemDAO.class);
		itemdao.cadastrarItemDAO(item);
		session.commit();
		session.close();
	} 
	
	public void atualizarItemDAO(final Item item){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final ItemDAO itemdao = session.getMapper(ItemDAO.class);
		itemdao.atualizarItemDAO(item);
		session.commit();
		session.close();
	} 
	
	public void excluirProdutoDAO(final int idItem){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final ItemDAO itemdao = session.getMapper(ItemDAO.class);
		itemdao.excluirProdutoDAO(idItem);
		session.commit();
		session.close();
	}
	
	public Item getItemDAO(final int idItem){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final ItemDAO itemdao = session.getMapper(ItemDAO.class);
		item = itemdao.getItemDAO(idItem);
		session.close();
		return item;
	}
	
	public List<Item> getItensDAO(final Empresa empresa){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final ItemDAO itemdao = session.getMapper(ItemDAO.class);
		final List<Item> itensdao = itemdao.getItensDAO(empresa);
		session.close();
		return itensdao;
	}
	
}
