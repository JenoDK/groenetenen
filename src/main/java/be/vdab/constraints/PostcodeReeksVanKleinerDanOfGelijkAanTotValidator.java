package be.vdab.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.valueobjects.PostcodeReeks;

public class PostcodeReeksVanKleinerDanOfGelijkAanTotValidator
		implements
		ConstraintValidator<PostcodeReeksVanKleinerDanOfGelijkAanTot, PostcodeReeks> {
	@Override
	public void initialize(PostcodeReeksVanKleinerDanOfGelijkAanTot arg0) {
	}

	@Override
	public boolean isValid(PostcodeReeks reeks,
			ConstraintValidatorContext context) {
		if (reeks.getVanpostcode() == null || reeks.getTotpostcode() == null) {
			return true;
		}
		return reeks.getVanpostcode() <= reeks.getTotpostcode();
	}
}
