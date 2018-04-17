package subwindow;

import figures.basicShapes.Circle;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class CloseButtonTest {
    private Circle circle;
    private Circle circle2;

    @Before
    public void setUp(){
        circle = new Circle(5,5,3);
        circle2 = new Circle(new Point2D.Double(5,5), 3);
    }

    @Test
    public void test_center_constructor1(){
        assertEquals(circle.getCenter(), new Point2D.Double(2,2));
    }

    @Test
    public void test_radius_constructor1(){
        assertEquals(circle.getRadius(), 6);
    }

    @Test
    public void test_center_constructor2(){
        assertEquals(circle2.getCenter(), new Point2D.Double(2,2));
    }

    @Test
    public void test_radius_constructor2(){
        assertEquals(circle2.getRadius(), 6);
    }


}
