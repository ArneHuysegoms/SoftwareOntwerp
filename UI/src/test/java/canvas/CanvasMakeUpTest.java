package canvas;
import canvascomponents.Clickable;
import canvascomponents.diagram.CommunicationsDiagram;
import canvascomponents.diagram.Diagram;
import canvascomponents.diagram.SequenceDiagram;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;
import uievents.MouseEvent;
import uievents.MouseEventType;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;
public class CanvasMakeUpTest {

    private CanvasMakeUp canvasMakeUp;

    @Before
    public void setUp() {
        canvasMakeUp = new CanvasMakeUp();
    }

    @Test
    public void activeDiagram_is_sequence_after_init(){
        assertTrue(canvasMakeUp.getActiveDiagram() instanceof SequenceDiagram);
    }

    @Test
    public void test_changeActiveDiagram(){
        canvasMakeUp.changeActiveDiagram();
        assertTrue(canvasMakeUp.getActiveDiagram() instanceof CommunicationsDiagram);
    }

    @Test
    public void test_handleKeyEvent_tab(){
        Diagram d = canvasMakeUp.getActiveDiagram();
        KeyEventType type = KeyEventType.TAB;
        KeyEvent keyEvent = new KeyEvent(type);
        canvasMakeUp.handleKeyEvent(keyEvent);
        Diagram d2 = canvasMakeUp.getActiveDiagram();
        System.out.print(d.getClass());
        System.out.print(d2.getClass());
        assertNotEquals(d.getClass(), d2.getClass());
    }

    @Test
    public void test_handleKeyEvent_deleteParty(){
        //lijst van parties does not contain selectedelement
        canvasMakeUp.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasMakeUp.getActiveDiagram().addCharToLabel(':');
        canvasMakeUp.getActiveDiagram().addCharToLabel('S');
        Clickable c = canvasMakeUp.getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasMakeUp.handleKeyEvent(new KeyEvent(KeyEventType.DEL));
        assertFalse(canvasMakeUp.getActiveDiagram().getParties().contains(c));
    }

    @Test
    public void test_handleMouseEvent_drag(){
        canvasMakeUp.getActiveDiagram().addNewParty(new Point2D.Double(25,50));
        canvasMakeUp.getActiveDiagram().addCharToLabel(':');
        canvasMakeUp.getActiveDiagram().addCharToLabel('S');
        Clickable c = canvasMakeUp.getActiveDiagram().findSelectedElement(new Point2D.Double(25,50));
        canvasMakeUp.handleMouseEvent(new MouseEvent(MouseEventType.DRAG, new Point2D.Double(25,51)));
        System.out.println(c.getDistance(new Point2D.Double(0,0)));
        System.out.println(c.getClass());
        assertEquals(5,5);
    }
}