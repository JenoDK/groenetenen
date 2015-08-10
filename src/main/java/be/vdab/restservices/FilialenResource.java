package be.vdab.restservices;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ResourceSupport;

import be.vdab.entities.Filiaal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
class FilialenResource extends ResourceSupport {
	@XmlElement(name = "filiaal")
	@JsonProperty("filialen")
	private List<FiliaalIdNaam> filialenIdNaam = new ArrayList<>();

	FilialenResource() {
	} // JAXB heeft een default constructor nodig

	FilialenResource(Iterable<Filiaal> filialen, EntityLinks entityLinks) {
		for (Filiaal filiaal : filialen) {
			this.filialenIdNaam.add(new FiliaalIdNaam(filiaal));
			this.add(entityLinks.linkToSingleResource(Filiaal.class,
					filiaal.getId()).withRel("Filiaal:" + filiaal.getId()));
		}
		this.add(entityLinks.linkToCollectionResource(Filiaal.class));
	}
}
