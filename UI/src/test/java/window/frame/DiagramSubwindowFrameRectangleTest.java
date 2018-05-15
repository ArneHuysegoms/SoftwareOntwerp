package window.frame;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;

public class DiagramSubwindowFrameRectangleTest {

    private SubwindowFrameRectangle subwindowFrameRectangle1;
    private SubwindowFrameRectangle subwindowFrameRectangle2;
    private SubwindowFrameRectangle subwindowFrameRectangle3;
    private SubwindowFrameRectangle subwindowFrameRectangle4;

    @Before
    public void setUp(){
        Point2D position = new Point2D.Double(20,20);
        int height = 10;
        int width = 10;
        subwindowFrameRectangle1 = new SubwindowFrameRectangle(position, height, width, RectangleType.LEFT);
        subwindowFrameRectangle2 = new SubwindowFrameRectangle(position, height, width, RectangleType.RIGHT);
        subwindowFrameRectangle3 = new SubwindowFrameRectangle(position, height, width, RectangleType.TOP);
        subwindowFrameRectangle4 = new SubwindowFrameRectangle(position, height, width, RectangleType.BOTTOM);
    }

    @Test
    public void test_constructor_1(){
        assertTrue(subwindowFrameRectangle1.getPosition().getX() == 20);
        assertTrue(subwindowFrameRectangle1.getPosition().getY() == 20);
        assertTrue(subwindowFrameRectangle1.getHeight() == 10);
        assertTrue(subwindowFrameRectangle1.getWidth() == 10);
        assertTrue(subwindowFrameRectangle1.getType().equals(RectangleType.LEFT));
    }
    @Test
    public void test_constructor_2(){
        assertTrue(subwindowFrameRectangle2.getPosition().getX() == 20);
        assertTrue(subwindowFrameRectangle2.getPosition().getY() == 20);
        assertTrue(subwindowFrameRectangle2.getHeight() == 10);
        assertTrue(subwindowFrameRectangle2.getWidth() == 10);
        assertTrue(subwindowFrameRectangle2.getType().equals(RectangleType.RIGHT));
    }
    @Test
    public void test_constructor_3(){
        assertTrue(subwindowFrameRectangle3.getPosition().getX() == 20);
        assertTrue(subwindowFrameRectangle3.getPosition().getY() == 20);
        assertTrue(subwindowFrameRectangle3.getHeight() == 10);
        assertTrue(subwindowFrameRectangle3.getWidth() == 10);
        assertTrue(subwindowFrameRectangle3.getType().equals(RectangleType.TOP));
    }
    @Test
    public void test_constructor_4(){
        assertTrue(subwindowFrameRectangle4.getPosition().getX() == 20);
        assertTrue(subwindowFrameRectangle4.getPosition().getY() == 20);
        assertTrue(subwindowFrameRectangle4.getHeight() == 10);
        assertTrue(subwindowFrameRectangle4.getWidth() == 10);
        assertTrue(subwindowFrameRectangle4.getType().equals(RectangleType.BOTTOM));
    }
    @Test
    public void test_isClicked_1(){
        assertTrue(subwindowFrameRectangle1.isClicked(new Point2D.Double(25,25)));
    }
    @Test
    public void test_isClicked_2(){
        assertTrue(subwindowFrameRectangle2.isClicked(new Point2D.Double(25,25)));
    }
    @Test
    public void test_isClicked_3(){
        assertTrue(subwindowFrameRectangle3.isClicked(new Point2D.Double(25,25)));
    }
    @Test
    public void test_isClicked_4(){
        assertTrue(subwindowFrameRectangle4.isClicked(new Point2D.Double(25,25)));
    }
}
