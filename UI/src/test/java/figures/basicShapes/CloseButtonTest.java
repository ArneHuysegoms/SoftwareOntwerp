package figures.basicShapes;

import figures.diagramFigures.SubwindowFigure;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertTrue;

public class CloseButtonTest {
    private CloseButton closeButton;
    private int x, y;

    @Before
    public void setUp() {
        x = 50;
        y = 40;
        closeButton = new CloseButton(x,y);
    }

    @Test
    public void test_getX() {
        assertTrue(closeButton.getX() == x);
    }

    @Test
    public void test_getY() {
        assertTrue(closeButton.getY() == y);
    }
}
