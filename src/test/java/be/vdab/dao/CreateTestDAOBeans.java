package be.vdab.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class CreateTestDAOBeans extends CreateDAOBeans {
	@Override
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = super
				.entityManagerFactory();
		factory.getJpaPropertyMap()
				.put("javax.persistence.schema-generation.database.action",
						"create");
		return factory;
	}
}
