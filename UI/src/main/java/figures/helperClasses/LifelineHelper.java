package figures.helperClasses;

import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Party;
import figures.Drawer.DiagramSpecificDrawers.SequenceInvokeMessageDrawer;
import figures.Drawer.DiagramSpecificDrawers.SequenseResponseMessageDrawer;
import figures.Drawer.Drawer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LifelineHelper {
    private Party party;
    private List<ActivationBar> bars;
    private Message initialMessage;

    public LifelineHelper(Message message) {
        bars = new ArrayList<ActivationBar>();
        setInitialMessage(message);

        calculateOuterBars(getInitialMessage());
    }

    private void calculateOuterBars(Message message) {
        int invokeCounter = 0, responseCounter = 0;
        Message sent = null;
        while (message != null) {
            if (message instanceof InvocationMessage) {
                if (sent == null) {
                    sent = message;
                }
                invokeCounter++;
            } else {
                responseCounter++;
            }

            if (responseCounter > 0 && invokeCounter == responseCounter) {
                bars.add(new ActivationBar(sent, message,false));
                sent = null;
            }

            message = message.getNextMessage();
        }
    }

    public int calculateLengthOfLine() {
        int counter = 0;
        Message tempMessage, response;
        for(ActivationBar a : bars){
            tempMessage = a.getSent();
            response = a.getResponse();

            while(tempMessage != response){
                counter++;
                tempMessage = tempMessage.getNextMessage();
            }
        }
        return counter;
    }

    public void draw(Graphics graphics, Drawer boxDrawer, Drawer invokeDrawer, Drawer responseDrawer) {
        for (ActivationBar a : bars) {
            a.draw(graphics,boxDrawer, invokeDrawer, responseDrawer);
        }
    }

    private void setInitialMessage(Message initialMessage) {
        this.initialMessage = initialMessage;
    }

    public Message getInitialMessage() {
        return initialMessage;
    }
}
