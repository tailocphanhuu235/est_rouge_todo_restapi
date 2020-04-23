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
import est.rouge.exception.Http400Exception;

/**
 * Class handle Bad Request Exception
 * 
 * @author tailocphanhuu
 */
@ControllerAdvice
public class Http400ExceptionHandler {
    /**
     * Message Source
     */
    @Autowired
    private MessageSource msg;

    /**
     * Handle Bad Request exception
     * 
     * @param http400Exception
     * @return ResponseEntity<CommonResonse<Object>>
     */
    @ExceptionHandler(Http400Exception.class)
    public ResponseEntity<?> handleException(Http400Exception http400Exception) {
        System.out.println("Error: =======Http400Exception========");
        CommonResponse<Object> commonResponse = new CommonResponse<>();
        commonResponse.getErrors().add(new CommonError(http400Exception.getMessage(),
                msg.getMessage(http400Exception.getMessage(), null, LocaleContextHolder.getLocale()), null));
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }
}
