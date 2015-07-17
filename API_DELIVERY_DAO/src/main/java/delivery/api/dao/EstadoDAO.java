package delivery.api.dao;

import java.util.List;

import delivery.model.Estado;

public interface EstadoDAO {
	
	public List<Estado> getEstadosDAO();
	
	public Estado getEstadoDAO(int idEstado);
	
}
