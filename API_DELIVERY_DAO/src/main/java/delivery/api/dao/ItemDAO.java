package delivery.api.dao;

import java.util.List;

import delivery.model.Empresa;
import delivery.model.Item;

public interface ItemDAO {
	
	void cadastrarItemDAO(Item item);
	
	void atualizarItemDAO(Item item);
	
	void excluirProdutoDAO(int idItem);
	
	Item getItemDAO(int idItem);
	
	List<Item> getItensDAO(Empresa empresa);
	
}
