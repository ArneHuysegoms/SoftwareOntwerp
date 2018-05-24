package command;

import command.changeType.ChangeToCommunicationCommand;
import org.junit.Before;
import org.junit.Test;
import window.diagram.DiagramSubwindow;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;

public class ChangeToCommunicationCommandTest {

    private ChangeToCommunicationCommand changeToCommunicationCommand;
    private DiagramSubwindow subwindow;

    @Before
    public void setUp(){
        subwindow = new DiagramSubwindow(new Point2D.Double(100,100));
        changeToCommunicationCommand = new ChangeToCommunicationCommand(subwindow);
    }

    @Test
    public void test_performAction(){
        assertTrue(subwindow.activeDiagamIsSequence());
        changeToCommunicationCommand.performAction();
        assertTrue(subwindow.activeDiagramIsCommunication());
    }
}
