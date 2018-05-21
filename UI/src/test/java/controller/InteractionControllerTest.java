package controller;
import command.closeWindow.CloseSubwindowCommand;
import exception.UIException;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import window.diagram.DiagramSubwindow;
import window.elements.button.Button;
import window.elements.button.CloseWindowButton;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InteractionControllerTest {

    private InteractionController interactionController;

    @Before
    public void setUp() {
        interactionController = new InteractionController();
    }

    @Test
    public void test_list_empty_after_init(){
        assertTrue(interactionController.getSubwindows().isEmpty());
    }

    @Test
    public void test_active_interactioncontroller_is_null_after_init(){
        assertTrue(interactionController.getActiveSubwindow() == null);
    }

    @Test
    public void test_addSubwindow(){
        DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, interactionController));
        diagramSubwindow.getFrame().setButton(button);
        interactionController.addSubwindow(diagramSubwindow);
        assertEquals(interactionController.getActiveSubwindow(),diagramSubwindow);
        assertEquals(interactionController.getSubwindows().size(),1);
    }

    @Test
    public void test_addSubwindow_twice(){
        DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, interactionController));
        diagramSubwindow.getFrame().setButton(button);
        interactionController.addSubwindow(diagramSubwindow);
        interactionController.addSubwindow(diagramSubwindow);
        assertEquals(interactionController.getActiveSubwindow(),diagramSubwindow);
        assertEquals(interactionController.getSubwindows().size(),1);
    }

    @Test
    public void test_addSubwindow_different(){
        DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, interactionController));
        diagramSubwindow.getFrame().setButton(button);

        DiagramSubwindow diagramSubwindow2 = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button2 = new CloseDiagramSubwindowButton(new CloseDiagramSubwindowCommand(interactionController, diagramSubwindow));
        diagramSubwindow.getFrame().setButton(button2);

        DiagramSubwindow diagramSubwindow3 = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button3 = new CloseDiagramSubwindowButton(new CloseDiagramSubwindowCommand(interactionController, diagramSubwindow));
        diagramSubwindow.getFrame().setButton(button3);

        interactionController.addSubwindow(diagramSubwindow);
        interactionController.addSubwindow(diagramSubwindow2);
        interactionController.addSubwindow(diagramSubwindow3);

        assertEquals(interactionController.getActiveSubwindow(),diagramSubwindow3);
        assertEquals(interactionController.getSubwindows().size(),3);
    }

    @Test
    public void test_addSubwindow_different2(){
        DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button = new CloseDiagramSubwindowButton(new CloseDiagramSubwindowCommand(interactionController, diagramSubwindow));
        diagramSubwindow.getFrame().setButton(button);

        DiagramSubwindow diagramSubwindow2 = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button2 = new CloseDiagramSubwindowButton(new CloseDiagramSubwindowCommand(interactionController, diagramSubwindow));
        diagramSubwindow.getFrame().setButton(button2);

        DiagramSubwindow diagramSubwindow3 = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button3 = new CloseDiagramSubwindowButton(new CloseDiagramSubwindowCommand(interactionController, diagramSubwindow));
        diagramSubwindow.getFrame().setButton(button3);

        interactionController.addSubwindow(diagramSubwindow);
        interactionController.addSubwindow(diagramSubwindow2);
        interactionController.addSubwindow(diagramSubwindow3);
        interactionController.removeSubwindow(diagramSubwindow3);

        assertEquals(interactionController.getActiveSubwindow(),diagramSubwindow2);
        assertEquals(interactionController.getSubwindows().size(),2);
    }

    @Test
    public void test_removeSubwindow(){
        DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button = new CloseDiagramSubwindowButton(new CloseDiagramSubwindowCommand(interactionController, diagramSubwindow));
        diagramSubwindow.getFrame().setButton(button);
        interactionController.addSubwindow(diagramSubwindow);
        interactionController.removeSubwindow(diagramSubwindow);
        assertEquals(interactionController.getActiveSubwindow(),null);
        assertEquals(interactionController.getSubwindows().size(),0);
    }

    @Test
    public void test_handleKeyEvent_createNewDiagramSubwindow() throws DomainException, UIException{
        KeyEvent ke = new KeyEvent(KeyEventType.CTRLN);
        interactionController.handleKeyEvent(ke);
        assertEquals(interactionController.getSubwindows().size(),1);
    }

    @Test
    public void test_handleKeyEvent_copyActiveDiagramSubwindow() throws DomainException, UIException{
        KeyEvent ke = new KeyEvent(KeyEventType.CTRLN);
        interactionController.handleKeyEvent(ke);
        assertEquals(interactionController.getSubwindows().size(),1);
        KeyEvent ke2 = new KeyEvent(KeyEventType.CTRLD);
        interactionController.handleKeyEvent(ke2);
        assertEquals(interactionController.getSubwindows().size(),2);
    }

    @Test
    public void test_getAppropriateSubwindow(){
        DiagramSubwindow diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button = new CloseDiagramSubwindowButton(new CloseDiagramSubwindowCommand(interactionController, diagramSubwindow));
        diagramSubwindow.getFrame().setButton(button);

        DiagramSubwindow diagramSubwindow2 = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button2 = new CloseDiagramSubwindowButton(new CloseDiagramSubwindowCommand(interactionController, diagramSubwindow));
        diagramSubwindow.getFrame().setButton(button2);

        DiagramSubwindow diagramSubwindow3 = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button3 = new CloseDiagramSubwindowButton(new CloseDiagramSubwindowCommand(interactionController, diagramSubwindow));
        diagramSubwindow.getFrame().setButton(button3);

        interactionController.addSubwindow(diagramSubwindow);
        interactionController.addSubwindow(diagramSubwindow2);
        interactionController.addSubwindow(diagramSubwindow3);
        assertEquals(interactionController.getHighestLevelSubwindow(), diagramSubwindow3);
        interactionController.removeSubwindow(diagramSubwindow3);
        assertEquals(interactionController.getHighestLevelSubwindow(), diagramSubwindow2);
        interactionController.removeSubwindow(diagramSubwindow2);
        assertEquals(interactionController.getHighestLevelSubwindow(), diagramSubwindow);
        interactionController.removeSubwindow(diagramSubwindow);
        assertEquals(interactionController.getHighestLevelSubwindow(), null);
    }

}