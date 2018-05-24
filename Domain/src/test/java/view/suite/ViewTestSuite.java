package view.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import view.diagram.CommunicationRepoTest;
import view.diagram.DiagramViewTest;
import view.diagram.SequenceRepoTest;
import view.label.LabelViewTest;
import view.message.CommunicationMessageViewTest;
import view.message.SequenceMessageViewTest;
import view.party.PartyViewTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PartyViewTest.class,
        LabelViewTest.class,
        SequenceMessageViewTest.class,
        CommunicationMessageViewTest.class,
        SequenceRepoTest.class,
        CommunicationRepoTest.class,
        DiagramViewTest.class
})
public class ViewTestSuite {
}
