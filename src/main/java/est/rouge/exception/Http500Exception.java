package est.rouge.exception;

/**
 * Class represents for Internal Server Error exception(INTERNAL_SERVER_ERROR(500, "Internal Server Error"))
 * 
 * @author tailocphanhuu
 */
@SuppressWarnings("serial")
public class Http500Exception extends GlobalException {
    /**
     * Constructor
     * 
     * @param message
     *            message content
     */
    public Http500Exception(String message) {
        super(message);
    }
}
