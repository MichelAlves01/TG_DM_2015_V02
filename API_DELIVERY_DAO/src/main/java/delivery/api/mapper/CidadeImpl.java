package delivery.api.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.CidadeDAO;
import delivery.model.Cidade;
import delivery.model.Estado;

public class CidadeImpl {
	
	private static EstadoImpl estadoImpl;
	
	private Estado estado;
	
	public List<Cidade> getCidadesDAO(int idEstado){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		CidadeDAO cidadeDao = session.getMapper(CidadeDAO.class);
		List<Cidade> cidades = cidadeDao.getCidadesDAO(idEstado);
		session.close();
		return cidades;
	}
	
	public Cidade geCidadeDAO(int idCidade){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		CidadeDAO cidadeDao = session.getMapper(CidadeDAO.class);
		Cidade cidade = cidadeDao.getCidadeDAO(idCidade);
		estadoImpl = new EstadoImpl();
		estado = estadoImpl.getEstadoDAO(cidade.getEstado().getId());
		cidade.setEstado(estado);
		session.close();
		return cidade;
	}
	
}
