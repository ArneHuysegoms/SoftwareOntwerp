package window.dialogbox;

import exception.UIException;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import uievents.MouseEvent;
import uievents.MouseEventType;
import view.diagram.CommunicationView;
import view.diagram.SequenceView;
import window.diagram.DiagramSubwindow;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class DiagramDialogBoxTest {

    DiagramSubwindow diagramSubwindow;
    DiagramDialogBox diagramDialogBox;

    @Before
    public void setUp() {
        diagramSubwindow = new DiagramSubwindow(new Point2D.Double(500, 500));
        try {
            diagramDialogBox = new DiagramDialogBox(new Point2D.Double(50, 50), diagramSubwindow);
        }
        catch (UIException exc){
            fail();
        }
    }

    @Test
    public void test_default_constructor_works(){
        assertEquals(new Point2D.Double(50, 50), diagramDialogBox.getPosition());
        assertEquals(DiagramDialogBox.WIDTH, diagramDialogBox.getWidth());
        assertEquals(DiagramDialogBox.HEIGHT, diagramDialogBox.getHeight());
        assertNotNull(diagramDialogBox.getToCommunicationDiagram());
        assertNotNull(diagramDialogBox.getToSequenceDiagram());
        assertNotNull(diagramDialogBox.getSelected());
        assertEquals(diagramDialogBox.getToCommunicationDiagram(), diagramDialogBox.getSelected());
        assertEquals(DiagramDialogBox.TOCOMMUNICATIONDIAGRAM_DESPCRIPTION,DiagramDialogBox.getTocommunicationdiagramDespcription());
        assertEquals(DiagramDialogBox.TOSEQUENCEDIAGRAM_DESCRIPTION, DiagramDialogBox.getTosequencediagramDescription());
    }

    @Test
    public void test_Tab_switches_radioButton(){
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(diagramDialogBox.getToSequenceDiagram(), diagramDialogBox.getSelected());
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(diagramDialogBox.getToCommunicationDiagram(), diagramDialogBox.getSelected());
    }

    @Test
    public void test_Space_executes_selected_radioButton(){
        assertEquals(diagramDialogBox.getToCommunicationDiagram(), diagramDialogBox.getSelected());
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));
        assertTrue(diagramSubwindow.getFacade().getActiveView() instanceof CommunicationView);
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.TAB));
        assertEquals(diagramDialogBox.getToSequenceDiagram(), diagramDialogBox.getSelected());
        diagramDialogBox.handleKeyEvent(new KeyEvent(KeyEventType.SPACE));
        assertTrue(diagramSubwindow.getFacade().getActiveView() instanceof SequenceView);
    }

    @Test
    public void test_MousePress_works(){
        assertEquals(diagramDialogBox.getToCommunicationDiagram(), diagramDialogBox.getSelected());
        diagramDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(20, 30)));
        assertTrue(diagramSubwindow.getFacade().getActiveView() instanceof CommunicationView);
        diagramDialogBox.handleMouseEvent(new MouseEvent(MouseEventType.PRESSED, new Point2D.Double(20, 60)));
        assertTrue(diagramSubwindow.getFacade().getActiveView() instanceof SequenceView);
    }
}
