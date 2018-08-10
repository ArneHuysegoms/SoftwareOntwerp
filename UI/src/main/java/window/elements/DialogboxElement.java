package window.elements;

import action.Action;
import action.EmptyAction;
import diagram.party.Party;
import exception.UIException;
import window.Clickable;
import window.Subwindow;
import window.diagram.DiagramSubwindow;

import java.awt.geom.Point2D;

/**
 * Abstract superclass for dialogboxelements, they are clickable
 */
public abstract class DialogboxElement implements Clickable, ICommandable {

    private Point2D coordinate;
    private String description;

    protected DialogboxElement() {

    }

    public void update(DiagramSubwindow subwindow, Party party){}
    public void update(DiagramSubwindow subwindow){}
    public void update(Party party){}

    /**
     * makes a new dialogboxelement with the given parameters
     *
     * @param coordinate  the new coordinate for this dialogbox
     * @param description the description for this dialogbox
     * @throws UIException if an illegal coordinate is given
     */
    public DialogboxElement(Point2D coordinate, String description) throws UIException {
        this.setCoordinate(coordinate);
        this.setDescription(description);
    }

    public void addCharToDescription(char c){
        setDescription(description + c);
    }
    public void deleteCharFromDescription(){
        if(description.length() > 0){
            setDescription(description.substring(0,description.length()-1));
        }
    }
    public boolean isValidDescription(){
        return !this.getDescription().isEmpty();
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description to the given description
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the coordinate of this dialogboxelement
     */
    public Point2D getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate the new coordinate for this dialogboxelement
     * @throws UIException if the given coordinate is null
     */
    public void setCoordinate(Point2D coordinate) throws UIException {
        if (coordinate == null) {
            throw new UIException("Coordinate may not be null for dialogboxElements");
        }
        this.coordinate = coordinate;
    }

    /**
     * @param coordinate the location of the click
     * @return true if this element is clicked on the given location
     */
    @Override
    public abstract boolean isClicked(Point2D coordinate);

    public abstract DialogboxElement clone();

    @Override
    public Action performAction(){
        return new EmptyAction();
    }
}
