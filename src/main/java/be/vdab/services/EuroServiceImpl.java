package be.vdab.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.vdab.restclients.KoersenClient;

@Service
class EuroServiceImpl implements EuroService {
	private final KoersenClient koersenClient;

	@Autowired
	EuroServiceImpl(KoersenClient koersenClient) {
		this.koersenClient = koersenClient;
	}

	@Override
	public BigDecimal naarDollar(BigDecimal euro) {
		return euro.multiply(koersenClient.getDollarKoers()).setScale(2,
				RoundingMode.HALF_UP);
	}
}
