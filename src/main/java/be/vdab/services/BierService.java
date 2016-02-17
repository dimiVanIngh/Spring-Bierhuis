package be.vdab.services;

import java.util.List;

import be.vdab.entities.Bier;
import be.vdab.entities.Brouwer;

public interface BierService {

	long findAantalBieren();

	public Bier read(long id);

	public List<Bier> readAll();
	
	public List<Bier> findByBrouwer(Brouwer brouwer);
}
