package controller;
import command.closeWindow.CloseSubwindowCommand;
import exception.UIException;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import view.diagram.CommunicationView;
import view.diagram.SequenceView;
import window.Subwindow;
import window.diagram.DiagramSubwindow;
import window.dialogbox.DialogBox;
import window.elements.button.Button;
import window.elements.button.CloseWindowButton;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InteractionControllerTest {

    private InteractionController interactionController;
    private DiagramSubwindow diagramSubwindow,diagramSubwindow2,diagramSubwindow3;

    @Before
    public void setUp() {
        interactionController = new InteractionController();

        diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, interactionController));
        diagramSubwindow.getFrame().setButton(button);

        diagramSubwindow2 = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button2 = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, interactionController));
        diagramSubwindow2.getFrame().setButton(button2);

        diagramSubwindow3 = new DiagramSubwindow(new Point2D.Double(100, 100));
        Button button3 = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, interactionController));
        diagramSubwindow3.getFrame().setButton(button3);
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
        interactionController.addSubwindow(diagramSubwindow);
        assertEquals(interactionController.getActiveSubwindow(),diagramSubwindow);
        assertEquals(interactionController.getSubwindows().size(),1);
    }

    @Test
    public void test_addSubwindow_twice(){
        interactionController.addSubwindow(diagramSubwindow);
        interactionController.addSubwindow(diagramSubwindow);
        assertEquals(interactionController.getActiveSubwindow(),diagramSubwindow);
        assertEquals(interactionController.getSubwindows().size(),1);
    }

    @Test
    public void test_addSubwindow_different(){
        interactionController.addSubwindow(diagramSubwindow);
        interactionController.addSubwindow(diagramSubwindow2);
        interactionController.addSubwindow(diagramSubwindow3);

        assertEquals(interactionController.getActiveSubwindow(),diagramSubwindow3);
        assertEquals(interactionController.getSubwindows().size(),3);
    }

    @Test
    public void test_addSubwindow_different2(){
        interactionController.addSubwindow(diagramSubwindow);
        interactionController.addSubwindow(diagramSubwindow2);
        interactionController.addSubwindow(diagramSubwindow3);
        interactionController.removeSubwindow(diagramSubwindow3);

        assertEquals(interactionController.getActiveSubwindow(),diagramSubwindow2);
        assertEquals(interactionController.getSubwindows().size(),2);
    }

    @Test
    public void test_removeSubwindow(){
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
    public void test_handleKeyEvent_actionForEachDiagramSubwindow() throws DomainException, UIException{
        interactionController.addSubwindow(diagramSubwindow);
        interactionController.addSubwindow(diagramSubwindow2);
        interactionController.addSubwindow(diagramSubwindow3);

        KeyEvent ke = new KeyEvent(KeyEventType.TAB);
        interactionController.handleKeyEvent(ke);
        Subwindow s = interactionController.getActiveSubwindow();
        assertTrue(((DiagramSubwindow)s).getFacade().getActiveView() instanceof CommunicationView);
        interactionController.setActiveSubwindow(diagramSubwindow2);
        s = interactionController.getActiveSubwindow();
        assertTrue(((DiagramSubwindow)s).getFacade().getActiveView() instanceof SequenceView);
    }

    @Test
    public void test_handleKeyEvent_actionForEachDiagramSubwindow_openDialogBox() throws DomainException, UIException{
        interactionController.addSubwindow(diagramSubwindow);
        interactionController.addSubwindow(diagramSubwindow2);
        interactionController.addSubwindow(diagramSubwindow3);

        KeyEvent ke = new KeyEvent(KeyEventType.CTRLENTER);
        interactionController.handleKeyEvent(ke);
        Subwindow s = interactionController.getActiveSubwindow();
        assertTrue(interactionController.getActiveSubwindow() instanceof DialogBox);
    }

    @Test
    public void test_getHighestLevelSubwindow(){
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

    @Test
    public void test_getAppropriateSubwindow(){
        interactionController.addSubwindow(diagramSubwindow);
        interactionController.addSubwindow(diagramSubwindow2);
        interactionController.addSubwindow(diagramSubwindow3);
        assertEquals(interactionController.getAppropriateSubwindow(new Point2D.Double(100,100)), diagramSubwindow3);
        interactionController.removeSubwindow(diagramSubwindow3);
        assertEquals(interactionController.getAppropriateSubwindow(new Point2D.Double(100,100)), diagramSubwindow2);
        interactionController.removeSubwindow(diagramSubwindow2);
        assertEquals(interactionController.getAppropriateSubwindow(new Point2D.Double(100,100)), diagramSubwindow);
        interactionController.removeSubwindow(diagramSubwindow);
        assertEquals(interactionController.getAppropriateSubwindow(new Point2D.Double(100,100)), null);
    }

}