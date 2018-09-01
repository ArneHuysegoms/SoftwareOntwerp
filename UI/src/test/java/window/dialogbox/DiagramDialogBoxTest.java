package window.dialogbox;

import exception.UIException;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import uievents.MouseEvent;
import uievents.MouseEventType;
import view.diagram.CommunicationView;
import view.diagram.SequenceView;
import window.diagram.DiagramSubwindow;
import window.elements.DialogboxElement;
import window.elements.radiobutton.ToCommunicationRadioButton;
import window.elements.radiobutton.ToSequenceRadioButton;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.Assert.*;

public class DiagramDialogBoxTest {

    DiagramSubwindow diagramSubwindow,diagramSubwindow2;
    DiagramDialogBox diagramDialogBox,diagramDialogBox2,diagramDialogBox3;

    @Before
    public void setUp() {
        diagramSubwindow = new DiagramSubwindow(new Point2D.Double(500, 500));
        diagramSubwindow2 = new DiagramSubwindow(new Point2D.Double(500, 500));
        try {
            diagramDialogBox = new DiagramDialogBox(new Point2D.Double(50, 50), diagramSubwindow);
            diagramDialogBox2 = new DiagramDialogBox(new Point2D.Double(50, 50), diagramSubwindow);
            diagramDialogBox3 = new DiagramDialogBox(new Point2D.Double(50, 50), diagramSubwindow2);
        }
        catch (UIException exc){
            fail();
        }
    }

    @Test
    public void test_default_constructor_works(){
        List<DialogboxElement> list = diagramDialogBox.getElementList();
        assertEquals(list.size(), diagramDialogBox.DIAGRAMBOXLIST.size());
        assertEquals(diagramSubwindow, diagramDialogBox.getDiagramSubwindow());
        assertEquals(DiagramDialogBox.WIDTH, diagramDialogBox.getWidth());
        assertEquals(DiagramDialogBox.HEIGHT, diagramDialogBox.getHeight());
        assertEquals(2, diagramDialogBox.getElementList().size());
        assertTrue(diagramSubwindow.getFacade().activeDiagramIsSequence());
    }

    @Test
    public void test_Tab_switches_radioButton(){
        DialogboxElement ele = diagramDialogBox.getSelected();

        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(!ele.equals(diagramDialogBox.getSelected()));
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertTrue(ele.equals(diagramDialogBox.getSelected()));
    }

    @Test
    public void test_Space_executes_selected_radioButton(){
        assertTrue(diagramSubwindow.getFacade().activeDiagramIsSequence());

        if(diagramDialogBox.getSelected() instanceof ToSequenceRadioButton){
            diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        }

        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));
        assertTrue(diagramSubwindow.getFacade().activeDiagramIsCommunication());

        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));
        assertTrue(diagramSubwindow.getFacade().activeDiagramIsSequence());
    }

    @Test
    public void test_MousePress_works(){
        assertTrue(diagramDialogBox.getSelected() instanceof ToCommunicationRadioButton);
        diagramDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(20, 30)));
        assertTrue(diagramSubwindow.getFacade().getActiveView() instanceof CommunicationView);
        diagramDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(20, 60)));
        assertTrue(diagramSubwindow.getFacade().getActiveView() instanceof SequenceView);
    }

    @Test
    public void test_designMode() throws DomainException {
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CTRLE));
        assertTrue(diagramDialogBox.getDesignerMode());
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.ENTER));
        assertFalse(diagramDialogBox.getDesignerMode());
    }

    @Test
    public void test_description_addAndDelChar(){
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CTRLE));
        assertEquals(diagramDialogBox.getSelected().getDescription().toString(), "Communication");

        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR,'t'));
        assertEquals(diagramDialogBox.getSelected().getDescription(), "Communicationt");
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
        assertEquals(diagramDialogBox.getSelected().getDescription(), "Communication");
    }

    @Test
    public void test_selectedindex_bigger_than_list(){
        assertEquals(0,diagramDialogBox.getSelectedindex());
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(1,diagramDialogBox.getSelectedindex());

        diagramDialogBox.selectedindex = 100;
        diagramDialogBox.updateList();
        assertEquals(0,diagramDialogBox.getSelectedindex());
    }

    @Test
    public void test_addDescription_sync_sameInteraction(){
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CTRLE));
        assertEquals(diagramDialogBox.getSelected().getDescription().toString(), "Communication");
        assertEquals(diagramDialogBox2.getSelected().getDescription().toString(), "Communication");

        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR,'t'));
        assertEquals(diagramDialogBox.getSelected().getDescription(), "Communicationt");
        assertEquals(diagramDialogBox2.getSelected().getDescription(), "Communicationt");
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
    }

    @Test
    public void test_addDescription_sync_otherInteraction(){
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CTRLE));
        assertEquals(diagramDialogBox.getSelected().getDescription().toString(), "Communication");
        assertEquals(diagramDialogBox3.getSelected().getDescription().toString(), "Communication");

        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.CHAR,'t'));
        assertEquals(diagramDialogBox.getSelected().getDescription(), "Communicationt");
        assertEquals(diagramDialogBox3.getSelected().getDescription(), "Communicationt");
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.BACKSPACE));
    }
}
