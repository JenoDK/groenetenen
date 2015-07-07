package be.vdab.constraints;

public class PostcodeValidator implements
		ConstraintValidator<Postcode, Integer> {
	private static final int MIN_POSTCODE = 1000;
	private static final int MAX_POSTCODE = 9999;

	@Override
	public void initialize(Postcode postcode) {
	}

	@Override
	public boolean isValid(Integer postcode, ConstraintValidatorContext context) {
		return postcode == null
				|| (postcode >= MIN_POSTCODE && postcode <= MAX_POSTCODE);
	}
}