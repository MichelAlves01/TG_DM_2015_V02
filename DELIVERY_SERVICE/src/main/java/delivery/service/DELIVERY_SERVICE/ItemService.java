package delivery.service.DELIVERY_SERVICE;

import java.util.ArrayList;
import java.util.List;

import delivery.api.mapper.ItemImpl;
import delivery.model.Item;
import delivery.model.ItemProduto;



public class ItemService {
	
	private static ItemImpl itemImpl;
	
	public void cadastrarItemService(Item item){
		itemImpl = new ItemImpl();
		itemImpl.cadastrarItemDAO(item);
	}
	
	public void atualizarItemService( Item item ){
		itemImpl = new ItemImpl();
		itemImpl.atualizarItemDAO(item);
	}
	
	public void excluirItemService(int idItem){
		
	}
	
	public List<ItemProduto> getItensType(final List<ItemProduto> itens,boolean type){
		List<ItemProduto> adicionais = new ArrayList<ItemProduto>(); 
		List<ItemProduto> itensProduto = new ArrayList<ItemProduto>(); 
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
