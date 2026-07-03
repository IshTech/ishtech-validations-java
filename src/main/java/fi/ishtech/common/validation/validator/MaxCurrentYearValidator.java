package fi.ishtech.common.validation.validator;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import fi.ishtech.common.validation.constraints.MaxCurrentYear;

/**
 * Checks that a given {@code Number} (e.g. {@code short}, {@code int}, {@code long} or their wrapper types) does not
 * exceed the current calendar year.<br>
 * {@code null} elements are considered valid.<br>
 *
 * @author Muneer Ahmed Syed
 */
public class MaxCurrentYearValidator implements ConstraintValidator<MaxCurrentYear, Number> {

	private boolean inclusive = true;

	@Override
	public void initialize(MaxCurrentYear constraintAnnotation) {
		this.inclusive = constraintAnnotation.inclusive();
	}

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		long currentYear = LocalDate.now().getYear();
		return inclusive ? value.longValue() <= currentYear : value.longValue() < currentYear;
	}
}