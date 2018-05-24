package window.diagram;

import action.Action;
import action.DialogBoxOpenedAction;
import command.closeWindow.CloseSubwindowCommand;
import diagram.label.Label;
import diagram.label.PartyLabel;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Party;
import exception.UIException;
import exceptions.DomainException;
import facade.DomainFacade;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import uievents.MouseEvent;
import uievents.MouseEventType;
import view.diagram.CommunicationView;
import view.diagram.DiagramView;
import window.Subwindow;
import window.dialogbox.*;
import window.elements.button.CloseWindowButton;

import java.awt.geom.Point2D;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class DiagramSubwindowTest {

    private DiagramSubwindow diagramSubwindow;
    private DiagramSubwindow diagramSubwindow2;
    private CloseWindowButton button;
    private CloseWindowButton button2;
    private Point2D point;


    @Before
    public void setUp(){
        point = new Point2D.Double(200,200);
        diagramSubwindow = new DiagramSubwindow(point);
        button = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow, null));
        diagramSubwindow.createFrame(button);

        diagramSubwindow2 = new DiagramSubwindow(point);
        button2 = new CloseWindowButton(new CloseSubwindowCommand(diagramSubwindow2, null));
        diagramSubwindow2.createFrame(button2);

    }

    @Test
    public void test_default_constructor(){
        assertEquals(point, diagramSubwindow.getPosition());
        assertEquals(Subwindow.WINDOWHEIGHT, diagramSubwindow.getHeight());
        assertEquals(Subwindow.WINDOWWIDTH, diagramSubwindow.getWidth());
        assertFalse(diagramSubwindow.isCommunicationDiagram());
        assertTrue(diagramSubwindow.isSequenceDiagram());
    }

    @Test
    public void test_advanced_constructor(){
        DiagramSubwindow s = new DiagramSubwindow(point, new DomainFacade());
        assertEquals(point, diagramSubwindow.getPosition());
        assertEquals(Subwindow.WINDOWHEIGHT, diagramSubwindow.getHeight());
        assertEquals(Subwindow.WINDOWWIDTH, diagramSubwindow.getWidth());
        assertFalse(diagramSubwindow.isCommunicationDiagram());
        assertTrue(diagramSubwindow.isSequenceDiagram());
    }

    @Test
    public void test_copy_of_facade_works(){
        DomainFacade facade = diagramSubwindow.getCopyOfFacade();
        assertEquals(diagramSubwindow.getFacade().getActiveView().getClass(), facade.getActiveView().getClass());
    }

    @Test
    public void test_handleTab() throws UIException, DomainException {
        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(diagramSubwindow.getFacade().getActiveView() instanceof CommunicationView);
    }

    @Test
    public void test_double_click_adds_party(){
        assertEquals(0, diagramSubwindow.getFacade().getDiagram().getParties().size());
        MouseEvent mouseEvent = new MouseEvent(MouseEventType.LEFTDOUBLECLICK, new Point2D.Double(50, 50));
        diagramSubwindow.handleMouseEvent(mouseEvent);
        assertTrue(diagramSubwindow.getSelected() instanceof PartyLabel);
        assertEquals(1, diagramSubwindow.getFacade().getDiagram().getParties().size());
        assertTrue(diagramSubwindow.isEditing());
        assertEquals("I", diagramSubwindow.getLabelContainer());
    }

    @Test
    public void test_adding_and_deleting_characters() throws DomainException, UIException{
        addParty(50, 50);
        Label label = (Label) diagramSubwindow.getSelected();

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        assertTrue(diagramSubwindow.isLabelMode());
        assertEquals("", label.getLabel());

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        assertTrue(diagramSubwindow.isLabelMode());
        assertEquals("", label.getLabel());

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));
        assertFalse(diagramSubwindow.isLabelMode());
        assertEquals("a:A", label.getLabel());

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertTrue(diagramSubwindow.isLabelMode());
        assertEquals("a:A", label.getLabel());

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertTrue(diagramSubwindow.isLabelMode());
        assertEquals("a:A", label.getLabel());

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        assertTrue(diagramSubwindow.isLabelMode());
        assertEquals("a:A", label.getLabel());

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'B'));
        assertFalse(diagramSubwindow.isLabelMode());
        assertEquals("a:B", label.getLabel());

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        assertFalse(diagramSubwindow.isLabelMode());
        assertEquals("a:Ba", label.getLabel());
    }

    @Test
    public void test_deleting_diagramElement() throws DomainException, UIException{
        addParty(50, 50);
        assertEquals(1, diagramSubwindow.getFacade().getDiagram().getParties().size());

        Label label = (Label) diagramSubwindow.getSelected();
        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'a'));
        assertTrue(diagramSubwindow.isLabelMode());
        assertEquals("", label.getLabel());

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, ':'));
        assertTrue(diagramSubwindow.isLabelMode());
        assertEquals("", label.getLabel());

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, 'A'));
        assertFalse(diagramSubwindow.isLabelMode());
        assertEquals("a:A", label.getLabel());

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.DEL));
        assertEquals(0, diagramSubwindow.getFacade().getDiagram().getParties().size());
    }

    @Test
    public void test_dragging_party() throws DomainException, UIException{
        addParty_with_valid_label(50, 50, "a:A");
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(50,50)));
        assertTrue(diagramSubwindow.getSelected() instanceof Party);
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.DRAG, new Point2D.Double(100,50)));
        Party p = (Party) diagramSubwindow.getSelected();
        assertEquals(new Point2D.Double(100, 50), diagramSubwindow.getFacade().getActiveView().getPartyView().getLocationOfParty(p));
    }

    @Test
    public void test_changing_party_type() throws DomainException, UIException{
        addParty_with_valid_label(50, 50, "a:A");
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(50,50)));
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.LEFTDOUBLECLICK, new Point2D.Double(50,50)));
        assertTrue(diagramSubwindow.getFacade().getDiagram().getParties().get(0) instanceof Object);
    }

    @Test
    public void test_handle_lefClick(){
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.LEFTCLICK, new Point2D.Double(50, 50)));
    }

    @Test
    public void test_handle_adding_messages() throws DomainException, UIException {
        addParty_with_valid_label(50, 50, "a:A");
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(300,200)));
        addParty_with_valid_label(150, 50, "b:B");
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(300,200)));
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(75,175)));
        assertTrue(diagramSubwindow.getSelected() instanceof DiagramView.MessageStart);
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.RELEASE, new Point2D.Double(175,175)));
        assertNotNull(diagramSubwindow.getFacade().getDiagram().getFirstMessage());
    }

    @Test
    public void test_handle_dialogBox_for_party_by_Label() throws DomainException, UIException{
        addParty_with_valid_label(50, 50, "a:A");
        assertTrue(diagramSubwindow.getSelected() instanceof  PartyLabel);
        Action action = diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CTRLENTER));
        assertTrue(action instanceof DialogBoxOpenedAction);
        DialogBox box = ((DialogBoxOpenedAction) action).getDialogBox();
        assertTrue(box instanceof PartyDialogBox);
        Party party = ((PartyDialogBox) box).getParty();
        assertEquals("a:A", party.getLabel().getLabel());
    }

    @Test
    public void test_handle_dialogBox_for_party_by_party()throws DomainException, UIException{
        addParty_with_valid_label(50, 50, "a:A");
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(50,50)));
        assertTrue(diagramSubwindow.getSelected() instanceof  Party);
        Action action = diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CTRLENTER));
        assertTrue(action instanceof DialogBoxOpenedAction);
        DialogBox box = ((DialogBoxOpenedAction) action).getDialogBox();
        assertTrue(box instanceof PartyDialogBox);
        Party party = ((PartyDialogBox) box).getParty();
        assertEquals("a:A", party.getLabel().getLabel());
    }

    @Test
    public void test_handle_dialogBox_for_diagram()throws DomainException, UIException{
        Action action = diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CTRLENTER));
        assertTrue(action instanceof DialogBoxOpenedAction);
        DialogBox box = ((DialogBoxOpenedAction) action).getDialogBox();
        assertTrue(box instanceof DiagramDialogBox);
    }

    @Test
    public void test_handle_open_invocationDialogBox() throws DomainException, UIException {
        addParty_with_valid_label(50, 50, "a:A");
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(300,200)));
        addParty_with_valid_label(150, 50, "b:B");
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(300,200)));
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(75,175)));
        assertTrue(diagramSubwindow.getSelected() instanceof DiagramView.MessageStart);
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.RELEASE, new Point2D.Double(175,175)));
        assertNotNull(diagramSubwindow.getFacade().getDiagram().getFirstMessage());
        Action action = diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CTRLENTER));
        assertTrue(action instanceof DialogBoxOpenedAction);
        DialogBox box = ((DialogBoxOpenedAction) action).getDialogBox();
        assertTrue(box instanceof InvocationMessageDialogBox);
    }

    @Test
    public void test_handle_open_ResultMessageDialogBox() throws DomainException, UIException {
        addParty_with_valid_label(50, 50, "a:A");
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(300,200)));
        addParty_with_valid_label(150, 50, "b:B");
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(300,200)));
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(75,175)));
        assertTrue(diagramSubwindow.getSelected() instanceof DiagramView.MessageStart);
        diagramSubwindow.handleMouseEvent(new MouseEvent(MouseEventType.RELEASE, new Point2D.Double(175,175)));
        assertNotNull(diagramSubwindow.getFacade().getDiagram().getFirstMessage());
        Message result = diagramSubwindow.getFacade().getDiagram().getFirstMessage().getNextMessage();
        diagramSubwindow.setSelected(result.getLabel());
        Action action = diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CTRLENTER));
        assertTrue(action instanceof DialogBoxOpenedAction);
        DialogBox box = ((DialogBoxOpenedAction) action).getDialogBox();
        assertTrue(box instanceof ResultMessageDialogBox);
    }

    private void addParty(int x, int y) {
        MouseEvent mouseEvent = new MouseEvent(MouseEventType.LEFTDOUBLECLICK, new Point2D.Double(x, y));
        diagramSubwindow.handleMouseEvent(mouseEvent);
    }

    private void addParty_with_valid_label(int x, int y, String lab) throws DomainException, UIException{
        MouseEvent mouseEvent = new MouseEvent(MouseEventType.LEFTDOUBLECLICK, new Point2D.Double(x, y));
        diagramSubwindow.handleMouseEvent(mouseEvent);

        Label label = (Label) diagramSubwindow.getSelected();
        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, lab.charAt(0)));

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, lab.charAt(1)));

        diagramSubwindow.handleKeyEvent(new KeyEvent(KeyEventType.CHAR, lab.charAt(2)));
        assertEquals(lab, label.getLabel());
    }


    /////////////////////////////////////////
    // old tests                           //
    /////////////////////////////////////////

    @Test
    public void test_subwindow_labelmode(){
        assertFalse(diagramSubwindow.isLabelMode());
    }
    @Test
    public void test_subwindow_facade_repos_are_empty(){
        assertTrue(diagramSubwindow.getFacade().getActiveView().getPartyView().getAllParties().isEmpty());
        assertTrue(diagramSubwindow.getFacade().getActiveView().getLabelView().getMap().isEmpty());
    }

    @Test
    public void test_subwindow_frame_has_4_corners(){
        assertTrue(diagramSubwindow.getFrame().getCorners().size() == 4);
    }
    @Test
    public void test_subwindow_frame_has_TL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow.getFrame().getCorners().get(0).getCenter().getX() == 200);
        assertTrue(diagramSubwindow.getFrame().getCorners().get(0).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow_frame_has_TR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow.getFrame().getCorners().get(1).getCenter().getX() == 800);
        assertTrue(diagramSubwindow.getFrame().getCorners().get(1).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow_frame_has_BL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow.getFrame().getCorners().get(2).getCenter().getX() == 200);
        assertTrue(diagramSubwindow.getFrame().getCorners().get(2).getCenter().getY() == 800);
    }
    @Test
    public void test_subwindow_frame_has_BR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow.getFrame().getCorners().get(3).getCenter().getX() == 800);
        assertTrue(diagramSubwindow.getFrame().getCorners().get(3).getCenter().getY() == 800);
    }

    @Test
    public void test_subwindow_frame_rectangle_left(){
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(0).getPosition().getX() == 205);
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(0).getPosition().getY() == 195);
    }
    @Test
    public void test_subwindow_frame_rectangle_right(){
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(1).getPosition().getX() == 195);
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(1).getPosition().getY() == 205);
    }
    @Test
    public void test_subwindow_frame_rectangle_top(){
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(2).getPosition().getX() == 205);
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(2).getPosition().getY() == (195 + diagramSubwindow.getHeight()));
    }
    @Test
    public void test_subwindow_frame_rectangle_bottom(){
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(3).getPosition().getX() == (195 + diagramSubwindow.getWidth()));
        assertTrue(diagramSubwindow.getFrame().getRectangles().get(3).getPosition().getY() == 205);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_setHeight_negative(){
        diagramSubwindow.setHeight(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void test_setWidth_negative(){
        diagramSubwindow.setWidth(-1);
    }


    @Test
    public void test_subwindow2_width(){
        assertEquals(diagramSubwindow2.getWidth(), 600);
    }
    @Test
    public void test_subwindow2_height(){
        assertEquals(diagramSubwindow2.getHeight(), 600);
    }
    @Test
    public void test_subwindow2_position(){
        assertTrue(diagramSubwindow2.getPosition().equals(point));
    }

    @Test
    public void test_subwindow2_labelmode(){
        assertFalse(diagramSubwindow2.isLabelMode());
    }
    @Test
    public void test_subwindow2_facade_repos_are_empty(){
        assertTrue(diagramSubwindow2.getFacade().getActiveView().getPartyView().getAllParties().isEmpty());
        assertTrue(diagramSubwindow2.getFacade().getActiveView().getLabelView().getMap().isEmpty());
    }
    @Test
    public void test_subwindow2_facade_repos_are_not_empty_after_fill() throws DomainException {
        try {
            diagramSubwindow2.getFacade().getActiveView().getPartyView().addPartyWithLocation(new Actor(new PartyLabel(":Actor")), new Point2D.Double(30, 30));
        } catch (DomainException d) {
            System.out.println(d.getMessage());
        }
        assertFalse(diagramSubwindow2.getFacade().getActiveView().getPartyView().getAllParties().isEmpty());
    }

    @Test
    public void test_subwindow2_frame_has_4_corners(){
        assertTrue(diagramSubwindow2.getFrame().getCorners().size() == 4);
    }
    @Test
    public void test_subwindow2_frame_has_TL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(0).getCenter().getX() == 200);
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(0).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow2_frame_has_TR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(1).getCenter().getX() == 800);
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(1).getCenter().getY() == 200);
    }
    @Test
    public void test_subwindow2_frame_has_BL_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(2).getCenter().getX() == 200);
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(2).getCenter().getY() == 800);
    }
    @Test
    public void test_subwindow2_frame_has_BR_corner(){
        // 200 200
        // 200+600 200
        // 200 200+600
        // 200+600 200+600
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(3).getCenter().getX() == 800);
        assertTrue(diagramSubwindow2.getFrame().getCorners().get(3).getCenter().getY() == 800);
    }

    @Test
    public void test_subwindow2_frame_rectangle_left(){
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(0).getPosition().getX() == 205);
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(0).getPosition().getY() == 195);
    }
    @Test
    public void test_subwindow2_frame_rectangle_right(){
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(1).getPosition().getX() == 195);
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(1).getPosition().getY() == 205);
    }
    @Test
    public void test_subwindow2_frame_rectangle_top(){
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(2).getPosition().getX() == 205);
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(2).getPosition().getY() == (195 + diagramSubwindow.getHeight()));
    }
    @Test
    public void test_subwindow2_frame_rectangle_bottom(){
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(3).getPosition().getX() == (195 + diagramSubwindow.getWidth()));
        assertTrue(diagramSubwindow2.getFrame().getRectangles().get(3).getPosition().getY() == 205);
    }
    @Test
    public void test_absolute_position(){
        assertTrue(diagramSubwindow.getAbsolutePosition(new Point2D.Double(24,25)).getX() == 224);
        assertTrue(diagramSubwindow.getAbsolutePosition(new Point2D.Double(24,25)).getY() == 225);
    }
    @Test
    public void test_subwindow_isClicked(){
        assertTrue(diagramSubwindow.isClicked(new Point2D.Double(200,200)));
    }
}
