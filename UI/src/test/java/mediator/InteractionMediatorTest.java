package mediator;

import diagram.DiagramElement;
import diagram.label.Label;
import diagram.label.PartyLabel;
import diagram.party.Actor;
import diagram.party.Party;
import org.junit.Before;
import org.junit.Test;
import subwindow.Button;
import subwindow.CloseButton;
import subwindow.Subwindow;

import java.awt.geom.Point2D;

import static org.junit.Assert.fail;

public class InteractionMediatorTest {
    private Party actor1;
    private Object object1;
    private Label labelActor;
    private Label labelObject;
    private DiagramElement diagramElement1;
    private DiagramElement diagramElement2;
    private Subwindow subwindow1;
    private Subwindow subwindow2;
    private Subwindow subwindow3;
    private Point2D subWindowLocation1;
    private Point2D subWindowLocation2;
    private Point2D subWindowLocation3;
    private Point2D partyLocation1;
    private Button button1;
    private Button button2;
    private Button button3;
    private InteractionMediator interactionMediator;

    @Before
    public void setUp() {
        try {
            subWindowLocation1 = new Point2D.Double(50, 50);
            subWindowLocation2 = new Point2D.Double(100, 50);
            subWindowLocation3 = new Point2D.Double(150, 50);
            partyLocation1 = new Point2D.Double(50,50);

            button1 = new CloseButton();
            button2 = new CloseButton();
            button3 = new CloseButton();

            interactionMediator = new InteractionMediator();

            subwindow2 = new Subwindow(subWindowLocation2, button2, interactionMediator);
            subwindow1 = new Subwindow(subWindowLocation1, button1, interactionMediator);
            subwindow3 = new Subwindow(subWindowLocation3, button3, interactionMediator);
            

        } catch (Exception e) {
            fail();
        }
    }


    public InteractionMediatorTest() {


    }
}
