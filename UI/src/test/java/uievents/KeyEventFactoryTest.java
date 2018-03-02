package uievents;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KeyEventFactoryTest {
    private KeyEvent k1;
    private KeyEvent k2;
    private KeyEvent k3;
    private KeyEvent k4;
    private KeyEventFactory keyEventFactory;

    @Before
    public void setUp(){
        keyEventFactory = new KeyEventFactory();
        k1 = new KeyEvent(KeyEventType.TAB);
        k2 = new KeyEvent(KeyEventType.DEL);
        k3 = new KeyEvent(KeyEventType.COLON);
        k4 = new KeyEvent(KeyEventType.CHAR, 'a');
    }
    @Test
    public void test_create_tab(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 9, '\t');
        assertEquals(test, k1);
    }
    @Test
    public void test_create_del(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 46, 'd');
        assertEquals(test, k2);
    }
    @Test
    public void test_create_colon(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_TYPED, 186, ':');
        assertEquals(test, k3);
    }
    @Test
    public void test_create_char(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_TYPED, -1, 'a');
        assertEquals(test, k4);
    }

}
