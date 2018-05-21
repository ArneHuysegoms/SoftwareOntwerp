package action;

import diagram.label.Label;
import diagram.label.PartyLabel;
import diagram.party.Actor;
import diagram.party.Party;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import window.diagram.DiagramSubwindow;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

public class UpdatePartyTypeActionTest {
    private UpdatePartyTypeAction updatePartyTypeAction;
    private Party party1;
    private Label label1;
    private Party party2;
    private Label label2;
    private DiagramSubwindow diagramSubwindow;

    @Before
    public void setUp() throws DomainException{
        label1 = new PartyLabel(":Jos");
        party1 = new Actor(label1);
        label2 = new PartyLabel(":Jos2");
        party2 = new Actor(label2);
        diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100,100));
        diagramSubwindow.getFacade().addPartyToRepo(party1,new Point2D.Double(150,150));
        diagramSubwindow.getFacade().addPartyToRepo(party2,new Point2D.Double(200,150));
        diagramSubwindow.setSelected(party1);
        updatePartyTypeAction = new UpdatePartyTypeAction(party1, party2);
    }

    @Test
    public void test_performAction(){
        assertEquals(diagramSubwindow.getSelected(),party1);
        updatePartyTypeAction.performAction(diagramSubwindow);
        assertEquals(diagramSubwindow.getSelected(),party2);

        //assertTrue(diagramSubwindow.getSelected()==null);
        //System.out.println(diagramSubwindow.getFacade().getActiveView().getMessageView().g);
    }

}