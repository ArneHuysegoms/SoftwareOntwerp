package diagram;

import exceptions.DomainException;

public class Argument {
    private String argumentInstance;
    private String argumentClass;

    /**
     * @param argumentInstance
     *        The instancename of this argument
     *
     * @param argumentClass
     *        The classname of this argument
     */
    public Argument(String argumentInstance, String argumentClass) {
        this.setArgumentInstance(argumentInstance);
        this.setArgumentClass(argumentClass);
    }

    /**
     * @param argumentInstance
     *        The instancename of this argument
     */
    public void setArgumentInstance(String argumentInstance) {
        this.argumentInstance = argumentInstance;
    }

    /**
     * @param argumentClass
     *        The classname of this argument
     */
    public void setArgumentClass(String argumentClass) {
        this.argumentClass = argumentClass;
    }

    /**
     *
     * @return this argument's instance
     */
    public String getArgumentInstance() {
        return argumentInstance;
    }

    /**
     *
     * @return this argument's class
     */
    public String getArgumentClass() {
        return argumentClass;
    }

    /**
     * @return the argument in the right format (instance: class)
     */
    @Override
    public String toString() {
        return this.getArgumentInstance() + ": " + this.getArgumentClass();
    }
}
