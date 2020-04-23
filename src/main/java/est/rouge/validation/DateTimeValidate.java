package est.rouge.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import est.rouge.common.utils.Utils;
import est.rouge.validation.annotation.DateTime;

/**
 * Class date time validation
 * 
 * @author tailocphanhuu
 */
public class DateTimeValidate implements ConstraintValidator<DateTime, String> {
    /***
     * Regex pattern
     */
    String regrexp;

    /**
     * Init
     * 
     * @param DateTimeCustomFormat
     */
    @Override
    public void initialize(DateTime dateTime) {
        regrexp = dateTime.regrexp();
    }

    /**
     * Check value
     * 
     * @param value
     *            value of date time
     * @param context
     *            constraint validator context
     * @return boolean
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Utils.isValidDateTimeFormat(regrexp, value);
    }
}
