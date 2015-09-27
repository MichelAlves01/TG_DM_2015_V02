package hello;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.ws.RequestWrapper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import delivery.api.mapper.EmpresaImpl;
import delivery.api.mapper.PedidoImpl;
import delivery.model.Empresa;
import delivery.model.ItemPedido;
import delivery.model.Pedido;
import delivery.model.Produto;
import delivery.model.UsuarioMob;
import delivery.service.DELIVERY_SERVICE.PedidoService;

@Controller
@RestController
public class PedidoController {
	
	
	@RequestMapping(value="/getPedidoController" , method=RequestMethod.GET)
	public List<Pedido> getPedido(@RequestParam(value="cpfCnpj") String cpfCnpj){
		PedidoImpl pedidoImpl = new PedidoImpl();
		List<Pedido> pedidos = pedidoImpl.getPedidosDAO(cpfCnpj);
		return pedidos;
	}
	
	@RequestMapping(value="/cadastrarPedidoController" , method=RequestMethod.POST)
	public void cadastrarPedido(@RequestParam(value="cpfCnpj") final String cpfCnpj,
								@RequestParam(value="endereco") final String endereco,
								@RequestParam(value="pgtoTipo") final String pgtoTipo,
								@RequestParam(value="pgtoObs") final String pgtoObs,
								@RequestParam(value="observacao") String observacao,
								@RequestParam(value="idUsuario") final String idUsuario,
								@RequestParam (value="produto") final String produto){
		
		observacao = observacao + "," + pgtoTipo + "," + pgtoObs;
		final Calendar cal = Calendar.getInstance();
		final int ano = cal.get(Calendar.YEAR);
		final int semana = cal.get(Calendar.WEEK_OF_YEAR);
		final int dia = cal.get(Calendar.DATE);
		final int hora = cal.get(Calendar.HOUR);
		final int min = cal.get(Calendar.MINUTE);
		final int seg = cal.get(Calendar.SECOND);
		System.out.println("year : " + ano);
		System.out.println("week : " + semana);
		System.out.println("dia : " + dia);
		System.out.println("hora : " + hora);
		System.out.println("min : " + min);
		System.out.println("seg : " + seg);
		
		final String id = Integer.toString(ano) + 
					Integer.toString(semana) + 
					Integer.toString(dia) + 
					Integer.toString(hora) + 
					Integer.toString(min) + 
					Integer.toString(seg) + cpfCnpj;
		
		System.out.println("id : " + id);
		final Pedido pedido = new Pedido();
		pedido.setId(id);
		pedido.setEndereco(endereco);
		final EmpresaImpl empresaImpl = new EmpresaImpl();
		
		Empresa empresa = new Empresa();
		empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		pedido.setEmpresa(empresa);
		pedido.setObservacao(observacao);
		UsuarioMob usuario = new UsuarioMob();
		usuario.setEmail(idUsuario);
		pedido.setUsuariosMob(usuario);
		
		final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		final Date date = new Date();
		pedido.setHoraAberto(dateFormat.format(date));
		pedido.setStatus(0);
		
		final Gson gson = new Gson();
		java.lang.reflect.Type collectionType = new TypeToken<List<Produto>>() {
	    }.getType();
		final List<Produto> produtos = gson.fromJson(produto, collectionType);
		final List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
		
		for(Produto prod : produtos){
			final ItemPedido itemPedido = new ItemPedido();
			itemPedido.setProduto(prod);
			itensPedido.add(itemPedido);
		}
		
		pedido.setItensPedido(itensPedido);
		
		System.out.println("deu bom : " + pedido.getHoraAberto());
		final PedidoService pedidoService = new PedidoService();
		pedidoService.cadastrarPedidoService(pedido);
		
	}
	
	@RequestMapping(value="/atualizarStatusPedidoController", method=RequestMethod.POST)
	public List<Pedido> aceitarPedido(	@RequestParam(value="idPedido") final String idPedido,
										@RequestParam(value="status") final int status,
										@RequestParam(value="cpfCnpj") final String cpfCnpj){
		
		final PedidoImpl pedidoImpl = new PedidoImpl();
		Pedido pedido = new Pedido();
		pedido = pedidoImpl.getPedidoDAO(idPedido);
		pedido.setStatus(status);
		pedidoImpl.atualizarStatusPedidoDAO(pedido);
		
		final List<Pedido> pedidos = pedidoImpl.getPedidosDAO(cpfCnpj);
		return pedidos;
	}
	
	@RequestMapping(value="getPedidoPorUsuario", method=RequestMethod.GET)
	public List<Pedido> getPedidoUsuario(@RequestParam(value="email") final String email){
		final PedidoImpl pedidoImpl = new PedidoImpl();
		List<Pedido> pedidos = new ArrayList<Pedido>();
		pedidos = pedidoImpl.getPedidosPorUsuario(email);
		final EmpresaImpl empresaImpl = new EmpresaImpl();
		for(Pedido pedido : pedidos){
			Empresa empresa = new Empresa();
			empresa = empresaImpl.getEmpresaDAO(pedido.getEmpresa().getCpfCnpj());
			pedido.setEmpresa(empresa);
		}
		return pedidos;
	}
}
