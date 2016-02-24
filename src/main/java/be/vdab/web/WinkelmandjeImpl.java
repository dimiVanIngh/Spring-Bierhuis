package be.vdab.web;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import be.vdab.entities.Bestelbon;

@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
class WinkelmandjeImpl implements Winkelmandje, Serializable {
	private long bestelbonId;
	
	private static final long serialVersionUID = 1L;

	@Override
	public long getBestelbonId() {
		return bestelbonId;
	}

	@Override
	public void setBestelbonId(Bestelbon bestelbon) {
		this.bestelbonId = bestelbon.getId();
		
	}
	@Override
	public void setBestelbonId(long id) {
		this.bestelbonId = id;
		
	}
}
