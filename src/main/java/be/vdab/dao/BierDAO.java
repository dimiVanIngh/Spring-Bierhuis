package be.vdab.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Bier;
import be.vdab.entities.Brouwer;

public interface BierDAO extends JpaRepository<Bier, Long> {
	// kijken java classes niet db
	List<Bier> findByBrouwer(Brouwer brouwer);
}
