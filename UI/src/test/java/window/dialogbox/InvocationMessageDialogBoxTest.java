package window.dialogbox;

import command.closeWindow.CloseDialogBoxCommand;
import diagram.label.InvocationMessageLabel;
import diagram.label.PartyLabel;
import diagram.message.InvocationMessage;
import diagram.party.Actor;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import window.diagram.DiagramSubwindow;
import window.elements.button.CloseDialogBoxButton;
import window.elements.button.CloseWindowButton;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static junit.framework.TestCase.fail;

import static org.junit.Assert.*;

public class InvocationMessageDialogBoxTest {

    InvocationMessage invocationMessage;
    InvocationMessageLabel invocationMessageLabel;
    DiagramSubwindow diagramSubwindow;
    InvocationMessageDialogBox invocationMessageDialogBox;

    @Before
    public void setUp(){
        try {
            diagramSubwindow = new DiagramSubwindow(new Point2D.Double(500, 500));
            invocationMessageLabel = new InvocationMessageLabel("", new ArrayList<>());
            invocationMessage = new InvocationMessage(null, invocationMessageLabel, new Actor(new PartyLabel("a:A")), new Actor(new PartyLabel("b:B")));
            invocationMessageDialogBox = new InvocationMessageDialogBox(new Point2D.Double(50, 50), invocationMessageLabel, diagramSubwindow);
            diagramSubwindow.addDialogBox(invocationMessageDialogBox);
            CloseWindowButton closeWindowButton = new CloseDialogBoxButton(new CloseDialogBoxCommand(diagramSubwindow, invocationMessageDialogBox));
            invocationMessageDialogBox.createFrame(closeWindowButton);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void test_default_constructor(){
        assertEquals(7, invocationMessageDialogBox.getDialogboxElements().size());
        assertEquals(invocationMessageLabel, invocationMessageDialogBox.getInvocationMessageLabel());
        assertNotNull(invocationMessageDialogBox.getMethodTextBox());
        assertNotNull(invocationMessageDialogBox.getArgumentTextBox());
        assertNotNull(invocationMessageDialogBox.getAddArgument());
        assertNotNull(invocationMessageDialogBox.getDeleteArgument());
        assertNotNull(invocationMessageDialogBox.getMoveDown());
        assertNotNull(invocationMessageDialogBox.getMoveUp());
        assertEquals(diagramSubwindow, invocationMessageDialogBox.getSubwindow());
        assertEquals(invocationMessageDialogBox.getMethodTextBox(), invocationMessageDialogBox.getSelected());
        assertEquals(InvocationMessageDialogBox.WIDTH, invocationMessageDialogBox.getWidth());
        assertEquals(InvocationMessageDialogBox.HEIGHT, invocationMessageDialogBox.getHeight());
    }

    @Test
    public void test_handleTab(){
        assertEquals(invocationMessageDialogBox.getMethodTextBox(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(invocationMessageDialogBox.getArgumentTextBox(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(invocationMessageDialogBox.getAddArgument(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(invocationMessageDialogBox.getDeleteArgument(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(invocationMessageDialogBox.getMoveDown(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(invocationMessageDialogBox.getMoveUp(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(invocationMessageDialogBox.getArgumentListBox(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(invocationMessageDialogBox.getMethodTextBox(), invocationMessageDialogBox.getSelected());
    }

}
