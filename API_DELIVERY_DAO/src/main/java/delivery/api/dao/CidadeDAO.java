package delivery.api.dao;

import java.util.List;

import delivery.model.Cidade;

public interface CidadeDAO {
	
	List<Cidade> getCidadesDAO(int idEstado);
		
	public Cidade getCidadeDAO(int idCidade);
	
}
