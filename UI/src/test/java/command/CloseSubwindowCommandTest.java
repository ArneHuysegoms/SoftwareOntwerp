package command;

import command.closeWindow.CloseSubwindowCommand;
import controller.InteractionController;
import figures.helperClasses.Pair;
import org.junit.Before;
import org.junit.Test;
import window.Subwindow;
import window.diagram.DiagramSubwindow;
import window.elements.button.Button;
import window.elements.button.CloseWindowButton;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;

public class CloseSubwindowCommandTest {

    private CloseSubwindowCommand closeSubwindowCommand;
    private Subwindow subwindow;
    private InteractionController interactionController;

    @Before
    public void setUp(){
        interactionController = new InteractionController();
        subwindow = new DiagramSubwindow(new Point2D.Double(100,100));
        closeSubwindowCommand = new CloseSubwindowCommand(subwindow, interactionController);
        Button button = new CloseWindowButton(closeSubwindowCommand);
        subwindow.getFrame().setButton(button);
        interactionController.addSubwindow(subwindow);
    }

    @Test
    public void test_performAction(){
        closeSubwindowCommand.performAction();
        assertTrue(interactionController.getActiveSubwindow() == null);
    }
}
