import figures.basicShapes.*;
import figures.diagramFigures.*;
import figures.helperClasses.*;
import uievents.*;
import canvas.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CanvasMakeUpTest.class,
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


})

public class AllTests {
}
