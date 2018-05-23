package action;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.label.MessageLabel;
import diagram.label.PartyLabel;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Actor;
import diagram.party.Party;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;
import window.diagram.DiagramSubwindow;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class UpdateLabelActionTest {
    private UpdateLabelAction updateLabelAction;
    private DiagramElement party1;
    private Label label1;
    private DiagramSubwindow diagramSubwindow;

    @Before
    public void setUp() throws DomainException{
        label1 = new PartyLabel(":Jos");
        party1 = new Actor(label1);
        diagramSubwindow = new DiagramSubwindow(new Point2D.Double(100,100));
        diagramSubwindow.setSelected(party1);
        updateLabelAction = new UpdateLabelAction(party1,label1);
    }

    @Test
    public void test_performAction(){
        updateLabelAction.performAction(diagramSubwindow);
        System.out.println(party1.toString());
    }

}