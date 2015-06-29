package be.vdab.dao;

import java.util.List;

import be.vdab.entities.Filiaal;

public interface FiliaalDAO {
	void create(Filiaal filiaal);

	Filiaal read(long id);

	void update(Filiaal filiaal);

	void delete(long id);

	List<Filiaal> findAll();

	long findAantalFilialen();

	// het aantal werknemers van een filiaal:
	long findAantalWerknemers(long id);
}