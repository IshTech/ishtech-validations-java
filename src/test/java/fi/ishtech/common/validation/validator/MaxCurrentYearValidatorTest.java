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
	void currentYearIsValid() {
		assertTrue(validator.isValid(LocalDate.now().getYear(), null));
	}

	@Test
	void pastYearIsValid() {
		assertTrue(validator.isValid(1999, null));
	}

	@Test
	void futureYearIsInvalid() {
		assertFalse(validator.isValid(LocalDate.now().getYear() + 1, null));
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
}
