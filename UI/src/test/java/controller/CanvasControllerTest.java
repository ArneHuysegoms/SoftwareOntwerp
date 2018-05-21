package controller;
import exception.UIException;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CanvasControllerTest {

    private CanvasController canvasController;

    @Before
    public void setUp() {
        canvasController = new CanvasController();
    }

    @Test
    public void test_list_empty_after_init(){
        assertTrue(canvasController.getInteractionControllers().isEmpty());
    }

    @Test
    public void test_active_interactioncontroller_is_null_after_init(){
        assertTrue(canvasController.getActiveInteractionController() == null);
    }

    @Test
    public void test_addInteractionController(){
        InteractionController ic = new InteractionController();
        canvasController.addInteractionController(ic);
        assertEquals(canvasController.getActiveInteractionController(),ic);
        assertEquals(canvasController.getInteractionControllers().size(),1);
    }

    @Test
    public void test_addInteractionController_twice(){
        InteractionController ic = new InteractionController();
        canvasController.addInteractionController(ic);
        canvasController.addInteractionController(ic);
        assertEquals(canvasController.getActiveInteractionController(),ic);
        assertEquals(canvasController.getInteractionControllers().size(),1);
    }

    @Test
    public void test_removeInteractionController(){
        InteractionController ic = new InteractionController();
        canvasController.addInteractionController(ic);
        canvasController.removeInteractionController(ic);
        assertTrue(canvasController.getActiveInteractionController() ==null);
        assertTrue(canvasController.getInteractionControllers().isEmpty());
    }

    @Test
    public void test_handleKeyEvent_CTRLN() throws DomainException, UIException{
        KeyEvent ke = new KeyEvent(KeyEventType.CTRLN);
        canvasController.handleKeyEvent(ke);
        assertEquals(canvasController.getInteractionControllers().size(),1);
    }

    @Test
    public void test_handleKeyEvent_CTRLD() throws DomainException, UIException{
        KeyEvent ke = new KeyEvent(KeyEventType.CTRLN);
        canvasController.handleKeyEvent(ke);
        assertEquals(canvasController.getInteractionControllers().size(),1);
        assertEquals(canvasController.getActiveInteractionController().getSubwindows().size(),1);
        KeyEvent ke2 = new KeyEvent(KeyEventType.CTRLD);
        canvasController.handleKeyEvent(ke2);
        assertEquals(canvasController.getInteractionControllers().size(),1);
        assertEquals(canvasController.getActiveInteractionController().getSubwindows().size(),2);
    }

    @Test
    public void test_handleKeyEvent_CTRLD_after_delete() throws DomainException, UIException{
        KeyEvent ke = new KeyEvent(KeyEventType.CTRLN);
        canvasController.handleKeyEvent(ke);
        assertEquals(canvasController.getInteractionControllers().size(),1);
        assertEquals(canvasController.getActiveInteractionController().getSubwindows().size(),1);
        canvasController.removeInteractionController(canvasController.getActiveInteractionController());
        KeyEvent ke2 = new KeyEvent(KeyEventType.CTRLD);
        canvasController.handleKeyEvent(ke2);
        assertEquals(canvasController.getInteractionControllers().size(),0);
    }

    @Test
    public void test_findHighestLevelInteractionController() throws DomainException, UIException{
        KeyEvent ke = new KeyEvent(KeyEventType.CTRLN);
        canvasController.handleKeyEvent(ke);
        KeyEvent ke2 = new KeyEvent(KeyEventType.CTRLD);
        canvasController.handleKeyEvent(ke2);
        assertEquals(canvasController.findHighestLevelInteractionController(), canvasController.getActiveInteractionController());
    }

    @Test
    public void test_getAppropriateInteractionController(){

    }
    /*
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

    *//*@Test
    public void test_handleMouseEvent_released(){

    }*//*

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
    }*/
}