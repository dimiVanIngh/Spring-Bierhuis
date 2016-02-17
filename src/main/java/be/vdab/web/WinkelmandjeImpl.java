package be.vdab.web;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import be.vdab.entities.Bestelbon;
import be.vdab.valueobjects.Bestelbonlijn;

@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
class WinkelmandjeImpl implements Winkelmandje, Serializable {
	private Bestelbon bestelbon;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void setWinkelmandje(Bestelbon bestelbon) {
		this.bestelbon = bestelbon;
	}

	@Override
	public void addBestelbonlijnToBestelbon(Bestelbonlijn bestelbonlijn) {
		// TODO Auto-generated method stub
		
	}

}
