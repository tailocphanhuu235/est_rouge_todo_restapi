package est.rouge.exception;

/**
 * Class represents for Conflict exception(CONFLICT(409, "Conflict"))
 * 
 * @author tailocphanhuu
 */
@SuppressWarnings("serial")
public class Http409Exception extends GlobalException {
    /**
     * Constructor
     * 
     * @param message
     *            message content
     */
    public Http409Exception(String message) {
        super(message);
    }
}
