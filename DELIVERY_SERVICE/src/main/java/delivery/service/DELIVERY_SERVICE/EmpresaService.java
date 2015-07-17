package delivery.service.DELIVERY_SERVICE;

import java.util.ArrayList;
import java.util.List;

import delivery.api.mapper.EmpresaImpl;
import delivery.model.Empresa;

public class EmpresaService {
	private Empresa empresa;
	
	private EmpresaImpl empresaImpl;
	
	
	public void cadastrarEmpresaService(final Empresa empresa){
		
	}
	
	public void excluirEmpresaService(String cpfCnpj){
		empresaImpl = new EmpresaImpl();
		empresaImpl.excluirEmpresaDAO(cpfCnpj);
	}
	
	public Empresa getEmpresaService( int cpfCnpj ){
		empresa = new Empresa();
		return empresa;
	}
	
	public List<Empresa> getEmpresasService(){
		List<Empresa> empresas = new ArrayList<Empresa>();
		return empresas;
	}
	/**
	 * esta função verifica se o cpf ou cnpj existe no banco de dados
	 * @param empresa
	 */
	public void verificaCpf(Empresa empresa){
		empresaImpl = new EmpresaImpl();
		List<Empresa> empresas = empresaImpl.getEmpresasDAO();
		boolean cpfNotExiste = true;
		
		for (Empresa emp : empresas) {
			if(emp.getCpfCnpj().equals(empresa.getCpfCnpj())){
				cpfNotExiste = false;
			}
		}
		
		if(cpfNotExiste){
			empresa.setStatus(0);
		} else {
			empresa.setStatus(1);
		}
		System.out.println("serviceStatus : " + empresa.getStatus());
	}
}
