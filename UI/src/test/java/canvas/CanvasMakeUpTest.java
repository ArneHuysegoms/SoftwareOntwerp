package canvas;
import diagram.Clickable;
import diagram.CommunicationsDiagram;
import diagram.Diagram;
import diagram.SequenceDiagram;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import uievents.MouseEvent;
import uievents.MouseEventType;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;
public class CanvasMakeUpTest {

    private CanvasMakeUp canvasMakeUp;

    @Before
    public void setUp() {
        canvasMakeUp = new CanvasMakeUp();
    }

    @Test
    public void activeDiagram_is_sequence_after_init(){
        assertTrue(canvasMakeUp.getActiveDiagram() instanceof SequenceDiagram);
    }

    @Test
    public void test_changeActiveDiagram(){
        canvasMakeUp.changeActiveDiagram();
        assertTrue(canvasMakeUp.getActiveDiagram() instanceof CommunicationsDiagram);
    }

    @Test
    public void test_changeActiveDiagram_twice(){
        canvasMakeUp.changeActiveDiagram();
        canvasMakeUp.changeActiveDiagram();
        assertTrue(canvasMakeUp.getActiveDiagram() instanceof SequenceDiagram);
    }

    @Test
    public void test_handleKeyEvent_tab(){
        Diagram d = canvasMakeUp.getActiveDiagram();
        KeyEventType type = KeyEventType.TAB;
        KeyEvent keyEvent = new KeyEvent(type);
        canvasMakeUp.handleKeyEvent(keyEvent);
        Diagram d2 = canvasMakeUp.getActiveDiagram();
        assertNotEquals(d.getClass(), d2.getClass());
    }

    @Test
    public void test_handleKeyEvent_deleteParty(){
        canvasMakeUp.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasMakeUp.getActiveDiagram().addCharToLabel(':');
        canvasMakeUp.getActiveDiagram().addCharToLabel('S');
        Clickable c = canvasMakeUp.getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasMakeUp.handleKeyEvent(new KeyEvent(KeyEventType.DEL));
        assertFalse(canvasMakeUp.getActiveDiagram().getParties().contains(c));
    }

    @Test
    public void test_handleKeyEvent_add_char_in_valid_label(){
        canvasMakeUp.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasMakeUp.getActiveDiagram().addCharToLabel(':');
        canvasMakeUp.getActiveDiagram().addCharToLabel('S');
        canvasMakeUp.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        assertEquals(canvasMakeUp.getActiveDiagram().getLabelContainer(), ":Sa");
    }
    @Test
    public void test_handleKeyEvent_add_colon_in_valid_label(){
        canvasMakeUp.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasMakeUp.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        assertEquals(canvasMakeUp.getActiveDiagram().getLabelContainer(), ":");
    }
    @Test
    public void test_handleKeyEvent_backspace_in_valid_label(){
        canvasMakeUp.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasMakeUp.getActiveDiagram().addCharToLabel(':');
        canvasMakeUp.getActiveDiagram().addCharToLabel('S');
        canvasMakeUp.getActiveDiagram().addCharToLabel('a');
        canvasMakeUp.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals(canvasMakeUp.getActiveDiagram().getLabelContainer(), ":S");
    }

    @Test
    public void test_handleKeyEvent_add_char_in_invalid_label(){
        canvasMakeUp.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasMakeUp.getActiveDiagram().addCharToLabel(':');
        canvasMakeUp.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'S'));
        assertEquals(canvasMakeUp.getActiveDiagram().getLabelContainer(), ":S");
    }

    @Test
    public void test_handleKeyEvent_backspace_in_invalid_label(){
        canvasMakeUp.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasMakeUp.getActiveDiagram().addCharToLabel(':');
        canvasMakeUp.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals(canvasMakeUp.getActiveDiagram().getLabelContainer(), "");
    }

    @Test
    public void test_handleMouseEvent_drag(){
        canvasMakeUp.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasMakeUp.getActiveDiagram().addCharToLabel(':');
        canvasMakeUp.getActiveDiagram().addCharToLabel('S');
        Clickable c = canvasMakeUp.getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasMakeUp.handleMouseEvent(new MouseEvent(MouseEventType.DRAG, new Point2D.Double(25,51)));
        Clickable c1 = canvasMakeUp.getActiveDiagram().findSelectedElement(new Point2D.Double(25,51));
        assertTrue(c.equals(c1));
    }

}