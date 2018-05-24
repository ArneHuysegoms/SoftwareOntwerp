package window;

import command.closeWindow.CloseSubwindowCommand;
import org.junit.Before;
import org.junit.Test;
import window.diagram.DiagramSubwindow;
import window.elements.button.Button;
import window.elements.button.CloseWindowButton;
import window.frame.SubwindowFrameCorner;
import window.frame.SubwindowFrameRectangle;
import window.frame.TitleBarClick;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SubwindowTest {

    DiagramSubwindow subwindow;
    Point2D ULCorner;
    Point2D LLCorner;
    Point2D URCorner;
    Point2D LRCorner;
    Point2D URectangle;
    Point2D LRectangle;
    Point2D RRectangle;
    Point2D BRectangle;
    Point2D titlebar;
    Point2D button;

    @Before
    public void setUp() {
        subwindow = new DiagramSubwindow(new Point2D.Double(100, 100));
        subwindow.createFrame(new CloseWindowButton(new CloseSubwindowCommand(subwindow, null)));
        ULCorner = new Point2D.Double(100, 100);
        LLCorner = new Point2D.Double(100, 700);
        URCorner = new Point2D.Double(700, 100);
        LRCorner = new Point2D.Double(700, 700);
        URectangle = new Point2D.Double(400, 100);
        LRectangle = new Point2D.Double(100, 400);
        RRectangle = new Point2D.Double(700, 400);
        BRectangle = new Point2D.Double(400, 700);
        titlebar = new Point2D.Double(400, 115);
        button = new Point2D.Double(690, 115);

        subwindow.frameIsClicked(ULCorner);
        assertTrue(subwindow.getFrameElement() instanceof SubwindowFrameCorner);

        subwindow.frameIsClicked(LLCorner);
        assertTrue(subwindow.getFrameElement() instanceof SubwindowFrameCorner);

        subwindow.frameIsClicked(URCorner);
        assertTrue(subwindow.getFrameElement() instanceof SubwindowFrameCorner);

        subwindow.frameIsClicked(LRCorner);
        assertTrue(subwindow.getFrameElement() instanceof SubwindowFrameCorner);

        subwindow.frameIsClicked(URectangle);
        assertTrue(subwindow.getFrameElement() instanceof SubwindowFrameRectangle);

        subwindow.frameIsClicked(LRectangle);
        assertTrue(subwindow.getFrameElement() instanceof SubwindowFrameRectangle);

        subwindow.frameIsClicked(RRectangle);
        assertTrue(subwindow.getFrameElement() instanceof SubwindowFrameRectangle);

        subwindow.frameIsClicked(BRectangle);
        assertTrue(subwindow.getFrameElement() instanceof SubwindowFrameRectangle);

        subwindow.frameIsClicked(titlebar);
        assertTrue(subwindow.getFrameElement() instanceof TitleBarClick);

        subwindow.frameIsClicked(button);
        assertTrue(subwindow.getFrameElement() instanceof Button);
    }

    @Test
    public void test_dragging_by_corner(){
        subwindow.frameIsClicked(ULCorner);
        subwindow.handleMovement(new Point2D.Double(200, 200));
        assertEquals(500, subwindow.getHeight());
        assertEquals(500, subwindow.getWidth());

        setUp();
        subwindow.frameIsClicked(LLCorner);
        subwindow.handleMovement(new Point2D.Double(200, 600));
        assertEquals(500, subwindow.getHeight());
        assertEquals(500, subwindow.getWidth());

        setUp();
        subwindow.frameIsClicked(URCorner);
        subwindow.handleMovement(new Point2D.Double(600, 200));
        assertEquals(500, subwindow.getHeight());
        assertEquals(500, subwindow.getWidth());

        setUp();
        subwindow.frameIsClicked(LRCorner);
        subwindow.handleMovement(new Point2D.Double(600, 600));
        assertEquals(500, subwindow.getHeight());
        assertEquals(500, subwindow.getWidth());
    }

    @Test
    public void test_dragging_by_rectangle(){
        subwindow.frameIsClicked(URectangle);
        subwindow.handleMovement(new Point2D.Double(400, 200));
        assertEquals(500, subwindow.getHeight());
        assertEquals(600, subwindow.getWidth());

        setUp();
        subwindow.frameIsClicked(BRectangle);
        subwindow.handleMovement(new Point2D.Double(400, 600));
        assertEquals(500, subwindow.getHeight());
        assertEquals(600, subwindow.getWidth());

        setUp();
        subwindow.frameIsClicked(LRectangle);
        subwindow.handleMovement(new Point2D.Double(200, 400));
        assertEquals(600, subwindow.getHeight());
        assertEquals(500, subwindow.getWidth());

        setUp();
        subwindow.frameIsClicked(RRectangle);
        subwindow.handleMovement(new Point2D.Double(600, 400));
        assertEquals(600, subwindow.getHeight());
        assertEquals(500, subwindow.getWidth());
    }

    @Test
    public void test_moving_by_titleBar(){
        subwindow.frameIsClicked(titlebar);
        subwindow.handleMovement(new Point2D.Double(500, 135));
        assertEquals(new Point2D.Double(200, 120), subwindow.getPosition());
    }
}
