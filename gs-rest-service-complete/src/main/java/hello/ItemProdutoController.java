package hello;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import delivery.api.mapper.ItemImpl;
import delivery.api.mapper.ItemProdutoImpl;
import delivery.api.mapper.ProdutoImpl;
import delivery.model.Item;
import delivery.model.ItemProduto;
import delivery.model.Produto;
import delivery.service.DELIVERY_SERVICE.ItemService;

@RestController
public class ItemProdutoController {
	
	private Produto produto;
	
	private Item item;
	
	private static ItemImpl itemImpl;
	
	private static ProdutoImpl produtoImpl;
	
	private ItemProduto itemProduto;
	
	private ItemProdutoImpl itemProdutoImpl;
	
	private ItemService itemService;
	
	@RequestMapping(value="/cadastrarItemProdutoController", method=RequestMethod.POST)
	public void cadastrarItemProduto(	@RequestParam(value="idItem") int idItem,
										@RequestParam(value="idProduto") int idProduto,
										@RequestParam(value="itemAdicional") String itemAdicional){
		
		itemImpl = new ItemImpl();
		item = itemImpl.getItemDAO(idItem);
		
		produtoImpl = new ProdutoImpl();
		produto = produtoImpl.getProdutoDAO(idProduto);
		
		itemProduto = new ItemProduto();
		itemProduto.setItem(item);
		itemProduto.setProduto(produto);
		final boolean adicional = Boolean.parseBoolean(itemAdicional);
		itemProduto.setItemAdicional(adicional);
		
		itemProdutoImpl = new ItemProdutoImpl();
		itemProdutoImpl.cadastrarItemProdutoDAO(itemProduto);
	}
	
	@RequestMapping(value="/getItensProdutoController" , method=RequestMethod.GET)
	public List<ItemProduto> getItensProduto(	@RequestParam(value="idProduto") int idProduto,
												@RequestParam(value="itemAdicional") String itemAdicional){
											
		produtoImpl = new ProdutoImpl();
		produto = produtoImpl.getProdutoDAO(idProduto);
		
		itemProduto = new ItemProduto();
		itemProduto.setProduto(produto);
		final boolean adicional = Boolean.parseBoolean(itemAdicional);
		itemProduto.setItemAdicional(adicional);
		
		itemProdutoImpl = new ItemProdutoImpl();
		List<ItemProduto> itensProduto = itemProdutoImpl.getItemProdutoDAO(itemProduto);
		itemService = new ItemService();
		return itensProduto = itemService.getItensType(itensProduto, adicional);
	}
	
	@RequestMapping(value="/excluirItemProdutoController" , method=RequestMethod.GET)
	public List<ItemProduto> excluirItemProduto(@RequestParam(value="idItem") int idItem,
												@RequestParam(value="idProduto") int idProduto){
		produtoImpl = new ProdutoImpl();
		produto = produtoImpl.getProdutoDAO(idProduto);
		
		itemImpl = new ItemImpl();
		item = itemImpl.getItemDAO(idItem);
		
		itemProduto = new ItemProduto();
		itemProduto.setProduto(produto);
		itemProduto.setItem(item);
		
		itemProdutoImpl = new ItemProdutoImpl();
		itemProdutoImpl.excluirItemProduto(itemProduto);
		List<ItemProduto> itensProduto = itemProdutoImpl.getItemProdutoDAO(itemProduto);
		
		return itensProduto ;
	} 
}
