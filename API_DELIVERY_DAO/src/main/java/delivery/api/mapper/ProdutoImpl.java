package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.ItemProdutoDAO;
import delivery.api.dao.ProdutoDAO;
import delivery.model.Empresa;
import delivery.model.Item;
import delivery.model.ItemProduto;
import delivery.model.Produto;


public class ProdutoImpl {
	
	private ItemImpl itemImpl;
	
	private Item item;
	
	
	public void cadastrarProdutoDAO(Produto produto){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ProdutoDAO produtodao = session.getMapper(ProdutoDAO.class);
		produtodao.cadastrarProdutoDAO(produto);
		session.commit();
		session.close();
	}
	
	public void atualizarProdutoDAO(Produto produto){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ProdutoDAO produtodao = session.getMapper(ProdutoDAO.class);
		System.out.println("2 - deu bom");
		produtodao.atualizarProdutoDAO(produto);
		session.commit();
		session.close();
	}
	
	public void excluirProdutoDAO(int idProduto){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ProdutoDAO produtodao = session.getMapper(ProdutoDAO.class);
		produtodao.excluirProdutoDAO(idProduto);
		session.commit();
		session.close();
	}
	
	public Produto getProdutoDAO(int idProduto){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ProdutoDAO produtodao = session.getMapper(ProdutoDAO.class);
		Produto produto = produtodao.getProdutoDAO(idProduto);
		session.close();
		return produto;
	}
	
	public List<Produto> getProdutosDAO(Empresa empresa){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final ProdutoDAO produtodao = session.getMapper(ProdutoDAO.class);
		final List<Produto> produtos = produtodao.getProdutosDAO(empresa);
		itemImpl = new ItemImpl();
		List<Item> itens = itemImpl.getItensDAO(empresa);
		itemImpl = new ItemImpl();
		item = new Item();
		final ItemProdutoImpl itemProdutoImpl = new ItemProdutoImpl();
		for(Produto prod : produtos){
			List<ItemProduto> itensProduto = itemProdutoImpl.getItensProdutoPorProduto(prod);
			for(ItemProduto itensProd : itensProduto ){
				if(itensProd.getProduto().getId() == prod.getId()){
					item = itemImpl.getItemDAO(itensProd.getItem().getId());
					prod.addItem(item);
				}
			}
			
		}
		session.close();
		return produtos;
	}
}
