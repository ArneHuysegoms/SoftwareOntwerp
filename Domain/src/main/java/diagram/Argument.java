package diagram;

import exceptions.DomainException;

import java.util.Collections;

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

    /**
     * @param instance
     *        The instance string
     * @param clas
     *        The class string
     * @return
     *        True if label is of the form instanceName:classname (instanceName optional), or empty for empty strings
     */
    public static boolean isValidArgument(String instance, String clas){
        if(instance.isEmpty() || clas.isEmpty()){
            return false;
        }
        else{
            return Character.isLowerCase(instance.charAt(0)) && Character.isUpperCase(clas.charAt(0));
        }
    }
}
