package uievents;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
public class MouseEventTest {

    private MouseEvent mouseEvent;
    private MouseEvent mouseEvent2;

    @Before
    public void setUp() {
        Point2D point = new Point2D.Double(0,0);
        Point2D point2 = new Point2D.Double(5,5);
        mouseEvent = new MouseEvent(MouseEventType.LEFTDOUBLECLICK, point);
        mouseEvent2 = new MouseEvent(MouseEventType.LEFTCLICK, point2);
    }

    @Test
    public void test_mouseEventType(){
        assertEquals(mouseEvent.getMouseEventType(), MouseEventType.LEFTDOUBLECLICK);
        assertEquals(mouseEvent2.getMouseEventType(), MouseEventType.LEFTCLICK);
    }

    @Test
    public void test_Point2D(){
        Point2D test = new Point2D.Double(0,0);
        Point2D test2 = new Point2D.Double(5,5);
        assertEquals(mouseEvent.getPoint(), test);
        assertEquals(mouseEvent2.getPoint(), test2);
    }
}
