package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/werknemers")
class WerknemerController {
	private static final String WERKNEMERS_VIEW = "werknemers/werknemers";

	@RequestMapping(method = RequestMethod.GET)
	String findAll() {
		return WERKNEMERS_VIEW;
	}
}
