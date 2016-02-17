package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;

import be.vdab.dao.BestelbonDAO;

@ReadOnlyTransactionalService
public class BestelbonServiceImpl implements BestelbonService{
	private final BestelbonDAO bestelbonDAO;

	@Autowired
	BestelbonServiceImpl(BestelbonDAO bestelbonDAO) {
		this.bestelbonDAO = bestelbonDAO;
	}
}
