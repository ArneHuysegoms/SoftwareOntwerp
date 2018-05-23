package window.elements.button;

import exception.UIException;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class FakeButtonTest {

    @Test
    public void test_constructor() throws UIException {
        FakeButton fakeButton = new FakeButton(new Point2D.Double(50, 50));
        assertTrue(fakeButton.getHeight() == 30);
        assertTrue(fakeButton.getWidth() == 30);
        assertEquals(new Point2D.Double(50, 50), fakeButton.getCoordinate());
    }

    @Test
    public void test_isClicked() throws UIException{
        FakeButton fakeButton = new FakeButton(new Point2D.Double(50, 50));
        assertTrue(fakeButton.isClicked(new Point2D.Double(60, 60)));
        assertFalse(fakeButton.isClicked(new Point2D.Double(49, 49)));
        assertFalse(fakeButton.isClicked(new Point2D.Double(90, 90)));
        assertFalse(fakeButton.isClicked(new Point2D.Double(60, 90)));
        assertFalse(fakeButton.isClicked(new Point2D.Double(90, 60)));
    }

    @Test
    public void test_TextualFakeButton() throws UIException{
        TextualFakeButton textualFakeButton = new TextualFakeButton(new Point2D.Double(50, 50), "Text");
        assertEquals("Text", textualFakeButton.getDescription());
    }
}
