package window.elements;

import exception.UIException;
import window.Clickable;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class for a listbox with arguments
 */
public class ListBox extends DialogboxElement implements Clickable {

    public static final int WIDTH = 120;
    public static final int HEIGHT = 75;
    public static final int ARGUMENT_HEIGHT = 14;

    private List<String> arguments;

    private int selectedIndex;

    /**
     * makes a new listbox with the given parameters
     *
     * @param coordinate  the coordinate for the listbox
     * @param description the description for the listbox
     * @throws UIException if a parameter is invalid
     */
    public ListBox(Point2D coordinate, String description) throws UIException {
        super(coordinate, description);
        this.setArguments(new ArrayList<>());
        this.setSelectedIndex(-1);
    }

    /**
     * @return the index of the selected element of this lisbox
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * sets the index of the selected element
     *
     * @param selectedIndex the index to set the index too, can't be higher than the argument list size
     */
    public void setSelectedIndex(int selectedIndex) {
        if (selectedIndex > arguments.size() - 1) {
            this.selectedIndex = arguments.size() - 1;
        } else {
            this.selectedIndex = selectedIndex;
        }
    }

    /**
     * @param newArguments set the argument list to the given arguments
     */
    public void setArguments(List<String> newArguments) {
        this.arguments = newArguments;
    }

    /**
     * @param location the location of the click
     * @return true if this lisbox is clicked
     */
    @Override
    public boolean isClicked(Point2D location) {
        double startX = getCoordinate().getX();
        double endX = getCoordinate().getX() + WIDTH;
        double startY = getCoordinate().getY();
        double endY = getCoordinate().getY() + HEIGHT;
        return (startX <= location.getX() && endX >= location.getX()) && (startY <= location.getY() && endY >= location.getY());
    }

    /**
     * returns a relative point based on the given location and the location of the window.diagram
     *
     * @param location the location that needs to be translated
     * @return a relative point to this window.diagram based on the given location
     */
    public Point2D getRelativePoint(Point2D location) {
        return new Point2D.Double(location.getX() - this.getCoordinate().getX(), location.getY() - this.getCoordinate().getY());
    }

    /**
     * select an argument based on the click
     *
     * @param location the location of the click
     */
    public void selectArgument(Point2D location) {
        Point2D relative = getRelativePoint(location);
        selectedIndex = (int) relative.getY() / 14;
        if (selectedIndex > arguments.size() - 1) {
            selectedIndex = arguments.size() - 1;
        }
    }

    /**
     * adds an argument to the list of arguments
     *
     * @param argument the argument to add
     */
    public void addArgument(String argument) {
        arguments.add(argument);
    }

    /**
     * removes the argument at the selected index
     */
    public void removeArgument() {
        arguments.remove(selectedIndex);
    }

    /**
     * swaps the selected argument with the argument above it
     */
    public void moveUp() {
        if (selectedIndex != 0) {
            Collections.swap(arguments, selectedIndex, selectedIndex - 1);
            selectedIndex--;
        }
    }

    /**
     * swaps the selected argument with the argument below it
     */
    public void moveDown() {
        if (selectedIndex != arguments.size() - 1) {
            Collections.swap(arguments, selectedIndex, selectedIndex + 1);
            selectedIndex++;
        }
    }

    /**
     * @return true if an argument is selected
     */
    public boolean hasSelectedArgument() {
        return selectedIndex > -1;
    }

    /**
     * @return get the arguments of this listbox
     */
    public List<String> getArguments() {
        return arguments;
    }
}
