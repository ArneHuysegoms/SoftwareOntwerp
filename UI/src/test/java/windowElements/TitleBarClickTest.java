package windowElements;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TitleBarClickTest {

    private TitleBarClick titleBarClick;
    private TitleBar titleBar;

    @Before
    public void setUp(){
        Point2D point = new Point2D.Double(20,20);
        titleBar = new TitleBar(point, 40);
        Point2D point2 = new Point2D.Double(50,50);
        titleBarClick = new TitleBarClick(titleBar, point2);
    }
    @Test
    public void test_constructor_initialposition(){
        assertTrue(titleBarClick.getInitialClickPosition().getX() == 50);
        assertTrue(titleBarClick.getInitialClickPosition().getY() == 50);
    }
    @Test
    public void test_constructor_titlebar(){
        assertTrue(titleBarClick.getTitleBar().equals(titleBar));
    }
    @Test
    public void test_isClicked(){
        assertFalse(titleBarClick.isClicked(new Point2D.Double(100,100)));
    }
}
