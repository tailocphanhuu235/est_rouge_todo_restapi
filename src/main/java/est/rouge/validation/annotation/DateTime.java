package est.rouge.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

import est.rouge.common.constants.Constants;
import est.rouge.validation.DateTimeValidate;

/**
 * Annotaion date time
 * 
 * @author tailocphanhuu
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { DateTimeValidate.class })
public @interface DateTime {
    /**
     * Error message
     * 
     * @return error message
     */
    String message() default "date is invalid, must be format yyyy/MM/dd";

    /**
     * Regex pattern for validation date time
     * 
     * @return regex pattern string
     */
    String regrexp() default Constants.DATE_FORMAT_YYYY_MM_DD;

    /**
     * Groups
     * 
     * @return Generic
     */
    Class<?>[] groups() default {};

    /**
     * Payload
     * 
     * @return generic
     */
    Class<? extends Payload>[] payload() default {};
}
