package figures.diagramFigures;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class StickManTest {
    private StickMan stickMan;

    private StickMan stickMan2;

    @Before
    public void setUp(){
        stickMan = new StickMan(10,10);
        stickMan2 = new StickMan(new Point2D.Double(10,10));
    }

    @Test
    public void test_getTopOfHead_constructor1(){
        assertEquals(stickMan.getTopOfHead(),new Point.Double(10,10));
    }
    @Test
    public void test_getTopOfHead_constructor2(){
        assertEquals(stickMan2.getTopOfHead(),new Point.Double(10,10));
    }
    @Test
    public void test_getConnectionHead_constructor1(){
        assertEquals(stickMan.getConnectionHead(), new Point2D.Double(10, 20));
    }
    @Test
    public void test_getConnectionHead_constructor2(){
        assertEquals(stickMan2.getConnectionHead(), new Point2D.Double(10, 20));
    }
    @Test
    public void test_getConnectionLegs_constructor1(){
        assertEquals(stickMan.getConnectionLegs(), new Point2D.Double(10, 40));
    }
    @Test
    public void test_getConnectionLegs_constructor2(){
        assertEquals(stickMan2.getConnectionLegs(), new Point2D.Double(10, 40));
    }
    @Test
    public void test_getConnectionArms_constructor1(){
        assertEquals(stickMan.getConnectionArms(), new Point2D.Double(10, 25));
    }
    @Test
    public void test_getConnectionArms_constructor2(){
        assertEquals(stickMan2.getConnectionArms(), new Point2D.Double(10, 25));
    }
}
