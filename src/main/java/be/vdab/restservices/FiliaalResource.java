package be.vdab.restservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ResourceSupport;

import be.vdab.entities.Filiaal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
class FiliaalResource extends ResourceSupport {
	@SuppressWarnings("unused")
	private Filiaal filiaal;

	FiliaalResource() {
	} // JAXB heeft een default constructor nodig

	FiliaalResource(Filiaal filiaal, EntityLinks entityLinks) {
		this.filiaal = filiaal;
		this.add(entityLinks.linkToSingleResource(Filiaal.class,
				filiaal.getId()));
		this.add(entityLinks
				.linkForSingleResource(Filiaal.class, filiaal.getId())
				.slash("werknemers").withRel("werknemers"));
	}
}
