package canvascomponents.diagram;

import diagram.label.PartyLabel;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class PartyLabelTest {

    PartyLabel partyLabel;

    @Before
    public void setUp(){
        partyLabel = new PartyLabel();
    }
    @Test
    public void getLabel() throws DomainException {
        Point2D lblPoint = new Point2D.Double(25, 350);
        PartyLabel l = new PartyLabel("test:Label", lblPoint);
        assertEquals("test:Label", l.getLabel());
    }

    @Test
    public void setLabel() {
        PartyLabel l = new PartyLabel();
        String txtLabel = "test:Label";
        l.setLabel(txtLabel);
        assertEquals(txtLabel, l.getLabel());
    }

    @Test
    public void isValidPartyLabel() {
        assertTrue(partyLabel.isValidLabel("test:Actor"));
        assertTrue(partyLabel.isValidLabel(":Actor"));
        assertFalse(partyLabel.isValidLabel("Actor"));
        assertFalse(partyLabel.isValidLabel("actor"));
    }

    @Test
    public void getDistance() throws DomainException {
        Point2D lblPoint = new Point2D.Double(25, 350);
        PartyLabel l = new PartyLabel("test:Dist", lblPoint);
        Point2D point = new Point2D.Double(30, 350);
        assertEquals(5,l.getDistance(point), 0.001);
    }
}