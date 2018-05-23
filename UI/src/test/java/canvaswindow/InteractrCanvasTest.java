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
import java.security.Key;
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
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '0');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 78, 'n');
    }

    public void screenshot(String filename) throws AWTException, IOException{
        bufferedImage2 = interactrCanvas.captureImage();
        file = new File(filename);
        ImageIO.write(bufferedImage2, "png", file);
    }

    @Test
    public void test_switchDiagramType_toCommunication() throws InterruptedException,IOException, AWTException {
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,200,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'M');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'k');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,400,170,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,400,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'C');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'o');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'m');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'m');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'u');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'n');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'i');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'c');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'i');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'o');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'n');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,600,170,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,600,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'D');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'i');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'g');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'r');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'m');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,241,241,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,440,238,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 8,'\b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,',');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,')');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,442,233,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,643,235,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 8,'\b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,',');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,')');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,628,283,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED,9,'\t');
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_switchDiagramType_toCommunication.png");
    }

    @Test
    public void test_switchDiagramType_backToSequence() throws InterruptedException,IOException, AWTException {
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,200,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'M');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'k');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,400,170,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,400,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'S');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'q');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'u');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'n');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'c');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,600,170,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,600,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'D');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'i');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'g');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'r');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'m');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,241,241,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,440,238,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 8,'\b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,',');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,')');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,442,233,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,643,235,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 8,'\b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,',');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,')');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,628,283,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED,9,'\t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED,9,'\t');
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_switchDiagramType_backToSequence.png");
    }

    @Test
    public void test_interactrCanvas_addParty() throws IOException, AWTException, InterruptedException {
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,200,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'A');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'d');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'d');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'p');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'r');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'y');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,628,283,1);
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_interactrCanvas_addParty.png");
    }

    @Test
    public void test_interactrCanvas_changePartyType_toActor() throws IOException, AWTException, InterruptedException {
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,200,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'o');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'c');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'o');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'r');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,235,160,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,235,160,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,235,160,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,235,160,2);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,235,160,2);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,235,160,2);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,628,283,1);
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_interactrCanvas_changePartyType_toActor.png");
    }

    @Test
    public void test_interactrCanvas_changePartyType_backToParty() throws IOException, AWTException, InterruptedException {
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,200,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'o');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'p');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'r');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'y');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,235,160,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,235,160,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,235,160,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,235,160,2);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,235,160,2);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,235,160,2);


        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,205,160,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,205,160,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,205,160,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,205,160,2);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,205,160,2);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,205,160,2);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,628,283,1);
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_interactrCanvas_changePartyType_backToParty.png");
    }

    @Test
    public void test_interactrCanvas_editLabel_with_multiple_subwindows() throws IOException, AWTException, InterruptedException{
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,216,172,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'E');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'d');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'i');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,355,111,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,991,235,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '0');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'd');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,255,175,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,255,175,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,255,175,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,255,175,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,255,175,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,255,175,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 8,'\b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 8,'\b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 8,'\b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 8,'\b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 8,'\b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'I');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'N');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'V');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'A');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'L');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'I');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'D');
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_interactrCanvas_editLabel_with_multiple_subwindows.png");
    }

    @Test
    public void test_interactrCanvas_addMessage() throws IOException, AWTException, InterruptedException {
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,200,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'M');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'k');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,400,170,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,400,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'M');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'g');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,241,241,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,440,238,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 8,'\b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,',');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,')');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,628,283,1);
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_interactrCanvas_addMessage.png");
    }

    @Test
    public void test_interactrCanvas_addMultipleMessages() throws IOException, AWTException, InterruptedException {
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,200,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'M');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'k');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,400,170,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,400,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'M');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'g');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,600,170,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,600,170,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'S');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'c');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'k');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,241,241,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,440,238,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 8,'\b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,',');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,')');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,442,233,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,643,235,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 8,'\b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,',');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'T');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'s');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,')');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,628,283,1);
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_interactrCanvas_addMultipleMessages.png");
    }

    @Test
    public void test_interactrCanvas_moveSubwindow() throws IOException, AWTException, InterruptedException{
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,197,175,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'M');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'o');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'v');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'e');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,351,108,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,872,161,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,628,283,1);
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_interactrCanvas_moveSubwindow.png");
    }

    @Test
    public void test_interactrCanvas_copySubwindow() throws IOException, AWTException, InterruptedException{
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,197,175,2);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,':');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'C');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'o');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'p');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'y');
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,351,108,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,872,161,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,628,283,1);
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 17, '0');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED, 68, 'd');
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_interactrCanvas_copySubwindow.png");
    }

    @Test
    public void test_interactrCanvas_closeSubwindow() throws IOException, AWTException, InterruptedException{
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,686,113,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,686,113,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,686,113,1);
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_interactrCanvas_closeSubwindow.png");
    }

    @Test
    public void test_interactrCanvas_resizeSubwindow() throws IOException, AWTException, InterruptedException{
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,100,100,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,150,150,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,150,700,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,210,637,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,700,636,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,615,555,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,613,149,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,549,227,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,381,228,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,383,264,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,209,420,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,248,421,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,390,555,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,393,518,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,549,406,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,506,410,1);
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_interactrCanvas_resizeSubwindow.png");
    }

    @Test
    public void test_interactrCanvas_diagramDialogBox() throws IOException, AWTException, InterruptedException {
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED,17,'0');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED,10,'0');
        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_interactrCanvas_diagramDialogBox.png");
    }

    @Test
    public void test_interactrCanvas_invocationMessageDialogBox() throws IOException, AWTException, InterruptedException {

    }

    @Test
    public void test_interactrCanvas_resultMessageDialogBox() throws IOException, AWTException, InterruptedException {

    }

    @Test
    public void test_interactrCanvas_partyDialogBox() throws IOException, AWTException, InterruptedException {

        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,594,213,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,594,213,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,594,213,1);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_PRESSED,594,213,2);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_RELEASED,594,213,2);
        interactrCanvas.handleMouseEvent(MouseEvent.MOUSE_CLICKED,594,213,2);

        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED,17,'0');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED,10,'0');

        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED,9,'\t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED,9,'\t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'p');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'r');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'y');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_PRESSED,9,'\t');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'D');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'i');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'a');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'l');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'o');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'g');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'b');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'o');
        interactrCanvas.handleKeyEvent(KeyEvent.KEY_TYPED, 0,'x');

        TimeUnit.SECONDS.sleep(1);
        this.screenshot("test_interactrCanvas_partyDialogBox.png");
    }

}
