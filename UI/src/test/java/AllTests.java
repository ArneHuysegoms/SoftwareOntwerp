import mediator.InteractionMediatorTest;
import suites.FiguresTests;
import suites.SubwindowTests;
import suites.UIEventTests;
import suites.WindowElementsTests;
import controller.*;
import canvaswindow.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

        InteractrCanvasTest.class,
        CanvasControllerTest.class,
        FiguresTests.class,
        InteractionMediatorTest.class,
        SubwindowTests.class,
        UIEventTests.class,
        WindowElementsTests.class,
})

public class AllTests {
}
