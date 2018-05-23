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

import static org.junit.Assert.assertTrue;

public class AddNewPartyToViewsActionTest {
    private AddNewPartyToViewsAction addNewPartyToViewsAction;
    private Party party1;
    private Label label1;
    private DiagramSubwindow diagramSubwindow;

    @Before
    public void setUp() throws DomainException{
        label1 = new PartyLabel(":Jos");
        party1 = new Actor(label1);
        diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100,100));
        diagramSubwindow.setSelected(party1);
        Point2D p = new Point2D.Double(150,150);
        addNewPartyToViewsAction = new AddNewPartyToViewsAction(party1, p);
    }

    @Test
    public void test_performAction(){
        addNewPartyToViewsAction.performAction(diagramSubwindow);
    }

}