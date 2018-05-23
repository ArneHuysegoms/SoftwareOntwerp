package window.dialogbox;

import action.Action;
import action.RemoveInViewsAction;
import action.UpdateLabelAction;
import command.closeWindow.CloseSubwindowCommand;
import controller.InteractionController;
import diagram.DiagramElement;
import diagram.label.InvocationMessageLabel;
import diagram.label.PartyLabel;
import diagram.message.InvocationMessage;
import diagram.party.Actor;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import uievents.MouseEvent;
import uievents.MouseEventType;
import window.diagram.DiagramSubwindow;
import window.elements.button.Button;
import window.elements.button.CloseWindowButton;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.fail;

import static org.junit.Assert.*;

public class InvocationMessageDialogBoxTest {

    InvocationMessage invocationMessage;
    InvocationMessageLabel invocationMessageLabel;
    DiagramSubwindow diagramSubwindow;
    InvocationMessageDialogBox invocationMessageDialogBox;
    InteractionController interactionController;

    @Before
    public void setUp(){
        try {
            interactionController = new InteractionController();
            diagramSubwindow = new DiagramSubwindow(new Point2D.Double(500, 500));
            invocationMessageLabel = new InvocationMessageLabel("a", new ArrayList<>());
            invocationMessage = new InvocationMessage(null, invocationMessageLabel, new Actor(new PartyLabel("a:A")), new Actor(new PartyLabel("b:B")));
            invocationMessageDialogBox = new InvocationMessageDialogBox(new Point2D.Double(50, 50), invocationMessageLabel, diagramSubwindow);
            Button closeWindowButton = new CloseWindowButton(new CloseSubwindowCommand(invocationMessageDialogBox, interactionController));
            invocationMessageDialogBox.createFrame(closeWindowButton);
            invocationMessageDialogBox.getMethodTextBox().setContents("a");
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

        assertEquals("a", invocationMessageDialogBox.getMethodTextBox().getContents());
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

    @Test
    public void test_handle_adding_and_deleting_chars_method(){
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        assertEquals("aa", invocationMessageLabel.getLabel());
        assertEquals("aa", invocationMessageDialogBox.getMethodTextBox().getContents());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals("a", invocationMessageLabel.getLabel());
        assertEquals("a", invocationMessageDialogBox.getMethodTextBox().getContents());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals("a", invocationMessageLabel.getLabel());
        assertEquals("", invocationMessageDialogBox.getMethodTextBox().getContents());
    }

    @Test
    public void test_handle_adding_deleting_chars_argumentTextBox(){
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));
        assertEquals("a:A", invocationMessageDialogBox.getArgumentTextBox().getContents());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals("a:", invocationMessageDialogBox.getArgumentTextBox().getContents());
    }

    @Test
    public void test_handle_addArgument(){
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        assertEquals("a:A", invocationMessageLabel.getArguments().get(0).toString());
        assertTrue(invocationMessageDialogBox.getArgumentListBox().getArguments().contains("a:A"));
    }

    @Test
    public void test_handleArgumentListBox(){
        add_argument1();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        assertEquals(0, invocationMessageDialogBox.getArgumentListBox().getSelectedIndex());
    }

