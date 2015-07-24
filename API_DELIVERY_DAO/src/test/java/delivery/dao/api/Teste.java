package delivery.dao.api;

import java.util.List;

import delivery.api.mapper.CidadeImpl;
import delivery.model.Cidade;

public class Teste {

	public static void main(String[] args) {
		CidadeImpl cidadeImpl = new CidadeImpl();
		List<Cidade> cidades = cidadeImpl.getCidadesDAO(2);
		int counter = 0;
		for(Cidade city: cidades){
			counter++;
			System.out.println(counter + " " + city.getNome());
		}
		
		
	}
}
