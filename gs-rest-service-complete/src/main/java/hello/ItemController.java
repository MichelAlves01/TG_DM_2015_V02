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
	public Item cadastrarItem(	@RequestParam(value="descricao") String descricao,
								@RequestParam(value="preco") double preco,
								@RequestParam(value="cpfCnpj") String cpfCnpj){
		
		empresaImpl = new EmpresaImpl();
		empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		
		item = new Item();
		item.setDescricao(descricao);
		item.setPreco(preco);
		item.setEmpresa(empresa);
		
		itemService = new ItemService();
		itemService.cadastrarItemService(item);
		return item;
	}
	
	@RequestMapping(value="/getItensController", method=RequestMethod.GET)
	public List<Item> getProdutos(@RequestParam(value="cpfCnpj") String cpfCnpj){
		empresaImpl = new EmpresaImpl();
		empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		
		itemImpl = new ItemImpl();
		List<Item> itens = itemImpl.getItensDAO(empresa);
		return itens;
	}
	
	@RequestMapping(value="/excluirItemController",method=RequestMethod.GET)
	public List<Item> excluirItem(	@RequestParam(value="id") int id,
									@RequestParam(value="cpfCnpj") String cpfCnpj){
		itemImpl = new ItemImpl();
		itemImpl.excluirProdutoDAO(id);
		
		empresaImpl = new EmpresaImpl();
		empresa = empresaImpl.getEmpresaDAO(cpfCnpj);
		
		List<Item> itens = itemImpl.getItensDAO(empresa);
		return itens;
	}
	
	@RequestMapping(value="/atualizarItemController", method=RequestMethod.GET)
	public void atualizarItem(	@RequestParam(value="id") int id,
								@RequestParam(value="descricao") String descricao,
								@RequestParam(value="preco") double preco,
								@RequestParam(value="cpfCnpj") String cpfCnpj){
		
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
