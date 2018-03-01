package canvascomponents.diagram;

import canvascomponents.Clickable;

import java.awt.geom.Point2D;
import java.util.List;

public class Lifeline implements Clickable{

    private Point2D coordinate;
    private List<Message> messages;

    public Lifeline(List<Message> messages, Point2D coordinate) {
        this.setMessages(messages);
        this.setCoordinate(coordinate);
    }

    private void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }

    public List<Message> getMessages() {
        return messages;
    }

    private void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message){
        this.getMessages().add(message);
    }

    private void removeMessage(Message message){
        this.removeMessage(message);
    }

    @Override
    public boolean isClicked(Point2D point2D) {
        return false;
    }
}
