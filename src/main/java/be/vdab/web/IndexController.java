package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
class IndexController {
	private static final String VIEW = "index";

	@RequestMapping(method = RequestMethod.GET)
	String index() {
		return VIEW;
	}
}
