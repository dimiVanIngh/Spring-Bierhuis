package be.vdab.services;

import java.util.List;

import be.vdab.entities.Brouwer;

public interface BrouwerService {

	List<Brouwer> readAll();

	Brouwer read(long id);	
}
