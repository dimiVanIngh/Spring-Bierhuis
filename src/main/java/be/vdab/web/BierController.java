package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Bier;
import be.vdab.services.BierService;

@Controller
@RequestMapping("/bieren")
public class BierController {
	private static final String BIEREN_DETAIL_VIEW = "bieren/detail";
	
	private final BierService bierService;

	@Autowired
	BierController( BierService bierService) {
		this.bierService = bierService;
	}
	
	@RequestMapping(path = "{bier}", method = RequestMethod.GET)
	ModelAndView read(@PathVariable Bier bier) {
		return new ModelAndView(BIEREN_DETAIL_VIEW).addObject(bier);
	}
}
