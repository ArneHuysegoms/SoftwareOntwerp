package canvascomponents.diagram;

import exceptions.DomainException;
import org.junit.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DiagramTest {

    Party actor1;
    Party object1;
    Party actor2;
    Party object2;
    List<Party> parties;
    Message firstMessage;
    Message secondMessage;

    @Before
    public void setUp() throws DomainException{
        actor1 = new Actor("","",1, new Point2D.Double(25, 50), new PartyLabel("", new Point2D.Double(10, 110)));
        object1 = new Object("","",2, new Point2D.Double(125, 50), new PartyLabel("", new Point2D.Double(135, 75)));
        actor2 = new Actor("","",3, new Point2D.Double(225, 50), new PartyLabel("", new Point2D.Double(210, 110)));
        object2 = new Object("","",4, new Point2D.Double(325, 50), new PartyLabel("", new Point2D.Double(335, 75)));

       parties = new ArrayList<>();
       parties.add(actor1);
       parties.add(object1);

       firstMessage = new InvocationMessage(null, new MessageLabel(), actor1, object1, 120);
       secondMessage = new ResultMessage(firstMessage, new MessageLabel(), object1, actor1, 140);
    }

    @Test
    public void Diagram_Default_Constructor(){
        Diagram sequenceDiagram = new SequenceDiagram();
        assertEquals(0, sequenceDiagram.getParties().size());
        assertEquals(null, sequenceDiagram.getFirstMessage());
        assertEquals(null, sequenceDiagram.getSelectedElement());
        assertEquals("", sequenceDiagram.getLabelContainer());
        assertFalse(sequenceDiagram.isMessageMode());
        assertFalse(sequenceDiagram.isLabelMode());
        assertFalse(sequenceDiagram.isValidLabel());

        Diagram commDiagram = new CommunicationsDiagram();
        assertEquals(0, commDiagram.getParties().size());
        assertEquals(null, commDiagram.getFirstMessage());
        assertEquals(null, commDiagram.getSelectedElement());
        assertEquals("", commDiagram.getLabelContainer());
        assertFalse(commDiagram.isMessageMode());
        assertFalse(commDiagram.isLabelMode());
        assertFalse(commDiagram.isValidLabel());
    }

    @Test
    public void Diagram_Test_Valid_Points(){
        Point2D validLocation = new Point2D.Double(25, 51);
        Point2D notValidLocation = new Point2D.Double(25, 40);

        Diagram sequenceDiagram = new SequenceDiagram();
        assertTrue(sequenceDiagram.isValidPartyLocation(validLocation));
        assertFalse(sequenceDiagram.isValidPartyLocation(notValidLocation));

        Diagram commDiagram = new CommunicationsDiagram();
        assertTrue(commDiagram.isValidPartyLocation(validLocation));
        assertTrue(commDiagram.isValidPartyLocation(notValidLocation));

        assertEquals(new Point2D.Double(25, 50), sequenceDiagram.getValidPartyLocation(validLocation));
        assertEquals(new Point2D.Double(25, 51), commDiagram.getValidPartyLocation(validLocation));
        assertEquals(new Point2D.Double(25, 40), commDiagram.getValidPartyLocation(notValidLocation));
    }

    @Test
    public void Diagram_test_isLifeline() throws DomainException{
        Party actor = new Actor("","",1, new Point2D.Double(25, 50), null);
        Diagram sequenceDiagram = new SequenceDiagram();
        assertTrue(sequenceDiagram.isLifeLine(new Point2D.Double(15, 180), actor));

        Diagram commDiagram = new CommunicationsDiagram();
        assertFalse(commDiagram.isLifeLine(new Point2D.Double(15, 180), actor));
    }

    @Test
    public void Test_add_and_remove_actor(){
        Diagram seq = new SequenceDiagram(parties, firstMessage);
        assertTrue(seq.getParties().contains(actor1));
        assertFalse(seq.getParties().contains(actor2));
        seq.addParty(actor2);
        assertTrue(seq.getParties().contains(actor2));
        seq.removeParty(actor2);
        assertFalse(seq.getParties().contains(actor2));
    }

    @Test
    public void Test_Change_Party_type(){
        Diagram seq = new SequenceDiagram(parties, firstMessage, actor1);
        seq.changePartyType(new Point2D.Double(25, 50));
        assertTrue(seq.getParties().get(1).getCoordinate().equals(new Point2D.Double(25, 50)));
    }

    @Test
    public void Test_findClickableElement() throws DomainException{
        Diagram seq = new SequenceDiagram(parties, firstMessage);
        Party actor3 = new Actor("","",1, new Point2D.Double(30, 55), new PartyLabel("", new Point2D.Double(15, 115)));
        seq.addParty(actor3);
        assertEquals(actor3, seq.findSelectedElement(new Point2D.Double(35, 60)));
    }


}
