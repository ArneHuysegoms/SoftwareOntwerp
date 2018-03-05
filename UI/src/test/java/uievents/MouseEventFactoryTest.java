package uievents;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;

public class MouseEventFactoryTest {
    private MouseEvent mouseEvent1;
    private MouseEvent mouseEvent2;
    private MouseEvent mouseEvent3;
    private MouseEvent mouseEvent4;
    private Point2D point;
    private MouseEventFactory mouseEventFactory;

    @Before
    public void setUp(){
        mouseEventFactory = new MouseEventFactory();
        point = new Point2D.Double(0,0);
        mouseEvent1 = new MouseEvent(MouseEventType.LEFTDOUBLECLICK, point);
        mouseEvent2 = new MouseEvent(MouseEventType.LEFTCLICK, point);
        mouseEvent3 = new MouseEvent(MouseEventType.DRAG, point);
        mouseEvent4 = new MouseEvent(MouseEventType.RELEASE, point);
    }
    @Test
    public void test_create_leftDoubleClick(){
        MouseEvent test = mouseEventFactory.createMouseEvent(java.awt.event.MouseEvent.MOUSE_CLICKED, 2, point);
        assertTrue(test.equals(mouseEvent1));
    }
    @Test
    public void test_create_leftClick(){
        MouseEvent test = mouseEventFactory.createMouseEvent(java.awt.event.MouseEvent.MOUSE_CLICKED, 1, point);
        assertTrue(test.equals(mouseEvent2));
    }
    @Test
    public void test_create_drag(){
        MouseEvent test = mouseEventFactory.createMouseEvent(java.awt.event.MouseEvent.MOUSE_DRAGGED, 1, point);
        assertTrue(test.equals(mouseEvent3));
    }
    @Test
    public void test_create_release(){
        MouseEvent test = mouseEventFactory.createMouseEvent(java.awt.event.MouseEvent.MOUSE_RELEASED, 1, point);
        assertTrue(test.equals(mouseEvent4));
    }
}
