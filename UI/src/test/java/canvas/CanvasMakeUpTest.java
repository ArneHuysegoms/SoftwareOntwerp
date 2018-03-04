package canvas;
import canvascomponents.diagram.SequenceDiagram;
import org.junit.Before;
import org.junit.Test;

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

}