package delivery.model;

import java.util.Date;
import java.util.List;

public class Pedido {

	private String id;
	private String endereco;
	private Date horaAberto;
	private Date horaFechado;
	private Empresa empresa;
	private UsuarioMob usuariosMob;
	private int status;
	private String observacao;	
	private List<AdicionalPedido> adicionaisPedido;
	private List<ItemPedido> itensPedido;
	private List<Agenda> horariosAgendados;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}	
	public Date getHoraAberto() {
		return horaAberto;
	}
	public void setHoraAberto(Date horaAberto) {
		this.horaAberto = horaAberto;
	}
	public Date getHoraFechado() {
		return horaFechado;
	}
	public void setHoraFechado(Date horaFechado) {
		this.horaFechado = horaFechado;
	}
	public UsuarioMob getUsuariosMob() {
		return usuariosMob;
	}
	public void setUsuariosMob(UsuarioMob usuariosMob) {
		this.usuariosMob = usuariosMob;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public List<AdicionalPedido> getAdicionaisPedido() {
		return adicionaisPedido;
	}
	public void setAdicionaisPedido(List<AdicionalPedido> adicionaisPedido) {
		this.adicionaisPedido = adicionaisPedido;
	}
	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}
	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
	public List<Agenda> getHorariosAgendados() {
		return horariosAgendados;
	}
	public void setHorariosAgendados(List<Agenda> horariosAgendados) {
		this.horariosAgendados = horariosAgendados;
	}
	
	
	
}
