package diagram.label;

import exceptions.DomainException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageLabelTest {

    private static final String VALIDLABEL = "message";
    private static final String INVALIDLABEL = "Message";

    @Test
    public void Test_constructor_with_valid_label_works() throws DomainException{
        Label label = new MessageLabel(VALIDLABEL);
        assertEquals(VALIDLABEL, label.getLabel());
    }

    @Test (expected = DomainException.class)
    public void Test_constructor_with_invalid_label_throws_exception() throws DomainException{
        Label label = new MessageLabel(INVALIDLABEL);
    }
}
