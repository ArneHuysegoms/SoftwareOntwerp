package window.elements;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import window.elements.button.ButtonTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RadioButtonTest.class,
        ListBoxTest.class,
        TextBoxTest.class,
        ButtonTestSuite.class,
})
public class ElementsTestSuite {
}
