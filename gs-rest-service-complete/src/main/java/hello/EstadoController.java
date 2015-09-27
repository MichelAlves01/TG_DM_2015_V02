package hello;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import delivery.model.Estado;
import delivery.api.mapper.EstadoImpl;
/**
 * Controller Estados
 * retorna JSON com todos os estados do Brasil
 * @author Michel
 *
 */
@RestController
public class EstadoController {
	
	@RequestMapping(value="/getEstados", method=RequestMethod.GET)
	public List<Estado> getEstadosController(){
		final EstadoImpl estadoimpl = new EstadoImpl();
		final List<Estado> estados = estadoimpl.getEstadosDAO();
		return estados;
	}
}
