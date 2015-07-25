package delivery.model;

public class ItemProduto {
	
	private Produto produto;
	private Item item;
	private boolean itemAdicional;

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
	
	
	
}
