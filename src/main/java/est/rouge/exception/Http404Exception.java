package est.rouge.exception;

/**
 * Class represents for Not Found exception(NOT_FOUND(404, "Not Found"))
 * 
 * @author tailocphanhuu
 */
@SuppressWarnings("serial")
public class Http404Exception extends GlobalException {
    /**
     * Constructor
     * 
     * @param message
     *            message content
     */
    public Http404Exception(String message) {
        super(message);
    }
}
