package window.elements;

import command.Command;
import command.changeType.DiagramCommand.ChangeToSequenceCommand;
import exception.UIException;
import org.junit.Test;
import window.elements.radiobutton.ToSequenceRadioButton;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class ToSequenceRadioButtonTest {
    @Test
    public void test_constructor() throws UIException {
        Command command = new ChangeToSequenceCommand(null);
        ToSequenceRadioButton tarb = new ToSequenceRadioButton(command, new Point2D.Double(50,50), "RadioButton");
        assertEquals(tarb.getCommand(), command);
        assertEquals(tarb.getCoordinate(), new Point2D.Double(50,50));
    }

    @Test (expected = UIException.class)
    public void test_empty_point_throws_exception() throws UIException{
        Command command = new ChangeToSequenceCommand(null);
        ToSequenceRadioButton tarb = new ToSequenceRadioButton(command, null, "RadioButton");
    }

    @Test
    public void test_isClicked() throws UIException{
        Command command = new ChangeToSequenceCommand(null);
        ToSequenceRadioButton tarb = new ToSequenceRadioButton(command, new Point2D.Double(50,50), "RadioButton");
        assertTrue(tarb.isClicked(new Point2D.Double(60, 60)));
        assertTrue(tarb.isClicked(new Point2D.Double(50, 50)));
        assertFalse(tarb.isClicked(new Point2D.Double(49, 49)));
    }
}
