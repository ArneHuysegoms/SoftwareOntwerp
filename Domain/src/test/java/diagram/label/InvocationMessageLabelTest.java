package diagram.label;

import exceptions.DomainException;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class InvocationMessageLabelTest {
    private static final String VALIDNAME = "invocation_message_1";
    private static final String VALIDNAME2 = "invocationMessage2";
    private static final String INVALIDNAME = "Invocation-message!";

    @Test
    public void Test_constructor() throws DomainException {
        String argumentInstance1 = "instance1";
        String argumentInstance2 = "instance2";;
        ArrayList<String> argumentsList = new ArrayList<>();
        argumentsList.add(argumentInstance1);
        argumentsList.add(argumentInstance2);

        Label label = new InvocationMessageLabel(VALIDNAME, argumentsList);
        Label label2 = new InvocationMessageLabel(VALIDNAME2, argumentsList);
        assertEquals(VALIDNAME, label.getLabel());
        assertEquals(VALIDNAME2, label2.getLabel());
        assertEquals(argumentsList, ((InvocationMessageLabel) label).getArguments());
        assertEquals(argumentsList, ((InvocationMessageLabel) label2).getArguments());

    }

    @Test (expected = DomainException.class)
    public void Test_constructor_invalid_exception() throws DomainException{
        Label label = new InvocationMessageLabel(INVALIDNAME, new ArrayList<>());
    }

    @Test
    public void test_valid_complete_label() throws DomainException{
        String argumentInstance1 = "instance1";
        String argumentInstance2 = "instance2";
        ArrayList<String> argumentsList = new ArrayList<>();
        argumentsList.add(argumentInstance1);
        argumentsList.add(argumentInstance2);

        InvocationMessageLabel label = new InvocationMessageLabel(VALIDNAME, argumentsList);
        String valid = "invocation_message_1(instance1:Class1,instance2:Class2)";
        assertTrue(label.isValidCompleteLabel(valid));
    }

    @Test
    public void test_set_complete_label() throws DomainException {
        String argumentInstance1 = "instance1";
        ArrayList<String> argumentsList = new ArrayList<>();
        argumentsList.add(argumentInstance1);

        InvocationMessageLabel label = new InvocationMessageLabel(VALIDNAME, argumentsList);
        String valid = "invocation_message_1(instance1)";
        label.setCompleteLabel(valid);
        assertEquals("invocation_message_1(instance1)", label.toString());
    }
}
