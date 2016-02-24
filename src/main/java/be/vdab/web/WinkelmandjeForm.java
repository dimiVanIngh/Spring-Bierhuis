package be.vdab.web;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

public class WinkelmandjeForm {
	private Set<Bestelbonlijn> bestelbonlijnen = new HashSet<Bestelbonlijn>();
	
	@Valid
	private Adres adres;
	
	@SafeHtml
	@NotBlank
	@Length(min = 1, max = 50)
	private String naam;
	
	public WinkelmandjeForm(){
		
	}

	public Set<Bestelbonlijn> getBestelbonlijnen() {
		return bestelbonlijnen;
	}

	public void setBestelbonlijnen(Set<Bestelbonlijn> bestelbonlijnen) {
		this.bestelbonlijnen = bestelbonlijnen;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}	
	
	public BigDecimal getTotalePrijs() {
		BigDecimal totalePrijs = BigDecimal.ZERO;
		for(Bestelbonlijn lijn : bestelbonlijnen){
			totalePrijs = totalePrijs.add(lijn.getTotaleprijs());
		}
		return totalePrijs;
	}
}
