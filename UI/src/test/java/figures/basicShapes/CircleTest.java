package figures.basicShapes;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CircleTest {
    private Circle circle;
    private Circle circle2;

    @Before
    public void setUp(){
        circle = new Circle(5,5,3);
        circle2 = new Circle(new Point2D.Double(10,10), 6);
    }

    @Test
    public void test_center_constructor1(){
        assertEquals(circle.getUpperLeft(), new Point2D.Double(2,2));
    }

    @Test
    public void test_radius_constructor1(){
        assertTrue(circle.getRadius() == 3);
    }

    @Test
    public void test_center_constructor2(){
        assertEquals(circle2.getUpperLeft(), new Point2D.Double(4,4));
    }

    @Test
    public void test_radius_constructor2(){
        assertTrue(circle2.getRadius() == 6);
    }

    @Test
    public void test_width_constructor1(){
        assertTrue(circle.getWidth() == 6);
    }

    @Test
    public void test_width_constructor2(){
        assertTrue(circle2.getWidth() == 12);
    }
}
