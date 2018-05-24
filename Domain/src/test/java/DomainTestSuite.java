import diagram.suite.DiagramTestSuite;
import facade.FacadeSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import view.suite.ViewTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DiagramTestSuite.class,
        ViewTestSuite.class,
        FacadeSuite.class
})
public class DomainTestSuite {
}
