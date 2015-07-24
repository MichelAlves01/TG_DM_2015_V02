package delivery.model;

import java.util.List;

public class Item {
	
	private int id;
	private String descricao;
	private double preco;
	private Empresa empresa;
	private List<ItemProduto> itensProduto;
	private List<AdicionalProduto> adicionaisProduto;
	
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
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public List<ItemProduto> getItensProduto() {
		return itensProduto;
	}
	public void setItensProduto(List<ItemProduto> itensProduto) {
		this.itensProduto = itensProduto;
	}
	public List<AdicionalProduto> getAdicionaisProduto() {
		return adicionaisProduto;
	}
	public void setAdicionaisProduto(List<AdicionalProduto> adicionaisProduto) {
		this.adicionaisProduto = adicionaisProduto;
	}

	
	
	
	
}
