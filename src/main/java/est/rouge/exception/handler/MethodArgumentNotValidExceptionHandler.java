package est.rouge.exception.handler;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import est.rouge.common.dto.CommonError;
import est.rouge.common.dto.CommonResponse;

/**
 * Class handle exception to be thrown when validation on an argument annotated with @Valid fails
 * 
 * @author tailocphanhuu
 */
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {
    /**
     * Message Source
     */
    @Autowired
    private MessageSource msg;

    /**
     * Handle exception to be thrown when validation on an argument annotated
     * with @Valid fails
     * 
     * @param methodArgumentNotValidException
     * @return ResponseEntity<CommonResonse<Object>>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException methodArgumentNotValidException) {
        System.out.println("Error: =======MethodArgumentNotValidException========");
        CommonResponse<Object> commonResponse = new CommonResponse<>();

        BindingResult result = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrorList = result.getFieldErrors();
        for (FieldError fieldError : fieldErrorList) {
            String code = fieldError.getDefaultMessage();
            String message = msg.getMessage(fieldError.getDefaultMessage(), null, LocaleContextHolder.getLocale());
            String item = fieldError.getField();
            commonResponse.getErrors().add(new CommonError(code, message, item));
        }
        commonResponse.getErrors().sort(Comparator.comparing(CommonError::getItem).thenComparing(CommonError::getCode));
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }
}
