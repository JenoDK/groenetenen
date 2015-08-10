package be.vdab.restservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import be.vdab.entities.Filiaal;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
class FiliaalIdNaam {
	@XmlAttribute
	private long id;
	@XmlAttribute
	private String naam;

	FiliaalIdNaam() {
	} // JAXB heeft een default constructor nodig

	FiliaalIdNaam(Filiaal filiaal) {
		this.id = filiaal.getId();
		this.naam = filiaal.getNaam();
	}
}
