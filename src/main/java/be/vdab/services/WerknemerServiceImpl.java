package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import be.vdab.dao.WerknemerDAO;
import be.vdab.entities.Werknemer;

@ReadOnlyTransactionalService
class WerknemerServiceImpl implements WerknemerService {
	private final WerknemerDAO werknemerDAO;

	@Autowired
	WerknemerServiceImpl(WerknemerDAO werknemerDAO) {
		this.werknemerDAO = werknemerDAO;
	}

	@Override
	public Page<Werknemer> findAll(Pageable pageable) {
		return werknemerDAO.findAll(pageable);
	}
}
