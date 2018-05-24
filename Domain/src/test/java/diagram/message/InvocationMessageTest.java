package diagram.message;

import diagram.label.Label;
import diagram.label.MessageLabel;
import diagram.label.PartyLabel;
import diagram.party.Actor;
import diagram.party.Party;
import exceptions.DomainException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class InvocationMessageTest {

    private static final String VALIDMESSAGELABEL = "message";

    private static final String VALIDSENDERLABEL = "a:A";
    private static final String VALIDRECEIVERLABEL = "b:B";

    private static final String VALIDMESSAGENUMBER = "1.1";

    private Party sender;
    private Party receiver;

    private Label senderLabel;
    private Label receiverLabel;
    private Label messageLabel;

    @Before
    public void setUp(){
        try {
            senderLabel = new PartyLabel(VALIDSENDERLABEL);
            receiverLabel = new PartyLabel(VALIDRECEIVERLABEL);

            sender = new Actor(senderLabel);
            receiver = new Actor(receiverLabel);

            messageLabel = new MessageLabel(VALIDMESSAGELABEL);
        }
        catch (Exception e){
            fail();
        }
    }

    @Test
    public void Test_basic_constructor_works() throws DomainException {
        Message m = new InvocationMessage(null, messageLabel, receiver, sender);
        assertEquals(sender, m.getSender());
        assertEquals(receiver, m.getReceiver());
        assertEquals(messageLabel, m.getLabel());
        assertEquals(null, m.getNextMessage());
        assertEquals(m.toString(), ((InvocationMessage) m).getMessageNumber() + " " + messageLabel.getLabel());
    }

    @Test (expected = DomainException.class)
    public void Test_basic_constructor_with_null_sender_fails() throws DomainException{
        Message m = new InvocationMessage(null, messageLabel, receiver, null);
    }

    @Test (expected = DomainException.class)
    public void Test_basic_constructor_with_null_receiver_fails() throws DomainException{
        Message m = new InvocationMessage(null, messageLabel, null, sender);
    }

    @Test
    public void Test_advanced_constructor_works() throws DomainException {
        Message m = new InvocationMessage(null, messageLabel, receiver, sender, VALIDMESSAGENUMBER);
        assertEquals(VALIDMESSAGENUMBER, ((InvocationMessage) m).getMessageNumber());
    }

    @Test (expected = DomainException.class)
    public void Test_advanced_constructor_with_null_sender_fails() throws DomainException{
        Message m = new InvocationMessage(null, messageLabel, receiver, null, VALIDMESSAGENUMBER);
    }

    @Test (expected = DomainException.class)
    public void Test_advanced_constructor_with_null_receiver_fails() throws DomainException{
        Message m = new InvocationMessage(null, messageLabel, null, sender, VALIDMESSAGENUMBER);
    }
}
