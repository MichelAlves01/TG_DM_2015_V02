package hello;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import delivery.api.mapper.EmpresaImpl;
import delivery.api.mapper.ItemImpl;
import delivery.model.Empresa;
import delivery.model.Item;
import delivery.service.DELIVERY_SERVICE.ItemService;

@RestController
public class ItemController {

	private Item item;
	
	private static ItemService itemService;
	
	private static ItemImpl itemImpl;
	
	private static EmpresaImpl empresaImpl;
	
	private Empresa empresa;
	
	@RequestMapping(value="/cadastrarItemController" , method=RequestMethod.POST)
	public List<Item> cadastrarItem(	@RequestParam(value="descricao") final String descricao,
								@RequestParam(value="preco") final double preco,
								@RequestParam(value="cpfCnpj") final String cpfCnpj){
		
		empresaImpl = new EmpresaImpl();
		empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		
		item = new Item();
		item.setDescricao(descricao);
		item.setPreco(preco);
		item.setEmpresa(empresa);
		
		itemService = new ItemService();
		itemService.cadastrarItemService(item);
		
		List<Item> itens = new ItemImpl().getItensDAO(empresa);
		return itens;
	}
	
	@RequestMapping(value="/getItensController", method=RequestMethod.GET)
	public List<Item> getProdutos(@RequestParam(value="cpfCnpj") final String cpfCnpj){
		empresaImpl = new EmpresaImpl();
		empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		
		itemImpl = new ItemImpl();
		final List<Item> itens = itemImpl.getItensDAO(empresa);
		return itens;
	}
	
	@RequestMapping(value="/excluirItemController",method=RequestMethod.GET)
	public List<Item> excluirItem(	@RequestParam(value="id") final int id,
									@RequestParam(value="cpfCnpj") final String cpfCnpj){
		itemImpl = new ItemImpl();
		itemImpl.excluirProdutoDAO(id);
		
		empresaImpl = new EmpresaImpl();
		empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		
		final List<Item> itens = itemImpl.getItensDAO(empresa);
		return itens;
	}
	
	@RequestMapping(value="/atualizarItemController", method=RequestMethod.GET)
	public void atualizarItem(	@RequestParam(value="id") final int id,
								@RequestParam(value="descricao") final String descricao,
								@RequestParam(value="preco") final double preco,
								@RequestParam(value="cpfCnpj") final String cpfCnpj){
		
		empresaImpl = new EmpresaImpl();
		empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		
		item = new Item();
		item.setId(id);
		item.setDescricao(descricao);
		item.setPreco(preco);
		item.setEmpresa(empresa);
		
		itemService = new ItemService();
		itemService.atualizarItemService(item);
		
	}
}
