package window.elements.button;

import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class ButtonTest {

    @Test
    public void test_constructor() {
        Button button = new CloseWindowButton(null);
        assertTrue(button.getHeight() == Button.HEIGHT);
        assertTrue(button.getWidth() == Button.WIDTH);
        assertTrue(button.getCommand() == null);
    }

    @Test
    public void test_isClicked(){
        Button button = new CloseWindowButton(null);
        button.setPosition( new Point2D.Double(50, 50));
        assertTrue(button.isClicked(new Point2D.Double(60, 60)));
        assertFalse(button.isClicked(new Point2D.Double(49, 49)));
        assertFalse(button.isClicked(new Point2D.Double(90, 90)));
        assertFalse(button.isClicked(new Point2D.Double(60, 90)));
        assertFalse(button.isClicked(new Point2D.Double(90, 60)));
    }
}
