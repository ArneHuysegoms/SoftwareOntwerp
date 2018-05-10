package view.diagram;

import diagram.Diagram;
import diagram.label.Label;
import diagram.label.MessageLabel;
import diagram.label.PartyLabel;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.message.ResultMessage;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import view.label.LabelView;
import view.message.SequenceMessageView;
import view.party.PartyView;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DiagramViewTest {

    private Party actor1;
    private Label label1;

    private Party object2;
    private Label label2;

    private Party actor3;
    private Label label3;

    private Message inv1;
    private Message res1;
    private Label invLabell;

    private Message inv2;
    private Message res2;
    private Label invLabel2;

    private Message inv3;
    private Message res3;
    private Label invLabel3;

    private Point2D validPoint1;
    private Point2D validPoint2;
    private Point2D validPoint3;

    private List<Message> messages;
    private List<Party> parties;

    private PartyView partyView;
    private LabelView labelView;

    private Diagram diagram;

    DiagramView diagramView;

    @Before
    public void setUp() {
        try {
            validPoint1 = new Point2D.Double(50, 50);
            validPoint2 = new Point2D.Double(150, 50);
            validPoint3 = new Point2D.Double(250, 50);

            label1 = new PartyLabel("a:A");
            actor1 = new Actor(label1);

            label2 = new PartyLabel("b:B");
            object2 = new Object(label2);

            label3 = new PartyLabel("c:C");
            actor3 = new Actor(label3);

            invLabell = new MessageLabel("invocation1");
            invLabel2 = new MessageLabel("invocation2");
            invLabel3 = new MessageLabel("invocation3");

            res3 = new ResultMessage(null, new MessageLabel(""), actor1, actor3);
            inv3 = new InvocationMessage(res3, invLabel3, actor3, actor1);

            res2 = new ResultMessage(inv3, new MessageLabel(""), actor1, object2);
            res1 = new ResultMessage(res2, new MessageLabel(""), object2, actor3);

            inv2 = new InvocationMessage(res1, invLabel2, actor3, object2);
            inv1 = new InvocationMessage(inv2, invLabell, object2, actor1);

            messages = new ArrayList<>();
            messages.add(inv1);
            messages.add(inv2);
            messages.add(res1);
            messages.add(res2);
            messages.add(inv3);
            messages.add(res3);

            parties = new ArrayList<>();
            parties.add(actor1);
            parties.add(object2);
            parties.add(actor3);

            diagram = new Diagram(parties, inv1);

            partyView = new PartyView();
            partyView.addPartyWithLocation(actor1, validPoint1);
            partyView.addPartyWithLocation(object2, validPoint2);
            partyView.addPartyWithLocation(actor3, validPoint3);

            labelView = new LabelView();

            diagramView = new SequenceView();

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void Test_addNewPartyToRepos(){
        diagramView.addNewPartyToRepos(actor1, validPoint1);
        diagramView.addNewPartyToRepos(object2, validPoint2);
        diagramView.addNewPartyToRepos(actor3, validPoint3);
        assertTrue(diagramView.getPartyView().getAllParties().size() == 3);
        assertTrue(diagramView.getPartyView().getAllParties().containsAll(parties));
        assertTrue(diagramView.getLabelView().getMap().size() == 3);
        assertTrue(diagramView.getLabelView().getMap().keySet().contains(label1));
        assertTrue(diagramView.getLabelView().getMap().keySet().contains(label2));
        assertTrue(diagramView.getLabelView().getMap().keySet().contains(label3));
    }

    @Test
    public void Test_changePartyTypeInRepos() throws DomainException {
        Party object1 = new Object(actor1.getLabel());
        diagramView.addNewPartyToRepos(actor1, validPoint1);
        diagramView.addNewPartyToRepos(object2, validPoint2);
        diagramView.addNewPartyToRepos(actor3, validPoint3);
        diagramView.changePartyTypeInRepos(actor1, object1);
        assertFalse(diagramView.getPartyView().getAllParties().contains(actor1));
        assertTrue(diagramView.getPartyView().getAllParties().contains(object1));
    }

    @Test
    public void Test_findReceiver(){
        diagramView.addNewPartyToRepos(actor1, validPoint1);
        diagramView.addNewPartyToRepos(object2, validPoint2);
        diagramView.addNewPartyToRepos(actor3, validPoint3);
        assertEquals(actor3, diagramView.findReceiver(new Point2D.Double(240, 200)));
    }

    @Test
    public void Test_deleteMessageInRepos(){
        diagramView.addNewPartyToRepos(actor1, validPoint1);
        diagramView.addNewPartyToRepos(object2, validPoint2);
        diagramView.addNewPartyToRepos(actor3, validPoint3);
        diagramView.getMessageView().addMessages(messages, inv1, diagramView.getPartyView(), diagramView.getLabelView());
        diagram.deleteElementByLabel(invLabel2);
        diagramView.deleteMessageInRepos(inv2, inv1);
        SequenceMessageView messageRepo = (SequenceMessageView) diagramView.getMessageView();
        assertFalse(messageRepo.getAllMessages().contains(inv2));
    }

    @Test
    public void Test_addingAllWorks(){
        diagramView.addNewPartyToRepos(actor1, validPoint1);
        diagramView.addNewPartyToRepos(object2, validPoint2);
        diagramView.addNewPartyToRepos(actor3, validPoint3);
        diagramView.getMessageView().addMessages(messages, inv1, diagramView.getPartyView(), diagramView.getLabelView());
        SequenceMessageView messageRepo = (SequenceMessageView) diagramView.getMessageView();
        assertTrue(messageRepo.getMap().size() == 6);
        System.out.println(diagramView.getLabelView().getMap().size());
        assertTrue(diagramView.getLabelView().getMap().size() == 9);
        assertTrue(diagramView.getPartyView().getMap().size() == 3);
    }

    @Test
    public void Test_getSelectedDiagramElement(){
        diagramView.addNewPartyToRepos(actor1, validPoint1);
        diagramView.addNewPartyToRepos(object2, validPoint2);
        diagramView.addNewPartyToRepos(actor3, validPoint3);
        diagramView.getMessageView().addMessages(messages, inv1, diagramView.getPartyView(), diagramView.getLabelView());
        assertEquals(actor3, diagramView.getSelectedDiagramElement(validPoint3));
        assertEquals(label2, diagramView.getSelectedDiagramElement(new Point2D.Double(160,75)));
        assertTrue(diagramView.getSelectedDiagramElement(new Point2D.Double(60, 170)) instanceof DiagramView.MessageStart);
    }

    @Test
    public void Test_copy(){
        diagramView.addNewPartyToRepos(actor1, validPoint1);
        diagramView.addNewPartyToRepos(object2, validPoint2);
        diagramView.addNewPartyToRepos(actor3, validPoint3);
        diagramView.getMessageView().addMessages(messages, inv1, diagramView.getPartyView(), diagramView.getLabelView());
        DiagramView copy = DiagramView.copy(diagramView);
        assertTrue(copy instanceof SequenceView);
        assertTrue(copy.getPartyView().getAllParties().size() == diagramView.getPartyView().getAllParties().size());
        assertFalse(copy.getPartyView() == diagramView.getPartyView());
        assertTrue(copy.getLabelView().getMap().size() == diagramView.getLabelView().getMap().size());
        assertFalse(copy.getLabelView() == diagramView.getLabelView());
    }
}
