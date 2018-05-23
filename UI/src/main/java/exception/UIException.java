package exception;

/**
 * exception for UI
 */
public class UIException extends Exception {

    /**
     * default ui exception
     */
    public UIException() {
        super();
    }

    /**
     * ui exception with message
     *
     * @param message message for this exception
     */
    public UIException(String message) {
        super(message);
    }
}
