package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repo.PartyRepoTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PartyRepoTest.class
})

public class DomainTestSuite {
}
