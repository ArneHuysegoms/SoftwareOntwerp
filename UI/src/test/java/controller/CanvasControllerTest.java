package controller;
import exception.UIException;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import uievents.MouseEvent;
import uievents.MouseEventType;
import window.diagram.DiagramSubwindow;

import java.awt.geom.Point2D;

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

        InteractionController ic = new InteractionController();
        InteractionController ic2 = new InteractionController();
        ic.addSubwindow(new DiagramSubwindow(new Point2D.Double(100,100)));
        ic2.addSubwindow(new DiagramSubwindow(new Point2D.Double(500,500)));
        canvasController.addInteractionController(ic2);
        canvasController.addInteractionController(ic);
        assertEquals(canvasController.getActiveInteractionController(),ic);
        canvasController.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED,new Point2D.Double(500,500)));

        assertEquals(canvasController.getActiveInteractionController(),ic2);
    }
}