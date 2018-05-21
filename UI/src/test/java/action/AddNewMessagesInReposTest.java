package action;

import diagram.label.Label;
import diagram.label.MessageLabel;
import diagram.label.PartyLabel;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.message.ResultMessage;
import diagram.party.Actor;
import diagram.party.Party;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import window.diagram.DiagramSubwindow;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class AddNewMessagesInReposTest {
    private AddNewMessagesInRepos addNewMessagesInRepos;
    private Message message1,message2;
    private DiagramSubwindow diagramSubwindow;

    @Before
    public void setUp() throws DomainException{
        Label label1 = new PartyLabel(":Jos");
        Party party1 = new Actor(label1);
        Label label2 = new PartyLabel(":Jos2");
        Party party2 = new Actor(label2);
        Label label3 = new PartyLabel(":Jos3");
        Party party3 = new Actor(label3);
        message1 = new InvocationMessage(null,new MessageLabel("test"),party1,party2);
        message2 = new InvocationMessage(null,new MessageLabel("test2"),party2,party3);
        List<Message> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);
        addNewMessagesInRepos = new AddNewMessagesInRepos(messages);
        diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100,100));
    }

    @Test
    public void test_performAction(){
        addNewMessagesInRepos.performAction(diagramSubwindow);
        System.out.println(diagramSubwindow.getFacade().getDiagram().getFirstMessage());
        //System.out.println(diagramSubwindow.getFacade().getActiveView().getMessageView().g);
    }

}