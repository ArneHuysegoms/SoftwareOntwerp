package suite;

import diagram.diagram.DiagramTest;
import diagram.label.MessageLabelTest;
import diagram.label.PartyLabelTest;
import diagram.message.InvocationMessageTest;
import diagram.message.ResultMessageTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repo.label.LabelRepoTest;
import repo.party.PartyRepoTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PartyRepoTest.class,
        LabelRepoTest.class,
        MessageLabelTest.class,
        PartyLabelTest.class,
        InvocationMessageTest.class,
        ResultMessageTest.class,
        DiagramTest.class
})

public class DomainTestSuite {
}
