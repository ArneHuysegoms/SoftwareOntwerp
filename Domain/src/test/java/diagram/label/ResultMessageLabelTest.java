package diagram.label;

import exceptions.DomainException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultMessageLabelTest {

    @Test
    public void Test_constructor_resultMessageLabel() throws DomainException {
        String strLabel = "Every possible label is valid";
        Label label = new ResultMessageLabel(strLabel);
        assertEquals(strLabel, label.getLabel());
    }
}
