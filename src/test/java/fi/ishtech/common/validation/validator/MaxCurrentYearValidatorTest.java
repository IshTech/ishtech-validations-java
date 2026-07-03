package fi.ishtech.common.validation.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class MaxCurrentYearValidatorTest {

	private final MaxCurrentYearValidator validator = new MaxCurrentYearValidator();

	@Test
	void nullIsValid() {
		assertTrue(validator.isValid(null, null));
	}

	@Test
	void intValueIsSupported() {
		int currentYear = LocalDate.now().getYear();

		assertTrue(validator.isValid(currentYear, null));
		assertTrue(validator.isValid(1999, null));
		assertFalse(validator.isValid(currentYear + 1, null));
	}

	@Test
	void shortValueIsSupported() {
		short currentYear = (short) LocalDate.now().getYear();
		short nextYear = (short) (currentYear + 1);

		assertTrue(validator.isValid(currentYear, null));
		assertFalse(validator.isValid(nextYear, null));
	}

	@Test
	void longValueIsSupported() {
		long currentYear = LocalDate.now().getYear();
		long nextYear = currentYear + 1;

		assertTrue(validator.isValid(currentYear, null));
		assertFalse(validator.isValid(nextYear, null));
	}

	@Test
	void longValueBeyondIntRangeIsInvalid() {
		// Regression test: narrowing to int (e.g. via Number#intValue()) would overflow and wrap
		// this value to a small/negative number, incorrectly passing validation.
		long farFuture = (long) Integer.MAX_VALUE + 1_000L;

		assertFalse(validator.isValid(farFuture, null));
	}
}
