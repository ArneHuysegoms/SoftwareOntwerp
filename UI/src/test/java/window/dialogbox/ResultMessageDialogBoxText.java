package window.dialogbox;

import action.Action;
import action.EmptyAction;
import action.RemoveInViewsAction;
import action.UpdateLabelAction;
import command.closeWindow.CloseSubwindowCommand;
import controller.InteractionController;
import diagram.DiagramElement;
import diagram.label.PartyLabel;
import diagram.label.ResultMessageLabel;
import diagram.message.ResultMessage;
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
import window.elements.button.CloseWindowButton;
import window.elements.textbox.TextBox;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

public class ResultMessageDialogBoxText {

    DiagramSubwindow diagramSubwindow;
    ResultMessage resultMessage;
    ResultMessageDialogBox resultMessageDialogBox;
    InteractionController interactionController;

    @Before
    public void setUp(){
        try{
            interactionController = new InteractionController();
            diagramSubwindow = new DiagramSubwindow(new Point2D.Double(500, 500));
            resultMessage = new ResultMessage(null, new ResultMessageLabel(""), new Actor(new PartyLabel("a:A")), new Actor(new PartyLabel("b:B")));
            resultMessageDialogBox = new ResultMessageDialogBox(new Point2D.Double(50, 50), resultMessage, diagramSubwindow);
            //diagramSubwindow.addDialogBox(resultMessageDialogBox);
            CloseWindowButton closeWindowButton = new CloseWindowButton(new CloseSubwindowCommand(resultMessageDialogBox, interactionController));
            resultMessageDialogBox.createFrame(closeWindowButton);
            interactionController.addSubwindow(resultMessageDialogBox);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void test_constructor(){
        assertEquals(new Point2D.Double(50,50), resultMessageDialogBox.getPosition());
        assertEquals(resultMessage, resultMessageDialogBox.getResultMessage());
        assertEquals(diagramSubwindow, resultMessageDialogBox.getDiagramSubwindow());
        for(DialogboxElement ele : resultMessageDialogBox.getElementList()) {
            if(ele instanceof TextBox)
                assertEquals("",((TextBox) ele).getContents());
                assertEquals(ele, resultMessageDialogBox.getSelected());
        }
        assertEquals(ResultMessageDialogBox.WIDTH, resultMessageDialogBox.getWidth());
        assertEquals(ResultMessageDialogBox.HEIGHT, resultMessageDialogBox.getHeight());
    }

    @Test
    public void test_handleMouseEvent_returns_empty_action(){
        assertTrue(resultMessageDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(0,0))) instanceof EmptyAction);
    }

    @Test
    public void test_handleChars(){
        Action resultAction = resultMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        assertTrue(resultAction instanceof UpdateLabelAction);
        for(DialogboxElement ele : resultMessageDialogBox.getElementList()) {
            if(ele instanceof TextBox)
                assertEquals("a",((TextBox) ele).getContents());
        }
        assertEquals("a", resultMessage.getLabel().getLabel());
    }

    @Test
    public void test_deleteChars(){
        Action resultAction = resultMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        assertTrue(resultAction instanceof UpdateLabelAction);
        for(DialogboxElement ele : resultMessageDialogBox.getElementList()) {
            if(ele instanceof TextBox)
                assertEquals("a",((TextBox) ele).getContents());
        }
        assertEquals("a", resultMessage.getLabel().getLabel());
    }

    @Test
    public void test_handleAction_updateLabelAction() throws DomainException {
        resultMessage.getLabel().setLabel("test");
        UpdateLabelAction updateLabelAction = new UpdateLabelAction(resultMessage, null);
        resultMessageDialogBox.handleAction(updateLabelAction);
        for(DialogboxElement ele : resultMessageDialogBox.getElementList()) {
            if(ele instanceof TextBox)
                assertEquals("test",((TextBox) ele).getContents());
        }
    }

    @Test
    public void test_handleAction_RemoveInReposAction(){
        Set<DiagramElement> el = new HashSet<>();
        el.add(resultMessage);
        RemoveInViewsAction action = new RemoveInViewsAction(el);
        resultMessageDialogBox.handleAction(action);
        assertFalse(interactionController.getSubwindows().contains(resultMessageDialogBox));
    }

    @Test
    public void test_designMode() throws DomainException{
        resultMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CTRLE));
        assertTrue(resultMessageDialogBox.getDesignerMode());
        resultMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.ENTER));
        assertFalse(resultMessageDialogBox.getDesignerMode());
    }

    @Test
    public void test_description_addChar(){
        resultMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CTRLE));
        assertEquals(resultMessageDialogBox.getSelected().getDescription().toString(), "message label");

        resultMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR,'t'));
        assertEquals(resultMessageDialogBox.getSelected().getDescription(), "message labelt");
        resultMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));

    }

    @Test
    public void test_description_delChar(){
        resultMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CTRLE));
        assertEquals(resultMessageDialogBox.getSelected().getDescription().toString(), "message label");

        resultMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR,'t'));
        assertEquals(resultMessageDialogBox.getSelected().getDescription(), "message labelt");

        resultMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals(resultMessageDialogBox.getSelected().getDescription(), "message label");
    }

    @Test
    public void test_selectedindex_bigger_than_list(){
        assertEquals(0,resultMessageDialogBox.getSelectedindex());
        resultMessageDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(0,resultMessageDialogBox.getSelectedindex());

        resultMessageDialogBox.selectedindex = 100;
        resultMessageDialogBox.updateList();
        assertEquals(0,resultMessageDialogBox.getSelectedindex());
    }

}
