package window.elements;

import action.Action;
import action.EmptyAction;
import diagram.label.InvocationMessageLabel;
import diagram.message.ResultMessage;
import diagram.party.Party;
import exception.UIException;
import window.Clickable;
import window.Subwindow;
import window.diagram.DiagramSubwindow;
import window.elements.textbox.ArgumentTextBox;

import java.awt.geom.Point2D;

/**
 * Abstract superclass for dialogboxelements, they are clickable
 */
public abstract class DialogboxElement implements Clickable, ICommandable {

    private Point2D coordinate;
    private String description;
    private String contents;

    protected DialogboxElement() {

    }

    public void update(DiagramSubwindow subwindow, Party party){}
    public void update(DiagramSubwindow subwindow){}
    public void update(Party party){}
    public void update(ResultMessage rm){}
    public void update(InvocationMessageLabel iml){}
    public void update(DiagramSubwindow subwindow, InvocationMessageLabel label, ListBox lb, ArgumentTextBox atb) {}

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
        this.setContents("");
    }

    /**
     * @return the contents of the textbox
     */
    public String getContents() {
        return contents;
    }


    /**
     * sets the contents to the given contents
     *
     * @param contents the new contents
     */
    public void setContents(String contents) {
        this.contents = contents;
    }


    public void addCharToContents(char toAdd) {
        this.contents += toAdd;
    }

    /**
     * deletes the last char from the contents
     */
    public void deleteLastCharFromContents() {
        if (this.contents.length() > 1) {
            this.setContents(this.getContents().substring(0, this.getContents().length() - 1));
        } else {
            this.setContents("");
        }
    }

    public void addCharToDescription(char c){
        setDescription(description + c);
    }
    public void deleteCharFromDescription(){
        if(description.length() > 0){
            setDescription(description.substring(0,description.length()-1));
        }
    }

    public abstract String getStaticDescription();
    public abstract void setStaticDescription(String s);

    /**
     * returns if the textbox has valid contents
     *
     * @return ture if the contents are valid
     */
    public boolean hasValidContents() {
        return !this.getContents().isEmpty();
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
