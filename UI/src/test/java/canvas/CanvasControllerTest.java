package canvas;
import diagram.Clickable;
import diagram.CommunicationsDiagram;
import diagram.Diagram;
import diagram.SequenceDiagram;
import diagram.label.PartyLabel;
import diagram.party.Actor;
import diagram.party.Party;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import uievents.MouseEvent;
import uievents.MouseEventType;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;
public class CanvasControllerTest {

    private CanvasController canvasController;

    @Before
    public void setUp() {
        canvasController = new CanvasController();
    }

    @Test
    public void activeDiagram_is_sequence_after_init(){
        assertTrue(canvasController.getActiveDiagram() instanceof SequenceDiagram);
    }

    @Test
    public void test_changeActiveDiagram(){
        canvasController.changeActiveDiagram();
        assertTrue(canvasController.getActiveDiagram() instanceof CommunicationsDiagram);
    }

    @Test
    public void test_changeActiveDiagram_twice(){
        canvasController.changeActiveDiagram();
        canvasController.changeActiveDiagram();
        assertTrue(canvasController.getActiveDiagram() instanceof SequenceDiagram);
    }

    @Test
    public void test_changeActiveDiagram_three_times(){
        canvasController.changeActiveDiagram();
        canvasController.changeActiveDiagram();
        canvasController.changeActiveDiagram();
        assertTrue(canvasController.getActiveDiagram() instanceof CommunicationsDiagram);
    }

    @Test
    public void test_handleKeyEvent_tab(){
        Diagram d = canvasController.getActiveDiagram();
        KeyEventType type = KeyEventType.TAB;
        KeyEvent keyEvent = new KeyEvent(type);
        canvasController.handleKeyEvent(keyEvent);
        Diagram d2 = canvasController.getActiveDiagram();
        assertNotEquals(d.getClass(), d2.getClass());
    }

    @Test
    public void test_handleKeyEvent_deleteParty(){
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getActiveDiagram().addCharToLabel(':');
        canvasController.getActiveDiagram().addCharToLabel('S');
        Clickable c = canvasController.getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasController.handleKeyEvent(new KeyEvent(KeyEventType.DEL));
        assertFalse(canvasController.getActiveDiagram().getParties().contains(c));
    }

    @Test
    public void test_handleKeyEvent_add_char_in_valid_label(){
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getActiveDiagram().addCharToLabel(':');
        canvasController.getActiveDiagram().addCharToLabel('S');
        canvasController.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        assertEquals(canvasController.getActiveDiagram().getLabelContainer(), ":Sa");
    }
    @Test
    public void test_handleKeyEvent_add_colon_in_valid_label(){
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        assertEquals(canvasController.getActiveDiagram().getLabelContainer(), ":");
    }
    @Test
    public void test_handleKeyEvent_backspace_in_valid_label(){
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getActiveDiagram().addCharToLabel(':');
        canvasController.getActiveDiagram().addCharToLabel('S');
        canvasController.getActiveDiagram().addCharToLabel('a');
        canvasController.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals(canvasController.getActiveDiagram().getLabelContainer(), ":S");
    }

    @Test
    public void test_handleKeyEvent_add_char_in_invalid_label(){
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getActiveDiagram().addCharToLabel(':');
        canvasController.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'S'));
        assertEquals(canvasController.getActiveDiagram().getLabelContainer(), ":S");
    }

    @Test
    public void test_handleKeyEvent_backspace_in_invalid_label(){
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getActiveDiagram().addCharToLabel(':');
        canvasController.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals(canvasController.getActiveDiagram().getLabelContainer(), "");
    }

    @Test
    public void test_handleMouseEvent_drag(){
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getActiveDiagram().addCharToLabel(':');
        canvasController.getActiveDiagram().addCharToLabel('S');
        Clickable c = canvasController.getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.DRAG, new Point2D.Double(25,51)));
        Clickable c1 = canvasController.getActiveDiagram().findSelectedElement(new Point2D.Double(25,51));
        assertTrue(c.equals(c1));
    }

    @Test
    public void test_handleMouseEvent_pressed_away_from_invalidLabel(){
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(25,50)));
        assertTrue(canvasController.getActiveDiagram().getSelectedElement() instanceof PartyLabel);
    }

    @Test
    public void test_handleMouseEvent_pressed_validLabel_of_object(){
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getActiveDiagram().addCharToLabel(':');
        canvasController.getActiveDiagram().addCharToLabel('S');
        canvasController.getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, ((Party) canvasController.getActiveDiagram().getSelectedElement()).getLabel().getCoordinate()));
        assertFalse(canvasController.getActiveDiagram().getSelectedElement() instanceof PartyLabel);

    }

    @Test
    public void test_handleMouseEvent_pressed_other_object_from_object(){
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getActiveDiagram().addCharToLabel(':');
        canvasController.getActiveDiagram().addCharToLabel('S');
        Clickable c = canvasController.getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(80,50));
        canvasController.getActiveDiagram().addCharToLabel(':');
        canvasController.getActiveDiagram().addCharToLabel('L');
        canvasController.getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED,new Point2D.Double(25,50)));
        assertEquals(canvasController.getActiveDiagram().getSelectedElement(), c);

    }

    /*@Test
    public void test_handleMouseEvent_released(){

    }*/

    @Test
    public void test_handleMouseEvent_leftClick(){
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getActiveDiagram().addCharToLabel(':');
        canvasController.getActiveDiagram().addCharToLabel('S');
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.LEFTCLICK, new Point2D.Double(25,50)));
        assertTrue(canvasController.getActiveDiagram().getSelectedElement() instanceof Party);
    }

    @Test
    public void test_handleMouseEvent_leftDoubleClick_changePartyType(){
        canvasController.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getActiveDiagram().addCharToLabel(':');
        canvasController.getActiveDiagram().addCharToLabel('S');
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.LEFTCLICK, new Point2D.Double(25,50)));
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.LEFTDOUBLECLICK, new Point2D.Double(25,50)));
        assertTrue(canvasController.getActiveDiagram().getSelectedElement() instanceof Actor);
    }

    @Test
    public void test_handleMouseEvent_leftDoubleClick_addParty(){
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.LEFTDOUBLECLICK, new Point2D.Double(25,50)));
        canvasController.getActiveDiagram().addCharToLabel(':');
        canvasController.getActiveDiagram().addCharToLabel('S');
        Clickable c = canvasController.getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        assertTrue(canvasController.getActiveDiagram().getParties().contains(c));
    }

}