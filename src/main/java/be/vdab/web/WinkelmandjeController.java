package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.BrouwerService;

@Controller
@RequestMapping("/winkelmandje")
public class WinkelmandjeController {
	private static final String WINKELMANDJE_VIEW = "winkelmandje/overzicht";
	
	private final BrouwerService brouwerService;
	private final Winkelmandje winkelmandje;
	
	@Autowired
	WinkelmandjeController(BrouwerService brouwerService, Winkelmandje winkelmandje) {
		this.winkelmandje = winkelmandje;
		this.brouwerService = brouwerService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView get() {
		return new ModelAndView(WINKELMANDJE_VIEW, "brouwers", brouwerService.readAll());
	}
}
