package delivery.model;

import java.util.List;

public class Estado {
	
	private int id;
	
	private String sigla;
	
	private String nome;
	
	private List<Cidade> cidade;
	
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
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public List<Cidade> getCidade() {
		return cidade;
	}
	
	public void setCidade(List<Cidade> cidade) {
		this.cidade = cidade;
	}
	
}
