package window.frame;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;

public class DiagramSubwindowFrameCornerTest {

    private SubwindowFrameCorner subwindowFrameCorner1;
    private SubwindowFrameCorner subwindowFrameCorner2;
    private SubwindowFrameCorner subwindowFrameCorner3;
    private SubwindowFrameCorner subwindowFrameCorner4;

    @Before
    public void setUp() {
        Point2D point = new Point2D.Double(21,22);
        subwindowFrameCorner1 = new SubwindowFrameCorner(point, CornerType.TOPLEFT);
        subwindowFrameCorner2 = new SubwindowFrameCorner(point, CornerType.TOPRIGHT);
        subwindowFrameCorner3 = new SubwindowFrameCorner(point, CornerType.BOTTOMLEFT);
        subwindowFrameCorner4 = new SubwindowFrameCorner(point, CornerType.BOTTOMRIGHT);
    }

    @Test
    public void test_constructor_1(){
        assertTrue(subwindowFrameCorner1.getCenter().getX() == 21);
        assertTrue(subwindowFrameCorner1.getCenter().getY() == 22);
        assertTrue(subwindowFrameCorner1.getType().equals(CornerType.TOPLEFT));
    }
    @Test
    public void test_constructor_2(){
        assertTrue(subwindowFrameCorner2.getCenter().getX() == 21);
        assertTrue(subwindowFrameCorner2.getCenter().getY() == 22);
        assertTrue(subwindowFrameCorner2.getType().equals(CornerType.TOPRIGHT));
    }
    @Test
    public void test_constructor_3(){
        assertTrue(subwindowFrameCorner3.getCenter().getX() == 21);
        assertTrue(subwindowFrameCorner3.getCenter().getY() == 22);
        assertTrue(subwindowFrameCorner3.getType().equals(CornerType.BOTTOMLEFT));
    }
    @Test
    public void test_constructor_4(){
        assertTrue(subwindowFrameCorner4.getCenter().getX() == 21);
        assertTrue(subwindowFrameCorner4.getCenter().getY() == 22);
        assertTrue(subwindowFrameCorner4.getType().equals(CornerType.BOTTOMRIGHT));
    }

    @Test
    public void test_isClicked_1(){
        assertTrue(subwindowFrameCorner1.isClicked(new Point2D.Double(25,25)));
    }
    @Test
    public void test_isClicked_2(){
        assertTrue(subwindowFrameCorner2.isClicked(new Point2D.Double(25,25)));
    }
    @Test
    public void test_isClicked_3(){
        assertTrue(subwindowFrameCorner3.isClicked(new Point2D.Double(25,25)));
    }
    @Test
    public void test_isClicked_4(){
        assertTrue(subwindowFrameCorner4.isClicked(new Point2D.Double(25,25)));
    }

}