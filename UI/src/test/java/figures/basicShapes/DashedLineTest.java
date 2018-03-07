package figures.basicShapes;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class DashedLineTest {
    private DashedLine dashedLine;
    private DashedLine dashedLine2;

    @Before
    public void setUp(){
        dashedLine = new DashedLine(1,2,3,4);
        dashedLine2 = new DashedLine(new Point2D.Double(1,2), new Point2D.Double(3,4));
    }

    @Test
    public void test_getStart_constructor1(){
        assertEquals(dashedLine.getStart(), new Point2D.Double(1,2));
    }
    @Test
    public void test_getStart_constructor2(){
        assertEquals(dashedLine2.getStart(), new Point2D.Double(1,2));
    }
    @Test
    public void test_getEnd_constructor1(){
        assertEquals(dashedLine.getEnd(), new Point2D.Double(3,4));
    }
    @Test
    public void test_getEnd_constructor2(){
        assertEquals(dashedLine2.getEnd(), new Point2D.Double(3,4));
    }

}
