package figures.drawable.diagramFigures;

import figures.drawable.subwindowFigures.DiagramSubwindowFigure;
import org.junit.Before;
import org.junit.Test;
import java.awt.geom.Point2D;
import static org.junit.Assert.assertTrue;

public class DiagramSubwindowFigureTest {
    private DiagramSubwindowFigure subwindowFigure;
    private int x1, x2, y1, y2, width, height;

    @Before
    public void setUp() {
        width = 300;
        height = 400;
        x1 = 10;
        y1 = 20;
        x2 = x1 + width;
        y2 = y1 + height;
        subwindowFigure = new DiagramSubwindowFigure(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2));
    }

    @Test
    public void test_getX1_x1_topleft() {
        assertTrue(subwindowFigure.getX1() == x1);
    }

    @Test
    public void test_getX2_x2_bottomright() {
        assertTrue(subwindowFigure.getX2() == x2);
    }

    @Test
    public void test_getY1_y1_bottomright() {
        assertTrue(subwindowFigure.getY1() == y1);
    }

    @Test
    public void test_getY2_y2_bottomright() {
        assertTrue(subwindowFigure.getY2() == y2);
    }

    @Test
    public void test_getWidth_returns_actual_with() {
        assertTrue(subwindowFigure.getWidth() == width);
    }

    @Test
    public void test_getHeight_returns_actual_with() {
        assertTrue(subwindowFigure.getHeight() == height);
    }
}
