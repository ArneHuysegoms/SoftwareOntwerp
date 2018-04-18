package suites;

import canvaswindow.InteractrCanvasTest;
import controller.CanvasControllerTest;
import figures.basicShapes.*;
import figures.diagramFigures.ArrowTest;
import figures.diagramFigures.BoxTest;
import figures.diagramFigures.DashedArrowTest;
import figures.diagramFigures.StickManTest;
import figures.helperClasses.PairTest;
import mediator.InteractionMediatorTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import subwindow.ButtonTest;
import subwindow.CloseButtonTest;
import subwindow.SubwindowTest;
import uievents.KeyEventFactoryTest;
import uievents.KeyEventTest;
import uievents.MouseEventFactoryTest;
import uievents.MouseEventTest;
import windowElements.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SubwindowFrameCornerTest.class,
        SubwindowFrameRectangleTest.class,
        SubwindowFrameTest.class,
        TitleBarClickTest.class,
        TitleBarTest.class
})

public class WindowElementsTests {
}
