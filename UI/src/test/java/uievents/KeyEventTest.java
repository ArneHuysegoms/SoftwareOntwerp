package uievents;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KeyEventTest {

    private KeyEvent keyEvent;
    private KeyEvent keyEvent2;

    @Before
    public void setUp() {
        keyEvent = new KeyEvent(KeyEventType.TAB);
        keyEvent2 = new KeyEvent(KeyEventType.CHAR, 'a');
    }

    @Test
    public void test_keyChar(){
        assertEquals(keyEvent2.getKeyChar(), 'a');
        assertEquals(keyEvent.getKeyChar(), '\0');
    }

    @Test
    public void test_keyEventType(){
        assertEquals(keyEvent.getKeyEventType(), KeyEventType.TAB);
        assertEquals(keyEvent2.getKeyEventType(), KeyEventType.CHAR);
    }

    @Test
    public void test_different_chars(){
        KeyEvent keyEvent3 = new KeyEvent(KeyEventType.CHAR, 'b');
        assertFalse(keyEvent2.equals(keyEvent3));
    }

    @Test
    public void test_different_keyevents(){
        KeyEvent keyEvent3 = new KeyEvent(KeyEventType.COLON);
        assertFalse(keyEvent.equals(keyEvent3));
    }

    @Test
    public void test_different_constructors(){
        KeyEvent keyEvent3 = new KeyEvent(KeyEventType.CHAR);
        assertFalse(keyEvent2.equals(keyEvent3));
    }
}