package hello;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import delivery.model.Empresa;
import delivery.model.ItemPedido;
import delivery.model.Pedido;
import delivery.model.Produto;
import delivery.service.DELIVERY_SERVICE.PedidoService;


@RestController
public class PedidoController {

	@RequestMapping(value="/cadastrarPedidoController" , method=RequestMethod.POST)
	public void cadastrarPedido(@RequestParam(value="cpfCnpj") String cpfCnpj,
								@RequestParam(value="endereco") String endereco,
								@RequestParam(value="pgtoTipo") String pgtoTipo,
								@RequestParam(value="pgtoObs") String pgtoObs,
								@RequestParam(value="observacao") String observacao,
								@RequestParam (value="produto") String produto){
		
		observacao = observacao + "," + pgtoTipo + "," + pgtoObs;
		final Pedido pedido = new Pedido();
		pedido.setEndereco(endereco);
		pedido.setIdEmpresa(cpfCnpj);
		pedido.setObservacao(observacao);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		pedido.setHoraAberto(dateFormat.format(date));
		pedido.setStatus(0);
		
		Gson gson = new Gson();
		java.lang.reflect.Type collectionType = new TypeToken<List<Produto>>() {
	    }.getType();
		List<Produto> produtos = gson.fromJson(produto, collectionType);
		List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
		
		for(Produto prod : produtos){
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setProduto(prod);
			itensPedido.add(itemPedido);
		}
		
		pedido.setItensPedido(itensPedido);
		
		System.out.println("deu bom : " + pedido.getObservacao());
		PedidoService pedidoService = new PedidoService();
		pedidoService.cadastrarPedidoService(pedido);
		
	}
}
