package view.message;

import diagram.message.Message;
import diagram.party.Party;
import view.label.LabelView;
import view.party.PartyView;

import java.io.Serializable;
import java.util.List;

/**
 * abstract superclass detailing a view containing the state of every message in the view, including everything positional orientated
 */
public abstract class MessageView implements Serializable {

    public static final int HEIGHT = 16;

    /**
     * default constructor
     */
    public MessageView(){
    }

    /**
     * removes a message form the view
     * @param message  the message to delete
     */
    public abstract void removeMessage(Message message);

    /**
     * resets the position of the messages if a change occurs
     * @param firstMessage the firstmessage of the diagram
     * @param partyView the view containing all detail of the parties
     * @param labelView the view containing all details of the labels
     */
    public abstract void resetMessagePositions(Message firstMessage, PartyView partyView, LabelView labelView);

    /**
     * resets the label of the messagse if a party is moved
     * @param labelView the view containing all details of the labels
     * @param partyView the view containing all detail of the parties
     * @param movedParty the party that was moved
     */
    public abstract void resetLabelPositionsForMovedParty(LabelView labelView, PartyView partyView, Party movedParty);

    /**
     * finds the message preceding the give location
     * @param yLocation the location we want the preceding message of
     * @param firstMessage the firstmessage of the diagram
     * @return the message that is directly preceding the given location
     */
    public abstract Message findPreviousMessage(int yLocation, Message firstMessage);

    /**
     * adds a message to the view
     * @param messages the message to add
     * @param firstMessage the first message of the diagram
     * @param partyView the view containing all detail of the parties
     * @param labelView the view containing all details of the labels
     */
    public abstract void addMessages(List<Message> messages, Message firstMessage, PartyView partyView, LabelView labelView);
}