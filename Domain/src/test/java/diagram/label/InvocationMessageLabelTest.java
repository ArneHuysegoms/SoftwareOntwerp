package diagram.label;

import diagram.Argument;
import exceptions.DomainException;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InvocationMessageLabelTest {
    private static final String VALIDNAME = "invocation_message_1";
    private static final String VALIDNAME2 = "invocationMessage2";
    private static final String INVALIDNAME = "Invocation-message!";

    @Test
    public void Test_constructor() throws DomainException {
        String argumentInstance1 = "instance1";
        String argumentInstance2 = "instance2";
        String argumentClass1 = "Class1";
        String argumentClass2 = "Class2";
        String argument1 = "instance1: Class1";
        String argument2 = "instance2: Class2";
        ArrayList<Argument> argumentsList = new ArrayList<>();
        Argument a1 = new Argument(argumentInstance1, argumentClass1);
        Argument a2 = new Argument(argumentInstance2, argumentClass2);
        argumentsList.add(a1);
        argumentsList.add(a2);

        Label label = new InvocationMessageLabel(VALIDNAME, argumentsList);
        Label label2 = new InvocationMessageLabel(VALIDNAME2, argumentsList);
        assertEquals(a1.toString(), argument1);
        assertEquals(a2.toString(), argument2);
        assertEquals(VALIDNAME, label.getLabel());
        assertEquals(VALIDNAME2, label2.getLabel());
        assertEquals(argumentsList, ((InvocationMessageLabel) label).getArguments());
        assertEquals(argumentsList, ((InvocationMessageLabel) label2).getArguments());

    }

    @Test (expected = DomainException.class)
    public void Test_constructor_invalid_exception() throws DomainException{
        Label label = new InvocationMessageLabel(INVALIDNAME, new ArrayList<>());
    }
}
