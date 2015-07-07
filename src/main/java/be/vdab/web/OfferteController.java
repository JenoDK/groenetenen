package be.vdab.web;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Offerte;

@Controller
@RequestMapping("/offertes")
@SessionAttributes("offerte")
class OfferteController {
	private static final String STAP1_VIEW = "offertes/stap1";
	private static final String STAP2_VIEW = "offertes/stap2";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/";
	private final static Logger logger = Logger
			.getLogger(OfferteController.class.getName());

	@RequestMapping(value = "aanvraag", method = RequestMethod.GET)
	ModelAndView createForm1() {
		return new ModelAndView(STAP1_VIEW).addObject(new Offerte());
	}

	@RequestMapping(method = RequestMethod.POST, params = "van1naar2")
	String createForm1Naar2(@Validated(Offerte.Stap1.class) Offerte offerte,
			BindingResult bindingResult) {
		return bindingResult.hasErrors() ? STAP1_VIEW : STAP2_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST, params = "van2naar1")
	String createForm2Naar1(@ModelAttribute Offerte offerte) {
		return STAP1_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST, params = "bevestigen")
	String create(@Validated(Offerte.Stap2.class) Offerte offerte,
			BindingResult bindingResult, SessionStatus sessionStatus) {
		if (!offerte.getGazontypes().values().contains(true)) {
			bindingResult.reject("minstensEenGazonType");
		}
		if (bindingResult.hasErrors()) {
			return STAP2_VIEW;
		}
		logger.info("offerte versturen via e-mail");
		sessionStatus.setComplete();
		return REDIRECT_URL_NA_TOEVOEGEN;
	}

	@RequestMapping(method = RequestMethod.POST, params = "nogeennummer")
	String nogEenNummer(@ModelAttribute Offerte offerte) {
		offerte.nogEenTelefoonNr();
		return STAP1_VIEW;
	}
}
