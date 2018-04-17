package subwindow;

import controller.CanvasController;
import figures.basicShapes.Circle;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ButtonTest {
    private Button button1;
    private Button button2;
    private CanvasController canvasController;

    @Before
    public void setUp(){
        button1 = new CloseButton();
        canvasController = new CanvasController();
        button2 = new CloseButton(canvasController);
    }

    @Test
    public void test_width_button1(){
        assertEquals(button1.getWidth(), 30);
    }
    @Test
    public void test_width_button2(){
        assertEquals(button2.getWidth(), 30);
    }
    @Test
    public void test_height_button1(){
        assertEquals(button1.getHeight(), 30);
    }
    @Test
    public void test_height_button2(){
        assertEquals(button2.getHeight(), 30);
    }
    @Test
    public void test_controller_button1(){
        assertTrue(button1.getController().equals(this.canvasController));
    }
    @Test
    public void test_controller_button2(){
        assertTrue(button2.getController().equals(this.canvasController));
    }

}
