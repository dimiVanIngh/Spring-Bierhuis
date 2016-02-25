package be.vdab.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

@Controller
@RequestMapping(path = "/bieren", produces = MediaType.TEXT_HTML_VALUE)
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
		ModelAndView modelAndView = new ModelAndView(BIEREN_DETAIL_VIEW);
		Bestelbon bon = bestelbonService.read(winkelmandje.getBestelbonId());
		Bestelbonlijn lijn = new Bestelbonlijn(bier);
		if(bon != null){
			if(bon.getBestelbonlijn(bier) != null){
				lijn = bon.getBestelbonlijn(bier);				
			}
		} 
		return modelAndView.addObject(lijn);
	}
	
	@RequestMapping(path = "{bier}", method = RequestMethod.POST)
	String post(@PathVariable Bier bier, @Valid Bestelbonlijn bestelbonlijn,BindingResult bindingResult) {
		if (bindingResult.hasErrors() || bier == null) {
			return BIEREN_DETAIL_VIEW;
		}		
		if(winkelmandje.getBestelbonId() == 0){
			Bestelbon bon = new Bestelbon("placeholder",new Adres("placeholder","61",3010,"Leuven"));
			bon.addBestelLijn(bestelbonlijn);
			bestelbonService.create(bon);
			winkelmandje.setBestelbonId(bon);
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