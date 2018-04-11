package repo.message;

import diagram.message.Message;
import diagram.party.Party;
import exceptions.DomainException;
import repo.label.LabelRepo;
import repo.party.PartyRepo;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SequenceMessageRepo extends MessageRepo{

    public static final int HEIGHT = 16;

    private Map<Message, Integer> messageYLocationMap;

    public SequenceMessageRepo(){
        this(new HashMap<>());
    }

    public SequenceMessageRepo(HashMap<Message, Integer> messageYLocationMap){
        this.messageYLocationMap = messageYLocationMap;
    }

    private Map<Message, Integer> getMap(){
        return this.messageYLocationMap;
    }

    public Message getMessageAtPosition(int yLocation) throws DomainException {
        return this.getMap().entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(yLocation))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(DomainException::new);
    }

    public int getLocationOfMessage(Message message){
        return this.getMap().get(message);
    }

    public void addMessageWithLocation(Message message, int yLocation){
        this.getMap().put(message, yLocation);
    }

    @Override
    public void removeMessage(Message message){
        this.getMap().remove(message);
    }

    public void removeMessageByPosition(int yLocation) throws DomainException{
        Message l = this.getMessageAtPosition(yLocation);
        this.removeMessage(l);
    }

    public void updateMessageLocation(int yLocation, Message message){
        this.getMap().put(message, yLocation);
    }

    public Map<Message, Double> getDistancesFromPointForMessages(Point2D point){
        return this.getMap().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> getDistance(point, e.getKey())));
    }

    public Set<Message> getClickedMessages(Point2D location, PartyRepo repo){
        return this.getMap().entrySet()
                .stream()
                .filter(entry -> isClicked(location, entry.getKey(), repo))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @param message
     *        The message to check
     * @param partyRepo
     *        PartRepo containing the location of the receiver and sender of the message
     * @return
     *        True if the clicked coordinates are within the coordinates of the image of this message
     */
    public boolean isClicked(Point2D point2D, Message message, PartyRepo partyRepo) {
        Point2D senderLocation = partyRepo.getLocationOfParty(message.getSender());
        Point2D receiverLocation = partyRepo.getLocationOfParty(message.getReceiver());
        int messageLocation = this.getLocationOfMessage(message);

        double clickX = point2D.getX();
        double clickY = point2D.getY();
        double startX = senderLocation.getX();
        double startY = messageLocation - HEIGHT/2;
        double endX = receiverLocation.getX();
        double endY = messageLocation + HEIGHT/2;
        return (clickX >= startX && clickX <= endX) && (clickY >= startY && clickY <= endY);
    }


    /**
     * @param point2D
     *        The coordinates of the mouse where the user clicked
     * @return
     *       returns the distance between the coordinate of this message and the given point
     */
    public double getDistance(Point2D point2D, Message message) {
        return Math.abs(point2D.getY() - this.getLocationOfMessage(message));
    }

    /**
     * sets the yLocation of all messages in the tree to an appropriate number
     */
    @Override
    public void resetMessagePositions(Message firstMessage, PartyRepo partyRepo, LabelRepo labelRepo){
        Message message = firstMessage;
        int yLocation = 120;
        while(message != null){
            this.addMessageWithLocation(message, yLocation);
            yLocation += 35;
            Point2D labelCoordinate = new Point2D.Double(getNewLabelXPosition(message.getSender(), message.getReceiver(), partyRepo)
                    , this.getLocationOfMessage(message) - 15);
            labelRepo.addLabelWithLocation(message.getLabel(), labelCoordinate);
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

    @Override
    public void resetLabelPositionsForMovedParty(LabelRepo labelRepo, PartyRepo partyRepo, Party movedParty){
        this.getMap().keySet()
                .stream()
                .forEach(m -> updateLabelPosition(labelRepo, partyRepo, movedParty, m));
    }

    private void updateLabelPosition(LabelRepo labelRepo, PartyRepo partyRepo, Party movedParty, Message message){
        if(message.getReceiver().equals(movedParty) || message.getSender().equals(movedParty)){
            Point2D labelCoordinate = new Point2D.Double(getNewLabelXPosition(message.getSender(), message.getReceiver(), partyRepo)
                    , this.getLocationOfMessage(message) - 15);
            labelRepo.addLabelWithLocation(message.getLabel(), labelCoordinate);
        }
    }

    /**
     * Finds the message proceeding the message on the provided yLocation
     *
     * @param yLocation the ylocation of the next event to add
     *
     * @return the message that will preceed the one the given yLocation, null if none was found or didn't exist
     */
    public Message findPreviousMessage(int yLocation, Message firstMessage){
        Message message = firstMessage;
        if(message == null){
            return null;
        }
        if(this.getLocationOfMessage(message) > yLocation){
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
            if(this.getLocationOfMessage(next) > yLocation){
                return previous;
            }
            previous = next;
        }
        return null;
    }

    @Override
    public void addMessages(List<Message> messages, Message firstMessage, PartyRepo partyRepo, LabelRepo labelRepo) {
        for(Message m : messages){
            this.addMessageWithLocation(m, 999999);
        }
        this.resetMessagePositions(firstMessage, partyRepo, labelRepo);
    }
}
