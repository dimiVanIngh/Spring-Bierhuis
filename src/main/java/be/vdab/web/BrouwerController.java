package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Bier;
import be.vdab.entities.Brouwer;
import be.vdab.services.BrouwerService;

@Controller
@RequestMapping("/brouwers")
class BrouwerController {
	private static final String BROUWERS_VIEW = "brouwers/brouwers";
	private static final String BIEREN_VIEW = "brouwers/bieren";
	
	private final BrouwerService brouwerService;

	@Autowired
	BrouwerController(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView get() {
		return new ModelAndView(BROUWERS_VIEW, "brouwers", brouwerService.readAll());
	}
	
	@RequestMapping(path = "{brouwer}", method = RequestMethod.GET)
	ModelAndView read(@PathVariable Brouwer brouwer) {
		for(Bier bier : brouwer.getBieren()){
			System.out.println(bier.getAlcohol());
		}
		return new ModelAndView(BIEREN_VIEW).addObject(brouwer);
	}
}
