package figures.helperClasses;

import canvascomponents.diagram.Message;
import canvascomponents.diagram.Party;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

public class ActivationBar {
    private List<ActivationBar> bars;
    private Message sent, response;
    Point2D start, end;

    public ActivationBar(Message sent, Message received) {
        setSent(sent);
        setReceived(received);
        calculateBars();
        calculateNextBars();
    }

    /**
     * Calculates next layer of activation bars within this activation bar
     */
    private void calculateBars() {

    }

    void calculateNextBars() {
        for (ActivationBar a : bars) {
            a.calculateNextBars();
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

    public void draw(Graphics graphics) {
        for (ActivationBar a : bars) {

        }
    }
}
