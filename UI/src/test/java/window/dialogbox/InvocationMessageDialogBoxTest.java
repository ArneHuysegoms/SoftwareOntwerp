package window.dialogbox;

import action.Action;
import action.AddNewMessagesInViewsAction;
import action.RemoveInViewsAction;
import action.UpdateLabelAction;
import command.closeWindow.CloseSubwindowCommand;
import controller.InteractionController;
import diagram.DiagramElement;
import diagram.label.InvocationMessageLabel;
import diagram.label.PartyLabel;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Actor;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import uievents.MouseEvent;
import uievents.MouseEventType;
import window.diagram.DiagramSubwindow;
import window.dialogbox.InvocationMessageDialogBox;
import window.elements.ListBox;
import window.elements.button.*;
import window.elements.textbox.ArgumentTextBox;
import window.elements.textbox.MethodTextBox;

import java.awt.geom.Point2D;
import java.util.*;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

public class InvocationMessageDialogBoxTest {

    private InvocationMessage invocationMessage;
    private InvocationMessageLabel invocationMessageLabel;
    private DiagramSubwindow diagramSubwindow;
    private InvocationMessageDialogBox invocationMessageDialogBox;
    private InteractionController interactionController;

    @Before
    public void setUp(){
        try {
            interactionController = new InteractionController();
            diagramSubwindow = new DiagramSubwindow(new Point2D.Double(500, 500));
            invocationMessageLabel = new InvocationMessageLabel("a", new ArrayList<>());
            invocationMessage = new InvocationMessage(null, invocationMessageLabel, new Actor(new PartyLabel("a:A")), new Actor(new PartyLabel("b:B")));
            diagramSubwindow.setSelected(invocationMessage);
            diagramSubwindow.stopEditingLabel();
            interactionController.addSubwindow(diagramSubwindow);

            invocationMessageDialogBox = new InvocationMessageDialogBox(new Point2D.Double(50, 50), invocationMessageLabel, diagramSubwindow);
            Button closeWindowButton = new CloseWindowButton(new CloseSubwindowCommand(invocationMessageDialogBox, interactionController));
            invocationMessageDialogBox.createFrame(closeWindowButton);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void test_default_constructor(){
        assertEquals(7, invocationMessageDialogBox.getElementList().size());
        assertEquals(invocationMessageLabel, invocationMessageDialogBox.getInvocationMessageLabel());
        assertEquals(diagramSubwindow, invocationMessageDialogBox.getSubwindow());
        assertEquals(InvocationMessageDialogBox.WIDTH, invocationMessageDialogBox.getWidth());
        assertEquals(InvocationMessageDialogBox.HEIGHT, invocationMessageDialogBox.getHeight());
        assertTrue(invocationMessageDialogBox.selected instanceof MethodTextBox);
        assertNotEquals("", ((MethodTextBox)invocationMessageDialogBox.selected).getContents());
    }

    @Test
    public void test_handleTab(){
        assertTrue(invocationMessageDialogBox.selected instanceof MethodTextBox);

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(invocationMessageDialogBox.selected instanceof ArgumentTextBox);

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(invocationMessageDialogBox.selected instanceof AddArgumentButton);

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(invocationMessageDialogBox.selected instanceof DeleteArgumentButton);

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(invocationMessageDialogBox.selected instanceof MoveDownButton);

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(invocationMessageDialogBox.selected instanceof MoveUpButton);

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(invocationMessageDialogBox.selected instanceof ListBox);

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(invocationMessageDialogBox.selected instanceof MethodTextBox);
    }

    @Test
    public void test_handle_adding_and_deleting_chars_method(){
        assertTrue(invocationMessageDialogBox.selected instanceof MethodTextBox);
        String initialMethodTextBoxContent = ((MethodTextBox)invocationMessageDialogBox.selected).getContents();

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        assertEquals(initialMethodTextBoxContent+"a", invocationMessageLabel.getLabel());
        assertEquals(initialMethodTextBoxContent+"a", ((MethodTextBox)invocationMessageDialogBox.selected).getContents());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals(initialMethodTextBoxContent, invocationMessageLabel.getLabel());
        assertEquals(initialMethodTextBoxContent, ((MethodTextBox)invocationMessageDialogBox.selected).getContents());

        while(!((MethodTextBox)invocationMessageDialogBox.selected).getContents().equals("")) {
            invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        }
        assertEquals(initialMethodTextBoxContent.substring(0,1), invocationMessageLabel.getLabel());
    }

    @Test
    public void test_handle_adding_deleting_chars_argumentTextBox(){
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(invocationMessageDialogBox.selected instanceof ArgumentTextBox);

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));
        assertEquals("a:A", ((ArgumentTextBox)invocationMessageDialogBox.selected).getContents());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals("a:", ((ArgumentTextBox)invocationMessageDialogBox.selected).getContents());
    }


