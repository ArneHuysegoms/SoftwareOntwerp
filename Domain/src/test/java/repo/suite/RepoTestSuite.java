package repo.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repo.label.LabelRepoTest;
import repo.message.CommunicationMessageRepoTest;
import repo.message.SequenceMessageRepoTest;
import repo.party.PartyRepoTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PartyRepoTest.class,
        LabelRepoTest.class,
        SequenceMessageRepoTest.class,
        CommunicationMessageRepoTest.class
})
public class RepoTestSuite {
}
