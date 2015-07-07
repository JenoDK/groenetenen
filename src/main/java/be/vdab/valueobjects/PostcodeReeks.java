package be.vdab.valueobjects;

import javax.validation.constraints.NotNull;

import be.vdab.constraints.Postcode;
import be.vdab.constraints.PostcodeReeksVanKleinerDanOfGelijkAanTot;

@PostcodeReeksVanKleinerDanOfGelijkAanTot
public class PostcodeReeks {
	@NotNull
	@Postcode
	private Integer vanpostcode;
	@NotNull
	@Postcode
	private Integer totpostcode;

	public Integer getVanpostcode() {
		return vanpostcode;
	}

	public Integer getTotpostcode() {
		return totpostcode;
	}

	public boolean bevat(Integer postcode) {
		// bevat de reeks een bepaalde postcode ? (wordt gebuikt in de DAO
		// layer)
		return postcode >= vanpostcode && postcode <= totpostcode;
	}
}
