package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.WerknemerService;

@Controller
@RequestMapping("/werknemers")
class WerknemerController {
	private static final String WERKNEMERS_VIEW = "werknemers/werknemers";
	private final WerknemerService werknemerService;

	@Autowired
	WerknemerController(WerknemerService werknemerService) {
		this.werknemerService = werknemerService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll(Pageable pageable) {
		return new ModelAndView(WERKNEMERS_VIEW, "page",
				werknemerService.findAll(pageable));
	}
}
