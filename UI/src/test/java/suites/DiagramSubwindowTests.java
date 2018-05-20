package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import window.elements.button.ButtonTest;
import window.diagram.DiagramSubwindowTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ButtonTest.class,
        DiagramSubwindowTest.class
})

public class DiagramSubwindowTests {
}
