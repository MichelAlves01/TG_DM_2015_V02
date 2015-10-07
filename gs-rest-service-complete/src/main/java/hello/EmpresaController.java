package hello;

import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import delivery.api.mapper.CidadeImpl;
import delivery.api.mapper.EmpresaImpl;
import delivery.api.mapper.UserImpl;
import delivery.model.Cidade;
import delivery.model.Empresa;
import delivery.model.UserRole;
import delivery.model.User;
import delivery.service.DELIVERY_SERVICE.EmpresaService;

@RestController
public class EmpresaController {
	
	private Empresa empresa;
	
	private static EmpresaImpl empresaImpl;
	
	private static CidadeImpl cidadeImpl;
	
	private static EmpresaService empresaService;
	
	private User user;
	
	private Cidade cidade;
	
	private static UserImpl userImpl;
	
	// controller para iniciar o cadastro da empresa 
	@RequestMapping(value="/iniciaCadastroEmpresa", method=RequestMethod.POST)
	public Empresa iniciaCadastro( 	@RequestParam(value="nome") final String nome,
									@RequestParam(value="cpfCnpj") final String cpfCnpj){
		empresa = new Empresa();
		empresa.setNome(nome);
		empresa.setCpfCnpj(cpfCnpj);
		empresaService = new EmpresaService();
		empresaService.verificaCpf(empresa);
		return empresa;
	}
	
	@RequestMapping(value="/getEmpresaCadastro", method=RequestMethod.GET)
	public Empresa getEmpresaCadastro(){
		return empresa;
	}
	
	@RequestMapping(value="/getEmpresaController", method=RequestMethod.GET)
	public Empresa getEmpresaController(@RequestParam(value="cpfCnpj")final String cpfCnpj){
		empresaImpl = new EmpresaImpl();
		empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		return empresa;
	}
	
	@RequestMapping(value="/cadastrarEmpresaController", method=RequestMethod.POST)
	public Empresa cadastrarEmpresa(	@RequestParam(value="tipo") final String tipo,
									@RequestParam(value="idCidade") final int idCidade,
									@RequestParam(value="endereco") final String endereco,
									@RequestParam(value="email") final String email,
									@RequestParam(value="telefoneFixo") final String telefoneFixo,
									@RequestParam(value="telefoneMovel") final String telefoneMovel,
									@RequestParam(value="cep") final String cep,
									@RequestParam(value="latitude") final String latitude,
									@RequestParam(value="longitude") final String longitude,
									@RequestParam(value="senha") final String senha){
		final double lat = Double.parseDouble(latitude);
		final double lon = Double.parseDouble(longitude);
		cidadeImpl = new CidadeImpl();
		cidade = cidadeImpl.geCidadeDAO(idCidade);
		user = new User();
		
		empresa.setTipo(tipo);
		empresa.setEmail(email);
		empresa.setEndereco(endereco);
		empresa.setCidade(cidade);
		empresa.setTelefoneFixo(telefoneFixo);
		empresa.setTelefoneMovel(telefoneMovel);
		empresa.setCep(cep);
		empresa.setRaio(5);
		empresa.setStatus(0);
		empresa.setUsaAgenda(0);
		empresa.setAvaliacao("0,0");
		empresa.setLatitude(lat);
		empresa.setLongitude(lon);
		
		user.setUsername(email);
		user.setPassword(senha);
		user.setEmpresa(empresa);
		
		empresaService = new EmpresaService();
		empresaService.cadastrarEmpresaService(empresa);
		
		userImpl = new UserImpl();
		userImpl.cadastrarUsuarioDAO(user);
		
		return empresa;
	}
	
	@RequestMapping(value="/atualizarEmpresaController", method=RequestMethod.POST)
	public Empresa atualizarEmpresa(@RequestParam(value="nome") final String nome,
									@RequestParam(value="cpfCnpj") final String cpfCnpj,
									@RequestParam(value="tipo") final String tipo,
									@RequestParam(value="idCidade") final int idCidade,
									@RequestParam(value="endereco") final String endereco,
									@RequestParam(value="email") final String email,
									@RequestParam(value="telefoneFixo") final String telefoneFixo,
									@RequestParam(value="telefoneMovel") final String telefoneMovel,
									@RequestParam(value="cep") final String cep,
									@RequestParam(value="latitude") final String latitude,
									@RequestParam(value="longitude") final String longitude){
		
		final double lat = Double.parseDouble(latitude);
		final double lon = Double.parseDouble(longitude);
		cidadeImpl = new CidadeImpl();
		final Cidade cidade = cidadeImpl.geCidadeDAO(idCidade);
		
		empresa.setNome(nome);
		empresa.setCpfCnpj(cpfCnpj);
		empresa.setTipo(tipo);
		empresa.setEmail(email);
		empresa.setEndereco(endereco);
		empresa.setCidade(cidade);
		empresa.setTelefoneFixo(telefoneFixo);
		empresa.setTelefoneMovel(telefoneMovel);
		empresa.setCep(cep);
		empresa.setRaio(5);
		empresa.setStatus(0);
		empresa.setUsaAgenda(0);
		//To fix empresa tem um valor de avaliaçao
		empresa.setAvaliacao("0,0");
		empresa.setLatitude(lat);
		empresa.setLongitude(lon);
		
		empresaImpl = new EmpresaImpl();
		empresaImpl.atualizarEmpresaDAO(empresa);
		
		return empresa;
	}
	
	@RequestMapping(value="/excluirEmpresaController", method=RequestMethod.POST) 
	public void excluirEmpresaController(@RequestParam(value="cpfCnpj") final String cpfCnpj){
		empresaImpl = new EmpresaImpl();
		empresaImpl.excluirEmpresaDAO(cpfCnpj);	
	}
	
	@RequestMapping(value="/definirRaioController" , method=RequestMethod.GET)
	public Empresa definirRaio(@RequestParam(value="cpfCnpj") final String cpfCnpf, 
								@RequestParam(value="raio") final double raio){
		
		empresaImpl = new EmpresaImpl();
		empresa = empresaImpl.getEmpresaDAO(cpfCnpf);
		empresa.setRaio(raio);
		
		empresaImpl = new EmpresaImpl();
		empresaImpl.atualizarEmpresaDAO(empresa);
		
		return empresa;
		
	}
	
	@RequestMapping(value="/getEmpresasPorLatLong" , method=RequestMethod.GET)
	public List<Empresa> getEmpresaPorLatLong(	@RequestParam(value="latitude") final double latitude,
							@RequestParam(value="longitude") final double longitude){
		empresaImpl = new EmpresaImpl();
		final List<Empresa> empresas = empresaImpl.getEmpresaPorLatLong(latitude,longitude);
		return empresas;
	}
	
	@RequestMapping(value="/avaliarEmpresaController" , method=RequestMethod.GET)
	public void avaliarEmpresa(@RequestParam(value="cpfCnpj") final String cpfCnpj, 
								@RequestParam(value="nota") final double nota){
		
		empresaImpl = new EmpresaImpl();
		final Empresa empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		final StringTokenizer st = new StringTokenizer(empresa.getAvaliacao(), ",");
		final double notaVelha =  Double.parseDouble(st.nextToken());
		final int qtdeAvaliacao = Integer.parseInt(st.nextToken());
		final EmpresaService empresaService = new EmpresaService();
		empresa.setAvaliacao(empresaService.calcAvaliacao(notaVelha, qtdeAvaliacao, nota));
		empresaImpl.atualizarEmpresaDAO(empresa);
	}
}
