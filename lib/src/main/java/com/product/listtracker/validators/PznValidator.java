package com.product.listtracker.validators;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PznValidator implements ConstraintValidator<PznConstraint, String> {
	private static final int PZN_LENGTH = 8;
	private static final String CONTAINS_ONLY_NUMBERS_REGEX = "[0-9]+";
	
	@Override
	public boolean isValid(String pzn, ConstraintValidatorContext context) {
		if (Objects.isNull(pzn)) {
			return false;
		}
		if (pzn.length() != PZN_LENGTH) {
			return false;
		}
		if (!pzn.matches(CONTAINS_ONLY_NUMBERS_REGEX)) {
			return false;
		}
		return true;
	}

}
