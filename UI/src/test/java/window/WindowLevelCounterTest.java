package window;

import org.junit.Test;

import static org.junit.Assert.*;

public class WindowLevelCounterTest {

    @Test
    public void test_windowLevelCounter_works(){
        assertEquals(0, WindowLevelCounter.getNextLevel());
        assertEquals(1, WindowLevelCounter.getNextLevel());
        assertEquals(2, WindowLevelCounter.getNextLevel());

    }
}
