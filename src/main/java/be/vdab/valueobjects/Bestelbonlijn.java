package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.entities.Bier;

@Embeddable
public class Bestelbonlijn implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = false)
	@JoinColumn(name = "bierid")
	private Bier bier;
	
	@SafeHtml
	private int aantal;

	public Bier getBier() {
		return bier;
	}

	public int getAantal() {
		return aantal;
	}

	public Bestelbonlijn(Bier bier, int aantal) {
		this.bier = bier;
		this.aantal = aantal;
	}

	public Bestelbonlijn(Bier bier) {
		this(bier, 0);
	}

	protected Bestelbonlijn() {

	}

	public BigDecimal getTotaleprijs() {
		return bier.getPrijs().multiply(BigDecimal.valueOf(aantal));
	}

	//TODO
	/*
	@Override
	public int hashCode() {
		return getVolledigeNaam().hashCode();
	} 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Bestelbonlijn))
			return false;
		Bestelbonlijn other = (Bestelbonlijn) obj;
		return other.getVolledigeNaam().equals(getVolledigeNaam());
	}*/
}