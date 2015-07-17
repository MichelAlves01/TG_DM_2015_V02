package delivery.model;

import java.util.List;

public class Cidade {
	
	private int id;
	private String nome;
	private Estado estado;
	private List<Empresa> empresa;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public List<Empresa> getEmpresa() {
		return empresa;
	}
	public void setEmpresa(List<Empresa> empresa) {
		this.empresa = empresa;
	}
	
}
