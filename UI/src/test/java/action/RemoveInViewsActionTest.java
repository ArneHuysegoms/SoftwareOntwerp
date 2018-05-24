package action;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.label.PartyLabel;
import diagram.party.Actor;
import diagram.party.Party;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import window.diagram.DiagramSubwindow;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class RemoveInViewsActionTest {
    private RemoveInViewsAction removeInViewsAction;
    private Party party1;
    private Label label1;
    private DiagramSubwindow diagramSubwindow;
    private Set<DiagramElement> diagramElementSet;

    @Before
    public void setUp() throws DomainException{
        diagramElementSet = new HashSet<>();
        label1 = new PartyLabel(":Jos");
        party1 = new Actor(label1);
        diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100,100));
        diagramSubwindow.setSelected(party1);
        diagramElementSet.add(party1);
        removeInViewsAction = new RemoveInViewsAction(diagramElementSet);
    }

    @Test
    public void test_performAction(){
        removeInViewsAction.performAction(diagramSubwindow);
        assertTrue(diagramSubwindow.getSelected()==null);
        //System.out.println(diagramSubwindow.getFacade().getActiveView().getMessageView().g);
    }

}