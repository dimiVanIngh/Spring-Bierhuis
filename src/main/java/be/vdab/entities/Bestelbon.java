package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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
import javax.validation.constraints.NotNull;

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
	@NotNull
	@Embedded
	private Adres adres;
	@SafeHtml
	@NotBlank
	@Length(min = 1, max = 50)
	private String naam;
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bestelbonid") )
	private Set<Bestelbonlijn> bestelbonlijnen;

	public Bestelbon() {
		bestelbonlijnen = new HashSet<Bestelbonlijn>();
	}
	
	public Bestelbon(String naam, Adres adres, Set<Bestelbonlijn> bestelbonlijnen){
		this(naam,adres);
		this.bestelbonlijnen = bestelbonlijnen;
	}
	
	public Bestelbon(String naam, Adres adres){
		this();
		this.naam = naam;
		this.adres = adres;
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

	public void addBestelLijn(Bestelbonlijn bestelbonlijn) {
		bestelbonlijnen.add(bestelbonlijn);
	}

	public BigDecimal getTotalePrijs() {
		BigDecimal totalePrijs = BigDecimal.ZERO;
		for(Bestelbonlijn lijn : bestelbonlijnen){
			totalePrijs = totalePrijs.add(lijn.getTotaleprijs());
		}
		return totalePrijs;
	}
}
