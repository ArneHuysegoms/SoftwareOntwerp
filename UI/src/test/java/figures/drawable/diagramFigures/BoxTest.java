package figures.drawable.diagramFigures;

import figures.drawable.basicShapes.Box;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class BoxTest {
    private Box box;

    @Before
    public void setUp(){
        box = new Box(new Point2D.Double(1,2), new Point2D.Double(3,4));
    }

    @Test
    public void test_getTl(){
        assertEquals(box.getTl(), new Point2D.Double(1,2));
    }

    @Test
    public void test_getBr(){
        assertEquals(box.getBr(), new Point2D.Double(3,4));
    }
}
