package be.vdab.dao;

import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.datasource.CreateTestDataSourceBean;
import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.Adres;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CreateTestDAOBeans.class,
		CreateTestDataSourceBean.class })
// DataSource bean en DAO beans laden in IOC container
@Transactional
// omringt elke test met een transactie, na de test rollback
public class FiliaalDAOImplTest {
	@Autowired
	private FiliaalDAO filiaalDAO;

	@Test
	public void create() {
		Filiaal filiaal = new Filiaal("TestNaam", true, BigDecimal.ONE,
				new Date(), new Adres("Straat", "HuisNr", 1000, "Gemeente"));
		filiaal = filiaalDAO.save(filiaal);
		assertNotEquals(0, filiaal.getId()); // id moet autonumber hebben:
	}
}
