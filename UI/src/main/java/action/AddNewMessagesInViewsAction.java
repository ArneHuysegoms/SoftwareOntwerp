package action;

import diagram.message.Message;
import window.diagram.DiagramSubwindow;

import java.util.List;

public class AddNewMessagesInViewsAction extends Action {

    private List<Message> messageList;

    public AddNewMessagesInViewsAction(List<Message> messages){
        this.messageList = messages;
    }

    @Override
    public void performAction(DiagramSubwindow subwindow) {
        subwindow.getFacade().addMessagesToRepos(messageList);
    }
}
