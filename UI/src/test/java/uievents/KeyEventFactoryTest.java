package uievents;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KeyEventFactoryTest {
    private KeyEvent keyEvent1;
    private KeyEvent keyEvent2;
    private KeyEvent keyEvent3;
    private KeyEvent keyEvent4;
    private KeyEvent keyEvent5;
    private KeyEventFactory keyEventFactory;

    @Before
    public void setUp(){
        keyEventFactory = new KeyEventFactory();
        keyEvent1 = new KeyEvent(KeyEventType.TAB);
        keyEvent2 = new KeyEvent(KeyEventType.DEL);
        keyEvent3 = new KeyEvent(KeyEventType.COLON);
        keyEvent4 = new KeyEvent(KeyEventType.CHAR, 'a');
        keyEvent5 = new KeyEvent(KeyEventType.BACKSPACE);
    }
    @Test
    public void test_create_tab(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 9, '\t');
        assertTrue(test.equals(keyEvent1));
    }
    @Test
    public void test_create_del(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 46, 'd');
        assertTrue(test.equals(keyEvent2));
    }
    @Test
    public void test_create_colon(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_TYPED, 186, ':');
        assertTrue(test.equals(keyEvent3));
    }
    @Test
    public void test_create_char(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_TYPED, -1, 'a');
        assertTrue(test.equals(keyEvent4));
    }
    @Test
    public void test_create_backspace(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 8, '\b');
        assertTrue(test.equals(keyEvent5));
    }

}
