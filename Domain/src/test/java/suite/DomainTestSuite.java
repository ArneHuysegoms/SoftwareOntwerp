package suite;

import diagram.suite.DiagramTestSuite;
import facade.FacadeSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import view.suite.RepoTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DiagramTestSuite.class,
        RepoTestSuite.class,
        FacadeSuite.class
})
public class DomainTestSuite {
}
