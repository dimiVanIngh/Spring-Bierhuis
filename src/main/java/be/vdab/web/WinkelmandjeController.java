package be.vdab.web;

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

import be.vdab.entities.Bestelbon;
import be.vdab.services.BestelbonService;
import be.vdab.services.BrouwerService;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

@Controller
@RequestMapping("/winkelmandje")
public class WinkelmandjeController {
	private static final String WINKELMANDJE_VIEW = "winkelmandje/overzicht";
	private static final String CONFIRM_VIEW = "winkelmandje/confirmed";
	private static final String REDIRECT_NA_CONFIRM_VIEW = "redirect:/winkelmandje/confirm";

	private final BestelbonService bestelbonService;
	private final Winkelmandje winkelmandje;

	@Autowired
	WinkelmandjeController(Winkelmandje winkelmandje, BestelbonService bestelbonService) {
		this.winkelmandje = winkelmandje;
		this.bestelbonService = bestelbonService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView get() {
		Bestelbon bestelbon = bestelbonService.read(winkelmandje.getBestelbonId());
		ModelAndView modelAndView = new ModelAndView(WINKELMANDJE_VIEW);
		if (bestelbon != null){
			modelAndView.addObject("bestelbon",bestelbon);
		}
		return modelAndView;
	}

	@RequestMapping(path = "confirm", method = RequestMethod.GET)
	ModelAndView confirm() {
		long orderNr = winkelmandje.getBestelbonId();
		winkelmandje.setBestelbonId(0);
		return new ModelAndView(CONFIRM_VIEW).addObject("orderNr", orderNr);
	}

	@RequestMapping(method = RequestMethod.POST)
	String post(@Valid Bestelbon bestelbon, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return WINKELMANDJE_VIEW;
		}
		bestelbonService.update(bestelbon);	
		return REDIRECT_NA_CONFIRM_VIEW;
	}
	
	@InitBinder("bestelbon")
	void initBinderBestelbon(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
}
