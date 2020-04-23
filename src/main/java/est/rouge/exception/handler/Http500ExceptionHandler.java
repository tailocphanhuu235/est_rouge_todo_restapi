package est.rouge.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import est.rouge.common.dto.CommonError;
import est.rouge.common.dto.CommonResponse;
import est.rouge.exception.Http500Exception;

/**
 * Class handle Internal Server Error Exception
 * 
 * @author tailocphanhuu
 */
@ControllerAdvice
public class Http500ExceptionHandler {
    /**
     * Message Source
     */
    @Autowired
    private MessageSource msg;

    /**
     * Handle Internal Server Error exception
     * 
     * @param http500Exception
     * @return ResponseEntity<CommonResonse<Object>>
     */
    @ExceptionHandler(Http500Exception.class)
    public ResponseEntity<?> handleException(Http500Exception http500Exception) {
        System.out.println("Error: =======Http500Exception========");
        CommonResponse<Object> commonResponse = new CommonResponse<>();
        commonResponse.getErrors().add(new CommonError(http500Exception.getMessage(),
                msg.getMessage(http500Exception.getMessage(), null, LocaleContextHolder.getLocale()), null));
        return new ResponseEntity<>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
