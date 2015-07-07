package be.vdab.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.services.FiliaalService;
import be.vdab.valueobjects.PostcodeReeks;

@Controller
@RequestMapping("/filialen")
class FiliaalController {
	private static final String FILIALEN_VIEW = "filialen/filialen";
	private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
	private final FiliaalService filiaalService;
	private static final String FILIAAL_VIEW = "filialen/filiaal";
	private static final String REDIRECT_URL_FILIAAL_NIET_GEVONDEN = "redirect:/filialen";
	private static final String REDIRECT_URL_NA_VERWIJDEREN = "redirect:/filialen/{id}/verwijderd";
	private static final String REDIRECT_URL_HEEFT_NOG_WERKNEMERS = "redirect:/filialen/{id}";
	private static final String VERWIJDERD_VIEW = "filialen/verwijderd";
	private static final String PER_POSTCODE_VIEW = "filialen/perpostcode";
	private static final String WIJZIGEN_VIEW = "filialen/wijzigen";

	@Autowired
	FiliaalController(FiliaalService filiaalService) {
		// Spring injecteert de parameter filiaalService met de bean die de
		// interface
		// FiliaalService implementeert: FiliaalServiceImpl
		this.filiaalService = filiaalService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Filiaal filiaal, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return TOEVOEGEN_VIEW;
		}
		filiaalService.create(filiaal);
		return REDIRECT_URL_NA_TOEVOEGEN;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAll() {
		return new ModelAndView(FILIALEN_VIEW, "filialen",
				filiaalService.findAll()).addObject("aantalFilialen",
				filiaalService.findAantalFilialen());
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	ModelAndView createForm() {
		return new ModelAndView(TOEVOEGEN_VIEW, "filiaal", new Filiaal());
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	ModelAndView read(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(FILIAAL_VIEW);
		Filiaal filiaal = filiaalService.read(id);
		if (filiaal != null) {
			modelAndView.addObject(filiaal);
		}
		return modelAndView;
	}

	@RequestMapping(value = "{id}/verwijderen", method = RequestMethod.POST)
	String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
		Filiaal filiaal = filiaalService.read(id);
		if (filiaal == null) {
			return REDIRECT_URL_FILIAAL_NIET_GEVONDEN;
		}
		try {
			filiaalService.delete(id);
			redirectAttributes.addAttribute("id", id).addAttribute("naam",
					filiaal.getNaam());
			return REDIRECT_URL_NA_VERWIJDEREN;
		} catch (FiliaalHeeftNogWerknemersException ex) {
			redirectAttributes.addAttribute("id", id).addAttribute("fout",
					"Filiaal heeft nog werknemers");
			return REDIRECT_URL_HEEFT_NOG_WERKNEMERS;
		}
	}

	@RequestMapping(value = "{id}/verwijderd", method = RequestMethod.GET)
	ModelAndView deleted(String naam) {
		return new ModelAndView(VERWIJDERD_VIEW, "naam", naam);
	}

	@RequestMapping(value = "perpostcode", method = RequestMethod.GET)
	ModelAndView findByPostcodeReeks() {
		PostcodeReeks reeks = new PostcodeReeks();
		// reeks.setVanpostcode(1000);
		// reeks.setTotpostcode(9999);
		return new ModelAndView(PER_POSTCODE_VIEW).addObject(reeks);
	}

	@RequestMapping(method = RequestMethod.GET, params = { "vanpostcode",
			"totpostcode" })
	ModelAndView findByPostcodeReeks(@Valid PostcodeReeks reeks,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(PER_POSTCODE_VIEW);
		if (!bindingResult.hasErrors()) {
			List<Filiaal> filialen = filiaalService.findByPostcodeReeks(reeks);
			if (filialen.isEmpty()) {
				bindingResult.reject("geenFilialen");
			} else {
				modelAndView.addObject("filialen", filialen);
			}
		}
		return modelAndView;
	}

	@InitBinder("postcodeReeks")
	void initBinderPostcodeReeks(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}

	@InitBinder("filiaal")
	void initBinderFiliaal(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}

	@RequestMapping(value = "{id}/wijzigen", method = RequestMethod.GET)
	ModelAndView updateForm(@PathVariable long id) {
		Filiaal filiaal = filiaalService.read(id);
		if (filiaal == null) {
			return new ModelAndView(REDIRECT_URL_FILIAAL_NIET_GEVONDEN);
		}
		return new ModelAndView(WIJZIGEN_VIEW).addObject(filiaal);
	}

	private static final String REDIRECT_URL_NA_WIJZIGEN = "redirect:/filialen";

	@RequestMapping(value = "{id}/wijzigen", method = RequestMethod.POST)
	String update(@Valid Filiaal filiaal, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return WIJZIGEN_VIEW;
		}
		filiaalService.update(filiaal);
		return REDIRECT_URL_NA_WIJZIGEN;
	}
}
