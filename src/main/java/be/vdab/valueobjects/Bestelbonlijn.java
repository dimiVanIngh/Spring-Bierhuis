package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import be.vdab.entities.Bier;

@Embeddable
// TODO int -> Integer allows @NotNull; should implement custom error messages now can't parseto Int
public class Bestelbonlijn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Valid
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = false)
	@JoinColumn(name = "bierid")
	private Bier bier;
	
	@Min(1)
	@NotNull
	private Integer aantal;

	public Bier getBier() {
		return bier;
	}

	public int getAantal() {
		return aantal;
	}

	public Bestelbonlijn(Bier bier, Integer aantal) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bier == null) ? 0 : bier.hashCode());
		return result;
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
		if (bier == null) {
			if (other.bier != null)
				return false;
		} else if (!bier.equals(other.bier))
			return false;
		return true;
	}

}
