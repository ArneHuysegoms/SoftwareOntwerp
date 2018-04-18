package repo.message;

import diagram.message.Message;
import diagram.party.Party;
import repo.label.LabelRepo;
import repo.party.PartyRepo;

import java.io.Serializable;
import java.util.List;

/**
 * abstract superclass detailing a repo containing the state of every message in the repo, including everything positional orientated
 */
public abstract class MessageRepo implements Serializable {

    public static final int HEIGHT = 16;

    /**
     * default constructor
     */
    public MessageRepo(){
    }

    /**
     * removes a message form the repo
     * @param message  the message to delete
     */
    public abstract void removeMessage(Message message);

    /**
     * resets the position of the messages if a change occurs
     * @param firstMessage the firstmessage of the diagram
     * @param partyRepo the repo containing all detail of the parties
     * @param labelRepo the repo containing all details of the labels
     */
    public abstract void resetMessagePositions(Message firstMessage, PartyRepo partyRepo, LabelRepo labelRepo);

    /**
     * resets the label of the messagse if a party is moved
     * @param labelRepo the repo containing all details of the labels
     * @param partyRepo the repo containing all detail of the parties
     * @param movedParty the party that was moved
     */
    public abstract void resetLabelPositionsForMovedParty(LabelRepo labelRepo, PartyRepo partyRepo, Party movedParty);

    /**
     * finds the message preceding the give location
     * @param yLocation the location we want the preceding message of
     * @param firstMessage the firstmessage of the diagram
     * @return the message that is directly preceding the given location
     */
    public abstract Message findPreviousMessage(int yLocation, Message firstMessage);

    /**
     * adds a message to the repo
     * @param messages the message to add
     * @param firstMessage the first message of the diagram
     * @param partyRepo the repo containing all detail of the parties
     * @param labelRepo the repo containing all details of the labels
     */
    public abstract void addMessages(List<Message> messages, Message firstMessage, PartyRepo partyRepo, LabelRepo labelRepo);
}