package subwindow;

import command.CloseSubwindowCommand;
import controller.CanvasController;
import org.junit.Before;
import org.junit.Test;
import window.elements.button.Button;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ButtonTest {
    private Button button1;
    private Button button2;
    private CanvasController canvasController;

    @Before
    public void setUp(){
        button1 = new Button();
        canvasController = new CanvasController();
        button2 = new Button(new CloseSubwindowCommand(canvasController, null));
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

}
