package figures.helperClasses;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;

public class PairTest {

    private Pair pair;
    private Pair pair2;

    @Before
    public void setUp(){
        pair = new Pair(4,5);
        pair2 = new Pair(new Point2D.Double(1,2),new Point2D.Double(3,4));
    }

    @Test
    public void test_getA_constructor1(){
        assertTrue(pair.getA().equals(4));
    }

    @Test
    public void test_getB_constructor1(){
        assertTrue(pair.getB().equals(5));
    }
    @Test
    public void test_getA_constructor2(){
        assertTrue(pair2.getA().equals(new Point2D.Double(1,2)));
    }

    @Test
    public void test_getB_constructor2(){
        assertTrue(pair2.getB().equals(new Point2D.Double(3,4)));
    }
}
