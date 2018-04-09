package repo.message;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import diagram.message.Message;
import exceptions.DomainException;
import repo.party.PartyRepo;

public class MessageRepo {

    public static final int HEIGHT = 16;

    private Map<Message, Integer> messageYLocationMap;

    public MessageRepo(){
        this(new HashMap<>());
    }

    public MessageRepo(HashMap<Message, Integer> messageYLocationMap){
        this.messageYLocationMap = messageYLocationMap;
    }

    private Map<Message, Integer> getMap(){
        return this.messageYLocationMap;
    }

    public Message getMessageAtPosition(int yLocation) throws DomainException{
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
    public void resetMessagePositions(Message firstMessage){
        Message message = firstMessage;
        int yLocation = 120;
        while(message != null){
            message.setyLocation(yLocation);
            yLocation += 35;
            Point2D labelCoordinate = new Point2D.Double(getNewLabelXPosition(message.getSender(), message.getReceiver()), message.getyLocation() - 15);
            message.getLabel().setCoordinate(labelCoordinate);
            message = message.getNextMessage();
        }
    }
}