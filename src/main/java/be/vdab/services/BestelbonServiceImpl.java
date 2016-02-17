package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;

import be.vdab.dao.BestelbonDAO;
import be.vdab.entities.Bestelbon;

@ReadOnlyTransactionalService
public class BestelbonServiceImpl implements BestelbonService{
	private final BestelbonDAO bestelbonDAO;

	@Autowired
	BestelbonServiceImpl(BestelbonDAO bestelbonDAO) {
		this.bestelbonDAO = bestelbonDAO;
	}
	
	@Override
	public Bestelbon read(long id) {
		return bestelbonDAO.findOne(id);
	}

	@Override
	public void update(Bestelbon bon) {
		bestelbonDAO.save(bon);	
	}
}
