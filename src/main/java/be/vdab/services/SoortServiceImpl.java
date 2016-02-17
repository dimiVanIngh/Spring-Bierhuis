package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;

import be.vdab.dao.SoortDAO;

@ReadOnlyTransactionalService
public class SoortServiceImpl implements SoortService{
	private final SoortDAO soortDAO;

	@Autowired
	SoortServiceImpl(SoortDAO soortDAO) {
		this.soortDAO = soortDAO;
	}
}
