package mediator;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.label.MessageLabel;
import diagram.label.PartyLabel;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.message.ResultMessage;
import diagram.party.Actor;
import diagram.party.Object;
import facade.DomainFacade;
import org.junit.Before;
import org.junit.Test;
import window.diagram.DiagramSubwindow;
import view.message.SequenceMessageView;
import window.windowElements.Button;

import java.awt.geom.Point2D;
import java.util.*;

import static org.junit.Assert.*;

public class InteractionMediatorTest {
    private Actor actor1, actor2;
    private Object object1, object2;
    private Label labelActor1, labelActor2, labelObject1, labelObject2;

    private DiagramSubwindow diagramSubwindow1, diagramSubwindow2, diagramSubwindow3, diagramSubwindow4;

    private Point2D subWindowLocation1, subWindowLocation2, subWindowLocation3, subWindowLocation4;

    private Point2D partyLocation1, partyLocation2;

    private Button button1, button2, button3, button4;

    private InteractionMediator interactionMediator, interactionMediator2;

    private Label invLabell, invLabel3;

    private Message inv2, res2;

    private Message inv3, res3;

    private List<Message> messages;

    @Before
    public void setUp() {
        try {
            subWindowLocation1 = new Point2D.Double(50, 50);
            subWindowLocation2 = new Point2D.Double(100, 50);
            subWindowLocation3 = new Point2D.Double(150, 50);
            subWindowLocation4 = new Point2D.Double(200, 50);

            partyLocation1 = new Point2D.Double(50,50);
            partyLocation2 = new Point2D.Double(50,50);

            button1 = new Button();
            button2 = new Button();
            button3 = new Button();
            button4 = new Button();

            labelActor1 = new PartyLabel("a:A");
            actor1 = new Actor(labelActor1);

            labelObject1 = new PartyLabel("o:O");
            object1 = new Object(labelObject1);

            labelActor2 = new PartyLabel("a:A");
            actor2 = new Actor(labelActor2);

            labelObject2 = new PartyLabel("o:O");
            object2 = new Object(labelObject2);

            invLabell = new MessageLabel("invocation1");
            invLabel3 = new MessageLabel("invocation3");

            res3 = new ResultMessage(null, new MessageLabel(""), object1, actor1);
            inv3 = new InvocationMessage(res3, invLabel3, actor1, object1);

            res2 = new ResultMessage(null, new MessageLabel(""), object2, actor2);
            inv2 = new InvocationMessage(res2, invLabell, actor2, object2);

            messages = new ArrayList<>();
            messages.add(inv2);
            messages.add(res2);
            messages.add(inv3);
            messages.add(res3);

            interactionMediator = new InteractionMediator();
            interactionMediator2 = new InteractionMediator();

            diagramSubwindow1 = new DiagramSubwindow(subWindowLocation1, interactionMediator);
            diagramSubwindow2 = new DiagramSubwindow(subWindowLocation2, interactionMediator);
            diagramSubwindow3 = new DiagramSubwindow(subWindowLocation3, interactionMediator);
            diagramSubwindow4 = new DiagramSubwindow(subWindowLocation4, interactionMediator2);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void Test_mediator_addNewPartyToOtherSubwindowRepos(){
        interactionMediator.addNewPartyToOtherSubwindowRepos(actor1, partyLocation1, diagramSubwindow1);
        interactionMediator.addNewPartyToOtherSubwindowRepos(object1, partyLocation2, diagramSubwindow1);

        //check if list of parties is not empty
        assertNotNull(diagramSubwindow2.getFacade().getDiagram().getParties());
        assertNotNull(diagramSubwindow3.getFacade().getDiagram().getParties());

        //check if the partyrepo of subwindows 2 and 3 contains the added element
        assertTrue(diagramSubwindow2.getFacade().getActiveRepo().getPartyView().getAllParties().contains(actor1));
        assertTrue(diagramSubwindow3.getFacade().getActiveRepo().getPartyView().getAllParties().contains(actor1));
        assertTrue(diagramSubwindow2.getFacade().getActiveRepo().getPartyView().getAllParties().contains(object1));
        assertTrue(diagramSubwindow3.getFacade().getActiveRepo().getPartyView().getAllParties().contains(object1));
    }

    @Test
    public void Test_mediator_removeInReposInOtherSubwindows(){
        interactionMediator.addNewPartyToOtherSubwindowRepos(actor2, partyLocation1, diagramSubwindow1);
        interactionMediator.addNewPartyToOtherSubwindowRepos(object2, partyLocation2, diagramSubwindow1);

        Set<DiagramElement> elementsToDelete = new HashSet<DiagramElement>(Arrays.asList(actor2,object2));

        interactionMediator.removeInReposInOtherSubwindows(elementsToDelete, diagramSubwindow2);
        interactionMediator.removeInReposInOtherSubwindows(elementsToDelete, diagramSubwindow3);

        //check if the partyrepo of subwindows 2 and 3 do not contain the added element
        assertFalse(diagramSubwindow2.getFacade().getActiveRepo().getPartyView().getAllParties().contains(actor2));
        assertFalse(diagramSubwindow3.getFacade().getActiveRepo().getPartyView().getAllParties().contains(actor2));
        assertFalse(diagramSubwindow2.getFacade().getActiveRepo().getPartyView().getAllParties().contains(object2));
        assertFalse(diagramSubwindow3.getFacade().getActiveRepo().getPartyView().getAllParties().contains(object2));
    }

    @Test
    public void Test_mediator_addSubwindow(){
        interactionMediator.addSubwindow(diagramSubwindow4);

        assertTrue(interactionMediator.getDiagramSubwindows().contains(diagramSubwindow4));
    }

    @Test
    public void Test_mediator_removeSubwindow(){
        interactionMediator.removeSubwindow(diagramSubwindow1);

        assertFalse(interactionMediator.getDiagramSubwindows().contains(diagramSubwindow4));
    }

    @Test
    public void Test_mediator_addNewMessageToOtherSubwindowRepos(){
        interactionMediator.addNewMessagesToOtherSubwindowRepos(messages, diagramSubwindow1);
        List<DiagramSubwindow> subList = interactionMediator.getDiagramSubwindows();
        for (DiagramSubwindow sb : subList){
            if (sb != diagramSubwindow1){
                DomainFacade f = sb.getFacade();
                SequenceMessageView sequenceMessageRepo = (SequenceMessageView) f.getActiveRepo().getMessageView();
                for (Message m : messages){
                    assertTrue(sequenceMessageRepo.getAllMessages().contains(m));
                }
            }
        }


    }




    public InteractionMediatorTest() {


    }

    @Test
    public void Test(){

    }
}
