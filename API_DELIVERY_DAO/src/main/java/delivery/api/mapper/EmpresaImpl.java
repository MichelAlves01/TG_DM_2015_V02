package delivery.api.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.EmpresaDAO;
import delivery.model.Cidade;
import delivery.model.Empresa;
import delivery.model.Produto;
/**
 * Esta classe implementa a interface Empresa 
 * Adiciona os relacionamentos de dependências de dados entre as classes
 * @author Michel
 *
 */
public class EmpresaImpl {
	
	private Cidade cidade;
	
	public void cadastrarEmpresaDAO(final Empresa empresa){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final EmpresaDAO empresaDao = session.getMapper(EmpresaDAO.class);
		empresaDao.cadastrarEmpresaDAO(empresa);
		session.commit();
		session.close();
	}
	
	public void atualizarEmpresaDAO(final Empresa empresa){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final EmpresaDAO empresaDao = session.getMapper(EmpresaDAO.class);
		empresaDao.atualizarEmpresaDAO(empresa);
		session.commit();
		session.close();
	}
	
	public void excluirEmpresaDAO(final String cpfCnpj){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final EmpresaDAO empresaDao = session.getMapper(EmpresaDAO.class);
		empresaDao.excluirEmpresaDAO(cpfCnpj);
		session.commit();
		session.close();
	}
	
	public Empresa getEmpresaDAO(final String cpfCnpj){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final EmpresaDAO empresaDao = session.getMapper(EmpresaDAO.class);
		final Empresa empresa = empresaDao.getEmpresaDAO(cpfCnpj);
		final CidadeImpl cidadeImpl = new CidadeImpl();
		cidade = new Cidade();
		//busca informações da cidade no banco de dados e preenche campos nulo
		cidade = cidadeImpl.geCidadeDAO(empresa.getCidade().getId());
		empresa.setCidade(cidade);		
		session.close();
		return empresa;
	}
	
	public List<Empresa> getEmpresasDAO(){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final EmpresaDAO empresaDao = session.getMapper(EmpresaDAO.class);
		final List<Empresa> empresas = empresaDao.getEmpresasDAO();
		session.close();
		return empresas;
	}
	
	public List<Empresa> getEmpresasPorTipoDAO(final String tipo){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final EmpresaDAO empresaDao = session.getMapper(EmpresaDAO.class);
		final List<Empresa> empresas = empresaDao.getEmpresasPorTipoDAO(tipo);
		session.close();
		return empresas;
	}
	
	/**
	 * Este metodo realiza uma busca no banco encontrando as empresas dentro 
	 * dos limites de latitude e longitude e entre o raio definido pela empresa 
	 * e o ponto que o usuario esta, para trazer as empresas proximas ao 
	 * usuario.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public List<Empresa> getEmpresaPorLatLong(final double latitude,final double longitude){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final EmpresaDAO empresaDao = session.getMapper(EmpresaDAO.class);
		final HashMap<String,Double> coordenadas = new HashMap<String,Double>();
		coordenadas.put("latitude", latitude);
		coordenadas.put("longitude", longitude);
		final List<Empresa> empresas = empresaDao.getEmpresaPorLatLong(coordenadas);
		final ProdutoImpl produtoImpl = new ProdutoImpl();
		for(Empresa emp : empresas){
			final List<Produto> produtos = produtoImpl.getProdutosDAO(emp);
			emp.setProduto(produtos);
		}
		session.close();
		return empresas;
	}
}
