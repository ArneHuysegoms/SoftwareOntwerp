import figures.basicShapes.*;
import figures.diagramFigures.*;
import figures.helperClasses.*;
import mediator.InteractionMediatorTest;
import subwindow.ButtonTest;
import subwindow.CloseButtonTest;
import subwindow.SubwindowTest;
import suites.WindowElementsTests;
import uievents.*;
import controller.*;
import canvaswindow.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import windowElements.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CanvasControllerTest.class,
        CircleTest.class,
        DashedLineTest.class,
        LineTest.class,
        RectangleTest.class,
        DashedRectangleTest.class,
        StickManTest.class,
        KeyEventFactoryTest.class,
        KeyEventTest.class,
        MouseEventFactoryTest.class,
        MouseEventTest.class,
        PairTest.class,
        BoxTest.class,
        ArrowTest.class,
        DashedArrowTest.class,
        InteractrCanvasTest.class,
        ButtonTest.class,
        CloseButtonTest.class,
        SubwindowTest.class,
        WindowElementsTests.class,
        InteractionMediatorTest.class
})

public class AllTests {
}
