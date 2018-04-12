package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repo.label.LabelRepoTest;
import repo.party.PartyRepoTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PartyRepoTest.class,
        LabelRepoTest.class
})

public class DomainTestSuite {
}
