package delivery.model;

import java.util.ArrayList;
import java.util.List;

public class ItemProduto {
	
	private Produto produto;
	private Item item;
	private boolean itemAdicional;
	private List<Item> itens = new ArrayList<Item>();
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public boolean isItemAdicional() {
		return itemAdicional;
	}
	public void setItemAdicional(boolean itemAdicional) {
		this.itemAdicional = itemAdicional;
	}
	public void addItem(Item item){
		this.itens.add(item);
	}
	
	
}
