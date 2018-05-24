package uievents;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        KeyEventFactoryTest.class,
        KeyEventTest.class,
        MouseEventFactoryTest.class,
        MouseEventTest.class
})

public class UIEventTests {
}
