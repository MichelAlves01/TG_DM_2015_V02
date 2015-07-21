package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.ProdutoDAO;
import delivery.model.Empresa;
import delivery.model.Produto;


public class ProdutoImpl {
	
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
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		ProdutoDAO produtodao = session.getMapper(ProdutoDAO.class);
		List<Produto> produtos = produtodao.getProdutosDAO(empresa);
		session.close();
		return produtos;
	}
}
