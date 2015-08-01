package hello;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import delivery.model.ItemPedido;

@RestController
public class PedidoController {

	@RequestMapping(value="/cadastrarPedidoController" , method=RequestMethod.GET)
	public void cadastrarPedido(@RequestParam(value="endereco") String endereco,
								@RequestParam(value="observacao") String observacao,
								@RequestParam(value="itensPedido") List<ItemPedido> itensPedido){
									for(int i=0 ; i<itensPedido.size() ; i++){
										System.out.println("itens " + itensPedido.get(i).getPedido().getId());
										System.out.println("itens " + itensPedido.get(i).getProduto().getId());
									}
										
								}
}
