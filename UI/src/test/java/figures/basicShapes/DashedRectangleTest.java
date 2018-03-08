package figures.basicShapes;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;

public class DashedRectangleTest {
    private DashedRectangle dashedRectangle;

    @Before
    public void setUp(){
        dashedRectangle = new DashedRectangle(1,2,3,4);
    }

    @Test
    public void test_getCornerBL(){
        assertTrue(dashedRectangle.getCornerBL().equals(new Point2D.Double(1,6)));
    }
    @Test
    public void test_getCornerBR(){
        assertTrue(dashedRectangle.getCornerBR().equals(new Point2D.Double(4,6)));
    }
    @Test
    public void test_getCornerTR(){
        assertTrue(dashedRectangle.getCornerTR().equals(new Point2D.Double(4,2)));
    }
    @Test
    public void test_getPositionTL(){
        assertTrue(dashedRectangle.getPositionTL().equals(new Point2D.Double(1,2)));
    }
}
