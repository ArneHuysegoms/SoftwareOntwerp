package command;

import command.closeWindow.CloseSubwindowCommand;
import controller.InteractionController;
import figures.helperClasses.Pair;
import org.junit.Before;
import org.junit.Test;
import window.Subwindow;
import window.diagram.DiagramSubwindow;

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
    }

}
