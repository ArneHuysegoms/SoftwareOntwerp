package subwindow;

import controller.CanvasController;
import mediator.InteractionMediator;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SubwindowTest {
    private Subwindow subwindow;
    private Button button;
    private InteractionMediator interactionMediator;
    private Point2D point;

    @Before
    public void setUp(){
        point = new Point2D.Double(200,200);
        button = new CloseButton();
        interactionMediator = new InteractionMediator();
        subwindow = new Subwindow(point, button, interactionMediator);
    }

    @Test
    public void test_subwindow_width(){
        assertEquals(subwindow.getWidth(), 600);
    }
    @Test
    public void test_subwindow_height(){
        assertEquals(subwindow.getHeight(), 600);
    }
    @Test
    public void test_subwindow_position(){
        assertTrue(subwindow.getPosition().equals(point));
    }
    @Test
    public void test_subwindow_button(){
        assertTrue(subwindow.getButton().equals(button));
    }
   /* @Test
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
*/
}
