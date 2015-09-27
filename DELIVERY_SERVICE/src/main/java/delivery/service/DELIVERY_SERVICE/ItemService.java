package delivery.service.DELIVERY_SERVICE;

import java.util.ArrayList;
import java.util.List;

import delivery.api.mapper.ItemImpl;
import delivery.model.Item;
import delivery.model.ItemProduto;



public class ItemService {
	
	private static ItemImpl itemImpl;
	
	public void cadastrarItemService(final Item item){
		itemImpl = new ItemImpl();
		itemImpl.cadastrarItemDAO(item);
	}
	
	public void atualizarItemService(final Item item ){
		itemImpl = new ItemImpl();
		itemImpl.atualizarItemDAO(item);
	}
	
	public List<ItemProduto> getItensType(final List<ItemProduto> itens,final boolean type){
		final List<ItemProduto> adicionais = new ArrayList<ItemProduto>(); 
		final List<ItemProduto> itensProduto = new ArrayList<ItemProduto>(); 
		for(ItemProduto item : itens){
			if(item.isItemAdicional()){
				adicionais.add(item);
			} else {
				itensProduto.add(item);
			}
		}
		
		// Obs: quando type == true significa que a requisição solicita adicionais do produto	
		if(type == true){
			return adicionais;
		} else {
			return itensProduto;
		}
	}
}
