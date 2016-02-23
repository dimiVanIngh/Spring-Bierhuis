package be.vdab.services;

import be.vdab.entities.Bestelbon;

public interface BestelbonService {
	Bestelbon read(long id);
	
	void update(Bestelbon bon);
	
	void create(Bestelbon bon);
}
