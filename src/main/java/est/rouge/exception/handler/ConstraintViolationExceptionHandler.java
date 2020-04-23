package est.rouge.exception.handler;

import java.util.Comparator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import est.rouge.common.dto.CommonError;
import est.rouge.common.dto.CommonResponse;

/**
 * Class handle exception constraint violations
 * 
 * @author tailocphanhuu
 */
@ControllerAdvice
public class ConstraintViolationExceptionHandler {

    /**
     * Message Source
     */
    @Autowired
    private MessageSource msg;

    /**
     * Handle exception constraint violations
     * 
     * @param constraintviolationException
     * @return ResponseEntity<CommonResonse<Object>>
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleException(ConstraintViolationException constraintviolationException) {
        System.out.println("Error: =======ConstraintViolationException========");
        CommonResponse<Object> commonResponse = new CommonResponse<>();
        Set<ConstraintViolation<?>> violationSet = constraintviolationException.getConstraintViolations();
        for (ConstraintViolation<?> violation : violationSet) {
            String code = violation.getMessage();
            String message = msg.getMessage(violation.getMessage(), null, LocaleContextHolder.getLocale());
            String item = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
            commonResponse.getErrors().add(new CommonError(code, message, item));
        }
        commonResponse.getErrors().sort(Comparator.comparing(CommonError::getItem).thenComparing(CommonError::getCode));
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }
}
