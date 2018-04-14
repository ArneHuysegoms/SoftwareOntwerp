package suite;

import diagram.suite.DiagramTestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repo.suite.RepoTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DiagramTestSuite.class,
        RepoTestSuite.class
})
public class DomainTestSuite {
}
