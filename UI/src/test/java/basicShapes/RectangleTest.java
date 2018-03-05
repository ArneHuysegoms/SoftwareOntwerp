package basicShapes;

import figures.basicShapes.Rectangle;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class RectangleTest {

    private Rectangle rectangle;
    private Rectangle rectangle2;

    @Before
    public void setUp(){
        rectangle = new Rectangle(11,12,13,14);
        rectangle2 = new Rectangle(new Point2D.Double(11,12), new Point2D.Double(24,26));
    }

    @Test
    public void test_positionTL_constructor1(){
        assertEquals(rectangle.getPositionTL(), new Point2D.Double(11,12));
    }

    @Test
    public void test_cornerTR_constructor1(){
        assertEquals(rectangle.getCornerTR(), new Point2D.Double(24,12));
    }

    @Test
    public void test_cornerBR_constructor1(){
        assertEquals(rectangle.getCornerBR(), new Point2D.Double(24,26));
    }

    @Test
    public void test_cornerBL_constructor1(){
        assertEquals(rectangle.getCornerBL(), new Point2D.Double(11,26));
    }

    @Test
    public void test_positionTL_constructor2(){
        assertEquals(rectangle2.getPositionTL(), new Point2D.Double(11,12));
    }

    @Test
    public void test_cornerTR_constructor2(){
        assertEquals(rectangle2.getCornerTR(), new Point2D.Double(24,12));
    }

    @Test
    public void test_cornerBR_constructor2(){
        assertEquals(rectangle2.getCornerBR(), new Point2D.Double(24,26));
    }

    @Test
    public void test_cornerBL_constructor2(){
        assertEquals(rectangle2.getCornerBL(), new Point2D.Double(11,26));
    }
}
