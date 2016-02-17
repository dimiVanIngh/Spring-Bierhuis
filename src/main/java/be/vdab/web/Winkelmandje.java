package be.vdab.web;

import be.vdab.entities.Bestelbon;
import be.vdab.valueobjects.Bestelbonlijn;

public interface Winkelmandje {
	void setWinkelmandje(Bestelbon bestelbon);
	
	void addBestelbonlijnToBestelbon(Bestelbonlijn bestelbonlijn);
}
