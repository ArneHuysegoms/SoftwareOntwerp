package action;

import diagram.message.Message;
import window.diagram.DiagramSubwindow;

import java.util.List;

/**
 * action class that adds new messages to the views
 */
public class AddNewMessagesInViewsAction extends Action {

    private List<Message> messageList;

    /**
     * creates a new addNewMessagesInViewsAction for the given messages
     * @param messages the messages to add
     */
    public AddNewMessagesInViewsAction(List<Message> messages){
        this.messageList = messages;
    }

    /**
     * add the messages to the views of the given subwindow
     * @param subwindow the subwindow to perform an action on
     */
    @Override
    public void performAction(DiagramSubwindow subwindow) {
        subwindow.getFacade().addMessagesToRepos(messageList);
    }
}
