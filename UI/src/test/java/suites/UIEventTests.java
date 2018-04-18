package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import subwindow.ButtonTest;
import subwindow.CloseButtonTest;
import subwindow.SubwindowTest;
import uievents.KeyEventFactoryTest;
import uievents.KeyEventTest;
import uievents.MouseEventFactoryTest;
import uievents.MouseEventTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        KeyEventFactoryTest.class,
        KeyEventTest.class,
        MouseEventFactoryTest.class,
        MouseEventTest.class
})

public class UIEventTests {
}
