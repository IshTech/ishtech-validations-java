package fi.ishtech.common.validation.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import fi.ishtech.common.validation.constraints.MaxCurrentYear;

class MaxCurrentYearValidatorTest {

	private static final int CURRENT_YEAR = LocalDate.now().getYear();

	/**
	 * Creates a validator initialized from the {@code @MaxCurrentYear} on the {@code year} field of the given test class.
	 */
	private static MaxCurrentYearValidator validatorFor(Object testClass) throws NoSuchFieldException {
		MaxCurrentYear annotation = testClass.getClass().getDeclaredField("year").getAnnotation(MaxCurrentYear.class);
		MaxCurrentYearValidator validator = new MaxCurrentYearValidator();
		validator.initialize(annotation);
		return validator;
	}

	@Nested
	@DisplayName("@MaxCurrentYear (inclusive by default)")
	class Default {

		@MaxCurrentYear
		private Integer year;

		@Test
		@DisplayName("currentYear - 1 is valid")
		void currentYearMinus1IsValid() throws NoSuchFieldException {
			assertTrue(validatorFor(this).isValid(CURRENT_YEAR - 1, null));
		}

		@Test
		@DisplayName("currentYear is valid")
		void currentYearIsValid() throws NoSuchFieldException {
			assertTrue(validatorFor(this).isValid(CURRENT_YEAR, null));
		}

		@Test
		@DisplayName("currentYear + 1 is invalid")
		void currentYearPlus1IsInvalid() throws NoSuchFieldException {
			assertFalse(validatorFor(this).isValid(CURRENT_YEAR + 1, null));
		}

	}

	@Nested
	@DisplayName("@MaxCurrentYear(inclusive = true)")
	class InclusiveTrue {

		@MaxCurrentYear(inclusive = true)
		private Integer year;

		@Test
		@DisplayName("currentYear - 1 is valid")
		void currentYearMinus1IsValid() throws NoSuchFieldException {
			assertTrue(validatorFor(this).isValid(CURRENT_YEAR - 1, null));
		}

		@Test
		@DisplayName("currentYear is valid")
		void currentYearIsValid() throws NoSuchFieldException {
			assertTrue(validatorFor(this).isValid(CURRENT_YEAR, null));
		}

		@Test
		@DisplayName("currentYear + 1 is invalid")
		void currentYearPlus1IsInvalid() throws NoSuchFieldException {
			assertFalse(validatorFor(this).isValid(CURRENT_YEAR + 1, null));
		}

	}

	@Nested
	@DisplayName("@MaxCurrentYear(inclusive = false)")
	class InclusiveFalse {

		@MaxCurrentYear(inclusive = false)
		private Integer year;

		@Test
		@DisplayName("currentYear - 1 is valid")
		void currentYearMinus1IsValid() throws NoSuchFieldException {
			assertTrue(validatorFor(this).isValid(CURRENT_YEAR - 1, null));
		}

		@Test
		@DisplayName("currentYear is invalid")
		void currentYearIsInvalid() throws NoSuchFieldException {
			assertFalse(validatorFor(this).isValid(CURRENT_YEAR, null));
		}

		@Test
		@DisplayName("currentYear + 1 is invalid")
		void currentYearPlus1IsInvalid() throws NoSuchFieldException {
			assertFalse(validatorFor(this).isValid(CURRENT_YEAR + 1, null));
		}

	}

	@Nested
	@DisplayName("@MaxCurrentYear with null, short and long values")
	class ValueTypes {

		@MaxCurrentYear
		private Integer year;

		@Test
		@DisplayName("null is valid")
		void nullIsValid() throws NoSuchFieldException {
			assertTrue(validatorFor(this).isValid(null, null));
		}

		@Test
		@DisplayName("short: currentYear is valid, currentYear + 1 is invalid")
		void shortIsSupported() throws NoSuchFieldException {
			MaxCurrentYearValidator validator = validatorFor(this);

			assertTrue(validator.isValid((short) CURRENT_YEAR, null));
			assertFalse(validator.isValid((short) (CURRENT_YEAR + 1), null));
		}

		@Test
		@DisplayName("long: currentYear is valid, currentYear + 1 is invalid")
		void longIsSupported() throws NoSuchFieldException {
			MaxCurrentYearValidator validator = validatorFor(this);

			assertTrue(validator.isValid((long) CURRENT_YEAR, null));
			assertFalse(validator.isValid((long) CURRENT_YEAR + 1, null));
		}

		@Test
		@DisplayName("long beyond int range is invalid (no int overflow)")
		void longBeyondIntRangeIsInvalid() throws NoSuchFieldException {
			// Regression test: narrowing to int (e.g. via Number#intValue()) would overflow and wrap
			// this value to a small/negative number, incorrectly passing validation.
			long farFuture = (long) Integer.MAX_VALUE + 1_000L;

			assertFalse(validatorFor(this).isValid(farFuture, null));
		}

	}

}
