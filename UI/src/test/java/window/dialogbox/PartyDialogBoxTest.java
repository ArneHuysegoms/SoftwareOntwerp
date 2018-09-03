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
import window.elements.DialogboxElement;
import window.elements.button.CloseWindowButton;
import window.elements.textbox.ClassTextBox;
import window.elements.textbox.InstanceTextBox;
import window.elements.textbox.TextBox;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PartyDialogBoxTest {

    Party party,party2;
    DiagramSubwindow diagramSubwindow, diagramSubwindow2;
    PartyDialogBox partyDialogBox,partyDialogBox2, partyDialogBox3;
    InteractionController interactionController, interactionController2;

    @Before
    public void setUp(){
        try{
            // partyDialogBox en partyDialogBox2 behoren tot zelfde interaction

            interactionController = new InteractionController();
            diagramSubwindow = new DiagramSubwindow(new Point2D.Double(500, 500));
            CloseWindowButton closeWindowButton2 =new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, interactionController));
            diagramSubwindow.createFrame(closeWindowButton2);

            party = diagramSubwindow.getFacade().addNewParty(new Point2D.Double(75, 75));

            partyDialogBox = new PartyDialogBox(new Point2D.Double(50, 50), party, diagramSubwindow);
            partyDialogBox2 = new PartyDialogBox(new Point2D.Double(50, 50), party, diagramSubwindow);
            CloseWindowButton closeWindowButton = new CloseWindowButton(new CloseSubwindowCommand(partyDialogBox, interactionController));
            partyDialogBox.createFrame(closeWindowButton);
            partyDialogBox2.createFrame(closeWindowButton);
            interactionController.addSubwindow(diagramSubwindow);
            interactionController.addSubwindow(partyDialogBox);
            interactionController.addSubwindow(partyDialogBox2);

            // partyDialogBox3 behoort tot andere interaction

            interactionController2 = new InteractionController();
            diagramSubwindow2 = new DiagramSubwindow(new Point2D.Double(500, 500));
            CloseWindowButton closeWindowButton3 =new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow2, interactionController2));
            diagramSubwindow2.createFrame(closeWindowButton3);

            party2 = diagramSubwindow2.getFacade().addNewParty(new Point2D.Double(75, 75));

            partyDialogBox3 = new PartyDialogBox(new Point2D.Double(50, 50), party2, diagramSubwindow2);
            CloseWindowButton closeWindowButton4 = new CloseWindowButton(new CloseSubwindowCommand(partyDialogBox3, interactionController2));
            partyDialogBox3.createFrame(closeWindowButton4);
            interactionController2.addSubwindow(diagramSubwindow2);
            interactionController2.addSubwindow(partyDialogBox3);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void test_default_constructor(){
        List<DialogboxElement> list = partyDialogBox.getElementList();
        assertEquals(list.size(), PartyDialogBox.PARTYBOXLIST.size());
        assertEquals(party, partyDialogBox.getParty());
        assertEquals(diagramSubwindow, partyDialogBox.getSubwindow());
        assertEquals(PartyDialogBox.WIDTH, partyDialogBox.getWidth());
        assertEquals(PartyDialogBox.HEIGHT, partyDialogBox.getHeight());
        assertEquals(4, partyDialogBox.getElementList().size());

        for(DialogboxElement ele:partyDialogBox.getElementList()) {
            if(ele instanceof TextBox)
                assertEquals("",((TextBox) ele).getContents());
        }
    }

    @Test
    public void test_tabbing_cycles_trough_elements(){
        assertEquals(0, partyDialogBox.getSelectedindex());
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));

        assertEquals(1, partyDialogBox.getSelectedindex());
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));

        assertEquals(2, partyDialogBox.getSelectedindex());
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));

        assertEquals(3, partyDialogBox.getSelectedindex());
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));

        assertEquals(0, partyDialogBox.getSelectedindex());
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

        for(DialogboxElement ele:partyDialogBox.getElementList()) {
            if(ele instanceof ClassTextBox) {
                ClassTextBox textBox = (ClassTextBox)ele;
                assertEquals(textBox, partyDialogBox.getSelected());
                partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));
                assertEquals(":A", party.getLabel().getLabel());
            }
        }
    }

    @Test
    public void test_delete_chars_fromClassTextBox(){
        test_adding_chars_toClassTextBox();

        for(DialogboxElement ele:partyDialogBox.getElementList()) {
            if(ele instanceof ClassTextBox) {
                ClassTextBox textBox = (ClassTextBox)ele;
                assertEquals(textBox, partyDialogBox.getSelected());
                assertEquals("A", textBox.getContents());
                partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
                assertEquals("", textBox.getContents());
                assertEquals(":A", party.getLabel().getLabel());
            }
        }
    }

    @Test
    public void test_add_char_toInstanceTextBox(){
        test_adding_chars_toClassTextBox();

        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));

        for(DialogboxElement ele:partyDialogBox.getElementList()) {
            if(ele instanceof InstanceTextBox) {
                InstanceTextBox textBox = (InstanceTextBox)ele;
                partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
                assertEquals("a", textBox.getContents());
            }
        }
    }

    @Test
    public void test_delete_char_fromInstanceTextBox(){
        test_add_char_toInstanceTextBox();
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals(":A", party.getLabel().getLabel());
    }

    @Test
    public void test_mouse_presses(){
        partyDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10, 60)));
        assertEquals(partyDialogBox.getElementList().get(2), partyDialogBox.getSelected());
        partyDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10, 85)));
        assertEquals(partyDialogBox.getElementList().get(3), partyDialogBox.getSelected());
        partyDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(85, 30)));
        assertEquals(partyDialogBox.getElementList().get(1), partyDialogBox.getSelected());
        partyDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(10, 30)));
        assertEquals(partyDialogBox.getElementList().get(0), partyDialogBox.getSelected());
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
        for(DialogboxElement ele:partyDialogBox.getElementList()) {
            if(ele instanceof InstanceTextBox){
                assertEquals("b", ((InstanceTextBox)ele).getContents());
            }
            else if(ele instanceof ClassTextBox){
                assertEquals("B", ((ClassTextBox)ele).getContents());
            }
        }
    }

    @Test
    public void test_handleAction_UpdatePartyTypeAction() throws DomainException{
        Party other = new Actor(new PartyLabel("b:B"));
        Action action = new UpdatePartyTypeAction(party, other);
        partyDialogBox.handleAction(action);
        assertTrue(! interactionController.getSubwindows().contains(partyDialogBox));
    }

    @Test
    public void test_designMode() throws DomainException{
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CTRLE));
        assertTrue(partyDialogBox.getDesignerMode());
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.ENTER));
        assertFalse(partyDialogBox.getDesignerMode());
    }

    @Test
    public void test_description_addChar(){
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CTRLE));
        assertEquals(partyDialogBox.getSelected().getDescription().toString(), "Actor");

        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR,'t'));
        assertEquals(partyDialogBox.getSelected().getDescription(), "Actort");
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));

    }

    @Test
    public void test_description_delChar(){
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CTRLE));
        assertEquals(partyDialogBox.getSelected().getDescription().toString(), "Actor");

        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR,'t'));
        assertEquals(partyDialogBox.getSelected().getDescription(), "Actort");

        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals(partyDialogBox.getSelected().getDescription(), "Actor");
    }

    @Test
    public void test_selectedindex_bigger_than_list(){
        assertEquals(0,partyDialogBox.getSelectedindex());
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(1,partyDialogBox.getSelectedindex());

        partyDialogBox.selectedindex = 100;
        partyDialogBox.updateList();
        assertEquals(0,partyDialogBox.getSelectedindex());
    }

    @Test
    public void test_addDescription_sync_sameInteraction(){
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CTRLE));
        assertEquals(partyDialogBox.getSelected().getDescription().toString(), "Actor");
        assertEquals(partyDialogBox2.getSelected().getDescription().toString(), "Actor");

        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR,'t'));
        assertEquals(partyDialogBox.getSelected().getDescription(), "Actort");
        assertEquals(partyDialogBox2.getSelected().getDescription(), "Actort");
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
    }

    @Test
    public void test_addDescription_sync_otherInteraction(){
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CTRLE));
        assertEquals(partyDialogBox.getSelected().getDescription().toString(), "Actor");
        assertEquals(partyDialogBox3.getSelected().getDescription().toString(), "Actor");

        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR,'t'));
        assertEquals(partyDialogBox.getSelected().getDescription(), "Actort");
        assertEquals(partyDialogBox3.getSelected().getDescription(), "Actort");
        partyDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
    }

}
