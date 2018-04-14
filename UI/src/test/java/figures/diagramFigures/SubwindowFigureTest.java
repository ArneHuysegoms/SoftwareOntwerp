package figures.diagramFigures;

import org.junit.Before;
import org.junit.Test;
import subwindow.Subwindow;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class SubwindowFigureTest {
    private SubwindowFigure subwindow;

    @Before
    public void setUp(){
        subwindow = new Subwindow(10,10);
    }

    @Test
    public void test_getTopOfHead_constructor1(){
        assertEquals(subwindow.getTopOfHead(),new Point.Double(10,10));
    }
    @Test
    public void test_getConnectionHead_constructor1(){
        assertEquals(subwindow.getConnectionHead(), new Point2D.Double(10, 20));
    }
     @Test
    public void test_getConnectionLegs_constructor1(){
        assertEquals(subwindow.getConnectionLegs(), new Point2D.Double(10, 40));
    }
    @Test
    public void test_getConnectionArms_constructor1(){
        assertEquals(subwindow.getConnectionArms(), new Point2D.Double(10, 25));
    }
    @Test
    public void test_getArms_constructor(){
        assertEquals(subwindow.getArms().getStart(), new Point2D.Double(4,25));
        assertEquals(subwindow.getArms().getEnd(), new Point2D.Double(16,25));
    }
    @Test
    public void test_getLegL_constructor(){
        assertEquals(subwindowsubwindow.getLegL().getStart(), new Point2D.Double(10,40));
        assertEquals(stickMan.getLegL().getEnd(), new Point2D.Double(6,55));
    }
    @Test
    public void test_getLegR_constructor(){
        assertEquals(stickMan.getLegR().getStart(), new Point2D.Double(10,40));
        assertEquals(stickMan.getLegR().getEnd(), new Point2D.Double(14,55));
    }
}
