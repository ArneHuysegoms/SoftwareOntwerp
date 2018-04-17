package facade;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.label.MessageLabel;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import repo.diagram.CommunicationRepo;
import repo.diagram.DiagramRepo;
import repo.diagram.SequenceRepo;
import repo.message.CommunicationMessageRepo;
import repo.message.SequenceMessageRepo;

import java.awt.geom.Point2D;
import java.util.List;

import static org.junit.Assert.*;

public class FacadeTest {

    private Point2D standardPoint;

    @Before
    public void setUp(){
        standardPoint = new Point2D.Double(50,50);
    }

    @Test
    public void Test_default_constructor_works(){
        DomainFacade f = new DomainFacade();
        assertNotNull(f.getActiveRepo());
        assertNotNull(f.getCommunicationRepo());
        assertNotNull(f.getSequenceRepo());
        assertNotNull(f.getDiagram());
        assertTrue(f.getActiveRepo() instanceof SequenceRepo);
    }

    @Test
    public void Test_changeActiveDiagram(){
        DomainFacade  f = new DomainFacade();
        assertTrue(f.getActiveRepo() instanceof SequenceRepo);
        f.changeActiveDiagram();
        assertTrue(f.getActiveRepo() instanceof CommunicationRepo);
    }

    @Test
    public void Test_addNewParty() {
        DomainFacade  f = new DomainFacade();
        f.addNewParty(new Point2D.Double(50,120));
        assertTrue(f.getActiveRepo().getPartyRepo().getAllParties().size() == 1);
        assertTrue(f.getActiveRepo().getLabelRepo().getMap().size() == 1);
        assertTrue(f.getOtherRepo().getPartyRepo().getAllParties().size() == 1);
        assertTrue(f.getOtherRepo().getLabelRepo().getMap().size() == 1);
        assertNotNull(f.getOtherRepo().getPartyRepo().getPartyAtPosition(new Point2D.Double(50, 120)));
        assertNotNull(f.getActiveRepo().getPartyRepo().getPartyAtPosition(new Point2D.Double(50,50)));
    }

    @Test
    public void Test_addNewParty_works_with_change_of_diagramType(){
        DomainFacade  f = new DomainFacade();
        f.addNewParty(new Point2D.Double(50,120));
        assertTrue(f.getActiveRepo().getPartyRepo().getAllParties().size() == 1);
        assertTrue(f.getActiveRepo().getLabelRepo().getMap().size() == 1);
        assertTrue(f.getOtherRepo().getPartyRepo().getAllParties().size() == 1);
        assertTrue(f.getOtherRepo().getLabelRepo().getMap().size() == 1);
        f.changeActiveDiagram();
        assertNotNull(f.getActiveRepo().getPartyRepo().getPartyAtPosition(new Point2D.Double(50, 120)));
        assertNotNull(f.getOtherRepo().getPartyRepo().getPartyAtPosition(new Point2D.Double(50,50)));
    }

    @Test
    public void Test_changePartyType() throws DomainException{
        DomainFacade f = new DomainFacade();
        f.addNewParty(standardPoint);
        Party party = f.getActiveRepo().getPartyRepo().getPartyAtPosition(standardPoint);
        f.changePartyType(party);
        assertTrue(f.getDiagram().getParties().size() == 1);
        assertTrue(f.getDiagram().getParties().get(0) instanceof Actor);
        Party changedParty = f.getActiveRepo().getPartyRepo().getPartyAtPosition(standardPoint);
        assertTrue(changedParty instanceof Actor);
        Party changedPartyOther = f.getOtherRepo().getPartyRepo().getPartyAtPosition(standardPoint);
        assertTrue(changedPartyOther  instanceof Actor);
        assertEquals(changedParty, changedPartyOther);
    }

    @Test
    public void Test_findSelectedElement(){
        DomainFacade f = new DomainFacade();
        f.addNewParty(standardPoint);
        assertTrue(f.findSelectedElement(standardPoint) instanceof Object);
        assertTrue(f.findSelectedElement(new Point2D.Double(60,75)) instanceof Label);
        assertTrue(f.findSelectedElement(new Point2D.Double(75, 600)) instanceof DiagramRepo.MessageStart);
    }

