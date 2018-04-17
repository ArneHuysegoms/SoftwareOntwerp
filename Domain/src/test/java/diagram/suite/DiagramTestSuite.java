package diagram.suite;

import diagram.diagram.DiagramTest;
import diagram.label.MessageLabelTest;
import diagram.label.PartyLabelTest;
import diagram.message.InvocationMessageTest;
import diagram.message.ResultMessageTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MessageLabelTest.class,
        PartyLabelTest.class,
        InvocationMessageTest.class,
        ResultMessageTest.class,
        DiagramTest.class,
})
public class DiagramTestSuite {
}
