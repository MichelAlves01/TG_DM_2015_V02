package delivery.model;

import java.util.List;

public class AdicionalProduto {
	
	private int id;
	private Produto produto;
	private Item item;
	private List<AdicionalPedido> adicionalPedido;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public List<AdicionalPedido> getAdicionalPedido() {
		return adicionalPedido;
	}
	public void setAdicionalPedido(List<AdicionalPedido> adicionalPedido) {
		this.adicionalPedido = adicionalPedido;
	}
	
	
}
