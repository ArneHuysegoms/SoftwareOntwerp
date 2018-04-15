package repo.message;

import diagram.message.InvocationMessage;
import diagram.message.Message;
import diagram.party.Party;
import repo.label.LabelRepo;
import repo.party.PartyRepo;
import util.PartyPair;

import java.util.ArrayList;
import java.util.List;

public class CommunicationMessageRepo extends MessageRepo {

    private List<PartyPair> pairs;

    public CommunicationMessageRepo(){
        this(new ArrayList<>());
    }

    public CommunicationMessageRepo(List<PartyPair> partyPairs){
        this.setPartyPairs(partyPairs);
    }

    private void setPartyPairs(List<PartyPair> partyPairs){
        if(partyPairs == null){
            throw new IllegalArgumentException("Map may not be null");
        }
        this.pairs = partyPairs;
    }

    public List<PartyPair> getMap(){
        return this.pairs;
    }

    @Override
    public void removeMessage(Message message) {}

    @Override
    public void resetMessagePositions(Message firstMessage, PartyRepo partyRepo, LabelRepo labelRepo) {
        pairs = new ArrayList<>();
        traverserMessages(firstMessage);
        setLabelPositions(labelRepo, partyRepo);
    }

    @Override
    public void resetLabelPositionsForMovedParty(LabelRepo labelRepo, PartyRepo partyRepo, Party movedParty) {
        setLabelPositions(labelRepo, partyRepo);
    }

    private void setLabelPositions(LabelRepo labelRepo, PartyRepo partyRepo){
        for(PartyPair p : pairs){
            p.updateLabelPosition(partyRepo, labelRepo);
        }
    }

    @Override
    public Message findPreviousMessage(int yLocation, Message firstMessage) throws IllegalStateException{
        throw new IllegalStateException("This operation should never happen, you have the wrong kind of repo");
    }

    @Override
    public void addMessages(List<Message> messages, Message firstMessage, PartyRepo partyRepo, LabelRepo labelRepo) {
        pairs = new ArrayList<>();
        traverserMessages(firstMessage);
        for(PartyPair p : pairs){
            p.updateLabelPosition(partyRepo, labelRepo);
        }
    }

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
