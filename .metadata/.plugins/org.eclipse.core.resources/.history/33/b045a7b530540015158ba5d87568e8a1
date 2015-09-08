package delivery.model;

import java.util.ArrayList;
import java.util.List;

public class Produto {

	private int id;
	private String descricao;
	private double preco;
	private Empresa empresa;
	private int status;
	private List<Item> itens = new ArrayList<Item>();
	
	private List<ItemPedido> itensPedido;
	private List<ItemProduto> itensProduto;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}
	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
	public List<ItemProduto> getItensProduto() {
		return itensProduto;
	}
	public void setItensProduto(List<ItemProduto> itensProduto) {
		this.itensProduto = itensProduto;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	public void addItem(Item item){
		this.itens.add(item);
	}
	
}
