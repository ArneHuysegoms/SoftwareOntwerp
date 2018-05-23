package command;

import command.changeType.ChangeToSequenceCommand;
import command.closeWindow.CloseSubwindowCommand;
import controller.InteractionController;
import org.junit.Before;
import org.junit.Test;
import window.Subwindow;
import window.diagram.DiagramSubwindow;
import window.elements.button.Button;
import window.elements.button.CloseWindowButton;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;

public class ChangeToSequenceCommandTest {

    private ChangeToSequenceCommand changeToSequenceCommand;
    private DiagramSubwindow subwindow;

    @Before
    public void setUp(){
        subwindow = new DiagramSubwindow(new Point2D.Double(100,100));
        changeToSequenceCommand = new ChangeToSequenceCommand(subwindow);
    }

    @Test
    public void test_performAction(){
        assertTrue(subwindow.activeDiagamIsSequence());
        changeToSequenceCommand.performAction();
        assertTrue(subwindow.activeDiagamIsSequence());
    }
}
