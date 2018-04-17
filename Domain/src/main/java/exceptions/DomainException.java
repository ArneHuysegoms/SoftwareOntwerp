package exceptions;

/**
 * class used for throwing Domain level exceptions
 */
public class DomainException extends Exception{

    /**
     * Messsageless error
     */
    public DomainException(){

    }

    /**
     * error containing a message
     *
     * @param message the message containing details about the error
     */
    public DomainException(String message){
        super(message);
    }
}
