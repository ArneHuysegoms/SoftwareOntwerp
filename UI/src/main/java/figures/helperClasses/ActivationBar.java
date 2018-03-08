package figures.helperClasses;

import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Party;
import figures.Drawer.DiagramSpecificDrawers.SequenceInvokeMessageDrawer;
import figures.Drawer.DiagramSpecificDrawers.SequenseResponseMessageDrawer;
import figures.Drawer.Drawer;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ActivationBar {
    //TODO refactor width verplaatsen???? of geen helper class maar complexe Figure van maken????
    private int barWidth = 20;
    private boolean hasParentBar = true;
    private List<ActivationBar> bars;
    private Message sent, response;

    public ActivationBar(Message sent, Message received) {
        bars = new ArrayList<ActivationBar>();
        setSent(sent);
        setReceived(received);

        calculateBars(getSent().getNextMessage());
    }

    public ActivationBar(Message sent, Message received, boolean hasParentBar) {
        this(sent, received);
        setParent(hasParentBar);
    }

    /**
     * Calculates next layer of activation bars within this activation bar
     */
    private void calculateBars(Message nextMessage) {
        if (!nextMessage.equals(response)) {
            int invokeCounter = 0, responseCounter = 0;
            Message sent = null;

            while (nextMessage != response) {
                if (nextMessage instanceof InvocationMessage) {
                    if (sent == null) {
                        sent = nextMessage;
                    }
                    invokeCounter++;
                } else {
                    responseCounter++;
                }

                if (responseCounter > 0 && invokeCounter == responseCounter) {
                    bars.add(new ActivationBar(sent, nextMessage));
                    sent = null;
                }

                nextMessage = nextMessage.getNextMessage();
            }
        }
    }

    private void setSent(Message sent) {
        this.sent = sent;
    }

    private void setReceived(Message received) {
        this.response = received;
    }

    private Party getSender() {
        return sent.getSender();
    }

    private Party getReceiver() {
        return sent.getReceiver();
    }

    /**
     * Used to calcuate how far this activation bar is from the first activation bar on the life line
     *
     * @return factor to calculate point2d start-position for this activation bar
     */
    private int calculateDepthWithinSender() {

        return 0;
    }

    /**
     * Calculates heigth of a singe activation bar from the list bar.
     *
     * @return
     */
    private int calculateHeightOfSingleBar() {
        //TODO calculate number of messages between sent and response, including these two
        return 0;
    }

    public void draw(Graphics graphics, Drawer boxDrawer, Drawer invokeDrawer, Drawer responseDrawer) {

        boxDrawer.draw(graphics, calculateOwnBarStart(), calculateOwnBarEnd(), "");
        boxDrawer.draw(graphics, calculateBrotherBarStart(), calculateBrotherBarEnd(), null);

        invokeDrawer.draw(graphics, new Point2D.Double(calculateOwnBarStartX() + barWidth, calculateBarStartY()), new Point2D.Double(calculateBrotherBarStartX(), calculateBarStartY()), null);
        responseDrawer.draw(graphics, new Point2D.Double(calculateBrotherBarStartX(), calculateBarEndY()), new Point2D.Double(calculateOwnBarStartX() + barWidth, calculateBarEndY()), null);

        for (ActivationBar a : bars) {
            a.draw(graphics, boxDrawer, invokeDrawer, responseDrawer);
        }
    }

    private void setParent(boolean hasParentBar) {
        this.hasParentBar = hasParentBar;
    }

    private Boolean hasParent() {
        return hasParentBar;
    }

    public Message getSent() {
        return sent;
    }

    public Message getResponse() {
        return response;
    }

    private double calculateOwnBarStartX() {
        if (hasParent()) {
            return getSent().getSender().getXLocationOfLifeline();
        } else {
            return getSent().getSender().getXLocationOfLifeline() - (barWidth / 2);
        }
    }

    private double calculateBarStartY() {
        return getSent().getyLocation();
    }

    private double calculateBarEndY() {
        return getResponse().getyLocation();
    }

    private double calculateOwnBarEndX() {
        if (hasParent()) {
            return getResponse().getReceiver().getXLocationOfLifeline() + barWidth;
        } else {
            return getSent().getSender().getXLocationOfLifeline() + (barWidth / 2);
        }
    }

    private double calculateBrotherBarEndX() {
        return getResponse().getSender().getXLocationOfLifeline() + (barWidth / 2);
    }

    private double calculateBrotherBarStartX() {
        return getSent().getReceiver().getXLocationOfLifeline() - (barWidth / 2);
    }

    private Point2D calculateOwnBarStart() {
        return new Point2D.Double(calculateOwnBarStartX(), calculateBarStartY());
    }

    private Point2D calculateOwnBarEnd() {
        return new Point2D.Double(calculateOwnBarEndX(), calculateBarEndY());
    }

    private Point2D calculateBrotherBarStart() {
        return new Point2D.Double(calculateBrotherBarStartX(), calculateBarStartY());
    }

    private Point2D calculateBrotherBarEnd() {
        return new Point2D.Double(calculateBrotherBarEndX(), calculateBarEndY());
    }
}
