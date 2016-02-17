package be.vdab.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Bier;
import be.vdab.services.BierService;
import be.vdab.valueobjects.Bestelbonlijn;

@Controller
@RequestMapping("/bieren")
public class BierController {
	private static final String BIEREN_DETAIL_VIEW = "bieren/detail";
	private static final String WINKELWAGEN_VIEW = "winkelwagen/overzicht";
	
	private final BierService bierService;
	private final Winkelmandje winkelmandje;

	@Autowired
	BierController( BierService bierService, Winkelmandje winkelmandje) {
		this.bierService = bierService;
		this.winkelmandje = winkelmandje;
	}
	
	@RequestMapping(path = "{bier}", method = RequestMethod.GET)
	ModelAndView read(@PathVariable Bier bier) {
		return new ModelAndView(BIEREN_DETAIL_VIEW).addObject(bier);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ModelAndView post(@PathVariable Bier bier, HttpServletRequest request) {
		Bestelbonlijn lijn = new Bestelbonlijn(bier,Integer.parseInt(request.getParameter("aantal")));
		winkelmandje.addBestelbonlijnToBestelbon(lijn);
		return new ModelAndView(BIEREN_DETAIL_VIEW).addObject(bier);
	}
}
