package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Table(name = "soorten")
public class Soort implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@SafeHtml
	@NotBlank
	@Length(min = 1, max = 50)
	private String naam;
	
	@OneToMany(mappedBy = "soort")
	private Set<Bier> bieren;
			
	protected Soort() {

	}
	
	public Soort(String naam, Set<Bier> bieren) {
		this.naam = naam;
		this.bieren = bieren;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public long getId() {
		return id;
	}

	public Set<Bier> getBieren() {
		return Collections.unmodifiableSet(bieren);
	}

	public void setBieren(Set<Bier> bieren) {
		this.bieren = bieren;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Soort))
			return false;
		Soort other = (Soort) obj;
		if (id != other.id)
			return false;
		return true;
	}
}