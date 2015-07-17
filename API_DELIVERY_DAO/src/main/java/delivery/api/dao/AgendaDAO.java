package delivery.api.dao;

import java.util.Date;

import delivery.model.Agenda;

public interface AgendaDAO {

	void cadastrarHorarioDAO(Agenda agenda);
	
	void excluirHorarioDAO(Date dataHora);
	
	Agenda getHorarioDAO();
	
}
