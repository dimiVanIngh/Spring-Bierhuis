package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import be.vdab.valueobjects.Adres;

@Entity
@Table (name = "brouwers")
public class Brouwer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@SafeHtml
	@NotBlank
	@Length(min = 1, max = 50)
	private String naam;
	
	@NumberFormat (style=Style.CURRENCY)
	@NotNull
	@Min(0)
	@Digits(integer = 10, fraction = 0)
	private Integer omzet;
	@Valid
	@NotNull
	@Embedded
	private Adres adres;
	
	@OneToMany(mappedBy = "brouwer", fetch = FetchType.LAZY)
	@OrderBy("naam")
	private Set<Bier> bieren;
	
	protected Brouwer(){

	}

	public Brouwer(String naam, Integer omzet, Adres adres, Set<Bier> bieren) {
		this.naam = naam;
		this.omzet = omzet;
		this.adres = adres;
		this.bieren = bieren;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public long getId() {
		return id;
	}

	public void setId(long brouwerNr) {
		this.id = brouwerNr;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Integer getOmzet() {
		return omzet;
	}

	public void setOmzet(Integer omzet) {
		this.omzet = omzet;
	}

	public Set<Bier> getBieren() {
		return Collections.unmodifiableSet(bieren);
	}

	public void setBieren(Set<Bier> bieren) {
		this.bieren = bieren;
	}
}