    public void test_handle_addArgument(){
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(invocationMessageDialogBox.selected instanceof ArgumentTextBox);

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        assertEquals("a:A", invocationMessageLabel.getArguments().get(0).toString());
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(invocationMessageDialogBox.selected instanceof ListBox);

        assertTrue(((ListBox)invocationMessageDialogBox.selected).getArguments().contains("a:A"));
    }


    public void test_handleArgumentListBox(){
        add_argument1();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        assertTrue(invocationMessageDialogBox.selected instanceof ArgumentTextBox);
        assertEquals(0, ((ListBox)invocationMessageDialogBox.selected).getSelectedIndex());
    }


    public void test_delete_argument(){
        add_argument1();
        assertEquals(1, invocationMessageLabel.getArguments().size());
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        while(!(invocationMessageDialogBox.getSelected() instanceof DeleteArgumentButton)){
            invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        }
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));

        assertTrue(invocationMessageDialogBox.selected instanceof ListBox);
        assertEquals(0,((ListBox)invocationMessageDialogBox.selected).getArguments().size());
        assertEquals(0, invocationMessageLabel.getArguments().size());
    }


    public void test_moveDown(){
        add_argument2();
        assertEquals(2, invocationMessageLabel.getArguments().size());

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));

        while(!(invocationMessageDialogBox.getSelected() instanceof ListBox)){
            invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        }
        assertEquals(0, ((ListBox)invocationMessageDialogBox.getSelected()).getSelectedIndex());

        while(!(invocationMessageDialogBox.getSelected() instanceof MoveDownButton)){
            invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        }
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        while(!(invocationMessageDialogBox.getSelected() instanceof ListBox)){
            invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        }
        assertEquals(1, ((ListBox)invocationMessageDialogBox.getSelected()).getSelectedIndex());
        assertEquals(2, ((ListBox)invocationMessageDialogBox.getSelected()).getArguments().size());

        assertEquals("a:A", ((ListBox)invocationMessageDialogBox.getSelected()).getArguments().get(0));
        assertEquals("a:A", ((ListBox)invocationMessageDialogBox.getSelected()).getArguments().get(1));

        assertEquals("a:A", invocationMessageLabel.getArguments().get(0).toString());
        assertEquals("a:A", invocationMessageLabel.getArguments().get(1).toString());
    }


    public void test_moveUp(){
        test_moveDown();
        while(!(invocationMessageDialogBox.getSelected() instanceof MoveUpButton)){
            invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        }
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        while(!(invocationMessageDialogBox.getSelected() instanceof ListBox)){
            invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        }
        assertEquals(0, ((ListBox)invocationMessageDialogBox.getSelected()).getSelectedIndex());
        assertEquals(2, ((ListBox)invocationMessageDialogBox.getSelected()).getArguments().size());

    }


    public void test_select_by_arrowDownKey(){
        add_argument2();

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        while(!(invocationMessageDialogBox.getSelected() instanceof ListBox)){
            invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        }
        assertEquals(0, ((ListBox)invocationMessageDialogBox.getSelected()).getSelectedIndex());

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.ARROWKEYDOWN));

        assertEquals(1, ((ListBox)invocationMessageDialogBox.getSelected()).getSelectedIndex());
    }


    public void test_select_by_arrowKeyUp(){
        test_select_by_arrowDownKey();
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.ARROWKEYUP));
        assertEquals(1, ((ListBox)invocationMessageDialogBox.getSelected()).getSelectedIndex());
    }


    public void test_MousePressed(){
        add_argument1();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        assertTrue(invocationMessageDialogBox.getSelected() instanceof ListBox);

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,50)));
        assertTrue(invocationMessageDialogBox.getSelected() instanceof MethodTextBox);

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,75)));
        assertTrue(invocationMessageDialogBox.getSelected() instanceof ArgumentTextBox);

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,100)));
        assertTrue(invocationMessageDialogBox.getSelected() instanceof AddArgumentButton);

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(90,100)));
        assertTrue(invocationMessageDialogBox.getSelected() instanceof MoveDownButton);

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(130,100)));
        assertTrue(invocationMessageDialogBox.getSelected() instanceof MoveUpButton);

        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(50,100)));
        assertTrue(invocationMessageDialogBox.getSelected() instanceof DeleteArgumentButton);
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
        while (!(invocationMessageDialogBox.getSelected() instanceof ArgumentTextBox)){
            invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        }

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));
    }

    private void add_argument2(){
        add_argument1();
        add_argument1();
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


/*package window.dialogbox;

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
import window.elements.DialogboxElement;
import window.elements.ListBox;
import window.elements.button.*;
import window.elements.textbox.ArgumentTextBox;
import window.elements.textbox.MethodTextBox;
import window.elements.textbox.TextBox;

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
            CloseWindowButton closeWindowButton2 =new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, interactionController));
            diagramSubwindow.createFrame(closeWindowButton2);

            invocationMessageLabel = new InvocationMessageLabel("a", new ArrayList<>());
            invocationMessage = new InvocationMessage(null, invocationMessageLabel, new Actor(new PartyLabel("a:A")), new Actor(new PartyLabel("b:B")));
            invocationMessageDialogBox = new InvocationMessageDialogBox(new Point2D.Double(50, 50), invocationMessageLabel, diagramSubwindow);
            Button closeWindowButton = new CloseWindowButton(new CloseSubwindowCommand(invocationMessageDialogBox, interactionController));
            invocationMessageDialogBox.createFrame(closeWindowButton);
            interactionController.addSubwindow(invocationMessageDialogBox);

            for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
                if(ele instanceof MethodTextBox)
                    ((MethodTextBox) ele).setContents("a");
            }
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void test_default_constructor(){
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof MethodTextBox) {
                assertNotNull(ele);
                assertEquals("a", ((MethodTextBox) ele).getContents());
                assertEquals(ele, invocationMessageDialogBox.getSelected());
            }
        }
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof ArgumentTextBox)
                assertNotNull(ele);
        }
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof AddArgumentButton)
                assertNotNull(ele);
        }
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof DeleteArgumentButton)
                assertNotNull(ele);
        }
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof MoveDownButton)
                assertNotNull(ele);
        }
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof MoveUpButton)
                assertNotNull(ele);
        }
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof ListBox)
                assertNotNull(ele);
        }
        assertEquals(7, invocationMessageDialogBox.getElementList().size());
        assertEquals(invocationMessageLabel, invocationMessageDialogBox.getInvocationMessageLabel());
        assertEquals(diagramSubwindow, invocationMessageDialogBox.getSubwindow());

        assertEquals(InvocationMessageDialogBox.WIDTH, invocationMessageDialogBox.getWidth());
        assertEquals(InvocationMessageDialogBox.HEIGHT, invocationMessageDialogBox.getHeight());

    }

    @Test
    public void test_handleTab(){

        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof MethodTextBox) {
                assertEquals(ele, invocationMessageDialogBox.getSelected());
                assertEquals(0, invocationMessageDialogBox.getSelectedindex());
            }
        }
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof ArgumentTextBox) {
                assertEquals(ele, invocationMessageDialogBox.getSelected());
                assertEquals(1, invocationMessageDialogBox.getSelectedindex());
            }
        }
                invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof AddArgumentButton) {
                assertEquals(ele, invocationMessageDialogBox.getSelected());
                assertEquals(2, invocationMessageDialogBox.getSelectedindex());
            }
        }
                invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof DeleteArgumentButton) {
                assertEquals(ele, invocationMessageDialogBox.getSelected());
                assertEquals(3, invocationMessageDialogBox.getSelectedindex());
            }
        }
                invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof MoveDownButton) {
                assertEquals(ele, invocationMessageDialogBox.getSelected());
                assertEquals(4, invocationMessageDialogBox.getSelectedindex());
            }
        }

                invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof MoveUpButton) {
                assertEquals(ele, invocationMessageDialogBox.getSelected());
                assertEquals(5, invocationMessageDialogBox.getSelectedindex());
            }
        }
                invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if (ele instanceof ListBox) {
                assertEquals(ele, invocationMessageDialogBox.getSelected());
                assertEquals(6, invocationMessageDialogBox.getSelectedindex());
            }
        }
                invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));




    }

    @Test
    public void test_handle_adding_and_deleting_chars_method(){
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        assertEquals("aa", invocationMessageLabel.getLabel());

        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof MethodTextBox)
                assertEquals("aa", ((MethodTextBox)ele).getContents());
        }

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals("a", invocationMessageLabel.getLabel());
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof MethodTextBox)
                assertEquals("a", ((MethodTextBox)ele).getContents());
        }

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals("a", invocationMessageLabel.getLabel());
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof MethodTextBox)
                assertEquals("", ((MethodTextBox)ele).getContents());
        }
    }

    @Test
    public void test_handle_adding_deleting_chars_argumentTextBox(){
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ArgumentTextBox)
                assertEquals("a:A", ((ArgumentTextBox)ele).getContents());
        }

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ArgumentTextBox)
                assertEquals("a:", ((ArgumentTextBox)ele).getContents());
        }
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
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox)
                assertTrue(((ListBox)ele).getArguments().contains("a:A"));
        }
    }

    @Test
    public void test_handleArgumentListBox(){
        add_argument1();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox)
                assertEquals(0, ((ListBox)ele).getSelectedIndex());
        }

    }

    @Test
    public void test_delete_argument(){
        add_argument1();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof DeleteArgumentButton) {
                invocationMessageDialogBox.selected = ele;
            }
        }
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox) {
                assertEquals(0, ((ListBox) ele).getArguments().size());
            }
        }
        assertEquals(0, invocationMessageLabel.getArguments().size());
    }

    @Test
    public void test_moveDown(){
        add_argument2();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox)
                assertEquals(0, ((ListBox)ele).getSelectedIndex());
        }
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof MoveDownButton)
                invocationMessageDialogBox.selected = ele;
        }
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox) {
                assertEquals(1, ((ListBox) ele).getSelectedIndex());
                assertEquals(2, ((ListBox) ele).getArguments().size());
                assertEquals("b:B", ((ListBox) ele).getArguments().get(0));
                assertEquals("a:A", ((ListBox) ele).getArguments().get(1));
            }
        }


        assertEquals("b:B", invocationMessageLabel.getArguments().get(0).toString());
        assertEquals("a:A", invocationMessageLabel.getArguments().get(1).toString());
    }

    @Test
    public void test_moveUp(){
        add_argument2();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,164)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox)
                assertEquals(1, ((ListBox)ele).getSelectedIndex());
        }
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof MoveUpButton)
                invocationMessageDialogBox.selected = ele;
        }
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox) {
                assertEquals(0, ((ListBox) ele).getSelectedIndex());
                assertEquals(2, ((ListBox) ele).getArguments().size());
                assertEquals("b:B", ((ListBox) ele).getArguments().get(0));
                assertEquals("a:A", ((ListBox) ele).getArguments().get(1));
            }
        }

        assertEquals("b:B", invocationMessageLabel.getArguments().get(0).toString());
        assertEquals("a:A", invocationMessageLabel.getArguments().get(1).toString());
    }

    @Test
    public void test_select_by_arrowDownKey(){
        add_argument2();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox)
                assertEquals(0, ((ListBox)ele).getSelectedIndex());
        }

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.ARROWKEYDOWN));

        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox)
                assertEquals(1, ((ListBox)ele).getSelectedIndex());
        }
    }

    @Test
    public void test_select_by_arrowKeyUp(){
        add_argument2();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,164)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox)
                assertEquals(1, ((ListBox)ele).getSelectedIndex());
        }

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.ARROWKEYUP));

        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox)
                assertEquals(0, ((ListBox)ele).getSelectedIndex());
        }
    }

    @Test
    public void test_MousePressed(){
        add_argument1();
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,150)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox)
                assertEquals(ele, invocationMessageDialogBox.getSelected());

        }
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,50)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof MethodTextBox)
                assertEquals(ele, invocationMessageDialogBox.getSelected());

        }
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,75)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ArgumentTextBox)
                assertEquals(ele, invocationMessageDialogBox.getSelected());

        }
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10,100)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof AddArgumentButton)
                assertEquals(ele, invocationMessageDialogBox.getSelected());

        }
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(90,100)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof MoveDownButton)
                assertEquals(ele, invocationMessageDialogBox.getSelected());

        }
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(130,100)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof MoveUpButton)
                assertEquals(ele, invocationMessageDialogBox.getSelected());

        }
        invocationMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(50,100)));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof DeleteArgumentButton)
                assertEquals(ele, invocationMessageDialogBox.getSelected());

        }}

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
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(invocationMessageDialogBox.getSelected() == ele) {
                break;
            }
        }
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));

        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
            if(invocationMessageDialogBox.selected instanceof AddArgumentButton){
                break;
            }
        }

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));
    }

    private void add_argument2(){
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ArgumentTextBox) {
                invocationMessageDialogBox.selected = ele;
            }
        }
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));

        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof AddArgumentButton) {
                invocationMessageDialogBox.selected = ele;
            }
        }
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ArgumentTextBox)
                invocationMessageDialogBox.selected = ele;
        }

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));

        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'b'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'B'));
        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof AddArgumentButton)
                invocationMessageDialogBox.selected = ele;
        }
        invocationMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));

        for(DialogboxElement ele : invocationMessageDialogBox.getElementList()) {
            if(ele instanceof ListBox)
                assertEquals(2, ((ListBox)ele).getArguments().size());
        }
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
/*
}*/
