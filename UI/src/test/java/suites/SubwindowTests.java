package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import subwindow.ButtonTest;
import subwindow.CloseButtonTest;
import subwindow.SubwindowTest;
import windowElements.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ButtonTest.class,
        CloseButtonTest.class,
        SubwindowTest.class
})

public class SubwindowTests {
}
