package window;

import org.junit.Test;

import static org.junit.Assert.*;

public class WindowLevelCounterTest {

    @Test
    public void test_windowLevelCounter_works(){
        assertEquals(14, WindowLevelCounter.getNextLevel());
        assertEquals(15, WindowLevelCounter.getNextLevel());
        assertEquals(16, WindowLevelCounter.getNextLevel());

    }
}
