package util;

import diagram.message.Message;
import diagram.party.Party;
import repo.label.LabelRepo;
import repo.party.PartyRepo;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class PartyPair extends Pair {
    private List<Message> messages;

    /**
     * @param first  the sender of a message to the second party
     * @param second the receiver of a message from the first party
     * @param m      a message that has been sent from the first party to the second
     */
    public PartyPair(Party first, Party second, Message m) {
        super(first, second);
        messages = new ArrayList<>();
        messages.add(m);
    }

    /**
     * method used to check if two parties are the same as the sender and receiver stored in this pair (order matters)
     *
     * @param sender   Party that is a sender of a message to reveiver party
     * @param receiver party that is a receiver of a message from the sender party
     * @return true if the parties and order match
     */
    public boolean equalPair(Party sender, Party receiver) {
        return sender == this.getA() && receiver == this.getB();
    }

    /**
     * adds a message that has been sent from the first stored to the second stored party in this pair
     *
     * @param message the message sent from the first stored to the second stored party in this pair
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * returns the sender of the messages
     *
     * @return the sender of the messages
     */
    public Party getSender() {
        return (Party) this.getA();
    }

    /**
     * returns the receiver of the messages
     *
     * @return the receiver of the messages
     */
    public Party getReceiver() {
        return (Party) this.getB();
    }

    /**
     * calculates start point of an arrow, position depends on how many messages are sent from the first party to the second
     * @param spaceBetweenArrows
     * @param partyRepo PartyRepo of the diagram
     * @return
     *      start point of the arrow
     */
    public Point2D calculateStart(int spaceBetweenArrows, PartyRepo partyRepo) {
        return new Point2D.Double(partyRepo.getLocationOfParty(getSender()).getX() + PartyRepo.OBJECTWIDTH, partyRepo.getLocationOfParty(getSender()).getY() + spaceBetweenArrows);
    }

    /**
     * calculates end point of an arrow, position depends on how many messages are sent from the first party to the second
     * @param spaceBetweenArrows
     * @param partyRepo PartyRepo of the diagram
     * @return
     *      end point of the arrow
     */
    public Point2D calculateEnd(int spaceBetweenArrows, PartyRepo partyRepo) {
        return new Point2D.Double(partyRepo.getLocationOfParty(getReceiver()).getX(), partyRepo.getLocationOfParty(getReceiver()).getY() + spaceBetweenArrows);
    }

    /**
     * update the position of the labels for the messages of this partypair
     *
     * @param partyRepo partyRepo of the diagram
     * @param labelRepo labelRepo of the diagram
     */
    public void updateLabelPosition(PartyRepo partyRepo, LabelRepo labelRepo) {
        int spread = PartyRepo.OBJECTHEIGHT / messages.size();
        Message message;
        Point2D start, end;

        for (int i = 0; i < messages.size(); i++) {
            message = messages.get(i);
            start = calculateStart(i * spread, partyRepo);
            end = calculateEnd(i * spread,partyRepo);
            labelRepo.updateLabelPosition(calculateLabelStartPosition(start, end),message.getLabel());
        }
    }

    /**
     * methos that calculates the label's start position
     * @param start
     *      start possition of the message that the label belongs tho
     * @param end
     *      end possition of the message that the label belongs tho
     * @return
     *      the label's start position
     */
    private Point2D calculateLabelStartPosition(Point2D start, Point2D end) {
        double x = Math.round((start.getX() + end.getX()) / 2);
        double y = Math.round((start.getY() + end.getY()) / 2);

        return new Point2D.Double(x, y);
    }
}