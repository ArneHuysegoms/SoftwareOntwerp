package canvas;
import diagram.Clickable;
import diagram.CommunicationsDiagram;
import diagram.Diagram;
import diagram.SequenceDiagram;
import diagram.label.PartyLabel;
import diagram.party.Actor;
import diagram.party.Party;
import exceptions.DomainException;
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
        assertTrue(canvasController.getFacade().getActiveDiagram() instanceof SequenceDiagram);
    }

    @Test
    public void test_changeActiveDiagram(){
        canvasController.getFacade().changeActiveDiagram();
        assertTrue(canvasController.getFacade().getActiveDiagram() instanceof CommunicationsDiagram);
    }

    @Test
    public void test_changeActiveDiagram_twice(){
        canvasController.getFacade().changeActiveDiagram();
        canvasController.getFacade().changeActiveDiagram();
        assertTrue(canvasController.getFacade().getActiveDiagram() instanceof SequenceDiagram);
    }

    @Test
    public void test_changeActiveDiagram_three_times(){
        canvasController.getFacade().changeActiveDiagram();
        canvasController.getFacade().changeActiveDiagram();
        canvasController.getFacade().changeActiveDiagram();
        assertTrue(canvasController.getFacade().getActiveDiagram() instanceof CommunicationsDiagram);
    }

    @Test
    public void test_handleKeyEvent_tab(){
        Diagram d = canvasController.getFacade().getActiveDiagram();
        KeyEventType type = KeyEventType.TAB;
        KeyEvent keyEvent = new KeyEvent(type);
        canvasController.handleKeyEvent(keyEvent);
        Diagram d2 = canvasController.getFacade().getActiveDiagram();
        assertNotEquals(d.getClass(), d2.getClass());
    }

    @Test
    public void test_handleKeyEvent_deleteParty(){
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getFacade().getActiveDiagram().addCharToLabel(':');
        canvasController.getFacade().getActiveDiagram().addCharToLabel('S');
        Clickable c = canvasController.getFacade().getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasController.handleKeyEvent(new KeyEvent(KeyEventType.DEL));
        assertFalse(canvasController.getFacade().getActiveDiagram().getParties().contains(c));
    }

    @Test
    public void test_handleKeyEvent_add_char_in_valid_label(){
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getFacade().getActiveDiagram().addCharToLabel(':');
        canvasController.getFacade().getActiveDiagram().addCharToLabel('S');
        canvasController.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        assertEquals(canvasController.getFacade().getActiveDiagram().getLabelContainer(), ":Sa");
    }
    @Test
    public void test_handleKeyEvent_add_colon_in_valid_label(){
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        assertEquals(canvasController.getFacade().getActiveDiagram().getLabelContainer(), ":");
    }
    @Test
    public void test_handleKeyEvent_backspace_in_valid_label(){
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getFacade().getActiveDiagram().addCharToLabel(':');
        canvasController.getFacade().getActiveDiagram().addCharToLabel('S');
        canvasController.getFacade().getActiveDiagram().addCharToLabel('a');
        canvasController.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals(canvasController.getFacade().getActiveDiagram().getLabelContainer(), ":S");
    }

    @Test
    public void test_handleKeyEvent_add_char_in_invalid_label(){
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getFacade().getActiveDiagram().addCharToLabel(':');
        canvasController.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'S'));
        assertEquals(canvasController.getFacade().getActiveDiagram().getLabelContainer(), ":S");
    }

    @Test
    public void test_handleKeyEvent_backspace_in_invalid_label(){
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getFacade().getActiveDiagram().addCharToLabel(':');
        canvasController.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals(canvasController.getFacade().getActiveDiagram().getLabelContainer(), "");
    }

    @Test
    public void test_handleMouseEvent_drag(){
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getFacade().getActiveDiagram().addCharToLabel(':');
        canvasController.getFacade().getActiveDiagram().addCharToLabel('S');
        Clickable c = canvasController.getFacade().getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.DRAG, new Point2D.Double(25,51)));
        Clickable c1 = canvasController.getFacade().getActiveDiagram().findSelectedElement(new Point2D.Double(25,51));
        assertTrue(c.equals(c1));
    }

    @Test
    public void test_handleMouseEvent_pressed_away_from_invalidLabel(){
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(25,50)));
        assertTrue(canvasController.getFacade().getActiveDiagram().getSelectedElement() instanceof PartyLabel);
    }

    @Test
    public void test_handleMouseEvent_pressed_validLabel_of_object(){
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getFacade().getActiveDiagram().addCharToLabel(':');
        canvasController.getFacade().getActiveDiagram().addCharToLabel('S');
        canvasController.getFacade().getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, ((Party) canvasController.getFacade().getActiveDiagram().getSelectedElement()).getLabel().getCoordinate()));
        assertFalse(canvasController.getFacade().getActiveDiagram().getSelectedElement() instanceof PartyLabel);

    }

    @Test
    public void test_handleMouseEvent_pressed_other_object_from_object(){
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getFacade().getActiveDiagram().addCharToLabel(':');
        canvasController.getFacade().getActiveDiagram().addCharToLabel('S');
        Clickable c = canvasController.getFacade().getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(80,50));
        canvasController.getFacade().getActiveDiagram().addCharToLabel(':');
        canvasController.getFacade().getActiveDiagram().addCharToLabel('L');
        canvasController.getFacade().getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED,new Point2D.Double(25,50)));
        assertEquals(canvasController.getFacade().getActiveDiagram().getSelectedElement(), c);

    }

    /*@Test
    public void test_handleMouseEvent_released(){

    }*/

    @Test
    public void test_handleMouseEvent_leftClick(){
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getFacade().getActiveDiagram().addCharToLabel(':');
        canvasController.getFacade().getActiveDiagram().addCharToLabel('S');
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.LEFTCLICK, new Point2D.Double(25,50)));
        assertTrue(canvasController.getFacade().getActiveDiagram().getSelectedElement() instanceof Party);
    }

    @Test
    public void test_handleMouseEvent_leftDoubleClick_changePartyType(){
        canvasController.getFacade().getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasController.getFacade().getActiveDiagram().addCharToLabel(':');
        canvasController.getFacade().getActiveDiagram().addCharToLabel('S');
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.LEFTCLICK, new Point2D.Double(25,50)));
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.LEFTDOUBLECLICK, new Point2D.Double(25,50)));
        assertTrue(canvasController.getFacade().getActiveDiagram().getSelectedElement() instanceof Actor);
    }

    @Test
    public void test_handleMouseEvent_leftDoubleClick_addParty(){
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.LEFTDOUBLECLICK, new Point2D.Double(25,50)));
        canvasController.getFacade().getActiveDiagram().addCharToLabel(':');
        canvasController.getFacade().getActiveDiagram().addCharToLabel('S');
        Clickable c = canvasController.getFacade().getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        assertTrue(canvasController.getFacade().getActiveDiagram().getParties().contains(c));
    }

   /* @Test
    public void test_label_resets() throws DomainException{
        canvasController.getFacade().addNewParty(new Point2D.Double(25,50));
        canvasController.getFacade().addCharToLabel(':');
        canvasController.getFacade().addCharToLabel('S');
        canvasController.getFacade().findSelectedElement(new Point2D.Double(25,50));
        System.out.println(((Party) canvasController.getFacade().getSelectedElement()).getLabel().getCoordinate());
        System.out.println(((Party) canvasController.getFacade().getSelectedElement()).getLabel().getLabel());
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(200,200)));
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(35,70)));
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(35,70)));
        ((Party) canvasController.getFacade().getSelectedElement()).getLabel().setLabel(":F");
        canvasController.getFacade().findSelectedElement(new Point2D.Double(25,50));
        canvasController.getFacade().addCharToLabel(':');
        canvasController.getFacade().addCharToLabel('F');
        assertEquals(((Party) canvasController.getFacade().getSelectedElement()).getLabel().getLabel(), ":F");
    }*/
}