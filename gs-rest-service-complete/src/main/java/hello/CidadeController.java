package hello;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import delivery.api.mapper.CidadeImpl;
import delivery.model.Cidade;

@RestController
public class CidadeController {
	
	@RequestMapping(value="/getCidades", method=RequestMethod.GET)
	public List<Cidade> getCidadesController(@RequestParam("idEstado") int idEstado){
		CidadeImpl cidadeImpl = new CidadeImpl();
		List<Cidade> cidades = cidadeImpl.getCidadesDAO(idEstado);
		return cidades;
	}
}
