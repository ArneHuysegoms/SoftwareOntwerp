package action;

import command.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddNewMessagesInReposTest.class,
        AddNewPartyToReposActionTest.class,
        RemoveInReposActionTest.class,
        UpdateLabelActionTest.class,
        UpdateLabelContainersActionTest.class,
        UpdatePartyTypeActionTest.class
})
public class ActionTestSuite {
}
