import action.ActionTestSuite;
import canvaswindow.InteractrCanvasTest;
import command.CommandTestSuite;
import controller.ControllerTestSuite;
import figures.FiguresTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import uievents.UIEventTests;
import window.WindowTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        WindowTestSuite.class,
        UIEventTests.class,
        FiguresTests.class,
        ControllerTestSuite.class,
        CommandTestSuite.class,
        InteractrCanvasTest.class,
        ActionTestSuite.class,
})
public class AllTests {
}
