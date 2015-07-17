package delivery.api.mapper;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import delivery.api.connection.ConnectionFactory;
import delivery.api.dao.AgendaDAO;
import delivery.model.Agenda;

public class AgendaImpl {

	public void cadastrarHorarioDAO(Agenda agenda){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		AgendaDAO agendaDao = session.getMapper(AgendaDAO.class);
		agendaDao.cadastrarHorarioDAO(agenda);
		session.commit();
		session.close();
	}
	
	public void excluirHorarioDAO(Date dataHora){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		AgendaDAO agendaDao = session.getMapper(AgendaDAO.class);
		agendaDao.excluirHorarioDAO(dataHora);
		session.commit();
		session.close();
	}
	
	public Agenda getHorarioDAO(){
		SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession();
		AgendaDAO agendaDao = session.getMapper(AgendaDAO.class);
		Agenda agenda = agendaDao.getHorarioDAO();
		session.close();
		return agenda;
	}
}
