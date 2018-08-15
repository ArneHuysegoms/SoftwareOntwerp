package uievents;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KeyEventFactoryTest {
    private KeyEvent keyEvent1, keyEvent2, keyEvent3, keyEvent4, keyEvent5, keyEvent6, keyEvent7, keyEvent8, keyEvent9, keyEvent10, keyEvent11,
                keyEvent12, keyEvent13, keyEvent14, keyEvent15, keyEvent16;
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
        keyEvent9 = new KeyEvent(KeyEventType.CTRLE);
        keyEvent10 = new KeyEvent(KeyEventType.CTRLENTER);
        keyEvent11 = new KeyEvent(KeyEventType.ENTER);
        keyEvent12 = new KeyEvent(KeyEventType.ARROWKEYLEFT);
        keyEvent13 = new KeyEvent(KeyEventType.ARROWKEYUP);
        keyEvent14 = new KeyEvent(KeyEventType.ARROWKEYRIGHT);
        keyEvent15 = new KeyEvent(KeyEventType.ARROWKEYDOWN);
        keyEvent16 = new KeyEvent(KeyEventType.SPACE);

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
    public void test_create_ctrlE(){
        keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 17, '0');
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 69, 'd');
        assertTrue(test.equals(keyEvent9));
    }
    @Test
    public void test_create_ctrlEnter(){
        keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 17, '0');
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 10, 'd');
        assertTrue(test.equals(keyEvent10));
    }
    @Test
    public void test_create_Enter(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 10, 'd');
        assertTrue(test.equals(keyEvent11));
    }
    @Test
    public void test_create_ArrowKeyLEFT(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 37, 'd');
        assertTrue(test.equals(keyEvent12));
    }
    @Test
    public void test_create_ArrowKeyUP(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 38, 'd');
        assertTrue(test.equals(keyEvent13));
    }
    @Test
    public void test_create_ArrowKeyRIGHT(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 39, 'd');
        assertTrue(test.equals(keyEvent14));
    }
    @Test
    public void test_create_ArrowKeyDOWN(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 40, 'd');
        assertTrue(test.equals(keyEvent15));
    }
    @Test
    public void test_create_Space(){
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 32, 'd');
        assertTrue(test.equals(keyEvent16));
    }
    @Test
    public void test_compare_with_other_keyEvent(){
        keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 17, '0');
        KeyEvent test = keyEventFactory.createKeyEvent(java.awt.event.KeyEvent.KEY_PRESSED, 78, 'n');
        assertFalse(test.equals(keyEvent8));
    }

}
