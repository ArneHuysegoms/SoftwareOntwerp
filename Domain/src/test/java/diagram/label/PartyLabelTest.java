package diagram.label;

import exceptions.DomainException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartyLabelTest {

    private static final String VALIDLABEL = "a:A";
    private static final String INVALIDLABEL = "a";

    @Test
    public void Test_constructor_with_valid_label_works() throws DomainException{
        Label label = new PartyLabel(VALIDLABEL);
        assertEquals(VALIDLABEL, label.getLabel());
    }

    @Test (expected = DomainException.class)
    public void Test_constructor_with_invalid_label_throws_exception() throws DomainException{
        Label label = new PartyLabel(INVALIDLABEL);
    }


}