    @Test
    public void test_delete_argument(){
        add_argument1();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getDeleteArgument());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        assertEquals(0, invocationMessageDialogBox.getArgumentListBox().getArguments().size());
        assertEquals(0, invocationMessageLabel.getArguments().size());
    }

    @Test
    public void test_moveDown(){
        add_argument2();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        assertEquals(0, invocationMessageDialogBox.getArgumentListBox().getSelectedIndex());

        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getMoveDown());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        assertEquals(1, invocationMessageDialogBox.getArgumentListBox().getSelectedIndex());
        assertEquals(2, invocationMessageDialogBox.getArgumentListBox().getArguments().size());
        assertEquals("b:B", invocationMessageDialogBox.getArgumentListBox().getArguments().get(0));
        assertEquals("a:A", invocationMessageDialogBox.getArgumentListBox().getArguments().get(1));

        assertEquals("b:B", invocationMessageLabel.getArguments().get(0).toString());
        assertEquals("a:A", invocationMessageLabel.getArguments().get(1).toString());
    }

    @Test
    public void test_moveUp(){
        add_argument2();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,164)));
        assertEquals(1, invocationMessageDialogBox.getArgumentListBox().getSelectedIndex());

        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getMoveUp());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        assertEquals(0, invocationMessageDialogBox.getArgumentListBox().getSelectedIndex());
        assertEquals(2, invocationMessageDialogBox.getArgumentListBox().getArguments().size());
        assertEquals("b:B", invocationMessageDialogBox.getArgumentListBox().getArguments().get(0));
        assertEquals("a:A", invocationMessageDialogBox.getArgumentListBox().getArguments().get(1));

        assertEquals("b:B", invocationMessageLabel.getArguments().get(0).toString());
        assertEquals("a:A", invocationMessageLabel.getArguments().get(1).toString());
    }

    @Test
    public void test_select_by_arrowDownKey(){
        add_argument2();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        assertEquals(0, invocationMessageDialogBox.getArgumentListBox().getSelectedIndex());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.ARROWKEYDOWN));

        assertEquals(1, invocationMessageDialogBox.getArgumentListBox().getSelectedIndex());
    }

    @Test
    public void test_select_by_arrowKeyUp(){
        add_argument2();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,164)));
        assertEquals(1, invocationMessageDialogBox.getArgumentListBox().getSelectedIndex());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.ARROWKEYUP));

        assertEquals(0, invocationMessageDialogBox.getArgumentListBox().getSelectedIndex());
    }

    @Test
    public void test_MousePressed(){
        add_argument1();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        assertEquals(invocationMessageDialogBox.getArgumentListBox(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,50)));
        assertEquals(invocationMessageDialogBox.getMethodTextBox(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,75)));
        assertEquals(invocationMessageDialogBox.getArgumentTextBox(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,100)));
        assertEquals(invocationMessageDialogBox.getAddArgument(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(90,100)));
        assertEquals(invocationMessageDialogBox.getMoveDown(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(130,100)));
        assertEquals(invocationMessageDialogBox.getMoveUp(), invocationMessageDialogBox.getSelected());

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(50,100)));
        assertEquals(invocationMessageDialogBox.getDeleteArgument(), invocationMessageDialogBox.getSelected());
    }

    @Test
    public void test_handleAction_RemoveInReposAction(){
        Set<DiagramElement> diagramElementSet = new HashSet<>();
        diagramElementSet.add(invocationMessage);
        Action action = new RemoveInViewsAction(diagramElementSet);
        invocationMessageDialogBox.handleAction(action);
    }

    @Test
    public void test_handleAction_UpdateLabelAction() throws DomainException {
        invocationMessage.getLabel().setLabel("method");
        invocationMessageLabel.addArgument("test");
        Action action = new UpdateLabelAction(invocationMessage, null);
        invocationMessageDialogBox.handleAction(action);
    }

    private void add_argument1(){
        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getArgumentTextBox());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));

        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getAddArgument());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));
    }

    private void add_argument2(){
        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getArgumentTextBox());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));

        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getAddArgument());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getArgumentTextBox());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'b'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'B'));

        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getAddArgument());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        assertEquals(2, invocationMessageDialogBox.getArgumentListBox().getArguments().size());
    }

    /*private void add_argument3(){
        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getAddArgument());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));

        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getAddArgument());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getArgumentTextBox());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'b'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'B'));

        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getAddArgument());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getArgumentTextBox());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'c'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'C'));

        invocationMessageDialogBox.setSelected(invocationMessageDialogBox.getAddArgument());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        assertEquals(3, invocationMessageDialogBox.getArgumentListBox().getArguments().size());
    }*/

}
