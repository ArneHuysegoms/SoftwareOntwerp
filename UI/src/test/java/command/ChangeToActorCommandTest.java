package command;

import command.changeType.ChangeToActorCommand;
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

public class ChangeToActorCommandTest {

    private ChangeToActorCommand changeToActorCommand;
    private DiagramSubwindow subwindow;
    private Party party;

    @Before
    public void setUp(){
        try {
            Label label = new PartyLabel(":Jos");
            party = new Actor(label);
            subwindow = new DiagramSubwindow(new Point2D.Double(150,150));
            subwindow.getFacade().addPartyToView(party, new Point2D.Double(200,200));
            changeToActorCommand = new ChangeToActorCommand(subwindow,party);
        }catch(DomainException e){
            System.out.println("Domainexception ChangeToActorCommandTest setup");
        }
    }

    @Test
    public void test_performAction(){
        changeToActorCommand.performAction();
        assertTrue(!subwindow.getFacade().getDiagram().getParties().contains(party));
    }
}
