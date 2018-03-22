package canvaswindow;

import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

public class InteractrCanvasTest {

    private InteractrCanvas interactrCanvas = new InteractrCanvas("Test");
    private File file;
    private BufferedImage bufferedImage;
    private BufferedImage bufferedImage2;

    @Before
    public void setUp() throws InvocationTargetException, InterruptedException {
        java.awt.EventQueue.invokeAndWait(() -> {
            interactrCanvas.show();
        });
    }

    public void screenshot(String filename) throws AWTException, IOException{
        bufferedImage2 = interactrCanvas.captureImage();
        file = new File(filename);
        ImageIO.write(bufferedImage2, "png", file);
    }

    /*@Test
    public void test_interactrCanvas_init() throws AWTException, IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        this.screenshot("init.png");
    }*/
    @Test
    public void test_interactrCanvas_addParty() throws IOException, AWTException, InterruptedException {
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,123,70,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'S');
        TimeUnit.SECONDS.sleep(2);
        this.screenshot("test_interactrCanvas_addParty.png");
    }

    @Test
    public void test_interactrCanvas_makeMessage() throws IOException, AWTException, InterruptedException {
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,123,70,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'S');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,332,70,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,332,70,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'F');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,163,412,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,362,412,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        TimeUnit.SECONDS.sleep(2);
        this.screenshot("test_interactrCanvas_makeMessage.png");
    }

    @Test
    public void test_interactrCanvas_message_stack() throws IOException, AWTException, InterruptedException {
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,75,76,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'S');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,212,76,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,212,76,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'F');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,379,76,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,379,76,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,115,151,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,252,150,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,253,135,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,419,139,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        TimeUnit.SECONDS.sleep(2);
        this.screenshot("interactrCanvas_message_stack.png");
    }

    @Test
    public void test_communication_diagram() throws InterruptedException,IOException, AWTException {
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,75,76,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'S');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,212,76,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,212,76,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'F');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,379,76,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,379,76,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,115,151,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,252,150,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,253,135,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,419,139,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,300,200,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,300,200,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED,9,'\t');
        TimeUnit.SECONDS.sleep(2);
        this.screenshot("communication_diagram.png");
    }

    @Test
    public void test_interactrCanvas_changeParty() throws IOException, AWTException, InterruptedException {
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,68,67,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'S');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,85,95,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,85,95,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,85,95,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,85,95,2);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,85,95,2);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,85,95,2);
        TimeUnit.SECONDS.sleep(2);
        this.screenshot("test_interactrCanvas_addParty.png");
    }

}
