package diagram.diagram;

import diagram.Diagram;
import diagram.DiagramElement;
import diagram.label.Label;
import diagram.label.MessageLabel;
import diagram.label.PartyLabel;
import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.message.ResultMessage;
import diagram.party.Actor;
import diagram.party.Object;
import diagram.party.Party;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class DiagramTest {

    Party actor1;
    Label label1;

    Party object2;
    Label label2;

    Party actor3;
    Label label3;

    Message inv1;
    Message res1;
    Label invLabell;

    Message inv2;
    Message res2;
    Label invLabel2;

    Message inv3;
    Message res3;
    Label invLabel3;

    Diagram diagram;

    List<Party> parties;

    @Before
    public void setUp(){
        try {
            label1 = new PartyLabel("a:A");
            actor1 = new Actor(label1);

            label2 = new PartyLabel("b:B");
            object2 = new Object(label2);

            label3 = new PartyLabel("c:C");
            actor3 = new Actor(label3);

            invLabell = new MessageLabel("invocation1");
            invLabel2 = new MessageLabel("invocation2");
            invLabel3 = new MessageLabel("invocation3");

            res3 = new ResultMessage(null, new MessageLabel(""),actor1,actor3);
            inv3 = new InvocationMessage(res3, invLabel3,actor3,actor1);

            res2 = new ResultMessage(inv3, new MessageLabel(""), actor1,object2);
            res1 = new ResultMessage(res2, new MessageLabel(""), object2, actor3);

            inv2 = new InvocationMessage(res1, invLabel2, actor3, object2);
            inv1 = new InvocationMessage(inv2, invLabell, object2, actor1);

            parties = new ArrayList<>();
            parties.add(actor1);
            parties.add(object2);
            parties.add(actor3);

            diagram = new Diagram(parties, inv1);

        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void Test_default_constructor_works(){
        Diagram d = new Diagram();
        assertEquals(null, d.getFirstMessage());
        assertTrue(d.getParties().size() == 0);
    }

    @Test
    public void Test_advanced_constructor_works(){
        assertEquals(inv1, diagram.getFirstMessage());
        assertTrue(diagram.getParties().size() == 3);
    }

    @Test
    public void Test_advanced_constructor_with_nullForParties_gives_newLisst(){
        Diagram d = new Diagram(null, inv1);
        assertEquals(inv1, d.getFirstMessage());
        assertTrue(d.getParties().size() == 0);
    }

    @Test
    public void Test_addNewParty_works(){
        Party newParty = diagram.addNewParty();
        assertTrue(newParty instanceof Object);
        assertTrue(newParty.getLabel().getLabel().equals(""));
        assertTrue(diagram.getParties().size() == 4);
    }

    @Test
    public void Test_changePartyType_works_for_actor(){
        Party newParty = diagram.changePartyType(actor1);
        assertTrue(newParty.getLabel().equals(actor1.getLabel()));
        assertTrue(newParty instanceof Object);
        assertEquals(inv1.getSender(), newParty);
        assertTrue(diagram.getParties().contains(newParty));
        assertFalse(diagram.getParties().contains(actor1));

    }

    @Test
    public void Test_changePartyType_works_for_object(){
        Party newParty = diagram.changePartyType(object2);
        assertTrue(newParty instanceof Actor);
        assertTrue(newParty.getLabel().equals(object2.getLabel()));
        assertEquals(inv2.getSender(), newParty);
        assertTrue(diagram.getParties().contains(newParty));
        assertFalse(diagram.getParties().contains(object2));
    }

    @Test
    public void Test_setMessageNumbers_works(){
        assertEquals(inv1.toString(), "1 " + inv1.getLabel().getLabel());
        assertEquals(inv2.toString(), "1.1 " + inv2.getLabel().getLabel());
        assertEquals(inv3.toString(), "2 " + inv3.getLabel().getLabel());
    }

    @Test
    public void Test_deleteElementByLabelWorks_with_actor1(){
        Set<DiagramElement> deleted = diagram.deleteElementByLabel(label1);
        assertTrue(deleted.contains(actor1));
        assertFalse(deleted.contains(actor3));
        assertFalse(deleted.contains(object2));
        assertTrue(deleted.contains(inv1));
        assertTrue(deleted.contains(res2));
        assertTrue(deleted.contains(inv3));
        assertTrue(deleted.contains(res3));
        assertTrue(deleted.contains(inv2));
        assertTrue(deleted.contains(res2));
        assertFalse(diagram.getParties().contains(actor1));
        assertTrue(diagram.getParties().contains(object2));
        assertTrue(diagram.getParties().contains(actor3));
        assertEquals(diagram.getFirstMessage(), null);
    }

    @Test
    public void Test_deleteElementByLabel_works_with_actor3(){
        Set<DiagramElement> deleted = diagram.deleteElementByLabel(label3);
        assertTrue(deleted.contains(actor3));
        assertFalse(deleted.contains(actor1));
        assertFalse(deleted.contains(object2));
        assertFalse(deleted.contains(inv1));
        assertFalse(deleted.contains(res2));
        assertTrue(deleted.contains(inv3));
        assertTrue(deleted.contains(res3));
        assertTrue(deleted.contains(inv2));
        assertTrue(deleted.contains(res1));
        assertFalse(diagram.getParties().contains(actor3));
        assertTrue(diagram.getParties().contains(object2));
        assertTrue(diagram.getParties().contains(actor1));
        assertEquals(diagram.getFirstMessage(), inv1);
        assertEquals(diagram.getFirstMessage().getNextMessage(), res2);
        assertEquals(diagram.getFirstMessage().getNextMessage().getNextMessage(), null);
    }

    @Test
    public void Test_deleteElementByLabelWorks_with_object2(){
        Set<DiagramElement> deleted = diagram.deleteElementByLabel(label2);
        assertTrue(deleted.contains(object2));
        assertFalse(deleted.contains(actor3));
        assertFalse(deleted.contains(actor1));
        assertTrue(deleted.contains(inv1));
        assertTrue(deleted.contains(res2));
        assertFalse(deleted.contains(inv3));
        assertFalse(deleted.contains(res3));
        assertTrue(deleted.contains(inv2));
        assertTrue(deleted.contains(res1));
        assertTrue(diagram.getParties().contains(actor3));
        assertFalse(diagram.getParties().contains(object2));
        assertTrue(diagram.getParties().contains(actor1));
        assertEquals(diagram.getFirstMessage(), inv3);
        assertEquals(diagram.getFirstMessage().getNextMessage(), res3);
    }

    @Test
    public void Test_deleteByLabel_works_with_message_invocation1(){
        Set<DiagramElement> deleted = diagram.deleteElementByLabel(invLabell);
        assertTrue(diagram.getParties().contains(actor1));
        assertTrue(diagram.getParties().contains(object2));
        assertTrue(diagram.getParties().contains(actor3));
        assertTrue(deleted.contains(inv1));
        assertTrue(deleted.contains(inv2));
        assertTrue(deleted.contains(res1));
        assertTrue(deleted.contains(res2));
        assertFalse(deleted.contains(inv3));
        assertFalse(deleted.contains(res3));
        assertEquals(inv3, diagram.getFirstMessage());
        assertEquals(res3, diagram.getFirstMessage().getNextMessage());
    }

    @Test
    public void Test_deleteByLabel_works_with_message_invocation2(){
        Set<DiagramElement> deleted = diagram.deleteElementByLabel(invLabel2);
        assertTrue(diagram.getParties().contains(actor1));
        assertTrue(diagram.getParties().contains(object2));
        assertTrue(diagram.getParties().contains(actor3));
        assertFalse(deleted.contains(inv1));
        assertTrue(deleted.contains(inv2));
        assertTrue(deleted.contains(res1));
        assertFalse(deleted.contains(res2));
        assertFalse(deleted.contains(inv3));
        assertFalse(deleted.contains(res3));
        assertEquals(inv1, diagram.getFirstMessage());
        assertEquals(res2, diagram.getFirstMessage().getNextMessage());
        assertEquals(inv3, diagram.getFirstMessage().getNextMessage().getNextMessage());
        assertEquals(res3, diagram.getFirstMessage().getNextMessage().getNextMessage().getNextMessage());
        assertEquals(null, diagram.getFirstMessage().getNextMessage().getNextMessage().getNextMessage().getNextMessage());
    }

    @Test
    public void Test_deleteByLabel_works_with_message_invocation3(){
        Set<DiagramElement> deleted = diagram.deleteElementByLabel(invLabel3);
        assertTrue(diagram.getParties().contains(actor1));
        assertTrue(diagram.getParties().contains(object2));
        assertTrue(diagram.getParties().contains(actor3));
        assertFalse(deleted.contains(inv1));
        assertFalse(deleted.contains(inv2));
        assertFalse(deleted.contains(res1));
        assertFalse(deleted.contains(res2));
        assertTrue(deleted.contains(inv3));
        assertTrue(deleted.contains(res3));
        assertEquals(inv1, diagram.getFirstMessage());
        assertEquals(inv2, diagram.getFirstMessage().getNextMessage());
        assertEquals(res1, diagram.getFirstMessage().getNextMessage().getNextMessage());
        assertEquals(res2, diagram.getFirstMessage().getNextMessage().getNextMessage().getNextMessage());
        assertEquals(null, diagram.getFirstMessage().getNextMessage().getNextMessage().getNextMessage().getNextMessage());
    }

    @Test
    public void  Test_adding_message_works_at_beginning(){
        List<Message> newMessages = diagram.addNewMessage(actor1, object2, null);
        assertEquals(diagram.getFirstMessage(), newMessages.get(0));
        assertEquals(diagram.getFirstMessage().getNextMessage(), newMessages.get(1));
        assertEquals(inv1, diagram.getFirstMessage().getNextMessage().getNextMessage());
    }

    @Test
    public void Test_adding_messages_works_at_end(){
        List<Message> newMessages = diagram.addNewMessage(actor1, object2, res3);
        assertEquals(res3.getNextMessage(), newMessages.get(0));
        assertEquals(res3.getNextMessage().getNextMessage(), newMessages.get(1));
        assertEquals(null, res3.getNextMessage().getNextMessage().getNextMessage());
    }

    @Test
    public void Test_adding_messages_works_at_middle(){
        List<Message> newMessages = diagram.addNewMessage(object2, actor3, res1);
        assertEquals(res1.getNextMessage(), newMessages.get(0));
        assertEquals(res1.getNextMessage().getNextMessage(), newMessages.get(1));
        assertEquals(res2, res1.getNextMessage().getNextMessage().getNextMessage());
    }
}
