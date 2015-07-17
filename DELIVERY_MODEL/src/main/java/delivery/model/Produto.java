package delivery.model;

import java.util.List;

public class Produto {

	private int id;
	private String descricao;
	private double preco;
	private Empresa empresa;
	private List<ItensPedido> itensPedido;
	private List<ItensProduto> itensProduto;
	
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
	public List<ItensPedido> getItensPedido() {
		return itensPedido;
	}
	public void setItensPedido(List<ItensPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
	public List<ItensProduto> getItensProduto() {
		return itensProduto;
	}
	public void setItensProduto(List<ItensProduto> itensProduto) {
		this.itensProduto = itensProduto;
	}
	
}
