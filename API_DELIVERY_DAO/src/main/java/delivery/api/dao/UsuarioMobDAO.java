package delivery.api.dao;

import delivery.model.UsuarioMob;

public interface UsuarioMobDAO {
	
	void cadastrarUsuarioMobDAO(UsuarioMob usuariomob);
	
	UsuarioMob getUsuarioMobDAO( String email );
	
	void atualizarUsuarioMobDAO(UsuarioMob usuariomob);
	
	void inativarUsuarioMobDAO(String email);
}
