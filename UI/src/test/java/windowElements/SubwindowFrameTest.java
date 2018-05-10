package windowElements;

import org.junit.Before;
import org.junit.Test;
import subwindow.Button;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SubwindowFrameTest {

    private SubwindowFrame subwindowFrame;

    @Before
    public void setUp(){
        Point2D point = new Point2D.Double(21,22);
        int height = 23;
        int width = 24;
        Button button = new Button();
        subwindowFrame = new SubwindowFrame(point, height, width, button);
    }

    @Test
    public void test_constructor(){
        assertTrue(subwindowFrame.getSubwindowWidth() == 24);
        assertTrue(subwindowFrame.getSubwindowHeight() == 23);
        assertTrue(subwindowFrame.getSubwindowPoint().getX() == 21);
        assertTrue(subwindowFrame.getSubwindowPoint().getY() == 22);
    }

    @Test
    public void test_corner_TL(){
        assertTrue(subwindowFrame.getCorners().get(0).getCenter().getX() == 21);
        assertTrue(subwindowFrame.getCorners().get(0).getCenter().getY() == 22);
    }
    @Test
    public void test_corner_TL_isClicked(){
        assertTrue(subwindowFrame.isClicked(new Point2D.Double(21,22)));
    }
    @Test
    public void test_corner_TR(){
        assertTrue(subwindowFrame.getCorners().get(1).getCenter().getX() == 45);
        assertTrue(subwindowFrame.getCorners().get(1).getCenter().getY() == 22);
    }
    @Test
    public void test_corner_TR_isClicked(){
        assertTrue(subwindowFrame.isClicked(new Point2D.Double(45,22)));
    }
    @Test
    public void test_corner_BL(){
        assertTrue(subwindowFrame.getCorners().get(2).getCenter().getX() == 21);
        assertTrue(subwindowFrame.getCorners().get(2).getCenter().getY() == 45);
    }
    @Test
    public void test_corner_BL_isClicked(){
        assertTrue(subwindowFrame.isClicked(new Point2D.Double(21,45)));
    }
    @Test
    public void test_corner_BR(){
        assertTrue(subwindowFrame.getCorners().get(3).getCenter().getX() == 45);
        assertTrue(subwindowFrame.getCorners().get(3).getCenter().getY() == 45);
    }
    @Test
    public void test_corner_BR_isClicked(){
        assertTrue(subwindowFrame.isClicked(new Point2D.Double(45,45)));
    }

    @Test
    public void test_rectangle_top(){
        assertTrue(subwindowFrame.getRectangles().get(0).getPosition().getX() == 26);
        assertTrue(subwindowFrame.getRectangles().get(0).getPosition().getY() == 17);
    }
    @Test
    public void test_rectangle_top_isClicked(){
        assertTrue(subwindowFrame.isClicked(new Point2D.Double(26,17)));
    }
    @Test
    public void test_rectangle_left(){
        assertTrue(subwindowFrame.getRectangles().get(1).getPosition().getX() == 16);
        assertTrue(subwindowFrame.getRectangles().get(1).getPosition().getY() == 27);
    }
    @Test
    public void test_rectangle_left_isClicked(){
        assertTrue(subwindowFrame.isClicked(new Point2D.Double(16,27)));
    }
    @Test
    public void test_rectangle_bottom(){
        assertTrue(subwindowFrame.getRectangles().get(2).getPosition().getX() == 26);
        assertTrue(subwindowFrame.getRectangles().get(2).getPosition().getY() == 40);
    }
    @Test
    public void test_rectangle_bottom_isClicked(){
        assertTrue(subwindowFrame.isClicked(new Point2D.Double(26,40)));
    }
    @Test
    public void test_rectangle_right(){
        assertTrue(subwindowFrame.getRectangles().get(3).getPosition().getX() == 40);
        assertTrue(subwindowFrame.getRectangles().get(3).getPosition().getY() == 27);
    }
    @Test
    public void test_rectangle_right_isClicked(){
        assertTrue(subwindowFrame.isClicked(new Point2D.Double(40,27)));
    }

    @Test
    public void test_edge_of_rectangle_right_isClicked(){
        assertTrue(subwindowFrame.isClicked(new Point2D.Double(47,30)));
    }
    @Test
    public void test_titlebar_isClicked(){
        Point2D titleBarPoint = subwindowFrame.getTitleBar().getPosition();
        assertTrue(subwindowFrame.isClicked(titleBarPoint));
    }
    @Test
    public void test_button_isClicked(){
        Point2D buttonPoint = subwindowFrame.getButton().getPosition();
        assertTrue(subwindowFrame.isClicked(new Point2D.Double(30,30)));
    }
    @Test
    public void test_isClicked_fails(){
        assertFalse(subwindowFrame.isClicked(new Point2D.Double(500,500)));
    }

    @Test
    public void test_getFrameElement_corner(){
        //top left corner
        assertTrue(subwindowFrame.getFrameElement(subwindowFrame.getSubwindowPoint()).equals(subwindowFrame.getCorners().get(0)));
    }
    @Test
    public void test_getFrameElement_rectangle(){
        //edge of right rectangle
        assertTrue(subwindowFrame.getFrameElement(new Point2D.Double(47,30)).equals(subwindowFrame.getRectangles().get(3)));
    }

    @Test
    public void Test(){

    }
}