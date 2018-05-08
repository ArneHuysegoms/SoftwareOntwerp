package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
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
