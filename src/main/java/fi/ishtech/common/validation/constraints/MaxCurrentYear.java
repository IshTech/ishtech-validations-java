package fi.ishtech.common.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import fi.ishtech.common.validation.constraints.MaxCurrentYear.List;
import fi.ishtech.common.validation.validator.MaxCurrentYearValidator;

/**
 * Validates that a numeric value does not exceed the current calendar year.<br>
 * <br>
 * Supported types are {@code short}, {@code int}, {@code long} and their respective wrapper types.<br>
 * {@code null} elements are considered valid. Use {@code @NotNull} additionally if a null check is required.<br>
 *
 * @author Muneer Ahmed Syed
 */
@Retention(RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Documented
@Constraint(validatedBy = MaxCurrentYearValidator.class)
@Repeatable(List.class)
public @interface MaxCurrentYear {

	String message() default "{fi.ishtech.common.validation.constraints.MaxCurrentYear.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several {@code @MaxCurrentYear} constraints on the same element.
	 *
	 * @see MaxCurrentYear
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		MaxCurrentYear[] value();
	}
}
