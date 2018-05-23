package action;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddNewMessagesInViewsActionTest.class,
        AddNewPartyToViewsActionTest.class,
        RemoveInViewsActionTest.class,
        UpdateLabelActionTest.class,
        UpdateLabelContainersActionTest.class,
        UpdatePartyTypeActionTest.class
})
public class ActionTestSuite {
}
