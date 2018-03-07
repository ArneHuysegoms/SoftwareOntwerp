package figures.helperClasses;

import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Object;
import diagram.party.Party;
import figures.Drawer.Drawer;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class PartyPair extends Pair {
    private int arrowCount = 1;
    private List<Message> messages;

    public PartyPair(Party first, Party second, Message m) {
        super(first, second);
        messages = new ArrayList<Message>();
        messages.add(m);
    }

    public boolean equalPair(Party sender, Party receiver) {
        return sender == this.getA() && receiver == this.getB();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Party getSender() {
        return (Party) this.getA();
    }

    public Party getReceiver() {
        return (Party) this.getB();
    }

    public Point2D calculateStart(int spaceBetweenArrows) {
        return new Point2D.Double(getSender().getCoordinate().getX() + Object.WIDTH, getSender().getCoordinate().getY() + spaceBetweenArrows);
    }

    public Point2D calculateEnd(int spaceBetweenArrows) {
        return new Point2D.Double(getReceiver().getCoordinate().getX(), getReceiver().getCoordinate().getY() + spaceBetweenArrows);
    }

    public void draw(Graphics graphics, Drawer messageDrawer, Drawer labelDrawer) {
        int spread = Object.HEIGHT / messages.size();
        InvocationMessage message;
        Point2D start, end;

        for (int i = 0; i < messages.size(); i++) {
            message = (InvocationMessage) messages.get(i);
            start = calculateStart(i * spread);
            end = calculateEnd(i * spread);
            messageDrawer.draw(graphics, start, end, "");
            //TODO labels tekenen:
            String label = message.getMessageNumber() + " " + message.getLabel().getLabel();
            labelDrawer.draw(graphics, calculateLabelStartPosition(start, end), null, label);
        }
    }

    private Point2D calculateLabelStartPosition(Point2D start, Point2D end) {
        double x = Math.round((start.getX() + end.getX()) / 2);
        double y = Math.round((start.getY() + end.getY()) / 2);

        return new Point2D.Double(x, y);
    }
}
