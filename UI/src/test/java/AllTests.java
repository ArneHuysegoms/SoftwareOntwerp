import figures.basicShapes.*;
import figures.diagramFigures.*;
import figures.helperClasses.*;
import mediator.InteractionMediatorTest;
import subwindow.ButtonTest;
import subwindow.CloseButtonTest;
import subwindow.SubwindowTest;
import suites.FiguresTests;
import suites.SubwindowTests;
import suites.UIEventTests;
import suites.WindowElementsTests;
import uievents.*;
import controller.*;
import canvaswindow.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import windowElements.*;

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
