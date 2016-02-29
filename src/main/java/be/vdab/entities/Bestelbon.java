package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

@Entity
@Table(name = "bestelbonnen")
public class Bestelbon implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Valid
	@Embedded
	private Adres adres;
	@SafeHtml
	@NotBlank
	@Length(min = 1, max = 50)
	private String naam;
	
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bestelbonid") )
	private Set<Bestelbonlijn> bestelbonlijnen = new HashSet<Bestelbonlijn>();

	public Bestelbon() {

	}
	
	public Bestelbon(String naam, Adres adres, Set<Bestelbonlijn> bestelbonlijnen){
		this(naam,adres);
		this.bestelbonlijnen = bestelbonlijnen;
	}
	
	public Bestelbon(String naam, Adres adres){
		this.naam = naam;
		this.adres = adres;
	}
		
	public Bestelbon(long id, Adres adres, String naam, Set<Bestelbonlijn> bestelbonlijnen) {
		this(naam,adres,bestelbonlijnen);
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	// meerdere mensen toegang, synchronized; werkt met session stuff, uniek iedereen dus moet nu niet
	public synchronized void addBestelLijn(Bestelbonlijn bestelbonlijn) {
		// set auto keeps 1st value; this is needed to save second value
		if(bestelbonlijnen.contains(bestelbonlijn)){
			bestelbonlijnen.remove(bestelbonlijn);
		}
		bestelbonlijnen.add(bestelbonlijn);
	}
	
	//TODO zou eigenlijk map moeten gebruiken, this is kinda cheating
	public Bestelbonlijn getBestelbonlijn(Bier bier){
		if(bestelbonlijnen.contains(new Bestelbonlijn(bier))){
			return bestelbonlijnen.stream().filter(o -> o.equals(new Bestelbonlijn(bier))).collect(Collectors.toList()).iterator().next();
		}
		return null;
	}

	public BigDecimal getTotalePrijs() {
		BigDecimal totalePrijs = BigDecimal.ZERO;
		for(Bestelbonlijn lijn : bestelbonlijnen){
			totalePrijs = totalePrijs.add(lijn.getTotaleprijs());
		}
		return totalePrijs;
	}
}
