package be.vdab.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Bestelbon;
import be.vdab.services.BestelbonService;

@Controller
@RequestMapping("/winkelmandje")
public class WinkelmandjeController {
	private static final String WINKELMANDJE_VIEW = "winkelmandje/overzicht";
	private static final String CONFIRM_VIEW = "winkelmandje/confirmed";
	private static final String REDIRECT_NA_CONFIRM_VIEW = "redirect:/winkelmandje/confirm";
	private static final String REDIRECT_NA_FOUTEN_BESTELBON = "redirect:/winkelmandje";

	private final BestelbonService bestelbonService;
	private final Winkelmandje winkelmandje;

	@Autowired
	WinkelmandjeController(Winkelmandje winkelmandje, BestelbonService bestelbonService) {
		this.winkelmandje = winkelmandje;
		this.bestelbonService = bestelbonService;
	}

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView get(WinkelmandjeForm otherForm) {
		Bestelbon bestelbon = bestelbonService.read(winkelmandje.getBestelbonId());		
		WinkelmandjeForm winkelmandjeForm = new WinkelmandjeForm();
		if(otherForm.isActive()) {
			winkelmandjeForm = otherForm;
		}
		ModelAndView modelAndView = new ModelAndView(WINKELMANDJE_VIEW);
		if (bestelbon != null){
			winkelmandjeForm.setStatus(true);
			winkelmandjeForm.setBestelbonlijnen(bestelbon.getBestelbonlijnen());
			modelAndView.addObject("bestelbon", bestelbon).addObject("winkelmandjeForm", winkelmandjeForm);
		}
		return modelAndView;
	}
	
	//TODO rethink code + rewrite
	/*ModelAndView get(WinkelmandjeForm otherForm) {
		Bestelbon bestelbon = bestelbonService.read(winkelmandje.getBestelbonId());
		WinkelmandjeForm winkelmandjeForm = otherForm;
		ModelAndView modelAndView = new ModelAndView(WINKELMANDJE_VIEW);
		if (bestelbon != null){
			winkelmandjeForm.setBestelbonlijnen(bestelbon.getBestelbonlijnen());
			modelAndView.addObject("bestelbon", bestelbon).addObject("winkelmandjeForm", winkelmandjeForm);
		}
		return modelAndView;
	}*/
	
	@RequestMapping(method = RequestMethod.POST)
	ModelAndView post(@Valid WinkelmandjeForm winkelmandjeForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			winkelmandjeForm.changeStatus();
			return get(winkelmandjeForm);
		}
		Bestelbon bestelbon = bestelbonService.read(winkelmandje.getBestelbonId());
		bestelbon.setNaam(winkelmandjeForm.getNaam());
		bestelbon.setAdres(winkelmandjeForm.getAdres());
		bestelbonService.update(bestelbon);	
		long orderNr = winkelmandje.getBestelbonId();
		winkelmandje.setBestelbonId(0);
		return new ModelAndView(CONFIRM_VIEW).addObject("orderNr", orderNr);
	}
	
	@InitBinder("winkelmandjeForm")
	void initBinderBestelbon(WebDataBinder binder) {
		binder.initDirectFieldAccess();
	}
}
