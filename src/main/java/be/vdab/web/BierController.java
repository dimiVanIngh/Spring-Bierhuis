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
import be.vdab.entities.Bier;
import be.vdab.services.BestelbonService;
import be.vdab.valueobjects.Bestelbonlijn;

@Controller
@RequestMapping("/bieren")
public class BierController {
	private static final String BIEREN_DETAIL_VIEW = "bieren/detail";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/winkelmandje";
	
	private final Winkelmandje winkelmandje;
	private final BestelbonService bestelbonService;

	@Autowired
	BierController(BestelbonService bestelbonService, Winkelmandje winkelmandje) {
		this.winkelmandje = winkelmandje;
		this.bestelbonService = bestelbonService;
	}
	
	@RequestMapping(path = "{bier}", method = RequestMethod.GET)
	ModelAndView read(@PathVariable Bier bier) {
		return new ModelAndView(BIEREN_DETAIL_VIEW).addObject(bier).addObject("bestelbonlijn", new Bestelbonlijn(bier));
	}
	
	@RequestMapping(path ="${bier}", method = RequestMethod.POST)
	String post(@Valid Bestelbonlijn bestelbonlijn,BindingResult bindingResult) {
		//TODO check > 0 + check mandje id != 0	
		/*if (bindingResult.hasErrors()) {
			return BIEREN_DETAIL_VIEW;
		}
		Bestelbonlijn lijn = new Bestelbonlijn(bier,Integer.parseInt(request.getParameter("aantal")));
		Bestelbon bon = bestelbonService.read(winkelmandje.getBestelbonId());
		bon.addBestelLijn(lijn);
		bestelbonService.update(bon);*/
		System.out.println("check");
		return REDIRECT_URL_NA_TOEVOEGEN;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	String postNoPath(@Valid Bestelbonlijn bestelbonlijn,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return BIEREN_DETAIL_VIEW;
		}
		//TODO mandje id = 0 -> create  != 0 read;
		if(winkelmandje.getBestelbonId() == 0){
			Bestelbon bon = new Bestelbon("placeholder",);
			bon.addBestelLijn(bestelbonlijn);
			bestelbonService.create(bon);
		} else {
			Bestelbon bon = bestelbonService.read(winkelmandje.getBestelbonId());
			bon.addBestelLijn(bestelbonlijn);
			bestelbonService.update(bon);
		}
		return REDIRECT_URL_NA_TOEVOEGEN;
	}
	
	@InitBinder("bestelbonlijn")
	void initBinderBestelbonlijn(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
}