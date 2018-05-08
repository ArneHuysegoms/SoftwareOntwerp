package repo.message;

import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Party;
import repo.label.LabelRepo;
import repo.party.PartyRepo;
import util.PartyPair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * a subclass of messagerepo, contains the state of the messages of the communication diagram
 */
public class CommunicationMessageRepo extends MessageRepo  implements Serializable {

    private List<PartyPair> pairs;

    /**
     * constructs a new empty communication message repo
     */
    public CommunicationMessageRepo(){
        this(new ArrayList<>());
    }

    /**
     * constructs a new communication message repo of which the state is equal to the state of the provided list
     * @param partyPairs the list containing the state we want to set
     */
    public CommunicationMessageRepo(List<PartyPair> partyPairs){
        this.setPartyPairs(partyPairs);
    }

    /**
     /**
     * sets the list which contains the state to the given state
     * @param partyPairs the list containing the state for the repo
     * @throws IllegalArgumentException if the given state is null
     */
    private void setPartyPairs(List<PartyPair> partyPairs) throws IllegalArgumentException{
        if(partyPairs == null){
            throw new IllegalArgumentException("Map may not be null");
        }
        this.pairs = partyPairs;
    }

    /**
     * @return the list containing the state of the repo
     */
    public List<PartyPair> getMap(){
        return this.pairs;
    }

    /**
     * removes a message form the repo
     * @param message  the message to delete
     */
    @Override
    public void removeMessage(Message message) {}

    /**
     * resets the position of the messages if a change occurs
     * @param firstMessage the firstmessage of the diagram
     * @param partyRepo the repo containing all detail of the parties
     * @param labelRepo the repo containing all details of the labels
     */
    @Override
    public void resetMessagePositions(Message firstMessage, PartyRepo partyRepo, LabelRepo labelRepo) {
        pairs = new ArrayList<>();
        traverserMessages(firstMessage);
        setLabelPositions(labelRepo, partyRepo);
    }

    /**
     * resets the label positions in case a party is moved
     *
     * @param labelRepo the repo containing all details of the labels
     * @param partyRepo the repo containing all detail of the parties
     * @param movedParty the party that was moved
     */
    @Override
    public void resetLabelPositionsForMovedParty(LabelRepo labelRepo, PartyRepo partyRepo, Party movedParty) {
        setLabelPositions(labelRepo, partyRepo);
    }

    /**
     * sets the positon of the label for the messages in this repo
     * @param labelRepo the repo containing all details of the labels
     * @param partyRepo the repo containing all detail of the parties
     */
    private void setLabelPositions(LabelRepo labelRepo, PartyRepo partyRepo){
        for(PartyPair p : pairs){
            p.updateLabelPosition(partyRepo, labelRepo);
        }
    }

    /**
     * finds the message preceding the give location
     * @param yLocation the location we want the preceding message of
     * @param firstMessage the firstmessage of the diagram
     * @return the message that is directly preceding the given location
     */
    @Override
    public Message findPreviousMessage(int yLocation, Message firstMessage) throws IllegalStateException{
        throw new IllegalStateException("This operation should never happen, you have the wrong kind of repo");
    }

    /**
     * adds messages to the repos and take cares of any other objects that need to be updated for the new messages
     *
     * @param messages the message to add
     * @param firstMessage the first message of the diagram
     * @param partyRepo the repo containing all detail of the parties
     * @param labelRepo the repo containing all details of the labels
     */
    @Override
    public void addMessages(List<Message> messages, Message firstMessage, PartyRepo partyRepo, LabelRepo labelRepo) {
        pairs = new ArrayList<>();
        traverserMessages(firstMessage);
        for(PartyPair p : pairs){
            p.updateLabelPosition(partyRepo, labelRepo);
        }
    }

    /**
     * finds a partypair by the receiver and sender of a message
     *
     * @param sender the sender
     * @param receiver the receiver
     * @return the partypair that corresponds to the given sender and receiver by equals
     */
    public PartyPair findPartyPairByParties(Party sender, Party receiver){
        for(PartyPair p : pairs){
            if(p.equalPair(sender, receiver)){
                return p;
            }
        }
        return null;
    }

    /**
     * a method that counts how many arrows are between two senders and saves this data in a PartyPair-object
     *
     * @param message the first message of the diagram
     */
    private void traverserMessages(Message message) {
        Party sender, receiver;
        boolean found = false;

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
                        found = true;
                    }
                }
                if (!found) {
                    pairs.add(new PartyPair(sender, receiver, message));
                }
                found = false;
            }
            message = message.getNextMessage();
        }
    }
}
