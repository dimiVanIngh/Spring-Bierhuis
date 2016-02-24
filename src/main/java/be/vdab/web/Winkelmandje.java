package be.vdab.web;

import be.vdab.entities.Bestelbon;

public interface Winkelmandje {
	long getBestelbonId();
	
	void setBestelbonId(Bestelbon bestelbon);
	
	void setBestelbonId(long id); 
}
