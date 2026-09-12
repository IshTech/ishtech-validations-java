package fi.ishtech.common.validation.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import fi.ishtech.common.validation.constraints.MaxCurrentYear;

@TestMethodOrder(OrderAnnotation.class)
class MaxCurrentYearValidatorTest {

	private final int currentYear = LocalDate.now().getYear();

	@MaxCurrentYear
	private Integer inclusiveYear;

	@MaxCurrentYear(inclusive = false)
	private Integer exclusiveYear;

	private MaxCurrentYearValidator validatorFor(String fieldName) throws NoSuchFieldException {
		MaxCurrentYear annotation = getClass().getDeclaredField(fieldName).getAnnotation(MaxCurrentYear.class);
		MaxCurrentYearValidator validator = new MaxCurrentYearValidator();
		validator.initialize(annotation);
		return validator;
	}

	@Test
	@Order(1)
	void nullIsValid() throws NoSuchFieldException {
		assertTrue(validatorFor("inclusiveYear").isValid(null, null));
	}

	@Test
	@Order(2)
	void shortIsSupported() throws NoSuchFieldException {
		assertTrue(validatorFor("inclusiveYear").isValid((short) (currentYear - 1), null));
	}

	@Test
	@Order(3)
	void intIsSupported() throws NoSuchFieldException {
		assertTrue(validatorFor("inclusiveYear").isValid(currentYear - 1, null));
	}

	@Test
	@Order(4)
	void longIsSupported() throws NoSuchFieldException {
		assertTrue(validatorFor("inclusiveYear").isValid((long) (currentYear - 1), null));
	}

	@Test
	@Order(5)
	void whenInclusive_thenPreviousYearIsValid() throws NoSuchFieldException {
		assertTrue(validatorFor("inclusiveYear").isValid(currentYear - 1, null));
	}

	@Test
	@Order(6)
	void whenInclusive_thenCurrentYearIsValid() throws NoSuchFieldException {
		assertTrue(validatorFor("inclusiveYear").isValid(currentYear, null));
	}

	@Test
	@Order(7)
	void whenInclusive_thenNextYearIsInvalid() throws NoSuchFieldException {
		assertFalse(validatorFor("inclusiveYear").isValid(currentYear + 1, null));
	}

	@Test
	@Order(8)
	void whenExclusive_thenPreviousYearIsValid() throws NoSuchFieldException {
		assertTrue(validatorFor("exclusiveYear").isValid(currentYear - 1, null));
	}

	@Test
	@Order(9)
	void whenExclusive_thenCurrentYearIsInvalid() throws NoSuchFieldException {
		assertFalse(validatorFor("exclusiveYear").isValid(currentYear, null));
	}

	@Test
	@Order(10)
	void whenExclusive_thenNextYearIsInvalid() throws NoSuchFieldException {
		assertFalse(validatorFor("exclusiveYear").isValid(currentYear + 1, null));
	}

}