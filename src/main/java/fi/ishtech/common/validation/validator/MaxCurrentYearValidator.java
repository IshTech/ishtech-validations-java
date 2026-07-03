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

	@Override
	public boolean isValid(Number value, ConstraintValidatorContext context) {
		return value == null || value.longValue() <= LocalDate.now().getYear();
	}
}
