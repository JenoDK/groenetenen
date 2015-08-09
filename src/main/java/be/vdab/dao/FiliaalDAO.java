package be.vdab.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Filiaal;

public interface FiliaalDAO extends JpaRepository<Filiaal, Long> {
	
	List<Filiaal> findByAdresPostcodeBetweenOrderByNaamAsc(int van, int tot);
	
	List<Filiaal> findByWaardeGebouwNot(BigDecimal waarde);
}