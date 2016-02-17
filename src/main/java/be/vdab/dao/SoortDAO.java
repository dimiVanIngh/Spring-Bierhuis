package be.vdab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Soort;

public interface SoortDAO extends JpaRepository<Soort, Long>{

}
