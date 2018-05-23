package window.dialogbox;

import action.Action;
import action.RemoveInViewsAction;
import action.UpdateLabelAction;
import action.UpdatePartyTypeAction;
import command.closeWindow.CloseSubwindowCommand;
import controller.InteractionController;
import diagram.DiagramElement;
import diagram.label.PartyLabel;
import diagram.party.Actor;
import diagram.party.Party;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import uievents.MouseEvent;
import uievents.MouseEventType;
import window.diagram.DiagramSubwindow;
import window.elements.button.CloseWindowButton;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

public class PartyDialogBoxTest {

    Party party;
    DiagramSubwindow diagramSubwindow;
    PartyDialogBox partyDialogBox;
    InteractionController interactionController;

    @Before
    public void setUp(){
        try{
            interactionController = new InteractionController();
            diagramSubwindow = new DiagramSubwindow(new Point2D.Double(500, 500));

            party = diagramSubwindow.getFacade().addNewParty(new Point2D.Double(75, 75));

            partyDialogBox = new PartyDialogBox(new Point2D.Double(50, 50), party, diagramSubwindow);
            CloseWindowButton closeWindowButton = new CloseWindowButton(new CloseSubwindowCommand(partyDialogBox, interactionController));
            partyDialogBox.createFrame(closeWindowButton);
            interactionController.addSubwindow(partyDialogBox);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void test_default_constructor(){
       assertEquals(party, partyDialogBox.getParty());
       assertEquals(diagramSubwindow, partyDialogBox.getSubwindow());
       assertEquals(PartyDialogBox.WIDTH, partyDialogBox.getWidth());
       assertEquals(PartyDialogBox.HEIGHT, partyDialogBox.getHeight());
       assertEquals(4, partyDialogBox.getElementList().size());
       assertEquals(partyDialogBox.getToActor(), partyDialogBox.getSelected());
       assertEquals("",partyDialogBox.getInstanceTextBox().getContents());
       assertEquals("",partyDialogBox.getClassTextBox().getContents());
    }

    @Test
    public void test_tabbing_cycles_trough_elements(){
        assertEquals(partyDialogBox.getToActor(), partyDialogBox.getSelected());

        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(partyDialogBox.getToObject(), partyDialogBox.getSelected());

        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(partyDialogBox.getInstanceTextBox(), partyDialogBox.getSelected());

        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(partyDialogBox.getClassTextBox(), partyDialogBox.getSelected());

        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(partyDialogBox.getToActor(), partyDialogBox.getSelected());
    }

    @Test
    public void test_handleSpace(){
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));
        assertEquals(1, diagramSubwindow.getFacade().getDiagram().getParties().size());
        assertTrue(diagramSubwindow.getFacade().getDiagram().getParties().get(0) instanceof Actor);

        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));
        assertEquals(1, diagramSubwindow.getFacade().getDiagram().getParties().size());
        assertTrue(diagramSubwindow.getFacade().getDiagram().getParties().get(0) instanceof Object);
    }

    @Test
    public void test_adding_chars_toClassTextBox(){
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(partyDialogBox.getClassTextBox(), partyDialogBox.getSelected());
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));
        assertEquals(":A", party.getLabel().getLabel());
    }

    @Test
    public void test_delete_chars_fromClassTextBox(){
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(partyDialogBox.getClassTextBox(), partyDialogBox.getSelected());
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals("", partyDialogBox.getClassTextBox().getContents());
        assertEquals(":A", party.getLabel().getLabel());
    }

    @Test
    public void test_add_char_toInstanceTextBox(){
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        assertEquals("a", partyDialogBox.getInstanceTextBox().getContents());
        assertEquals("", party.getLabel().getLabel());

        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));
        assertEquals("a:A", party.getLabel().getLabel());
    }

    @Test
    public void test_delete_char_fromInstanceTextBox(){
        test_add_char_toInstanceTextBox();
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals(":A", party.getLabel().getLabel());
    }

    @Test
    public void test_mouse_presses(){
        partyDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10, 60)));
        assertEquals(partyDialogBox.getInstanceTextBox(), partyDialogBox.getSelected());
        partyDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10, 85)));
        assertEquals(partyDialogBox.getClassTextBox(), partyDialogBox.getSelected());
        partyDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(85, 30)));
        assertEquals(partyDialogBox.getToObject(), partyDialogBox.getSelected());
        partyDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10, 30)));
        assertEquals(partyDialogBox.getToActor(), partyDialogBox.getSelected());
    }

    @Test
    public void test_handleAction_RemoveInReposAction(){
        Set<DiagramElement> diagramElementSet = new HashSet<>();
        diagramElementSet.add(party);
        Action action = new RemoveInViewsAction(diagramElementSet);
        partyDialogBox.handleAction(action);
        assertTrue(! interactionController.getSubwindows().contains(partyDialogBox));
    }

    @Test
    public void test_handleAction_UpdateLabelAction() throws DomainException {
        party.getLabel().setLabel("b:B");
        Action action = new UpdateLabelAction(party, null);
        partyDialogBox.handleAction(action);
        assertEquals("b", partyDialogBox.getInstanceTextBox().getContents());
        assertEquals("B", partyDialogBox.getClassTextBox().getContents());
    }

    @Test
    public void test_handleAction_UpdatePartyTypeAction() throws DomainException{
        Party other = new Actor(new PartyLabel("b:B"));
        Action action = new UpdatePartyTypeAction(party, other);
        partyDialogBox.handleAction(action);
        assertTrue(! interactionController.getSubwindows().contains(partyDialogBox));
    }
}
