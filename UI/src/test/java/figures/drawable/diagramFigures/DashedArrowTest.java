package figures.drawable.diagramFigures;

import figures.drawable.basicShapes.DashedArrow;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class DashedArrowTest {
    private DashedArrow dashedArrow;

    @Before
    public void setUp(){
        dashedArrow = new DashedArrow(new Point2D.Double(1,2), new Point2D.Double(3,4));
    }

    @Test
    public void test_getLineStart(){
        assertEquals(dashedArrow.getLineStart(), new Point2D.Double(1,2));
    }

    @Test
    public void test_getLineEnd(){
        assertEquals(dashedArrow.getLineEnd(), new Point2D.Double(3,4));
    }
}
