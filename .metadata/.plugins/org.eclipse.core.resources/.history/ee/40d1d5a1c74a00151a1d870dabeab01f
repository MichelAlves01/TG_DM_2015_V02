package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LoginController {

	  @RequestMapping(value="/", method=RequestMethod.GET)
	    public ModelAndView getTerms() {
	        return new ModelAndView(new RedirectView("DeliveryApp.html", true));
	    }
}
