package repo.message;

import diagram.message.Message;
import diagram.party.Party;
import repo.label.LabelRepo;
import repo.party.PartyRepo;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * subclass of messagerepo, contains the state of sequencediagrams
 */
public class SequenceMessageRepo extends MessageRepo implements Serializable {

    public static final int HEIGHT = 16;

    private Map<Message, Integer> messageYLocationMap;

    /**
     * constructs a new empty sequencemessagerepo
     */
    public SequenceMessageRepo(){
        this(new HashMap<>());
    }

    /**
     * constructs a new sequencemessagerepo of which the location is equal to the state of the provided map
     * @param messageYLocationMap the map containing the state we want the new repo to have
     * @throws IllegalArgumentException if the provided map is null
     */
    public SequenceMessageRepo(Map<Message, Integer> messageYLocationMap) throws IllegalArgumentException{
        if(messageYLocationMap == null){
            throw new IllegalArgumentException("Map may not be null");
        }
        this.messageYLocationMap = messageYLocationMap;
    }

    /**
     * @return the map containing the state of this repo
     */
    public Map<Message, Integer> getMap(){
        return this.messageYLocationMap;
    }

    /**
     * gets the message at the specified location, null if no message exists at that location
     * @param yLocation the location we want the message off
     * @return he message at the specified location, null if no message exists at that location
     */
    public Message getMessageAtPosition(int yLocation){
        return this.getMap().entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(yLocation))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    /**
     * returns the location of the given message
     * @param message the message we want the location of
     * @return the location of the given message
     */
    public int getLocationOfMessage(Message message){
        return this.getMap().get(message);
    }

    /**
     * adds the given message with the given location to the repo
     * @param message the message to add
     * @param yLocation the location of the message
     */
    private void addMessageWithLocation(Message message, int yLocation){
        this.getMap().put(message, yLocation);
    }

    /**
     * removes the given message from the repo
     * @param message  the message to delete
     */
    @Override
    public void removeMessage(Message message){
        this.getMap().remove(message);
    }

    /**
     * removes the message at the given position from the repo, if such message exists
     * @param yLocation the location of the message to remove
     */
    public void removeMessageByPosition(int yLocation){
        Message l = this.getMessageAtPosition(yLocation);
        this.removeMessage(l);
    }

    /**
     * @return a set containing all messages in this repo
     */
    public Set<Message> getAllMessages(){
        return this.getMap().keySet();
    }

    /**
     * updates the location of the given message to the given location
     * @param yLocation the new location
     * @param message the message to change the position off
     */
    private void updateMessageLocation(int yLocation, Message message){
        this.getMap().put(message, yLocation);
    }

    /**
     * resets the position of the messages if a change occurs
     * @param firstMessage the firstmessage of the diagram
     * @param partyRepo the repo containing all detail of the parties
     * @param labelRepo the repo containing all details of the labels
     */
    @Override
    public void resetMessagePositions(Message firstMessage, PartyRepo partyRepo, LabelRepo labelRepo){
        Message message = firstMessage;
        int yLocation = 120;
        while(message != null){
            this.updateMessageLocation(yLocation,message);
            Point2D labelCoordinate = new Point2D.Double(getNewLabelXPosition(message.getSender(), message.getReceiver(), partyRepo)
                    , yLocation - 15);
            labelRepo.addLabelWithLocation(message.getLabel(), labelCoordinate);
            yLocation += 35;
            message = message.getNextMessage();
        }
    }

    /**
     * returns a x-position for a new label, based on the location of the sender and receiver
     *
     * @param p1 the first party
     * @param p2 the second party
     * @param partyRepo the repo containing the location of the messages
     *
     * @return a new Point2D containing the location for the new message
     */
    private Double getNewLabelXPosition(Party p1, Party p2, PartyRepo partyRepo){
        return (partyRepo.getLocationOfParty(p1).getX() + partyRepo.getLocationOfParty(p2).getX())/2;
    }

    /**
     * resets the label of the messagse if a party is moved
     * @param labelRepo the repo containing all details of the labels
     * @param partyRepo the repo containing all detail of the parties
     * @param movedParty the party that was moved
     */
    @Override
    public void resetLabelPositionsForMovedParty(LabelRepo labelRepo, PartyRepo partyRepo, Party movedParty){
        this.getMap().keySet()
                .stream()
                .forEach(m -> updateLabelPosition(labelRepo, partyRepo, movedParty, m));
    }

    /**
     * updates the label position of the given message in respect to the given party
     * @param labelRepo the repo containing all details of the labels
     * @param partyRepo the repo containing all detail of the parties
     * @param movedParty the party that was moved
     * @param message the message off which the label has to be changed
     */
    private void updateLabelPosition(LabelRepo labelRepo, PartyRepo partyRepo, Party movedParty, Message message){
        if(message.getReceiver().equals(movedParty) || message.getSender().equals(movedParty)){
            Point2D labelCoordinate = new Point2D.Double(getNewLabelXPosition(message.getSender(), message.getReceiver(), partyRepo)
                    , this.getLocationOfMessage(message) - 15);
            labelRepo.addLabelWithLocation(message.getLabel(), labelCoordinate);
        }
    }

    /**
     * finds the message preceding the give location
     * @param yLocation the location we want the preceding message of
     * @param firstMessage the firstmessage of the diagram
     * @return the message that is directly preceding the given location
     */
    public Message findPreviousMessage(int yLocation, Message firstMessage){
        Message message = firstMessage;
        if(message == null){
            return null;
        }
        if(this.getLocationOfMessage(firstMessage) >= yLocation){
            return null;
        }
        Message previous = message;
        Message next;
        boolean found = false;
        while(! found){
            next = previous.getNextMessage();
            if(next == null){
                return previous;
            }
            if(this.getLocationOfMessage(next) >= yLocation){
                return previous;
            }
            previous = next;
        }
        return null;
    }

    /**
     * adds a message to the repo
     * @param messages the message to add
     * @param firstMessage the first message of the diagram
     * @param partyRepo the repo containing all detail of the parties
     * @param labelRepo the repo containing all details of the labels
     */
    @Override
    public void addMessages(List<Message> messages, Message firstMessage, PartyRepo partyRepo, LabelRepo labelRepo) {
        for(Message m : messages){
            this.addMessageWithLocation(m, 999999);
        }
        this.resetMessagePositions(firstMessage, partyRepo, labelRepo);
    }
}
