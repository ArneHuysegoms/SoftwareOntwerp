package canvas;
import canvascomponents.diagram.CommunicationsDiagram;
import canvascomponents.diagram.Diagram;
import canvascomponents.diagram.SequenceDiagram;
import org.junit.Before;
import org.junit.Test;
import uievents.KeyEvent;
import uievents.KeyEventType;

import java.awt.*;

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
    public void test_handleKeyEvent(){
        Diagram d = canvasMakeUp.getActiveDiagram();
        KeyEventType type = KeyEventType.TAB;
        KeyEvent keyEvent = new KeyEvent(type);
        canvasMakeUp.handleKeyEvent(keyEvent);
        Diagram d2 = canvasMakeUp.getActiveDiagram();
        System.out.print(d.getClass());
        System.out.print(d2.getClass());
        assertNotEquals(d.getClass(), d2.getClass());
    }

}