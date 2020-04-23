package est.rouge.exception;

/**
 * Class represents for Bad Request exception(BAD_REQUEST(400, "Bad Request"))
 * 
 * @author tailocphanhuu
 */
@SuppressWarnings("serial")
public class Http400Exception extends GlobalException {
    /**
     * Constructor
     * 
     * @param message
     *            message content
     */
    public Http400Exception(String message) {
        super(message);
    }
}
