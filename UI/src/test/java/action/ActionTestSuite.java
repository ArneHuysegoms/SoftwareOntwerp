package action;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddNewMessagesInViewsActionTest.class,
        AddNewPartyToReposActionTest.class,
        RemoveInReposActionTest.class,
        UpdateLabelActionTest.class,
        UpdateLabelContainersActionTest.class,
        UpdatePartyTypeActionTest.class
})
public class ActionTestSuite {
}
