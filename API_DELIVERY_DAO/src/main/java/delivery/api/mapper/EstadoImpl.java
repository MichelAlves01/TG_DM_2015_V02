package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.EstadoDAO;
import delivery.model.Cidade;
import delivery.model.Estado;

public class EstadoImpl {
	
	private static CidadeImpl cidadeImpl;
	
	private List<Cidade> cidades;
	
	/**
	 * este metodo retorna uma lista de estado e as respectivas cidades 
	 * @return
	 */
	public List<Estado> getEstadosDAO(){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final EstadoDAO estadoDao = session.getMapper(EstadoDAO.class);
		final List<Estado> estados = estadoDao.getEstadosDAO();
		cidadeImpl = new CidadeImpl();	
		
		//insere dependencias entre cidade e estado
		for (Estado estado : estados) {
			cidades = cidadeImpl.getCidadesDAO(estado.getId());
			estado.setCidade(cidades);
		}
		session.close();
		return estados;
	}
	
	public Estado getEstadoDAO(final int idEstado){
		final SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		final EstadoDAO estadoDao = session.getMapper(EstadoDAO.class);
		final Estado estado = estadoDao.getEstadoDAO(idEstado);
		session.close();
		return estado;
	}
	
}
