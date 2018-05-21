package window;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import window.diagram.DiagramSubwindowTest;
import window.dialogbox.DialogBoxTestSuite;
import window.elements.ElementsTestSuite;
import window.frame.FrameTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ElementsTestSuite.class,
        FrameTestSuite.class,
        DialogBoxTestSuite.class,
        DiagramSubwindowTest.class,
        SubwindowTest.class
})
public class WindowTestSuite {
}
