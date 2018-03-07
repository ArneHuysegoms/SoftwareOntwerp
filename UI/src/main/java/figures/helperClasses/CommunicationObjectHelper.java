package figures.helperClasses;

import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Party;
import figures.Drawer.Drawer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommunicationObjectHelper {
    private List<PartyPair> pairs;

    public CommunicationObjectHelper(Message m) {
        pairs = new ArrayList<PartyPair>();
        traverserMessages(m);
    }

    private void traverserMessages(Message message) {
        Party sender, receiver;
        PartyPair pair;

        if (message instanceof InvocationMessage) {
            pairs.add(new PartyPair(message.getSender(), message.getReceiver(), message));
            message = message.getNextMessage();
        }

        while (message != null) {
            if (message instanceof InvocationMessage) {

                sender = message.getSender();
                receiver = message.getReceiver();

                PartyPair temp;
                int size = pairs.size();
                for (int i = 0; i < size; i++) {
                    temp = pairs.get(i);
                    if (temp.equalPair(sender, receiver)) {
                        temp.addMessage(message);
                    } else {
                        pairs.add(new PartyPair(sender, receiver, message));
                    }
                }
            }
            message = message.getNextMessage();
        }
    }

    public void draw(Graphics graphics, Drawer messageDrawer, Drawer labelDrawer) {
        for (PartyPair pair : pairs) {
            pair.draw(graphics, messageDrawer, labelDrawer);
        }
    }

}
