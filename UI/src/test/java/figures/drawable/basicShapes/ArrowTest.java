package figures.drawable.basicShapes;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;

public class ArrowTest {
    private Arrow arrow1;
    private Arrow arrow2;
    private Arrow arrow3;
    private Arrow arrow4;
    private Arrow arrowFail;

    @Before
    public void setUp(){
        arrow1 = new Arrow(new Point2D.Double(1,2), new Point2D.Double(3,2));
        arrow2 = new Arrow(new Point2D.Double(3,2), new Point2D.Double(1,2));
        arrow3 = new Arrow(new Point2D.Double(1,1), new Point2D.Double(1,3));
        arrow4 = new Arrow(new Point2D.Double(1,3), new Point2D.Double(1,1));
        arrowFail = new Arrow(new Point2D.Double(1,1), new Point2D.Double(1,1));
    }

    @Test
    public void test_getLineStart_constructor(){
        assertTrue(arrow1.getLineStart().equals(new Point2D.Double(1,2)));
    }
    @Test
    public void test_getLineEnd_constructor(){
        assertTrue(arrow1.getLineEnd().equals(new Point2D.Double(3,2)));
    }
    @Test
    public void test_getArrowTop_constructor1(){
        assertTrue(arrow1.getArrowTop().getStart().equals(new Point2D.Double(3,2)));
        assertTrue(arrow1.getArrowTop().getEnd().equals(new Point2D.Double(-7,-8)));
    }
    @Test
    public void test_getArrowBottom_constructor1(){
        assertTrue(arrow1.getArrowBottom().getStart().equals(new Point2D.Double(3,2)));
        assertTrue(arrow1.getArrowBottom().getEnd().equals(new Point2D.Double(-7,12)));
    }
    @Test
    public void test_getArrowTop_constructor2(){
        assertTrue(arrow2.getArrowTop().getStart().equals(new Point2D.Double(1,2)));
        assertTrue(arrow2.getArrowTop().getEnd().equals(new Point2D.Double(11,-8)));
    }
    @Test
    public void test_getArrowBottom_constructor2(){
        assertTrue(arrow2.getArrowBottom().getStart().equals(new Point2D.Double(1,2)));
        assertTrue(arrow2.getArrowBottom().getEnd().equals(new Point2D.Double(11,12)));

    }
    @Test
    public void test_getArrowTop_constructor3(){
        assertTrue(arrow3.getArrowTop().getStart().equals(new Point2D.Double(1,3)));
        assertTrue(arrow3.getArrowTop().getEnd().equals(new Point2D.Double(11,-7)));
    }
    @Test
    public void test_getArrowBottom_constructor3(){
        assertTrue(arrow3.getArrowBottom().getStart().equals(new Point2D.Double(1,3)));
        assertTrue(arrow3.getArrowBottom().getEnd().equals(new Point2D.Double(-9,-7)));
    }
    @Test
    public void test_getArrowTop_constructor4(){
        assertTrue(arrow4.getArrowTop().getStart().equals(new Point2D.Double(1,1)));
        assertTrue(arrow4.getArrowTop().getEnd().equals(new Point2D.Double(-9,11)));
    }
    @Test
    public void test_getArrowBottom_constructor4(){
        assertTrue(arrow4.getArrowBottom().getStart().equals(new Point2D.Double(1,1)));
        assertTrue(arrow4.getArrowBottom().getEnd().equals(new Point2D.Double(11,11)));
    }
    @Test (expected = NullPointerException.class)
    public void test_getArrowTop_constructorFail(){
        assertTrue(arrowFail.getArrowTop().getStart().equals(new Point2D.Double(1,1)));
        assertTrue(arrowFail.getArrowTop().getEnd().equals(new Point2D.Double(1,1)));
    }
    @Test (expected = NullPointerException.class)
    public void test_getArrowBottom_constructorFail(){
        assertTrue(arrowFail.getArrowBottom().getStart().equals(new Point2D.Double(1,1)));
        assertTrue(arrowFail.getArrowBottom().getEnd().equals(new Point2D.Double(1,1)));
    }

}
