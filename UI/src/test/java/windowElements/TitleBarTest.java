package windowElements;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class TitleBarTest {

    private TitleBar titleBar;

    @Before
    public void setUp(){
        Point2D point = new Point2D.Double(20,20);
        titleBar = new TitleBar(point, 40);
    }
    @Test
    public void test_constructor(){
        assertTrue(titleBar.getPosition().getX() == 20);
        assertTrue(titleBar.getPosition().getY() == 20);
    }

    @Test
    public void test_width(){
        assertTrue(titleBar.getWidth() == 40);
    }

    @Test
    public void test_isClicked(){
        assertTrue(titleBar.isClicked(new Point2D.Double(30,20)));
    }
}
