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

@RestController
public class ItemProdutoController {
	
	private Produto produto;
	
	private Item item;
	
	private static ItemImpl itemImpl;
	
	private static ProdutoImpl produtoImpl;
	
	private ItemProduto itemProduto;
	
	private ItemProdutoImpl itemProdutoImpl;
	
	@RequestMapping(value="/cadastrarItemProdutoController", method=RequestMethod.POST)
	public void cadastrarItemProduto(	@RequestParam(value="idItem") int idItem,
										@RequestParam(value="idProduto") int idProduto){
		
		itemImpl = new ItemImpl();
		item = itemImpl.getItemDAO(idItem);
		
		produtoImpl = new ProdutoImpl();
		produto = produtoImpl.getProdutoDAO(idProduto);
		
		itemProduto = new ItemProduto();
		itemProduto.setItem(item);
		itemProduto.setProduto(produto);
		
		itemProdutoImpl = new ItemProdutoImpl();
		itemProdutoImpl.cadastrarItemProdutoDAO(itemProduto);
	}
	
	@RequestMapping(value="/getItensProdutoController" , method=RequestMethod.GET)
	public List<ItemProduto> getItensProduto(@RequestParam(value="idProduto") int idProduto){
											
		produtoImpl = new ProdutoImpl();
		produto = produtoImpl.getProdutoDAO(idProduto);
		
		itemProduto = new ItemProduto();
		itemProduto.setProduto(produto);
		
		itemProdutoImpl = new ItemProdutoImpl();
		List<ItemProduto> itensProduto = itemProdutoImpl.getItemProdutoDAO(itemProduto);
		
		return itensProduto;
	}
}
