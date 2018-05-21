import suites.FiguresTests;
import suites.DiagramSubwindowTests;
import suites.UIEventTests;
import suites.WindowElementsTests;
import controller.*;
import canvaswindow.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import window.WindowTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        InteractrCanvasTest.class,
        CanvasControllerTest.class,
        FiguresTests.class,
        DiagramSubwindowTests.class,
        UIEventTests.class,
        WindowElementsTests.class,
        WindowTestSuite.class,
})
public class AllTests {
}
