package figures.diagramFigures;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;

public class ArrowTest {
    private Arrow arrow;
    private Arrow arrow2;

    @Before
    public void setUp(){
        arrow = new Arrow(1,2,3,4);
        arrow2 = new Arrow(new Point2D.Double(1,2), new Point2D.Double(3,4));
    }

    @Test
    public void test_getLineStart_constructor1(){
        assertTrue(arrow.getLineStart().equals(new Point2D.Double(1,2)));
    }
    @Test
    public void test_getLineStart_constructor2(){
        assertTrue(arrow2.getLineStart().equals(new Point2D.Double(1,2)));
    }
    @Test
    public void test_getLineEnd_constructor1(){
        assertTrue(arrow.getLineEnd().equals(new Point2D.Double(3,4)));
    }
    @Test
    public void test_getLineEnd_constructor2(){
        assertTrue(arrow2.getLineEnd().equals(new Point2D.Double(3,4)));
    }
    @Test
    public void test_getArrowTop_constructor1(){
        assertTrue(arrow.getArrowTop().getStart().equals(new Point2D.Double(3,4)));
        assertTrue(arrow.getArrowTop().getEnd().equals(new Point2D.Double(3,-6)));
    }
    @Test
    public void test_getArrowTop_constructor2(){
        assertTrue(arrow2.getArrowTop().getStart().equals(new Point2D.Double(3,4)));
        assertTrue(arrow2.getArrowTop().getEnd().equals(new Point2D.Double(3,-6)));
    }
    @Test
    public void test_getArrowBottom_constructor1(){
        assertTrue(arrow.getArrowBottom().getStart().equals(new Point2D.Double(3,4)));
        assertTrue(arrow.getArrowBottom().getEnd().equals(new Point2D.Double(-7,4)));
    }
    @Test
    public void test_getArrowBottom_constructor2(){
        assertTrue(arrow2.getArrowBottom().getStart().equals(new Point2D.Double(3,4)));
        assertTrue(arrow2.getArrowBottom().getEnd().equals(new Point2D.Double(-7,4)));
    }
}
