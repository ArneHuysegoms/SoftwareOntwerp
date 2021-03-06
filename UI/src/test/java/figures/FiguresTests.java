package figures;

import figures.drawable.basicShapes.*;
import figures.drawable.diagramFigures.CloseButtonFigureTest;
import figures.drawable.diagramFigures.StickManTest;
import figures.drawable.subwindowFigures.DiagramSubwindowFigureTest;
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
        DiagramSubwindowFigureTest.class,
        PairTest.class,
})

public class FiguresTests {
}
