package hello;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import delivery.api.mapper.CidadeImpl;
import delivery.model.Cidade;
/**
 * Controller para Cidade
 * retorna - JSON com dados de Cidades 
 * 
 * @author Michel
 *
 */
@RestController
public class CidadeController {
	
	@RequestMapping(value="/getCidades", method=RequestMethod.GET)
	public List<Cidade> getCidadesController(@RequestParam("idEstado")final int idEstado){
		final CidadeImpl cidadeImpl = new CidadeImpl();
		final List<Cidade> cidades = cidadeImpl.getCidadesDAO(idEstado);
		return cidades;
	}
}
