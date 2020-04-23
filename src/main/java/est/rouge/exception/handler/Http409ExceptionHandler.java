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
import est.rouge.exception.Http409Exception;

/**
 * Class handle Conflict Exception
 * 
 * @author tailocphanhuu
 */
@ControllerAdvice
public class Http409ExceptionHandler {
    /**
     * Message Source
     */
    @Autowired
    private MessageSource msg;

    /**
     * Handle Conflict exception
     * 
     * @param http409Exception
     * @return ResponseEntity<CommonResonse<Object>>
     */
    @ExceptionHandler(Http409Exception.class)
    public ResponseEntity<?> handleException(Http409Exception http409Exception) {
        System.out.println("Error: =======Http409Exception========");
        CommonResponse<Object> commonResponse = new CommonResponse<>();
        commonResponse.getErrors().add(new CommonError(http409Exception.getMessage(),
                msg.getMessage(http409Exception.getMessage(), null, LocaleContextHolder.getLocale()), null));
        return new ResponseEntity<>(commonResponse, HttpStatus.CONFLICT);
    }
}
