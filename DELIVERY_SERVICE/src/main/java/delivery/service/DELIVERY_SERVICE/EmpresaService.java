package delivery.service.DELIVERY_SERVICE;

import java.util.ArrayList;
import java.util.List;

import delivery.api.mapper.EmpresaImpl;
import delivery.model.Empresa;

public class EmpresaService {
	private Empresa empresa;
	
	private EmpresaImpl empresaImpl;
	
	public void excluirEmpresaService(final String cpfCnpj){
		empresaImpl = new EmpresaImpl();
		empresaImpl.excluirEmpresaDAO(cpfCnpj);
	}
	
	
	public List<Empresa> getEmpresasService(){
		final List<Empresa> empresas = new ArrayList<Empresa>();
		return empresas;
	}
	/**
	 * esta função verifica se o cpf ou cnpj existe no banco de dados
	 * @param empresa
	 */
	public void verificaCpf(final Empresa empresa){
		empresaImpl = new EmpresaImpl();
		final List<Empresa> empresas = empresaImpl.getEmpresasDAO();
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
	
	/**
	 * Calcula a avaliação entre a nova nota e as notas anteriores
	 * @param notaVelha
	 * @param qtdeAvaliacao
	 * @param novaNota
	 * @return
	 */
	public String calcAvaliacao(double notaVelha, final int qtdeAvaliacao,double novaNota){
		notaVelha = notaVelha * qtdeAvaliacao;
		novaNota = (notaVelha + novaNota) / (qtdeAvaliacao + 1);
		return String.valueOf(novaNota ) + "," + String.valueOf(qtdeAvaliacao + 1);
	}
}
