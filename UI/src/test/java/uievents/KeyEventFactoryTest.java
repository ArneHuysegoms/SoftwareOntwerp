package uievents;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KeyEventFactoryTest {
    private KeyEvent keyEvent1, keyEvent2, keyEvent3, keyEvent4, keyEvent5, keyEvent6, keyEvent7, keyEvent8;
    private KeyEventFactory keyEventFactory;

    @Before
    public void setUp(){
        keyEventFactory = new KeyEventFactory();
        keyEvent1 = new KeyEvent(KeyEventType.TAB);
        keyEvent2 = new KeyEvent(KeyEventType.DEL);
        keyEvent3 = new KeyEvent(KeyEventType.IRRELEVANT);
        keyEvent4 = new KeyEvent(KeyEventType.CHAR, 'a');
        keyEvent5 = new KeyEvent(KeyEventType.BACKSPACE);
        keyEvent6 = new KeyEvent(KeyEventType.CHAR);
        keyEvent7 = new KeyEvent(KeyEventType.CTRLN);
        keyEvent8 = new KeyEvent(KeyEventType.CTRLD);
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
    public void test_create_irrelevant(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 0, '*');
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
    @Test
    public void test_create_denied_char(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_TYPED, 0, '7');
        assertTrue(test.equals(keyEvent6));
    }
    @Test
    public void test_create_ctrlN(){
        keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 17, '0');
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 78, 'n');
        assertTrue(test.equals(keyEvent7));
    }
    @Test
    public void test_create_ctrlD(){
        keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 17, '0');
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 68, 'd');
        assertTrue(test.equals(keyEvent8));
    }
    @Test
    public void test_compare_with_other_keyEvent(){
        keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 17, '0');
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 78, 'n');
        assertFalse(test.equals(keyEvent8));
    }

}
