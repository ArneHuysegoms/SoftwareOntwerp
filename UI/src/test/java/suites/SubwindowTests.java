package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import subwindow.ButtonTest;
import subwindow.SubwindowTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ButtonTest.class,
        SubwindowTest.class
})

public class SubwindowTests {
}
