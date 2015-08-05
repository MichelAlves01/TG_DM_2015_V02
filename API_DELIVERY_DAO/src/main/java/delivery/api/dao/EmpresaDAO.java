package delivery.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import delivery.model.Empresa;

public interface EmpresaDAO {

	public void cadastrarEmpresaDAO(Empresa empresa);
	
	public void atualizarEmpresaDAO(Empresa empresa);
	
	public void excluirEmpresaDAO(String cpfCnpj);
	
	Empresa getEmpresaDAO(String cpfCnpj);
	
	List<Empresa> getEmpresasDAO();
	
	List<Empresa> getEmpresasPorTipoDAO(String tipo);
	
	public List<Empresa> getEmpresaPorLatLong(HashMap<String, Double> coordenadas);
	
}
