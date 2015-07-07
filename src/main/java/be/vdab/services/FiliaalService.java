package be.vdab.services;

import java.util.List;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.PostcodeReeks;

public interface FiliaalService {
	void create(Filiaal filiaal);

	Filiaal read(long id);

	void update(Filiaal filiaal);

	void delete(long id);

	List<Filiaal> findAll();

	long findAantalFilialen();

	List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks);
}
