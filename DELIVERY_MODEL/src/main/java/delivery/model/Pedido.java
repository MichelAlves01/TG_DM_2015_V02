package delivery.model;

import java.util.Date;
import java.util.List;

public class Pedido {

	private int id;
	private String endereco;
	private Date horaAberto;
	private Date horaFechado;
	private UsuarioMob usuariosMob;
	private int status;
	private String observacao;
	private List<AdicionalPedido> adicionaisPedido;
	private List<ItensPedido> itensPedido;
	private List<Agenda> horariosAgendados;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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

	public List<AdicionalPedido> getAdicionaisPedido() {
		return adicionaisPedido;
	}
	public void setAdicionaisPedido(List<AdicionalPedido> adicionaisPedido) {
		this.adicionaisPedido = adicionaisPedido;
	}
	public List<ItensPedido> getItensPedido() {
		return itensPedido;
	}
	public void setItensPedido(List<ItensPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
	public List<Agenda> getHorariosAgendados() {
		return horariosAgendados;
	}
	public void setHorariosAgendados(List<Agenda> horariosAgendados) {
		this.horariosAgendados = horariosAgendados;
	}
	
	
	
	
}