    @Test
    public void Test_deleteElementByLabel_for_label_belonging_to_party(){
        DomainFacade f = new DomainFacade();
        Party newParty= f.addNewParty(standardPoint);
        f.deleteElementByLabel(newParty.getLabel());
        assertTrue(f.getDiagram().getParties().size() == 0);
        assertTrue(f.getActiveRepo().getPartyRepo().getAllParties().size() == 0);
        assertTrue(f.getActiveRepo().getLabelRepo().getMap().size() == 0);
        assertTrue(f.getOtherRepo().getPartyRepo().getAllParties().size() == 0);
        assertTrue(f.getOtherRepo().getLabelRepo().getMap().size() == 0);
    }

    @Test
    public void Test_changePartyPosition(){
        DomainFacade f = new DomainFacade();
        f.addNewParty(standardPoint);
        Point2D newLocation = new Point2D.Double(100,50);
        Party party = f.getActiveRepo().getPartyRepo().getPartyAtPosition(standardPoint);
        f.changePartyPosition(newLocation, party);
        assertEquals(party, f.getActiveRepo().getPartyRepo().getPartyAtPosition(new Point2D.Double(100, 50)));
        assertTrue(f.getActiveRepo().getPartyRepo().getAllParties().size() == 1);
        assertTrue(f.getActiveRepo().getLabelRepo().getMap().size() == 1);
        assertEquals(party.getLabel(), f.getActiveRepo().getLabelRepo().getLabelAtPosition(new Point2D.Double(110, 75)));
    }

    @Test
    public void Test_addNewMessage(){
        DomainFacade f = new DomainFacade();
        f.addNewParty(standardPoint);
        f.addNewParty(new Point2D.Double(150, 50));
        DiagramElement element = f.findSelectedElement(new Point2D.Double(75, 150));
        assertTrue(element instanceof DiagramRepo.MessageStart);
        DiagramRepo.MessageStart messageStart = (DiagramRepo.MessageStart) element;
        List<Message> addNewMessage = f.addNewMessage(new Point2D.Double(175, 150), messageStart);
        assertNotNull(f.getDiagram().getFirstMessage());
        assertEquals(addNewMessage.get(0).getLabel(), f.getDiagram().getFirstMessage().getLabel());
        assertTrue(f.getActiveRepo().getLabelRepo().getMap().size() == 4);
        assertTrue(f.getOtherRepo().getLabelRepo().getMap().size() == 3);
        SequenceMessageRepo sequenceMessageRepo = (SequenceMessageRepo) f.getActiveRepo().getMessageRepo();
        assertTrue(sequenceMessageRepo.getAllMessages().size() == 2);
        CommunicationMessageRepo communicationMessageRepo = (CommunicationMessageRepo) f.getOtherRepo().getMessageRepo();
        assertTrue(communicationMessageRepo.getMap().size() == 1);
    }

    @Test
    public void Test_deleteElementByLabel_works_for_message(){
        DomainFacade f = new DomainFacade();
        f.addNewParty(standardPoint);
        f.addNewParty(new Point2D.Double(150, 50));
        DiagramElement element = f.findSelectedElement(new Point2D.Double(75, 150));
        assertTrue(element instanceof DiagramRepo.MessageStart);
        DiagramRepo.MessageStart messageStart = (DiagramRepo.MessageStart) element;
        List<Message> addNewMessage = f.addNewMessage(new Point2D.Double(175, 150), messageStart);
        f.deleteElementByLabel(addNewMessage.get(0).getLabel());
        assertNull(f.getDiagram().getFirstMessage());
        assertTrue(f.getActiveRepo().getLabelRepo().getMap().size() == 2);
        assertTrue(f.getOtherRepo().getLabelRepo().getMap().size() == 2);
        SequenceMessageRepo sequenceMessageRepo = (SequenceMessageRepo) f.getActiveRepo().getMessageRepo();
        assertTrue(sequenceMessageRepo.getAllMessages().size() == 0);
        CommunicationMessageRepo communicationMessageRepo = (CommunicationMessageRepo) f.getOtherRepo().getMessageRepo();
        assertTrue(communicationMessageRepo.getMap().size() == 0);
    }

    @Test
    public void Test_addPartyToRepos() throws DomainException{
        DomainFacade f = new DomainFacade();
        Party party = new Object(new MessageLabel("a:A"));
        f.addPartyToRepo(party, standardPoint);
        assertTrue(f.getActiveRepo().getPartyRepo().getAllParties().size() == 1);
        assertTrue(f.getActiveRepo().getLabelRepo().getMap().size() == 1);
        assertTrue(f.getOtherRepo().getPartyRepo().getAllParties().size() == 1);
        assertTrue(f.getOtherRepo().getLabelRepo().getMap().size() == 1);
        assertTrue(f.getDiagram().getParties().size() == 0);
    }
}
