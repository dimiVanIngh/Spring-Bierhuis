package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Bestelbon;
import be.vdab.services.BestelbonService;
import be.vdab.services.BrouwerService;

@Controller
@RequestMapping("/winkelmandje")
public class WinkelmandjeController {
	private static final String WINKELMANDJE_VIEW = "winkelmandje/overzicht";

	private final BestelbonService bestelbonService;
	private final BrouwerService brouwerService;
	private final Winkelmandje winkelmandje;

	@Autowired
	WinkelmandjeController(BrouwerService brouwerService, Winkelmandje winkelmandje,
			BestelbonService bestelbonService) {
		this.winkelmandje = winkelmandje;
		this.brouwerService = brouwerService;
		this.bestelbonService = bestelbonService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView get() {
		Bestelbon mandje = bestelbonService.read(winkelmandje.getBestelbonId());
		ModelAndView modelAndView = new ModelAndView(WINKELMANDJE_VIEW);
		if (mandje != null)
			modelAndView.addObject("mandje", mandje);
		return modelAndView;
	}
}
