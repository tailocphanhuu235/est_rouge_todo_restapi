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
import est.rouge.exception.Http404Exception;

/**
 * Class handle Not Found Exception
 * 
 * @author tailocphanhuu
 */
@ControllerAdvice
public class Http404ExceptionHandler {
    /**
     * Message Source
     */
    @Autowired
    private MessageSource msg;

    /**
     * Handle Not Found exception
     * 
     * @param http404Exception
     * @return ResponseEntity<CommonResonse<Object>>
     */
    @ExceptionHandler(Http404Exception.class)
    public ResponseEntity<?> handleException(Http404Exception http404Exception) {
        System.out.println("Error: =======Http404Exception========");
        CommonResponse<Object> commonResponse = new CommonResponse<>();
        commonResponse.getErrors().add(new CommonError(http404Exception.getMessage(),
                msg.getMessage(http404Exception.getMessage(), null, LocaleContextHolder.getLocale()), null));
        return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
    }
}
