package repo.diagram;

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
import repo.label.LabelRepo;
import repo.message.SequenceMessageRepo;
import repo.party.PartyRepo;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DiagramRepoTest {

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

    private PartyRepo partyRepo;
    private LabelRepo labelRepo;

    private Diagram diagram;

    DiagramRepo diagramRepo;

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

            partyRepo = new PartyRepo();
            partyRepo.addPartyWithLocation(actor1, validPoint1);
            partyRepo.addPartyWithLocation(object2, validPoint2);
            partyRepo.addPartyWithLocation(actor3, validPoint3);

            labelRepo = new LabelRepo();

            diagramRepo = new SequenceRepo();

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void Test_addNewPartyToRepos(){
        diagramRepo.addNewPartyToRepos(actor1, validPoint1);
        diagramRepo.addNewPartyToRepos(object2, validPoint2);
        diagramRepo.addNewPartyToRepos(actor3, validPoint3);
        assertTrue(diagramRepo.getPartyRepo().getAllParties().size() == 3);
        assertTrue(diagramRepo.getPartyRepo().getAllParties().containsAll(parties));
        assertTrue(diagramRepo.getLabelRepo().getMap().size() == 3);
        assertTrue(diagramRepo.getLabelRepo().getMap().keySet().contains(label1));
        assertTrue(diagramRepo.getLabelRepo().getMap().keySet().contains(label2));
        assertTrue(diagramRepo.getLabelRepo().getMap().keySet().contains(label3));
    }

    @Test
    public void Test_changePartyTypeInRepos() throws DomainException {
        Party object1 = new Object(actor1.getLabel());
        diagramRepo.addNewPartyToRepos(actor1, validPoint1);
        diagramRepo.addNewPartyToRepos(object2, validPoint2);
        diagramRepo.addNewPartyToRepos(actor3, validPoint3);
        diagramRepo.changePartyTypeInRepos(actor1, object1);
        assertFalse(diagramRepo.getPartyRepo().getAllParties().contains(actor1));
        assertTrue(diagramRepo.getPartyRepo().getAllParties().contains(object1));
    }

    @Test
    public void Test_findReceiver(){
        diagramRepo.addNewPartyToRepos(actor1, validPoint1);
        diagramRepo.addNewPartyToRepos(object2, validPoint2);
        diagramRepo.addNewPartyToRepos(actor3, validPoint3);
        assertEquals(actor3, diagramRepo.findReceiver(new Point2D.Double(240, 200)));
    }

    @Test
    public void Test_deleteMessageInRepos(){
        diagramRepo.addNewPartyToRepos(actor1, validPoint1);
        diagramRepo.addNewPartyToRepos(object2, validPoint2);
        diagramRepo.addNewPartyToRepos(actor3, validPoint3);
        diagramRepo.getMessageRepo().addMessages(messages, inv1, diagramRepo.getPartyRepo(), diagramRepo.getLabelRepo());
        diagram.deleteElementByLabel(invLabel2);
        diagramRepo.deleteMessageInRepos(inv2, inv1);
        SequenceMessageRepo messageRepo = (SequenceMessageRepo) diagramRepo.getMessageRepo();
        assertFalse(messageRepo.getAllMessages().contains(inv2));
    }

    @Test
    public void Test_addingAllWorks(){
        diagramRepo.addNewPartyToRepos(actor1, validPoint1);
        diagramRepo.addNewPartyToRepos(object2, validPoint2);
        diagramRepo.addNewPartyToRepos(actor3, validPoint3);
        diagramRepo.getMessageRepo().addMessages(messages, inv1, diagramRepo.getPartyRepo(), diagramRepo.getLabelRepo());
        SequenceMessageRepo messageRepo = (SequenceMessageRepo) diagramRepo.getMessageRepo();
        assertTrue(messageRepo.getMap().size() == 6);
        System.out.println(diagramRepo.getLabelRepo().getMap().size());
        assertTrue(diagramRepo.getLabelRepo().getMap().size() == 9);
        assertTrue(diagramRepo.getPartyRepo().getMap().size() == 3);
    }

    @Test
    public void Test_getSelectedDiagramElement() throws DomainException{
        diagramRepo.addNewPartyToRepos(actor1, validPoint1);
        diagramRepo.addNewPartyToRepos(object2, validPoint2);
        diagramRepo.addNewPartyToRepos(actor3, validPoint3);
        diagramRepo.getMessageRepo().addMessages(messages, inv1, diagramRepo.getPartyRepo(), diagramRepo.getLabelRepo());
        assertEquals(actor3, diagramRepo.getSelectedDiagramElement(validPoint3));
        assertEquals(label2, diagramRepo.getSelectedDiagramElement(new Point2D.Double(160,75)));
        assertTrue(diagramRepo.getSelectedDiagramElement(new Point2D.Double(60, 170)) instanceof DiagramRepo.MessageStart);
    }
}
