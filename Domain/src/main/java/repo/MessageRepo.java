package repo;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import diagram.message.Message;
import exceptions.DomainException;

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

    public Message getMessageAtPosition(Integer yLocation) throws DomainException{
        return this.getMap().entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(yLocation))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(DomainException::new);
    }

    public Integer getLocationOfMessage(Message message){
        return this.getMap().get(message);
    }

    public void addMessageWithLocation(Message message, Integer yLocation){
        this.getMap().put(message, yLocation);
    }

    public void removeMessage(Message message){
        this.getMap().remove(message);
    }

    public void removeMessageByPosition(Integer yLocation) throws DomainException{
        Message l = this.getMessageAtPosition(yLocation);
        this.removeMessage(l);
    }


    public Set<Message> getClickedMessagess(Point2D location, PartyRepo repo){
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
}