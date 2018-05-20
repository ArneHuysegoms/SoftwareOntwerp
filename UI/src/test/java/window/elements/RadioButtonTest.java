package window.elements;

import command.Command;
import command.changeType.ChangeToActorCommand;
import exception.UIException;
import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

public class RadioButtonTest {

    @Test
    public void test_constructor() throws UIException {
        Command command = new ChangeToActorCommand(null, null);
        RadioButton r = new RadioButton(command, new Point2D.Double(50,50), "RadioButton");
        assertEquals(r.getCommand(), command);
        assertEquals(r.getCoordinate(), new Point2D.Double(50,50));
    }

    @Test (expected = UIException.class)
    public void test_empty_point_throws_exception() throws UIException{
        Command command = new ChangeToActorCommand(null, null);
        RadioButton r = new RadioButton(command, null, "RadioButton");
    }

    @Test
    public void test_isClicked() throws UIException{
        Command command = new ChangeToActorCommand(null, null);
        RadioButton r = new RadioButton(command, new Point2D.Double(50,50), "RadioButton");
        assertTrue(r.isClicked(new Point2D.Double(60, 60)));
        assertTrue(r.isClicked(new Point2D.Double(50, 50)));
        assertFalse(r.isClicked(new Point2D.Double(49, 49)));
    }
}
