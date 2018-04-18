package suites;

import figures.basicShapes.*;
import figures.diagramFigures.*;
import figures.helperClasses.PairTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CircleTest.class,
        DashedLineTest.class,
        DashedRectangleTest.class,
        LineTest.class,
        RectangleTest.class,
        CloseButtonFigureTest.class,
        ArrowTest.class,
        BoxTest.class,
        DashedArrowTest.class,
        StickManTest.class,
        SubwindowFigureTest.class,

        PairTest.class,
})

public class FiguresTests {
}
