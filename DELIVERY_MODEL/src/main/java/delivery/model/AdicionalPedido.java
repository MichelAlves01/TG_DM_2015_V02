package delivery.model;

public class AdicionalPedido {
	private Pedido pedido;
	private AdicionalProduto adicionalproduto;
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public AdicionalProduto getAdicionalproduto() {
		return adicionalproduto;
	}
	public void setAdicionalproduto(AdicionalProduto adicionalproduto) {
		this.adicionalproduto = adicionalproduto;
	}
	
	
}
