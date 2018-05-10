package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import subwindow.ButtonTest;
import subwindow.DiagramSubwindowTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ButtonTest.class,
        DiagramSubwindowTest.class
})

public class DiagramSubwindowTests {
}
