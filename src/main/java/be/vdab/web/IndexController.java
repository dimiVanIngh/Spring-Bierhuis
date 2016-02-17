package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.BierService;

@Controller
@RequestMapping("/")
class IndexController {
	private static final String VIEW = "index";
	private final BierService bierService;

	@Autowired
	IndexController(BierService bierService) {
		this.bierService = bierService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView findAllBieren() {
		return new ModelAndView(VIEW, "aantalBieren", bierService.findAantalBieren());
	}
}
