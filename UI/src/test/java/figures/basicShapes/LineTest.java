package figures.basicShapes;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LineTest {
    private Line line;
    private Line line2;

    @Before
    public void setUp(){
        line = new Line(1,2,3,4);
        line2 = new Line(new Point2D.Double(1,2), new Point2D.Double(3,4));
    }

    @Test
    public void test_start_constructor1(){
        assertEquals(line.getStart(), new Point2D.Double(1,2));
    }

    @Test
    public void test_end_constructor1(){
        assertEquals(line.getEnd(), new Point2D.Double(3,4));
    }

    @Test
    public void test_start_constructor2(){
        assertEquals(line2.getStart(), new Point2D.Double(1,2));
    }

    @Test
    public void test_end_constructor2(){
        assertEquals(line2.getEnd(), new Point2D.Double(3,4));
    }

}
