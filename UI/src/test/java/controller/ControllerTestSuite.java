package controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import window.WindowLevelCounterTest;
import window.dialogbox.DialogBoxTestSuite;
import window.elements.ElementsTestSuite;
import window.frame.FrameTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CanvasControllerTest.class,
        InteractionControllerTest.class
})
public class ControllerTestSuite {
}
