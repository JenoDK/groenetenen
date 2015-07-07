package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.vdab.dao.FiliaalDAO;
import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.valueobjects.PostcodeReeks;

@Service
class FiliaalServiceImpl implements FiliaalService {
	private final FiliaalDAO filiaalDAO;

	@Autowired
	FiliaalServiceImpl(FiliaalDAO filiaalDAO) {
		this.filiaalDAO = filiaalDAO;
	}

	@Override
	public void create(Filiaal filiaal) {
		filiaalDAO.create(filiaal);
	}

	@Override
	public Filiaal read(long id) {
		return filiaalDAO.read(id);
	}

	@Override
	public void update(Filiaal filiaal) {
		filiaalDAO.update(filiaal);
	}

	@Override
	public void delete(long id) {
		if (filiaalDAO.findAantalWerknemers(id) != 0) {
			throw new FiliaalHeeftNogWerknemersException();
		}
		filiaalDAO.delete(id);
	}

	@Override
	public List<Filiaal> findAll() {
		return filiaalDAO.findAll();
	}

	@Override
	public long findAantalFilialen() {
		return filiaalDAO.findAantalFilialen();
	}

	@Override
	public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
		return filiaalDAO.findByPostcodeReeks(reeks);
	}
}
