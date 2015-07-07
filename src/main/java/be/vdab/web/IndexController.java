package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController {
	private static final String VIEW = "index";
	private final Voorkeur voorkeur;

	@Autowired
	IndexController(Voorkeur voorkeur) {
		this.voorkeur = voorkeur;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView index() {
		return new ModelAndView(VIEW, "foto", voorkeur.getFoto());
	}

	@RequestMapping(method = RequestMethod.GET, params = "foto")
	String kleurKeuze(String foto) {
		voorkeur.setFoto(foto);
		return "redirect:/";
	}
}
