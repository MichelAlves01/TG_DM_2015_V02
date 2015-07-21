package delivery.service.DELIVERY_SERVICE;

import delivery.api.mapper.ItemImpl;
import delivery.model.Item;



public class ItemService {
	
	private static ItemImpl itemImpl;
	
	public void cadastrarItemService(Item item){
		itemImpl = new ItemImpl();
		itemImpl.cadastrarItemDAO(item);
	}
	
	public void atualizarItemService( Item item ){
		
	}
	
	public void excluirItemService(int idItem){
		
	}
}
